package com.kuyu.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.PcmsItemLogMapper;
import com.kuyu.mapper.pcms.PcmsItemMapper;
import com.kuyu.mapper.pcms.PcmsUserItemMapper;
import com.kuyu.model.pcms.PcmsItem;
import com.kuyu.model.pcms.PcmsItemLog;
import com.kuyu.model.pcms.PcmsUserItemModel;
import com.kuyu.model.pcms.PcmsUserModel;
import com.kuyu.service.PcmsUserItemService;
import com.kuyu.service.PcmsUserService;
import com.kuyu.util.DateUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import javax.annotation.Resource;

/**
 * Created by pc on 2018/11/22
 */
@Service
@Transactional
public class PcmsUserItemServiceImpl extends ServiceImpl<PcmsUserItemMapper, PcmsUserItemModel>
        implements PcmsUserItemService {

    @Resource
    private PcmsItemMapper pcmsItemMapper;

    @Autowired
    private PcmsUserService pcmsUserService;
    
    @Autowired
    private PcmsItemLogMapper pcmsItemLogMapper;

    @Override
    public ResultVo doReceipt(PcmsUserItemModel pcmsUserItemModel) throws Exception {
       
    	Date createTime=new Date();
    	
    	if(StringUtil.isEmpty(pcmsUserItemModel.getOpenid())){
            throw new ParamException("openid为空");
        }
        PcmsItem pcmsItem = pcmsItemMapper.selectByPrimaryKey(pcmsUserItemModel.getItid());
        if(pcmsItem == null){
            throw new ParamException(pcmsUserItemModel.getItid()+"单号不存在");
        }
        PcmsUserModel pcmsUserModel = new PcmsUserModel();
        pcmsUserModel.setOpenid(pcmsUserItemModel.getOpenid());
        PcmsUserModel pcmsUserModel1 = pcmsUserService.selectPcmsUserModel(pcmsUserModel);
        if(pcmsUserModel1 == null){
            throw new ParamException(pcmsUserItemModel.getOpenid()+"用户不存在");
        }
        pcmsUserItemModel.setCreate_time(DateUtils.getLongDateStr());
        pcmsUserItemModel.setOpenid(pcmsUserItemModel.getOpenid());
        baseMapper.insert(pcmsUserItemModel);
        pcmsItem.setStatus(1);
        pcmsItem.setUpdateTime(createTime);
        pcmsItemMapper.updateByPrimaryKey(pcmsItem);
        
	    //增加立项单日志
	    PcmsItemLog log=new PcmsItemLog();
	    log.setItid(pcmsUserItemModel.getItid());
	    log.setStatus(1);
	    log.setNote("已接单，物料制作中");
	    log.setCreateTime(createTime);
	    pcmsItemLogMapper.insertSelective(log);
	    
	    
        return ResultVo.get(ResultVo.SUCCESS);
    }
}
