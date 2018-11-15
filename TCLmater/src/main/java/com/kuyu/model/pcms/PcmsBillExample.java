package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsBillExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsBillExample() {
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

        public Criteria andBlidIsNull() {
            addCriterion("blid is null");
            return (Criteria) this;
        }

        public Criteria andBlidIsNotNull() {
            addCriterion("blid is not null");
            return (Criteria) this;
        }

        public Criteria andBlidEqualTo(Integer value) {
            addCriterion("blid =", value, "blid");
            return (Criteria) this;
        }

        public Criteria andBlidNotEqualTo(Integer value) {
            addCriterion("blid <>", value, "blid");
            return (Criteria) this;
        }

        public Criteria andBlidGreaterThan(Integer value) {
            addCriterion("blid >", value, "blid");
            return (Criteria) this;
        }

        public Criteria andBlidGreaterThanOrEqualTo(Integer value) {
            addCriterion("blid >=", value, "blid");
            return (Criteria) this;
        }

        public Criteria andBlidLessThan(Integer value) {
            addCriterion("blid <", value, "blid");
            return (Criteria) this;
        }

        public Criteria andBlidLessThanOrEqualTo(Integer value) {
            addCriterion("blid <=", value, "blid");
            return (Criteria) this;
        }

        public Criteria andBlidIn(List<Integer> values) {
            addCriterion("blid in", values, "blid");
            return (Criteria) this;
        }

        public Criteria andBlidNotIn(List<Integer> values) {
            addCriterion("blid not in", values, "blid");
            return (Criteria) this;
        }

        public Criteria andBlidBetween(Integer value1, Integer value2) {
            addCriterion("blid between", value1, value2, "blid");
            return (Criteria) this;
        }

        public Criteria andBlidNotBetween(Integer value1, Integer value2) {
            addCriterion("blid not between", value1, value2, "blid");
            return (Criteria) this;
        }

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(Integer value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(Integer value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(Integer value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(Integer value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(Integer value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(Integer value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<Integer> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<Integer> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(Integer value1, Integer value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(Integer value1, Integer value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andItidIsNull() {
            addCriterion("itid is null");
            return (Criteria) this;
        }

        public Criteria andItidIsNotNull() {
            addCriterion("itid is not null");
            return (Criteria) this;
        }

        public Criteria andItidEqualTo(String value) {
            addCriterion("itid =", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidNotEqualTo(String value) {
            addCriterion("itid <>", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidGreaterThan(String value) {
            addCriterion("itid >", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidGreaterThanOrEqualTo(String value) {
            addCriterion("itid >=", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidLessThan(String value) {
            addCriterion("itid <", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidLessThanOrEqualTo(String value) {
            addCriterion("itid <=", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidLike(String value) {
            addCriterion("itid like", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidNotLike(String value) {
            addCriterion("itid not like", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidIn(List<String> values) {
            addCriterion("itid in", values, "itid");
            return (Criteria) this;
        }

        public Criteria andItidNotIn(List<String> values) {
            addCriterion("itid not in", values, "itid");
            return (Criteria) this;
        }

        public Criteria andItidBetween(String value1, String value2) {
            addCriterion("itid between", value1, value2, "itid");
            return (Criteria) this;
        }

        public Criteria andItidNotBetween(String value1, String value2) {
            addCriterion("itid not between", value1, value2, "itid");
            return (Criteria) this;
        }

        public Criteria andSpidIsNull() {
            addCriterion("spid is null");
            return (Criteria) this;
        }

        public Criteria andSpidIsNotNull() {
            addCriterion("spid is not null");
            return (Criteria) this;
        }

        public Criteria andSpidEqualTo(String value) {
            addCriterion("spid =", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidNotEqualTo(String value) {
            addCriterion("spid <>", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidGreaterThan(String value) {
            addCriterion("spid >", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidGreaterThanOrEqualTo(String value) {
            addCriterion("spid >=", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidLessThan(String value) {
            addCriterion("spid <", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidLessThanOrEqualTo(String value) {
            addCriterion("spid <=", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidLike(String value) {
            addCriterion("spid like", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidNotLike(String value) {
            addCriterion("spid not like", value, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidIn(List<String> values) {
            addCriterion("spid in", values, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidNotIn(List<String> values) {
            addCriterion("spid not in", values, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidBetween(String value1, String value2) {
            addCriterion("spid between", value1, value2, "spid");
            return (Criteria) this;
        }

        public Criteria andSpidNotBetween(String value1, String value2) {
            addCriterion("spid not between", value1, value2, "spid");
            return (Criteria) this;
        }

        public Criteria andMridIsNull() {
            addCriterion("mrid is null");
            return (Criteria) this;
        }

        public Criteria andMridIsNotNull() {
            addCriterion("mrid is not null");
            return (Criteria) this;
        }

        public Criteria andMridEqualTo(Integer value) {
            addCriterion("mrid =", value, "mrid");
            return (Criteria) this;
        }

        public Criteria andMridNotEqualTo(Integer value) {
            addCriterion("mrid <>", value, "mrid");
            return (Criteria) this;
        }

        public Criteria andMridGreaterThan(Integer value) {
            addCriterion("mrid >", value, "mrid");
            return (Criteria) this;
        }

        public Criteria andMridGreaterThanOrEqualTo(Integer value) {
            addCriterion("mrid >=", value, "mrid");
            return (Criteria) this;
        }

        public Criteria andMridLessThan(Integer value) {
            addCriterion("mrid <", value, "mrid");
            return (Criteria) this;
        }

        public Criteria andMridLessThanOrEqualTo(Integer value) {
            addCriterion("mrid <=", value, "mrid");
            return (Criteria) this;
        }

        public Criteria andMridIn(List<Integer> values) {
            addCriterion("mrid in", values, "mrid");
            return (Criteria) this;
        }

        public Criteria andMridNotIn(List<Integer> values) {
            addCriterion("mrid not in", values, "mrid");
            return (Criteria) this;
        }

        public Criteria andMridBetween(Integer value1, Integer value2) {
            addCriterion("mrid between", value1, value2, "mrid");
            return (Criteria) this;
        }

        public Criteria andMridNotBetween(Integer value1, Integer value2) {
            addCriterion("mrid not between", value1, value2, "mrid");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(Double value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(Double value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(Double value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(Double value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(Double value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<Double> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<Double> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(Double value1, Double value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(Double value1, Double value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andStatementDateIsNull() {
            addCriterion("statement_date is null");
            return (Criteria) this;
        }

        public Criteria andStatementDateIsNotNull() {
            addCriterion("statement_date is not null");
            return (Criteria) this;
        }

        public Criteria andStatementDateEqualTo(Integer value) {
            addCriterion("statement_date =", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateNotEqualTo(Integer value) {
            addCriterion("statement_date <>", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateGreaterThan(Integer value) {
            addCriterion("statement_date >", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateGreaterThanOrEqualTo(Integer value) {
            addCriterion("statement_date >=", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateLessThan(Integer value) {
            addCriterion("statement_date <", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateLessThanOrEqualTo(Integer value) {
            addCriterion("statement_date <=", value, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateIn(List<Integer> values) {
            addCriterion("statement_date in", values, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateNotIn(List<Integer> values) {
            addCriterion("statement_date not in", values, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateBetween(Integer value1, Integer value2) {
            addCriterion("statement_date between", value1, value2, "statementDate");
            return (Criteria) this;
        }

        public Criteria andStatementDateNotBetween(Integer value1, Integer value2) {
            addCriterion("statement_date not between", value1, value2, "statementDate");
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