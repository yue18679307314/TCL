package com.kuyu.model.pcms;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * Created by pc on 2018/12/26
 */
@ApiModel("PcmsReconciliationDetailModel(对账中间表模型)")
@TableName("pcms_reconciliation_detail")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PcmsReconciliationDetailModel extends Model<PcmsReconciliationDetailModel> {


    /**id*/
    @TableId(type= IdType.AUTO)
    private Integer id;

    private Integer pmid;

    private Integer pcms_reconciliation_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPmid() {
        return pmid;
    }

    public void setPmid(Integer pmid) {
        this.pmid = pmid;
    }

    public Integer getPcms_reconciliation_id() {
        return pcms_reconciliation_id;
    }

    public void setPcms_reconciliation_id(Integer pcms_reconciliation_id) {
        this.pcms_reconciliation_id = pcms_reconciliation_id;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }
}
