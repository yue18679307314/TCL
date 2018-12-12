package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.List;

public class PcmsOutdoorsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsOutdoorsExample() {
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

        public Criteria andDetailIdIsNull() {
            addCriterion("DETAIL_ID is null");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNotNull() {
            addCriterion("DETAIL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDetailIdEqualTo(String value) {
            addCriterion("DETAIL_ID =", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotEqualTo(String value) {
            addCriterion("DETAIL_ID <>", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThan(String value) {
            addCriterion("DETAIL_ID >", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_ID >=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThan(String value) {
            addCriterion("DETAIL_ID <", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_ID <=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLike(String value) {
            addCriterion("DETAIL_ID like", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotLike(String value) {
            addCriterion("DETAIL_ID not like", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIn(List<String> values) {
            addCriterion("DETAIL_ID in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotIn(List<String> values) {
            addCriterion("DETAIL_ID not in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdBetween(String value1, String value2) {
            addCriterion("DETAIL_ID between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotBetween(String value1, String value2) {
            addCriterion("DETAIL_ID not between", value1, value2, "detailId");
            return (Criteria) this;
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

        public Criteria andChildren2TaskIsNull() {
            addCriterion("CHILDREN2_TASK is null");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskIsNotNull() {
            addCriterion("CHILDREN2_TASK is not null");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskEqualTo(String value) {
            addCriterion("CHILDREN2_TASK =", value, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskNotEqualTo(String value) {
            addCriterion("CHILDREN2_TASK <>", value, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskGreaterThan(String value) {
            addCriterion("CHILDREN2_TASK >", value, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_TASK >=", value, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskLessThan(String value) {
            addCriterion("CHILDREN2_TASK <", value, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_TASK <=", value, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskLike(String value) {
            addCriterion("CHILDREN2_TASK like", value, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskNotLike(String value) {
            addCriterion("CHILDREN2_TASK not like", value, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskIn(List<String> values) {
            addCriterion("CHILDREN2_TASK in", values, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskNotIn(List<String> values) {
            addCriterion("CHILDREN2_TASK not in", values, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskBetween(String value1, String value2) {
            addCriterion("CHILDREN2_TASK between", value1, value2, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2TaskNotBetween(String value1, String value2) {
            addCriterion("CHILDREN2_TASK not between", value1, value2, "children2Task");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeIsNull() {
            addCriterion("CHILDREN2_LAST_BUILDTIME is null");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeIsNotNull() {
            addCriterion("CHILDREN2_LAST_BUILDTIME is not null");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeEqualTo(String value) {
            addCriterion("CHILDREN2_LAST_BUILDTIME =", value, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeNotEqualTo(String value) {
            addCriterion("CHILDREN2_LAST_BUILDTIME <>", value, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeGreaterThan(String value) {
            addCriterion("CHILDREN2_LAST_BUILDTIME >", value, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_LAST_BUILDTIME >=", value, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeLessThan(String value) {
            addCriterion("CHILDREN2_LAST_BUILDTIME <", value, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_LAST_BUILDTIME <=", value, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeLike(String value) {
            addCriterion("CHILDREN2_LAST_BUILDTIME like", value, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeNotLike(String value) {
            addCriterion("CHILDREN2_LAST_BUILDTIME not like", value, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeIn(List<String> values) {
            addCriterion("CHILDREN2_LAST_BUILDTIME in", values, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeNotIn(List<String> values) {
            addCriterion("CHILDREN2_LAST_BUILDTIME not in", values, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeBetween(String value1, String value2) {
            addCriterion("CHILDREN2_LAST_BUILDTIME between", value1, value2, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2LastBuildtimeNotBetween(String value1, String value2) {
            addCriterion("CHILDREN2_LAST_BUILDTIME not between", value1, value2, "children2LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaIsNull() {
            addCriterion("CHILDREN2_AREA is null");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaIsNotNull() {
            addCriterion("CHILDREN2_AREA is not null");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaEqualTo(String value) {
            addCriterion("CHILDREN2_AREA =", value, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaNotEqualTo(String value) {
            addCriterion("CHILDREN2_AREA <>", value, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaGreaterThan(String value) {
            addCriterion("CHILDREN2_AREA >", value, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_AREA >=", value, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaLessThan(String value) {
            addCriterion("CHILDREN2_AREA <", value, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_AREA <=", value, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaLike(String value) {
            addCriterion("CHILDREN2_AREA like", value, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaNotLike(String value) {
            addCriterion("CHILDREN2_AREA not like", value, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaIn(List<String> values) {
            addCriterion("CHILDREN2_AREA in", values, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaNotIn(List<String> values) {
            addCriterion("CHILDREN2_AREA not in", values, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaBetween(String value1, String value2) {
            addCriterion("CHILDREN2_AREA between", value1, value2, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaNotBetween(String value1, String value2) {
            addCriterion("CHILDREN2_AREA not between", value1, value2, "children2Area");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailIsNull() {
            addCriterion("CHILDREN2_AREA_DETAIL is null");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailIsNotNull() {
            addCriterion("CHILDREN2_AREA_DETAIL is not null");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailEqualTo(String value) {
            addCriterion("CHILDREN2_AREA_DETAIL =", value, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailNotEqualTo(String value) {
            addCriterion("CHILDREN2_AREA_DETAIL <>", value, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailGreaterThan(String value) {
            addCriterion("CHILDREN2_AREA_DETAIL >", value, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_AREA_DETAIL >=", value, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailLessThan(String value) {
            addCriterion("CHILDREN2_AREA_DETAIL <", value, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_AREA_DETAIL <=", value, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailLike(String value) {
            addCriterion("CHILDREN2_AREA_DETAIL like", value, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailNotLike(String value) {
            addCriterion("CHILDREN2_AREA_DETAIL not like", value, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailIn(List<String> values) {
            addCriterion("CHILDREN2_AREA_DETAIL in", values, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailNotIn(List<String> values) {
            addCriterion("CHILDREN2_AREA_DETAIL not in", values, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailBetween(String value1, String value2) {
            addCriterion("CHILDREN2_AREA_DETAIL between", value1, value2, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2AreaDetailNotBetween(String value1, String value2) {
            addCriterion("CHILDREN2_AREA_DETAIL not between", value1, value2, "children2AreaDetail");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeIsNull() {
            addCriterion("CHILDREN2_BUILDTIME is null");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeIsNotNull() {
            addCriterion("CHILDREN2_BUILDTIME is not null");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeEqualTo(String value) {
            addCriterion("CHILDREN2_BUILDTIME =", value, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeNotEqualTo(String value) {
            addCriterion("CHILDREN2_BUILDTIME <>", value, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeGreaterThan(String value) {
            addCriterion("CHILDREN2_BUILDTIME >", value, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_BUILDTIME >=", value, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeLessThan(String value) {
            addCriterion("CHILDREN2_BUILDTIME <", value, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_BUILDTIME <=", value, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeLike(String value) {
            addCriterion("CHILDREN2_BUILDTIME like", value, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeNotLike(String value) {
            addCriterion("CHILDREN2_BUILDTIME not like", value, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeIn(List<String> values) {
            addCriterion("CHILDREN2_BUILDTIME in", values, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeNotIn(List<String> values) {
            addCriterion("CHILDREN2_BUILDTIME not in", values, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeBetween(String value1, String value2) {
            addCriterion("CHILDREN2_BUILDTIME between", value1, value2, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2BuildtimeNotBetween(String value1, String value2) {
            addCriterion("CHILDREN2_BUILDTIME not between", value1, value2, "children2Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorIsNull() {
            addCriterion("CHILDREN2_VENDOR is null");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorIsNotNull() {
            addCriterion("CHILDREN2_VENDOR is not null");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorEqualTo(String value) {
            addCriterion("CHILDREN2_VENDOR =", value, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorNotEqualTo(String value) {
            addCriterion("CHILDREN2_VENDOR <>", value, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorGreaterThan(String value) {
            addCriterion("CHILDREN2_VENDOR >", value, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_VENDOR >=", value, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorLessThan(String value) {
            addCriterion("CHILDREN2_VENDOR <", value, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_VENDOR <=", value, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorLike(String value) {
            addCriterion("CHILDREN2_VENDOR like", value, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorNotLike(String value) {
            addCriterion("CHILDREN2_VENDOR not like", value, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorIn(List<String> values) {
            addCriterion("CHILDREN2_VENDOR in", values, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorNotIn(List<String> values) {
            addCriterion("CHILDREN2_VENDOR not in", values, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorBetween(String value1, String value2) {
            addCriterion("CHILDREN2_VENDOR between", value1, value2, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2VendorNotBetween(String value1, String value2) {
            addCriterion("CHILDREN2_VENDOR not between", value1, value2, "children2Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountIsNull() {
            addCriterion("CHILDREN2_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountIsNotNull() {
            addCriterion("CHILDREN2_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountEqualTo(String value) {
            addCriterion("CHILDREN2_AMOUNT =", value, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountNotEqualTo(String value) {
            addCriterion("CHILDREN2_AMOUNT <>", value, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountGreaterThan(String value) {
            addCriterion("CHILDREN2_AMOUNT >", value, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_AMOUNT >=", value, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountLessThan(String value) {
            addCriterion("CHILDREN2_AMOUNT <", value, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN2_AMOUNT <=", value, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountLike(String value) {
            addCriterion("CHILDREN2_AMOUNT like", value, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountNotLike(String value) {
            addCriterion("CHILDREN2_AMOUNT not like", value, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountIn(List<String> values) {
            addCriterion("CHILDREN2_AMOUNT in", values, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountNotIn(List<String> values) {
            addCriterion("CHILDREN2_AMOUNT not in", values, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountBetween(String value1, String value2) {
            addCriterion("CHILDREN2_AMOUNT between", value1, value2, "children2Amount");
            return (Criteria) this;
        }

        public Criteria andChildren2AmountNotBetween(String value1, String value2) {
            addCriterion("CHILDREN2_AMOUNT not between", value1, value2, "children2Amount");
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