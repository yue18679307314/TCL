package com.kuyu.controller.pcms;

import com.kuyu.annotation.AOP_Controller_LOG;
import com.kuyu.model.pcms.PcmsUserItemModel;
import com.kuyu.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by pc on 2018/11/27
 */
@AOP_Controller_LOG
@RequestMapping("/market")
public class PcmsMarketController {


    /**
     * 详情
     * @param itid
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "详情",response = PcmsUserItemModel.class)
    @GetMapping("/getItemDetail")
    public ResultVo getItemDetail(@RequestParam(value = "itid") Integer itid) throws Exception{
        return null;
    }
}
