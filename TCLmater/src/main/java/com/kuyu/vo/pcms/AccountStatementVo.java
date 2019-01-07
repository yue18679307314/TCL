package com.kuyu.vo.pcms;

import com.kuyu.model.pcms.PcmsIinitializationModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by pc on 2019/1/3
 */
public class AccountStatementVo {

    private Integer id;

    @ApiModelProperty("往来数据记录")
    private List<CurrentDetailModelVo> list;
    @ApiModelProperty("统计记录")
    private PcmsIinitializationModel pcmsIinitializationModel;
    @ApiModelProperty("对账编号")
    private String reconciliation_id;
    @ApiModelProperty("对账时间")
    private String accountStatementDate;
    @ApiModelProperty("供应商公司名称")
    private String vendor_name;
    @ApiModelProperty("是否有差异")
    private int type;
    @ApiModelProperty("钱")
    private String amount;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("经办人")
    private String person_name;
    @ApiModelProperty("联系电话")
    private String vendor_mobile;
    @ApiModelProperty("日期")
    private String vendor_time;
    @ApiModelProperty("经办人")
    private String company_name;
    @ApiModelProperty("联系电话")
    private String company_mobile;
    @ApiModelProperty("日期")
    private String company_date;

    private Integer statisticsId;

    @ApiModelProperty("期初余额")
    private String Initial_balance;
    @ApiModelProperty("状态")
    private Integer state;
    @ApiModelProperty("入账法人名称")
    private String incorporated_person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CurrentDetailModelVo> getList() {
        return list;
    }

    public void setList(List<CurrentDetailModelVo> list) {
        this.list = list;
    }

    public PcmsIinitializationModel getPcmsIinitializationModel() {
        return pcmsIinitializationModel;
    }

    public void setPcmsIinitializationModel(PcmsIinitializationModel pcmsIinitializationModel) {
        this.pcmsIinitializationModel = pcmsIinitializationModel;
    }

    public String getReconciliation_id() {
        return reconciliation_id;
    }

    public void setReconciliation_id(String reconciliation_id) {
        this.reconciliation_id = reconciliation_id;
    }

    public String getAccountStatementDate() {
        return accountStatementDate;
    }

    public void setAccountStatementDate(String accountStatementDate) {
        this.accountStatementDate = accountStatementDate;
    }

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getVendor_mobile() {
        return vendor_mobile;
    }

    public void setVendor_mobile(String vendor_mobile) {
        this.vendor_mobile = vendor_mobile;
    }

    public String getVendor_time() {
        return vendor_time;
    }

    public void setVendor_time(String vendor_time) {
        this.vendor_time = vendor_time;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_mobile() {
        return company_mobile;
    }

    public void setCompany_mobile(String company_mobile) {
        this.company_mobile = company_mobile;
    }

    public String getCompany_date() {
        return company_date;
    }

    public void setCompany_date(String company_date) {
        this.company_date = company_date;
    }

    public Integer getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(Integer statisticsId) {
        this.statisticsId = statisticsId;
    }

    public String getInitial_balance() {
        return Initial_balance;
    }

    public void setInitial_balance(String initial_balance) {
        Initial_balance = initial_balance;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getIncorporated_person() {
        return incorporated_person;
    }

    public void setIncorporated_person(String incorporated_person) {
        this.incorporated_person = incorporated_person;
    }
}
