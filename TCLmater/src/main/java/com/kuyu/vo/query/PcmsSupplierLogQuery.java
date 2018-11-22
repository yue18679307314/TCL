package com.kuyu.vo.query;

import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by pc on 2018/11/21
 */
@ApiModel("供应商日志查询vo")
public class PcmsSupplierLogQuery extends BasePageQuery {
    /**供应商编码*/
    @ApiModelProperty("供应商编码")
    private String vendor_id;

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }
}
