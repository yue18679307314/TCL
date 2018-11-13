package com.kuyu.model;



import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 *
 * @Author jt_L
 * @Date 2017-10-19
 * @Description 实体类:活动实际上报数据表
 */
@ApiModel("活动实际上报数据表")
@TableName("tpm_actual_activity_data")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmActualActivityDataModel extends Model<TpmActualActivityDataModel> {

    /**
     * 活动uuid
     */
	@TableId("activity_uuid")
	@ApiModelProperty(value = "活动uuid(主键)",position = -49)
	private String activityUuid;
    /**
     * 实际销售量
     */
	@TableField("actual_sales_no")
	@ApiModelProperty(value = "实际销售量",position = -48)
	private Integer actualSalesNo;
    /**
     * 实际销售额
     */
	@TableField("actual_sales_money")
	@ApiModelProperty(value = "实际销售额",position = -47)
	private Double actualSalesMoney;
	/**
	 * 实际花费
	 */
	//@TableField("actual_cost")
//	@ApiModelProperty(value = "实际花费",position = -47)
//	private Double actualCost;
    /**
     * 实际费用率
     */
	//@TableField("actual_cost_rate")
//	@ApiModelProperty(value = "实际费用率",position = -46)
//	private Double actualCostRate;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@JsonIgnore
	@ApiModelProperty(value = "创建时间",position = -45)
	private String createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	@JsonIgnore
	@ApiModelProperty(value = "更新时间",position = -44)
	private String updateTime;
    /**
     * 最后修改用户
     */
	@TableField("lastupdate_user")
	@JsonIgnore
	@ApiModelProperty(value = "最后修改用户",position = -43)
	private String lastupdateUser;


	public String getActivityUuid() {
		return activityUuid;
	}

	public void setActivityUuid(String activityUuid) {
		this.activityUuid = activityUuid;
	}

	public Integer getActualSalesNo() {
		return actualSalesNo;
	}

	public void setActualSalesNo(Integer actualSalesNo) {
		this.actualSalesNo = actualSalesNo;
	}

	public Double getActualSalesMoney() {
		return actualSalesMoney;
	}

	public void setActualSalesMoney(Double actualSalesMoney) {
		this.actualSalesMoney = actualSalesMoney;
	}

	/*public Double getActualCostRate() {
		return actualCostRate;
	}

	public void setActualCostRate(Double actualCostRate) {
		this.actualCostRate = actualCostRate;
	}
*/
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getLastupdateUser() {
		return lastupdateUser;
	}

	public void setLastupdateUser(String lastupdateUser) {
		this.lastupdateUser = lastupdateUser;
	}

	/*public Double getActualCost(){
		return actualCost;
	}

	public void setActualCost(Double actualCost){
		this.actualCost = actualCost;
	}*/

	@Override
	protected Serializable pkVal() {
		return this.activityUuid;
	}

}
