package com.kuyu.vo.query;

import com.kuyu.common.BasePageQuery;
import io.swagger.annotations.ApiModel;

/**
 * Created by pc on 2018/11/21
 */
@ApiModel("接单列表vo")
public class ReceiptQuery extends BasePageQuery {

    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
