package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsPaymentDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsPaymentDetailExample() {
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

        public Criteria andPmdidIsNull() {
            addCriterion("pmdid is null");
            return (Criteria) this;
        }

        public Criteria andPmdidIsNotNull() {
            addCriterion("pmdid is not null");
            return (Criteria) this;
        }

        public Criteria andPmdidEqualTo(Integer value) {
            addCriterion("pmdid =", value, "pmdid");
            return (Criteria) this;
        }

        public Criteria andPmdidNotEqualTo(Integer value) {
            addCriterion("pmdid <>", value, "pmdid");
            return (Criteria) this;
        }

        public Criteria andPmdidGreaterThan(Integer value) {
            addCriterion("pmdid >", value, "pmdid");
            return (Criteria) this;
        }

        public Criteria andPmdidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pmdid >=", value, "pmdid");
            return (Criteria) this;
        }

        public Criteria andPmdidLessThan(Integer value) {
            addCriterion("pmdid <", value, "pmdid");
            return (Criteria) this;
        }

        public Criteria andPmdidLessThanOrEqualTo(Integer value) {
            addCriterion("pmdid <=", value, "pmdid");
            return (Criteria) this;
        }

        public Criteria andPmdidIn(List<Integer> values) {
            addCriterion("pmdid in", values, "pmdid");
            return (Criteria) this;
        }

        public Criteria andPmdidNotIn(List<Integer> values) {
            addCriterion("pmdid not in", values, "pmdid");
            return (Criteria) this;
        }

        public Criteria andPmdidBetween(Integer value1, Integer value2) {
            addCriterion("pmdid between", value1, value2, "pmdid");
            return (Criteria) this;
        }

        public Criteria andPmdidNotBetween(Integer value1, Integer value2) {
            addCriterion("pmdid not between", value1, value2, "pmdid");
            return (Criteria) this;
        }

        public Criteria andFsscBillIsNull() {
            addCriterion("fssc_bill is null");
            return (Criteria) this;
        }

        public Criteria andFsscBillIsNotNull() {
            addCriterion("fssc_bill is not null");
            return (Criteria) this;
        }

        public Criteria andFsscBillEqualTo(String value) {
            addCriterion("fssc_bill =", value, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillNotEqualTo(String value) {
            addCriterion("fssc_bill <>", value, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillGreaterThan(String value) {
            addCriterion("fssc_bill >", value, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillGreaterThanOrEqualTo(String value) {
            addCriterion("fssc_bill >=", value, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillLessThan(String value) {
            addCriterion("fssc_bill <", value, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillLessThanOrEqualTo(String value) {
            addCriterion("fssc_bill <=", value, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillLike(String value) {
            addCriterion("fssc_bill like", value, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillNotLike(String value) {
            addCriterion("fssc_bill not like", value, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillIn(List<String> values) {
            addCriterion("fssc_bill in", values, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillNotIn(List<String> values) {
            addCriterion("fssc_bill not in", values, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillBetween(String value1, String value2) {
            addCriterion("fssc_bill between", value1, value2, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFsscBillNotBetween(String value1, String value2) {
            addCriterion("fssc_bill not between", value1, value2, "fsscBill");
            return (Criteria) this;
        }

        public Criteria andFinancialNumIsNull() {
            addCriterion("financial_num is null");
            return (Criteria) this;
        }

        public Criteria andFinancialNumIsNotNull() {
            addCriterion("financial_num is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialNumEqualTo(String value) {
            addCriterion("financial_num =", value, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumNotEqualTo(String value) {
            addCriterion("financial_num <>", value, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumGreaterThan(String value) {
            addCriterion("financial_num >", value, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumGreaterThanOrEqualTo(String value) {
            addCriterion("financial_num >=", value, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumLessThan(String value) {
            addCriterion("financial_num <", value, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumLessThanOrEqualTo(String value) {
            addCriterion("financial_num <=", value, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumLike(String value) {
            addCriterion("financial_num like", value, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumNotLike(String value) {
            addCriterion("financial_num not like", value, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumIn(List<String> values) {
            addCriterion("financial_num in", values, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumNotIn(List<String> values) {
            addCriterion("financial_num not in", values, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumBetween(String value1, String value2) {
            addCriterion("financial_num between", value1, value2, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialNumNotBetween(String value1, String value2) {
            addCriterion("financial_num not between", value1, value2, "financialNum");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyIsNull() {
            addCriterion("financial_money is null");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyIsNotNull() {
            addCriterion("financial_money is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyEqualTo(String value) {
            addCriterion("financial_money =", value, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyNotEqualTo(String value) {
            addCriterion("financial_money <>", value, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyGreaterThan(String value) {
            addCriterion("financial_money >", value, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("financial_money >=", value, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyLessThan(String value) {
            addCriterion("financial_money <", value, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyLessThanOrEqualTo(String value) {
            addCriterion("financial_money <=", value, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyLike(String value) {
            addCriterion("financial_money like", value, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyNotLike(String value) {
            addCriterion("financial_money not like", value, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyIn(List<String> values) {
            addCriterion("financial_money in", values, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyNotIn(List<String> values) {
            addCriterion("financial_money not in", values, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyBetween(String value1, String value2) {
            addCriterion("financial_money between", value1, value2, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialMoneyNotBetween(String value1, String value2) {
            addCriterion("financial_money not between", value1, value2, "financialMoney");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusIsNull() {
            addCriterion("financial_status is null");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusIsNotNull() {
            addCriterion("financial_status is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusEqualTo(String value) {
            addCriterion("financial_status =", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotEqualTo(String value) {
            addCriterion("financial_status <>", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusGreaterThan(String value) {
            addCriterion("financial_status >", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusGreaterThanOrEqualTo(String value) {
            addCriterion("financial_status >=", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusLessThan(String value) {
            addCriterion("financial_status <", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusLessThanOrEqualTo(String value) {
            addCriterion("financial_status <=", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusLike(String value) {
            addCriterion("financial_status like", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotLike(String value) {
            addCriterion("financial_status not like", value, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusIn(List<String> values) {
            addCriterion("financial_status in", values, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotIn(List<String> values) {
            addCriterion("financial_status not in", values, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusBetween(String value1, String value2) {
            addCriterion("financial_status between", value1, value2, "financialStatus");
            return (Criteria) this;
        }

        public Criteria andFinancialStatusNotBetween(String value1, String value2) {
            addCriterion("financial_status not between", value1, value2, "financialStatus");
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