package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsProjectExample() {
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

        public Criteria andRequestIdIsNull() {
            addCriterion("REQUEST_ID is null");
            return (Criteria) this;
        }

        public Criteria andRequestIdIsNotNull() {
            addCriterion("REQUEST_ID is not null");
            return (Criteria) this;
        }

        public Criteria andRequestIdEqualTo(String value) {
            addCriterion("REQUEST_ID =", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotEqualTo(String value) {
            addCriterion("REQUEST_ID <>", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThan(String value) {
            addCriterion("REQUEST_ID >", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_ID >=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThan(String value) {
            addCriterion("REQUEST_ID <", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_ID <=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLike(String value) {
            addCriterion("REQUEST_ID like", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotLike(String value) {
            addCriterion("REQUEST_ID not like", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdIn(List<String> values) {
            addCriterion("REQUEST_ID in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotIn(List<String> values) {
            addCriterion("REQUEST_ID not in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdBetween(String value1, String value2) {
            addCriterion("REQUEST_ID between", value1, value2, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotBetween(String value1, String value2) {
            addCriterion("REQUEST_ID not between", value1, value2, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestTitleIsNull() {
            addCriterion("REQUEST_TITLE is null");
            return (Criteria) this;
        }

        public Criteria andRequestTitleIsNotNull() {
            addCriterion("REQUEST_TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTitleEqualTo(String value) {
            addCriterion("REQUEST_TITLE =", value, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleNotEqualTo(String value) {
            addCriterion("REQUEST_TITLE <>", value, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleGreaterThan(String value) {
            addCriterion("REQUEST_TITLE >", value, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_TITLE >=", value, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleLessThan(String value) {
            addCriterion("REQUEST_TITLE <", value, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_TITLE <=", value, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleLike(String value) {
            addCriterion("REQUEST_TITLE like", value, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleNotLike(String value) {
            addCriterion("REQUEST_TITLE not like", value, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleIn(List<String> values) {
            addCriterion("REQUEST_TITLE in", values, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleNotIn(List<String> values) {
            addCriterion("REQUEST_TITLE not in", values, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleBetween(String value1, String value2) {
            addCriterion("REQUEST_TITLE between", value1, value2, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andRequestTitleNotBetween(String value1, String value2) {
            addCriterion("REQUEST_TITLE not between", value1, value2, "requestTitle");
            return (Criteria) this;
        }

        public Criteria andDetailAccountIsNull() {
            addCriterion("DETAIL_ACCOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDetailAccountIsNotNull() {
            addCriterion("DETAIL_ACCOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDetailAccountEqualTo(String value) {
            addCriterion("DETAIL_ACCOUNT =", value, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountNotEqualTo(String value) {
            addCriterion("DETAIL_ACCOUNT <>", value, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountGreaterThan(String value) {
            addCriterion("DETAIL_ACCOUNT >", value, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_ACCOUNT >=", value, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountLessThan(String value) {
            addCriterion("DETAIL_ACCOUNT <", value, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_ACCOUNT <=", value, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountLike(String value) {
            addCriterion("DETAIL_ACCOUNT like", value, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountNotLike(String value) {
            addCriterion("DETAIL_ACCOUNT not like", value, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountIn(List<String> values) {
            addCriterion("DETAIL_ACCOUNT in", values, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountNotIn(List<String> values) {
            addCriterion("DETAIL_ACCOUNT not in", values, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountBetween(String value1, String value2) {
            addCriterion("DETAIL_ACCOUNT between", value1, value2, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andDetailAccountNotBetween(String value1, String value2) {
            addCriterion("DETAIL_ACCOUNT not between", value1, value2, "detailAccount");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeIsNull() {
            addCriterion("REQUEST_COMPANY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeIsNotNull() {
            addCriterion("REQUEST_COMPANY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeEqualTo(String value) {
            addCriterion("REQUEST_COMPANY_CODE =", value, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeNotEqualTo(String value) {
            addCriterion("REQUEST_COMPANY_CODE <>", value, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeGreaterThan(String value) {
            addCriterion("REQUEST_COMPANY_CODE >", value, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_COMPANY_CODE >=", value, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeLessThan(String value) {
            addCriterion("REQUEST_COMPANY_CODE <", value, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_COMPANY_CODE <=", value, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeLike(String value) {
            addCriterion("REQUEST_COMPANY_CODE like", value, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeNotLike(String value) {
            addCriterion("REQUEST_COMPANY_CODE not like", value, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeIn(List<String> values) {
            addCriterion("REQUEST_COMPANY_CODE in", values, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeNotIn(List<String> values) {
            addCriterion("REQUEST_COMPANY_CODE not in", values, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeBetween(String value1, String value2) {
            addCriterion("REQUEST_COMPANY_CODE between", value1, value2, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andRequestCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("REQUEST_COMPANY_CODE not between", value1, value2, "requestCompanyCode");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectIsNull() {
            addCriterion("DETAIL_SUBJECT is null");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectIsNotNull() {
            addCriterion("DETAIL_SUBJECT is not null");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectEqualTo(String value) {
            addCriterion("DETAIL_SUBJECT =", value, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectNotEqualTo(String value) {
            addCriterion("DETAIL_SUBJECT <>", value, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectGreaterThan(String value) {
            addCriterion("DETAIL_SUBJECT >", value, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_SUBJECT >=", value, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectLessThan(String value) {
            addCriterion("DETAIL_SUBJECT <", value, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_SUBJECT <=", value, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectLike(String value) {
            addCriterion("DETAIL_SUBJECT like", value, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectNotLike(String value) {
            addCriterion("DETAIL_SUBJECT not like", value, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectIn(List<String> values) {
            addCriterion("DETAIL_SUBJECT in", values, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectNotIn(List<String> values) {
            addCriterion("DETAIL_SUBJECT not in", values, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectBetween(String value1, String value2) {
            addCriterion("DETAIL_SUBJECT between", value1, value2, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andDetailSubjectNotBetween(String value1, String value2) {
            addCriterion("DETAIL_SUBJECT not between", value1, value2, "detailSubject");
            return (Criteria) this;
        }

        public Criteria andRequestDeptIsNull() {
            addCriterion("REQUEST_DEPT is null");
            return (Criteria) this;
        }

        public Criteria andRequestDeptIsNotNull() {
            addCriterion("REQUEST_DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andRequestDeptEqualTo(String value) {
            addCriterion("REQUEST_DEPT =", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNotEqualTo(String value) {
            addCriterion("REQUEST_DEPT <>", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptGreaterThan(String value) {
            addCriterion("REQUEST_DEPT >", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_DEPT >=", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptLessThan(String value) {
            addCriterion("REQUEST_DEPT <", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_DEPT <=", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptLike(String value) {
            addCriterion("REQUEST_DEPT like", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNotLike(String value) {
            addCriterion("REQUEST_DEPT not like", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptIn(List<String> values) {
            addCriterion("REQUEST_DEPT in", values, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNotIn(List<String> values) {
            addCriterion("REQUEST_DEPT not in", values, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptBetween(String value1, String value2) {
            addCriterion("REQUEST_DEPT between", value1, value2, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNotBetween(String value1, String value2) {
            addCriterion("REQUEST_DEPT not between", value1, value2, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestUserIsNull() {
            addCriterion("REQUEST_USER is null");
            return (Criteria) this;
        }

        public Criteria andRequestUserIsNotNull() {
            addCriterion("REQUEST_USER is not null");
            return (Criteria) this;
        }

        public Criteria andRequestUserEqualTo(String value) {
            addCriterion("REQUEST_USER =", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotEqualTo(String value) {
            addCriterion("REQUEST_USER <>", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserGreaterThan(String value) {
            addCriterion("REQUEST_USER >", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_USER >=", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserLessThan(String value) {
            addCriterion("REQUEST_USER <", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_USER <=", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserLike(String value) {
            addCriterion("REQUEST_USER like", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotLike(String value) {
            addCriterion("REQUEST_USER not like", value, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserIn(List<String> values) {
            addCriterion("REQUEST_USER in", values, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotIn(List<String> values) {
            addCriterion("REQUEST_USER not in", values, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserBetween(String value1, String value2) {
            addCriterion("REQUEST_USER between", value1, value2, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestUserNotBetween(String value1, String value2) {
            addCriterion("REQUEST_USER not between", value1, value2, "requestUser");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneIsNull() {
            addCriterion("REQUEST_TELPHONE is null");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneIsNotNull() {
            addCriterion("REQUEST_TELPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneEqualTo(String value) {
            addCriterion("REQUEST_TELPHONE =", value, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneNotEqualTo(String value) {
            addCriterion("REQUEST_TELPHONE <>", value, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneGreaterThan(String value) {
            addCriterion("REQUEST_TELPHONE >", value, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_TELPHONE >=", value, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneLessThan(String value) {
            addCriterion("REQUEST_TELPHONE <", value, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_TELPHONE <=", value, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneLike(String value) {
            addCriterion("REQUEST_TELPHONE like", value, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneNotLike(String value) {
            addCriterion("REQUEST_TELPHONE not like", value, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneIn(List<String> values) {
            addCriterion("REQUEST_TELPHONE in", values, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneNotIn(List<String> values) {
            addCriterion("REQUEST_TELPHONE not in", values, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneBetween(String value1, String value2) {
            addCriterion("REQUEST_TELPHONE between", value1, value2, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestTelphoneNotBetween(String value1, String value2) {
            addCriterion("REQUEST_TELPHONE not between", value1, value2, "requestTelphone");
            return (Criteria) this;
        }

        public Criteria andRequestEmailIsNull() {
            addCriterion("REQUEST_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andRequestEmailIsNotNull() {
            addCriterion("REQUEST_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andRequestEmailEqualTo(String value) {
            addCriterion("REQUEST_EMAIL =", value, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailNotEqualTo(String value) {
            addCriterion("REQUEST_EMAIL <>", value, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailGreaterThan(String value) {
            addCriterion("REQUEST_EMAIL >", value, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_EMAIL >=", value, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailLessThan(String value) {
            addCriterion("REQUEST_EMAIL <", value, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_EMAIL <=", value, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailLike(String value) {
            addCriterion("REQUEST_EMAIL like", value, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailNotLike(String value) {
            addCriterion("REQUEST_EMAIL not like", value, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailIn(List<String> values) {
            addCriterion("REQUEST_EMAIL in", values, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailNotIn(List<String> values) {
            addCriterion("REQUEST_EMAIL not in", values, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailBetween(String value1, String value2) {
            addCriterion("REQUEST_EMAIL between", value1, value2, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestEmailNotBetween(String value1, String value2) {
            addCriterion("REQUEST_EMAIL not between", value1, value2, "requestEmail");
            return (Criteria) this;
        }

        public Criteria andRequestInfoIsNull() {
            addCriterion("REQUEST_INFO is null");
            return (Criteria) this;
        }

        public Criteria andRequestInfoIsNotNull() {
            addCriterion("REQUEST_INFO is not null");
            return (Criteria) this;
        }

        public Criteria andRequestInfoEqualTo(String value) {
            addCriterion("REQUEST_INFO =", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoNotEqualTo(String value) {
            addCriterion("REQUEST_INFO <>", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoGreaterThan(String value) {
            addCriterion("REQUEST_INFO >", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_INFO >=", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoLessThan(String value) {
            addCriterion("REQUEST_INFO <", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_INFO <=", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoLike(String value) {
            addCriterion("REQUEST_INFO like", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoNotLike(String value) {
            addCriterion("REQUEST_INFO not like", value, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoIn(List<String> values) {
            addCriterion("REQUEST_INFO in", values, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoNotIn(List<String> values) {
            addCriterion("REQUEST_INFO not in", values, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoBetween(String value1, String value2) {
            addCriterion("REQUEST_INFO between", value1, value2, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestInfoNotBetween(String value1, String value2) {
            addCriterion("REQUEST_INFO not between", value1, value2, "requestInfo");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameIsNull() {
            addCriterion("request_dept_name is null");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameIsNotNull() {
            addCriterion("request_dept_name is not null");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameEqualTo(String value) {
            addCriterion("request_dept_name =", value, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameNotEqualTo(String value) {
            addCriterion("request_dept_name <>", value, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameGreaterThan(String value) {
            addCriterion("request_dept_name >", value, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameGreaterThanOrEqualTo(String value) {
            addCriterion("request_dept_name >=", value, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameLessThan(String value) {
            addCriterion("request_dept_name <", value, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameLessThanOrEqualTo(String value) {
            addCriterion("request_dept_name <=", value, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameLike(String value) {
            addCriterion("request_dept_name like", value, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameNotLike(String value) {
            addCriterion("request_dept_name not like", value, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameIn(List<String> values) {
            addCriterion("request_dept_name in", values, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameNotIn(List<String> values) {
            addCriterion("request_dept_name not in", values, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameBetween(String value1, String value2) {
            addCriterion("request_dept_name between", value1, value2, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNameNotBetween(String value1, String value2) {
            addCriterion("request_dept_name not between", value1, value2, "requestDeptName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameIsNull() {
            addCriterion("request_user_name is null");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameIsNotNull() {
            addCriterion("request_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameEqualTo(String value) {
            addCriterion("request_user_name =", value, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameNotEqualTo(String value) {
            addCriterion("request_user_name <>", value, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameGreaterThan(String value) {
            addCriterion("request_user_name >", value, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("request_user_name >=", value, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameLessThan(String value) {
            addCriterion("request_user_name <", value, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameLessThanOrEqualTo(String value) {
            addCriterion("request_user_name <=", value, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameLike(String value) {
            addCriterion("request_user_name like", value, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameNotLike(String value) {
            addCriterion("request_user_name not like", value, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameIn(List<String> values) {
            addCriterion("request_user_name in", values, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameNotIn(List<String> values) {
            addCriterion("request_user_name not in", values, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameBetween(String value1, String value2) {
            addCriterion("request_user_name between", value1, value2, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameNotBetween(String value1, String value2) {
            addCriterion("request_user_name not between", value1, value2, "requestUserName");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyIsNull() {
            addCriterion("request_user_name_copy is null");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyIsNotNull() {
            addCriterion("request_user_name_copy is not null");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyEqualTo(String value) {
            addCriterion("request_user_name_copy =", value, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyNotEqualTo(String value) {
            addCriterion("request_user_name_copy <>", value, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyGreaterThan(String value) {
            addCriterion("request_user_name_copy >", value, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyGreaterThanOrEqualTo(String value) {
            addCriterion("request_user_name_copy >=", value, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyLessThan(String value) {
            addCriterion("request_user_name_copy <", value, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyLessThanOrEqualTo(String value) {
            addCriterion("request_user_name_copy <=", value, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyLike(String value) {
            addCriterion("request_user_name_copy like", value, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyNotLike(String value) {
            addCriterion("request_user_name_copy not like", value, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyIn(List<String> values) {
            addCriterion("request_user_name_copy in", values, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyNotIn(List<String> values) {
            addCriterion("request_user_name_copy not in", values, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyBetween(String value1, String value2) {
            addCriterion("request_user_name_copy between", value1, value2, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andRequestUserNameCopyNotBetween(String value1, String value2) {
            addCriterion("request_user_name_copy not between", value1, value2, "requestUserNameCopy");
            return (Criteria) this;
        }

        public Criteria andFinanceUserIsNull() {
            addCriterion("finance_user is null");
            return (Criteria) this;
        }

        public Criteria andFinanceUserIsNotNull() {
            addCriterion("finance_user is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceUserEqualTo(String value) {
            addCriterion("finance_user =", value, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNotEqualTo(String value) {
            addCriterion("finance_user <>", value, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserGreaterThan(String value) {
            addCriterion("finance_user >", value, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserGreaterThanOrEqualTo(String value) {
            addCriterion("finance_user >=", value, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserLessThan(String value) {
            addCriterion("finance_user <", value, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserLessThanOrEqualTo(String value) {
            addCriterion("finance_user <=", value, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserLike(String value) {
            addCriterion("finance_user like", value, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNotLike(String value) {
            addCriterion("finance_user not like", value, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserIn(List<String> values) {
            addCriterion("finance_user in", values, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNotIn(List<String> values) {
            addCriterion("finance_user not in", values, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserBetween(String value1, String value2) {
            addCriterion("finance_user between", value1, value2, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNotBetween(String value1, String value2) {
            addCriterion("finance_user not between", value1, value2, "financeUser");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameIsNull() {
            addCriterion("finance_user_name is null");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameIsNotNull() {
            addCriterion("finance_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameEqualTo(String value) {
            addCriterion("finance_user_name =", value, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameNotEqualTo(String value) {
            addCriterion("finance_user_name <>", value, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameGreaterThan(String value) {
            addCriterion("finance_user_name >", value, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("finance_user_name >=", value, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameLessThan(String value) {
            addCriterion("finance_user_name <", value, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameLessThanOrEqualTo(String value) {
            addCriterion("finance_user_name <=", value, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameLike(String value) {
            addCriterion("finance_user_name like", value, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameNotLike(String value) {
            addCriterion("finance_user_name not like", value, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameIn(List<String> values) {
            addCriterion("finance_user_name in", values, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameNotIn(List<String> values) {
            addCriterion("finance_user_name not in", values, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameBetween(String value1, String value2) {
            addCriterion("finance_user_name between", value1, value2, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andFinanceUserNameNotBetween(String value1, String value2) {
            addCriterion("finance_user_name not between", value1, value2, "financeUserName");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeIsNull() {
            addCriterion("REQUEST_CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeIsNotNull() {
            addCriterion("REQUEST_CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeEqualTo(String value) {
            addCriterion("REQUEST_CREATE_TIME =", value, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeNotEqualTo(String value) {
            addCriterion("REQUEST_CREATE_TIME <>", value, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeGreaterThan(String value) {
            addCriterion("REQUEST_CREATE_TIME >", value, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_CREATE_TIME >=", value, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeLessThan(String value) {
            addCriterion("REQUEST_CREATE_TIME <", value, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_CREATE_TIME <=", value, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeLike(String value) {
            addCriterion("REQUEST_CREATE_TIME like", value, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeNotLike(String value) {
            addCriterion("REQUEST_CREATE_TIME not like", value, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeIn(List<String> values) {
            addCriterion("REQUEST_CREATE_TIME in", values, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeNotIn(List<String> values) {
            addCriterion("REQUEST_CREATE_TIME not in", values, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeBetween(String value1, String value2) {
            addCriterion("REQUEST_CREATE_TIME between", value1, value2, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestCreateTimeNotBetween(String value1, String value2) {
            addCriterion("REQUEST_CREATE_TIME not between", value1, value2, "requestCreateTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeIsNull() {
            addCriterion("REQUEST_END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeIsNotNull() {
            addCriterion("REQUEST_END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeEqualTo(String value) {
            addCriterion("REQUEST_END_TIME =", value, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeNotEqualTo(String value) {
            addCriterion("REQUEST_END_TIME <>", value, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeGreaterThan(String value) {
            addCriterion("REQUEST_END_TIME >", value, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_END_TIME >=", value, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeLessThan(String value) {
            addCriterion("REQUEST_END_TIME <", value, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_END_TIME <=", value, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeLike(String value) {
            addCriterion("REQUEST_END_TIME like", value, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeNotLike(String value) {
            addCriterion("REQUEST_END_TIME not like", value, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeIn(List<String> values) {
            addCriterion("REQUEST_END_TIME in", values, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeNotIn(List<String> values) {
            addCriterion("REQUEST_END_TIME not in", values, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeBetween(String value1, String value2) {
            addCriterion("REQUEST_END_TIME between", value1, value2, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andRequestEndTimeNotBetween(String value1, String value2) {
            addCriterion("REQUEST_END_TIME not between", value1, value2, "requestEndTime");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("CREATE_USER is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("CREATE_USER is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("CREATE_USER =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("CREATE_USER <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("CREATE_USER >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_USER >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("CREATE_USER <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("CREATE_USER <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("CREATE_USER like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("CREATE_USER not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("CREATE_USER in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("CREATE_USER not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("CREATE_USER between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("CREATE_USER not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("CURRENCY is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("CURRENCY is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("CURRENCY =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("CURRENCY <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("CURRENCY >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENCY >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("CURRENCY <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("CURRENCY <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("CURRENCY like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("CURRENCY not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("CURRENCY in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("CURRENCY not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("CURRENCY between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("CURRENCY not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andExchangeIsNull() {
            addCriterion("EXCHANGE is null");
            return (Criteria) this;
        }

        public Criteria andExchangeIsNotNull() {
            addCriterion("EXCHANGE is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeEqualTo(String value) {
            addCriterion("EXCHANGE =", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotEqualTo(String value) {
            addCriterion("EXCHANGE <>", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeGreaterThan(String value) {
            addCriterion("EXCHANGE >", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeGreaterThanOrEqualTo(String value) {
            addCriterion("EXCHANGE >=", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeLessThan(String value) {
            addCriterion("EXCHANGE <", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeLessThanOrEqualTo(String value) {
            addCriterion("EXCHANGE <=", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeLike(String value) {
            addCriterion("EXCHANGE like", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotLike(String value) {
            addCriterion("EXCHANGE not like", value, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeIn(List<String> values) {
            addCriterion("EXCHANGE in", values, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotIn(List<String> values) {
            addCriterion("EXCHANGE not in", values, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeBetween(String value1, String value2) {
            addCriterion("EXCHANGE between", value1, value2, "exchange");
            return (Criteria) this;
        }

        public Criteria andExchangeNotBetween(String value1, String value2) {
            addCriterion("EXCHANGE not between", value1, value2, "exchange");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(String value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(String value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(String value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(String value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(String value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(String value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLike(String value) {
            addCriterion("balance like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotLike(String value) {
            addCriterion("balance not like", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<String> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<String> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(String value1, String value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(String value1, String value2) {
            addCriterion("balance not between", value1, value2, "balance");
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

        public Criteria andCreatTimeIsNull() {
            addCriterion("creat_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIsNotNull() {
            addCriterion("creat_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatTimeEqualTo(Date value) {
            addCriterion("creat_time =", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotEqualTo(Date value) {
            addCriterion("creat_time <>", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeGreaterThan(Date value) {
            addCriterion("creat_time >", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creat_time >=", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeLessThan(Date value) {
            addCriterion("creat_time <", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeLessThanOrEqualTo(Date value) {
            addCriterion("creat_time <=", value, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeIn(List<Date> values) {
            addCriterion("creat_time in", values, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotIn(List<Date> values) {
            addCriterion("creat_time not in", values, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeBetween(Date value1, Date value2) {
            addCriterion("creat_time between", value1, value2, "creatTime");
            return (Criteria) this;
        }

        public Criteria andCreatTimeNotBetween(Date value1, Date value2) {
            addCriterion("creat_time not between", value1, value2, "creatTime");
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