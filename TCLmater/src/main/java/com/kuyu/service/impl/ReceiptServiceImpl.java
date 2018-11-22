package com.kuyu.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.kuyu.exception.ParamException;
import com.kuyu.mapper.pcms.ReceiptMapper;
import com.kuyu.model.pcms.PcmsUserModel;
import com.kuyu.model.pcms.ReceiptModel;
import com.kuyu.service.PcmsUserService;
import com.kuyu.service.ReceiptService;
import com.kuyu.util.CheckParamUtils;
import com.kuyu.util.StringUtil;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.ReceiptQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pc on 2018/11/21
 */
@Service
@Transactional
public class ReceiptServiceImpl extends ServiceImpl<ReceiptMapper, ReceiptModel>
        implements ReceiptService {
    @Autowired
    private PcmsUserService pcmsUserService;

    @Override
    public ResultVo findReceiptList(ReceiptQuery query) throws Exception {
        if(StringUtil.isEmpty(query.getOpenid())){
            throw new ParamException("openid为空");
        }
        PcmsUserModel pcmsUserModel = new PcmsUserModel();
        pcmsUserModel.setOpenid(query.getOpenid());
        PcmsUserModel pcmsUserModel1 = pcmsUserService.selectPcmsUserModel(pcmsUserModel);
        if(null == pcmsUserModel1){
            throw new ParamException(ResultVo.getData("403","未绑定"));
        }
        query = (ReceiptQuery) CheckParamUtils.trimWithObjectField(query);
        Page<ReceiptModel> page = new Page<>(query.getCurrent(),query.getSize());
        List<ReceiptModel> receiptList = baseMapper.findReceiptList(query,page);
        page.setRecords(receiptList);
        return ResultVo.getDataWithSuccess(page);
    }
}
