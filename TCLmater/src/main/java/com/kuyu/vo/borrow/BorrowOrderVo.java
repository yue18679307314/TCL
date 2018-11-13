package com.kuyu.vo.borrow;

/**
 * @Author jt_L
 * @Date 2017/11/11
 * @Description 借款单可发起付款vo
 */
public class BorrowOrderVo {

    /**
     * 申请单id
     */
    private String request_id;

    /**
     * 申请人
     */
    private String request_user;

    /**
     * 申请部门
     */
    private String request_dept;

    /**
     * 项目编号
     */
    private String project_id;

    /**
     * 活动编号
     */
    private String user_statement_uuid;

    /**
     * 活动编号
     */
    private String activity_uuid;

    /**
     * 结算用户openid
     */
    private String openid;

    /**
     * 结算用户报酬
     */
    private Double real_salary;

    /**
     * 活动负责人
     */
    private String manager;
    
    /**
     * 活动负责人
     */
    private String dept;

    /**
     * 结算用户银行是否审核通过
     */
    private String bank_account_verify;

    /**
     * 结算用户银行名称
     */
    private String account_bank_name;

    /**
     * 结算用户银行开户名
     */
    private String account_name;

    /**
     * 结算用户银行账号
     */
    private String account_value;
    
    private String settlement_cycle;
    
    private String end_time;
    
    public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getSettlement_cycle() {
		return settlement_cycle;
	}

	public void setSettlement_cycle(String settlement_cycle) {
		this.settlement_cycle = settlement_cycle;
	}

	public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getRequest_user() {
        return request_user;
    }

    public void setRequest_user(String request_user) {
        this.request_user = request_user;
    }

    public String getRequest_dept() {
        return request_dept;
    }

    public void setRequest_dept(String request_dept) {
        this.request_dept = request_dept;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getActivity_uuid() {
        return activity_uuid;
    }

    public void setActivity_uuid(String activity_uuid) {
        this.activity_uuid = activity_uuid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Double getReal_salary() {
        return real_salary;
    }

    public void setReal_salary(Double real_salary) {
        this.real_salary = real_salary;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getBank_account_verify() {
        return bank_account_verify;
    }

    public void setBank_account_verify(String bank_account_verify) {
        this.bank_account_verify = bank_account_verify;
    }

    public String getAccount_bank_name() {
        return account_bank_name;
    }

    public void setAccount_bank_name(String account_bank_name) {
        this.account_bank_name = account_bank_name;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_value() {
        return account_value;
    }

    public void setAccount_value(String account_value) {
        this.account_value = account_value;
    }

    public String getUser_statement_uuid() {
        return user_statement_uuid;
    }

    public void setUser_statement_uuid(String user_statement_uuid) {
        this.user_statement_uuid = user_statement_uuid;
    }

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
    
}
