package com.kuyu.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @Author tang_zhen
 * @Date 2017/9/25
 * @Description
 */
@ApiModel("用户基本资料Model")
//@JsonIgnoreProperties(value = {"password"})
//@JsonInclude(JsonInclude.Include.NON_EMPTY)
@TableName("tpm_user_base_info")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TpmUserBaseInfoModel extends Model<TpmUserBaseInfoModel>{
    @TableId
    @ApiModelProperty("主键")
    private String openid;
    /**
     * 手机号
     * */
    @ApiModelProperty("手机号")
    private String mobile;
    /**
     * 姓名
     * */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 性别
     * */
    @ApiModelProperty("性别")
    private String sex;
    /**
     * 微信性别
     * */
    @TableField(exist = false)
    @ApiModelProperty("微信性别")
    private String wxSex;
    /**
     *身份证
     * */
    @ApiModelProperty("身份证号")
    private String id_card;
    /**
     *所在省
     * */
    @ApiModelProperty("所在省")
    private String province;
    /**
     *所在市
     * */
    @ApiModelProperty("所在市")
    private String city;
    /**
     *所在区县
     * */
    @ApiModelProperty("所在区县")
    private String district;
    /**
     *详细地址
     * */
    @ApiModelProperty("详细地址")
    private String address;
    /**
     *身份证正面
     * */
    @ApiModelProperty("身份证正面url")
    private String idimgurl_front;
    /**
     *身份证背面
     * */
    @ApiModelProperty("身份证背面url")
    private String idimgurl_back;
    /**
     *状态码（0关闭，1正常）
     * */
    @ApiModelProperty("状态码（0关闭，1正常）")
    private String status;
    /**
     *创建时间
     * */
    @ApiModelProperty("创建时间")
    private String create_time;
    /**
     *修改时间
     * */
    @ApiModelProperty("修改时间")
    private String modify_time;
    /**
     *头像
     * */
    @TableField(exist = false)
    @ApiModelProperty("头像")
    private String headImage;
    /**
     *员工账号
     * */
    @TableField(exist = false)
    @ApiModelProperty("员工账号")
    private String ITcode;
    /**
     *临促人员编号
     * */
    @TableField(exist = false)
    @ApiModelProperty("临促人员编号")
    private String number;
    /**
     *微信昵称
     * */
    @TableField(exist = false)
    @ApiModelProperty("微信昵称")
    private String wxName;
    /**
     *修改人
     * */
    @ApiModelProperty("修改人")
    private String modify_user;
    /**
     *户名
     * */
    @ApiModelProperty("户名")
    private String account_name;
    /**
     *开户省
     * */
    @ApiModelProperty("开户省")
    private String open_province;
    /**
     *开户市
     * */
    @ApiModelProperty("开户市")
    private String open_city;
    /**
     *开户银行名
     * */
    @ApiModelProperty("开户银行名")
    private String account_bank_name;
    /**
     *银行账号
     * */
    @ApiModelProperty("银行账号")
    private String account_value;
    /**
     *开户分行网点
     * */
    @ApiModelProperty("开户分行网点")
    private String open_branch;
    /**
     *银行卡账号核实，财务共享回调，通过初步审核后设为1,未通过设为0,首次填写银行卡或者更新银行卡还未审核设为2（审核中）
     * */
    @ApiModelProperty("银行卡是否有效:0无效，1有效")
    @TableField("bank_account_verify")
    private String bank_account_verify;
    /**
     *是否绑定员工信息 0未绑定，1已绑定
     * */
    @ApiModelProperty("是否绑定员工信息 0未绑定，1已绑定")
    @TableField("binding_verify")
    private String binding_verify;
    /**
     *财务共享流水单号
     * */
    @ApiModelProperty("财务共享流水单号")
    @TableField("req_fssc_bill")
    private String req_fssc_bill;

    /**
     *分页当前页
     * */
    @ApiModelProperty("分页当前页")
    @TableField(exist = false)
    private  Integer current;
    /**
     *分页页大小
     * */
    @ApiModelProperty("分页页大小")
    @TableField(exist = false)
    private  Integer size;
    /**
     *登录人员类型
     * */
    @ApiModelProperty("登录人员类型,仅用于数据库")
    @TableField(exist = false)
    private  Integer personType;
    /**
     *登录人员员工号
     * */
    @ApiModelProperty("登录人员员工号,仅用于数据库")
    @TableField(exist = false)
    private  String personCode;

    @TableField(exist = false)
    @ApiModelProperty("立项单号")
	private String requestId;
	
    @TableField(exist = false)
	@ApiModelProperty("活动名称")
	private String activityName;
	
    @TableField(exist = false)
	@ApiModelProperty("时间")
	private String activityTime;
	
    @TableField(exist = false)
	@ApiModelProperty("活动负责人")
	private String managerName;
    
    public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getWxSex() {
        return wxSex;
    }

    public void setWxSex(String wxSex) {
        this.wxSex = wxSex;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public Integer getCurrent() {

        return current;
    }

    public void setCurrent(Integer current) {
        if (current < 1) {
            current = 1;
        }
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        if (size < 10) {
            size = 10;
        }
        this.size = size;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getReq_fssc_bill() {
        return req_fssc_bill;
    }

    public void setReq_fssc_bill(String req_fssc_bill) {
        this.req_fssc_bill = req_fssc_bill;
    }

    public String getBank_account_verify() {
        return bank_account_verify;
    }

    public void setBank_account_verify(String bank_account_verify) {
        this.bank_account_verify = bank_account_verify;
    }

    public String getBinding_verify() {
        return binding_verify;
    }

    public void setBinding_verify(String binding_verify) {
        this.binding_verify = binding_verify;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getIdimgurl_front() {
        return idimgurl_front;
    }

    public void setIdimgurl_front(String idimgurl_front) {
        this.idimgurl_front = idimgurl_front;
    }

    public String getIdimgurl_back() {
        return idimgurl_back;
    }

    public void setIdimgurl_back(String idimgurl_back) {
        this.idimgurl_back = idimgurl_back;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getAccount_bank_name() {
        return account_bank_name;
    }

    public void setAccount_bank_name(String account_bank_name) {
        this.account_bank_name = account_bank_name;
    }

    public String getAccount_value() {
        return account_value;
    }

    public void setAccount_value(String account_value) {
        this.account_value = account_value;
    }

    public String getOpen_branch() {
        return open_branch;
    }

    public void setOpen_branch(String open_branch) {
        this.open_branch = open_branch;
    }

    public String getOpen_province() {
        return open_province;
    }

    public void setOpen_province(String open_province) {
        this.open_province = open_province;
    }

    public String getOpen_city() {
        return open_city;
    }

    public void setOpen_city(String open_city) {
        this.open_city = open_city;
    }


    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getModify_user() {
        return modify_user;
    }

    public void setModify_user(String modify_user) {
        this.modify_user = modify_user;
    }

    public String getITcode() {
        return ITcode;
    }

    public void setITcode(String ITcode) {
        this.ITcode = ITcode;
    }

    public String getWxName() {
        return wxName;
    }

    public void setWxName(String wxName) {
        this.wxName = wxName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    protected Serializable pkVal() {
        return this.openid;
    }
}
