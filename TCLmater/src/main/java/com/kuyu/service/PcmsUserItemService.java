package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.LoginUserInfo;
import com.kuyu.model.pcms.PcmsUserItemModel;
import com.kuyu.vo.ResultVo;

/**
 * Created by pc on 2018/11/22
 */
public interface PcmsUserItemService extends IService<PcmsUserItemModel> {


    ResultVo doReceipt(PcmsUserItemModel pcmsUserItemModel)throws Exception;

}
