package com.kuyu.mapper.pcms;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.kuyu.model.pcms.ReceiptModel;
import com.kuyu.vo.query.ReceiptQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by pc on 2018/11/21
 */
public interface ReceiptMapper extends BaseMapper<ReceiptModel> {
    /**分页查询*/
    List<ReceiptModel> findReceiptList(@Param("params") ReceiptQuery query, Page<ReceiptModel> page);

}
