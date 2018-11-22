package com.kuyu.service;

import com.baomidou.mybatisplus.service.IService;
import com.kuyu.model.pcms.ReceiptModel;
import com.kuyu.vo.ResultVo;
import com.kuyu.vo.query.ReceiptQuery;

/**
 * Created by pc on 2018/11/21
 */
public interface ReceiptService extends IService<ReceiptModel> {

    /**分页查询*/
    ResultVo findReceiptList(ReceiptQuery query)throws Exception;
}
