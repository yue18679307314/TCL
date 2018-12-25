package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsSettlementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsSettlementExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andSeidIsNull() {
            addCriterion("seid is null");
            return (Criteria) this;
        }

        public Criteria andSeidIsNotNull() {
            addCriterion("seid is not null");
            return (Criteria) this;
        }

        public Criteria andSeidEqualTo(Integer value) {
            addCriterion("seid =", value, "seid");
            return (Criteria) this;
        }

        public Criteria andSeidNotEqualTo(Integer value) {
            addCriterion("seid <>", value, "seid");
            return (Criteria) this;
        }

        public Criteria andSeidGreaterThan(Integer value) {
            addCriterion("seid >", value, "seid");
            return (Criteria) this;
        }

        public Criteria andSeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("seid >=", value, "seid");
            return (Criteria) this;
        }

        public Criteria andSeidLessThan(Integer value) {
            addCriterion("seid <", value, "seid");
            return (Criteria) this;
        }

        public Criteria andSeidLessThanOrEqualTo(Integer value) {
            addCriterion("seid <=", value, "seid");
            return (Criteria) this;
        }

        public Criteria andSeidIn(List<Integer> values) {
            addCriterion("seid in", values, "seid");
            return (Criteria) this;
        }

        public Criteria andSeidNotIn(List<Integer> values) {
            addCriterion("seid not in", values, "seid");
            return (Criteria) this;
        }

        public Criteria andSeidBetween(Integer value1, Integer value2) {
            addCriterion("seid between", value1, value2, "seid");
            return (Criteria) this;
        }

        public Criteria andSeidNotBetween(Integer value1, Integer value2) {
            addCriterion("seid not between", value1, value2, "seid");
            return (Criteria) this;
        }

        public Criteria andSettNumberIsNull() {
            addCriterion("sett_number is null");
            return (Criteria) this;
        }

        public Criteria andSettNumberIsNotNull() {
            addCriterion("sett_number is not null");
            return (Criteria) this;
        }

        public Criteria andSettNumberEqualTo(String value) {
            addCriterion("sett_number =", value, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberNotEqualTo(String value) {
            addCriterion("sett_number <>", value, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberGreaterThan(String value) {
            addCriterion("sett_number >", value, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberGreaterThanOrEqualTo(String value) {
            addCriterion("sett_number >=", value, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberLessThan(String value) {
            addCriterion("sett_number <", value, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberLessThanOrEqualTo(String value) {
            addCriterion("sett_number <=", value, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberLike(String value) {
            addCriterion("sett_number like", value, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberNotLike(String value) {
            addCriterion("sett_number not like", value, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberIn(List<String> values) {
            addCriterion("sett_number in", values, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberNotIn(List<String> values) {
            addCriterion("sett_number not in", values, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberBetween(String value1, String value2) {
            addCriterion("sett_number between", value1, value2, "settNumber");
            return (Criteria) this;
        }

        public Criteria andSettNumberNotBetween(String value1, String value2) {
            addCriterion("sett_number not between", value1, value2, "settNumber");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleIsNull() {
            addCriterion("application_title is null");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleIsNotNull() {
            addCriterion("application_title is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleEqualTo(String value) {
            addCriterion("application_title =", value, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleNotEqualTo(String value) {
            addCriterion("application_title <>", value, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleGreaterThan(String value) {
            addCriterion("application_title >", value, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleGreaterThanOrEqualTo(String value) {
            addCriterion("application_title >=", value, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleLessThan(String value) {
            addCriterion("application_title <", value, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleLessThanOrEqualTo(String value) {
            addCriterion("application_title <=", value, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleLike(String value) {
            addCriterion("application_title like", value, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleNotLike(String value) {
            addCriterion("application_title not like", value, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleIn(List<String> values) {
            addCriterion("application_title in", values, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleNotIn(List<String> values) {
            addCriterion("application_title not in", values, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleBetween(String value1, String value2) {
            addCriterion("application_title between", value1, value2, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andApplicationTitleNotBetween(String value1, String value2) {
            addCriterion("application_title not between", value1, value2, "applicationTitle");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNull() {
            addCriterion("company_code is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNotNull() {
            addCriterion("company_code is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeEqualTo(String value) {
            addCriterion("company_code =", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotEqualTo(String value) {
            addCriterion("company_code <>", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThan(String value) {
            addCriterion("company_code >", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("company_code >=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThan(String value) {
            addCriterion("company_code <", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("company_code <=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLike(String value) {
            addCriterion("company_code like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotLike(String value) {
            addCriterion("company_code not like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIn(List<String> values) {
            addCriterion("company_code in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotIn(List<String> values) {
            addCriterion("company_code not in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeBetween(String value1, String value2) {
            addCriterion("company_code between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("company_code not between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andRequestUserIsNull() {
            addCriterion("request_user is null");
            return (Criteria) this;
        }

        public Criteria andRequestUserIsNotNull() {
            addCriterion("request_user is not null");
            return (Criteria) this;
        }

        public Criteria andRequestUserEqualTo(String value) {
            addCriterion("request_user =", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotEqualTo(String value) {
            addCriterion("request_user <>", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserGreaterThan(String value) {
            addCriterion("request_user >", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserGreaterThanOrEqualTo(String value) {
            addCriterion("request_user >=", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserLessThan(String value) {
            addCriterion("request_user <", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserLessThanOrEqualTo(String value) {
            addCriterion("request_user <=", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserLike(String value) {
            addCriterion("request_user like", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotLike(String value) {
            addCriterion("request_user not like", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserIn(List<String> values) {
            addCriterion("request_user in", values, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotIn(List<String> values) {
            addCriterion("request_user not in", values, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserBetween(String value1, String value2) {
            addCriterion("request_user between", value1, value2, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotBetween(String value1, String value2) {
            addCriterion("request_user not between", value1, value2, "requestUser");
            return (Criteria) this;
        }

        public Criteria andDeptIsNull() {
            addCriterion("dept is null");
            return (Criteria) this;
        }

        public Criteria andDeptIsNotNull() {
            addCriterion("dept is not null");
            return (Criteria) this;
        }

        public Criteria andDeptEqualTo(String value) {
            addCriterion("dept =", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotEqualTo(String value) {
            addCriterion("dept <>", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThan(String value) {
            addCriterion("dept >", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThanOrEqualTo(String value) {
            addCriterion("dept >=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThan(String value) {
            addCriterion("dept <", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThanOrEqualTo(String value) {
            addCriterion("dept <=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLike(String value) {
            addCriterion("dept like", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotLike(String value) {
            addCriterion("dept not like", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptIn(List<String> values) {
            addCriterion("dept in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotIn(List<String> values) {
            addCriterion("dept not in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptBetween(String value1, String value2) {
            addCriterion("dept between", value1, value2, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotBetween(String value1, String value2) {
            addCriterion("dept not between", value1, value2, "dept");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesIsNull() {
            addCriterion("application_notes is null");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesIsNotNull() {
            addCriterion("application_notes is not null");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesEqualTo(String value) {
            addCriterion("application_notes =", value, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesNotEqualTo(String value) {
            addCriterion("application_notes <>", value, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesGreaterThan(String value) {
            addCriterion("application_notes >", value, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesGreaterThanOrEqualTo(String value) {
            addCriterion("application_notes >=", value, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesLessThan(String value) {
            addCriterion("application_notes <", value, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesLessThanOrEqualTo(String value) {
            addCriterion("application_notes <=", value, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesLike(String value) {
            addCriterion("application_notes like", value, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesNotLike(String value) {
            addCriterion("application_notes not like", value, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesIn(List<String> values) {
            addCriterion("application_notes in", values, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesNotIn(List<String> values) {
            addCriterion("application_notes not in", values, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesBetween(String value1, String value2) {
            addCriterion("application_notes between", value1, value2, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andApplicationNotesNotBetween(String value1, String value2) {
            addCriterion("application_notes not between", value1, value2, "applicationNotes");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andTaxNumberIsNull() {
            addCriterion("tax_number is null");
            return (Criteria) this;
        }

        public Criteria andTaxNumberIsNotNull() {
            addCriterion("tax_number is not null");
            return (Criteria) this;
        }

        public Criteria andTaxNumberEqualTo(String value) {
            addCriterion("tax_number =", value, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberNotEqualTo(String value) {
            addCriterion("tax_number <>", value, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberGreaterThan(String value) {
            addCriterion("tax_number >", value, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberGreaterThanOrEqualTo(String value) {
            addCriterion("tax_number >=", value, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberLessThan(String value) {
            addCriterion("tax_number <", value, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberLessThanOrEqualTo(String value) {
            addCriterion("tax_number <=", value, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberLike(String value) {
            addCriterion("tax_number like", value, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberNotLike(String value) {
            addCriterion("tax_number not like", value, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberIn(List<String> values) {
            addCriterion("tax_number in", values, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberNotIn(List<String> values) {
            addCriterion("tax_number not in", values, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberBetween(String value1, String value2) {
            addCriterion("tax_number between", value1, value2, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andTaxNumberNotBetween(String value1, String value2) {
            addCriterion("tax_number not between", value1, value2, "taxNumber");
            return (Criteria) this;
        }

        public Criteria andSumMoneyIsNull() {
            addCriterion("sum_money is null");
            return (Criteria) this;
        }

        public Criteria andSumMoneyIsNotNull() {
            addCriterion("sum_money is not null");
            return (Criteria) this;
        }

        public Criteria andSumMoneyEqualTo(String value) {
            addCriterion("sum_money =", value, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyNotEqualTo(String value) {
            addCriterion("sum_money <>", value, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyGreaterThan(String value) {
            addCriterion("sum_money >", value, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("sum_money >=", value, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyLessThan(String value) {
            addCriterion("sum_money <", value, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyLessThanOrEqualTo(String value) {
            addCriterion("sum_money <=", value, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyLike(String value) {
            addCriterion("sum_money like", value, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyNotLike(String value) {
            addCriterion("sum_money not like", value, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyIn(List<String> values) {
            addCriterion("sum_money in", values, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyNotIn(List<String> values) {
            addCriterion("sum_money not in", values, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyBetween(String value1, String value2) {
            addCriterion("sum_money between", value1, value2, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andSumMoneyNotBetween(String value1, String value2) {
            addCriterion("sum_money not between", value1, value2, "sumMoney");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andFccsBillIsNull() {
            addCriterion("fccs_bill is null");
            return (Criteria) this;
        }

        public Criteria andFccsBillIsNotNull() {
            addCriterion("fccs_bill is not null");
            return (Criteria) this;
        }

        public Criteria andFccsBillEqualTo(String value) {
            addCriterion("fccs_bill =", value, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillNotEqualTo(String value) {
            addCriterion("fccs_bill <>", value, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillGreaterThan(String value) {
            addCriterion("fccs_bill >", value, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillGreaterThanOrEqualTo(String value) {
            addCriterion("fccs_bill >=", value, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillLessThan(String value) {
            addCriterion("fccs_bill <", value, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillLessThanOrEqualTo(String value) {
            addCriterion("fccs_bill <=", value, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillLike(String value) {
            addCriterion("fccs_bill like", value, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillNotLike(String value) {
            addCriterion("fccs_bill not like", value, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillIn(List<String> values) {
            addCriterion("fccs_bill in", values, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillNotIn(List<String> values) {
            addCriterion("fccs_bill not in", values, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillBetween(String value1, String value2) {
            addCriterion("fccs_bill between", value1, value2, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andFccsBillNotBetween(String value1, String value2) {
            addCriterion("fccs_bill not between", value1, value2, "fccsBill");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}