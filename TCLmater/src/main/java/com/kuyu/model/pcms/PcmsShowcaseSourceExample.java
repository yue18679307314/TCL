package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsShowcaseSourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsShowcaseSourceExample() {
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

        public Criteria andScidIsNull() {
            addCriterion("scid is null");
            return (Criteria) this;
        }

        public Criteria andScidIsNotNull() {
            addCriterion("scid is not null");
            return (Criteria) this;
        }

        public Criteria andScidEqualTo(Integer value) {
            addCriterion("scid =", value, "scid");
            return (Criteria) this;
        }

        public Criteria andScidNotEqualTo(Integer value) {
            addCriterion("scid <>", value, "scid");
            return (Criteria) this;
        }

        public Criteria andScidGreaterThan(Integer value) {
            addCriterion("scid >", value, "scid");
            return (Criteria) this;
        }

        public Criteria andScidGreaterThanOrEqualTo(Integer value) {
            addCriterion("scid >=", value, "scid");
            return (Criteria) this;
        }

        public Criteria andScidLessThan(Integer value) {
            addCriterion("scid <", value, "scid");
            return (Criteria) this;
        }

        public Criteria andScidLessThanOrEqualTo(Integer value) {
            addCriterion("scid <=", value, "scid");
            return (Criteria) this;
        }

        public Criteria andScidIn(List<Integer> values) {
            addCriterion("scid in", values, "scid");
            return (Criteria) this;
        }

        public Criteria andScidNotIn(List<Integer> values) {
            addCriterion("scid not in", values, "scid");
            return (Criteria) this;
        }

        public Criteria andScidBetween(Integer value1, Integer value2) {
            addCriterion("scid between", value1, value2, "scid");
            return (Criteria) this;
        }

        public Criteria andScidNotBetween(Integer value1, Integer value2) {
            addCriterion("scid not between", value1, value2, "scid");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNull() {
            addCriterion("detail_id is null");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNotNull() {
            addCriterion("detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andDetailIdEqualTo(String value) {
            addCriterion("detail_id =", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotEqualTo(String value) {
            addCriterion("detail_id <>", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThan(String value) {
            addCriterion("detail_id >", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThanOrEqualTo(String value) {
            addCriterion("detail_id >=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThan(String value) {
            addCriterion("detail_id <", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThanOrEqualTo(String value) {
            addCriterion("detail_id <=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLike(String value) {
            addCriterion("detail_id like", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotLike(String value) {
            addCriterion("detail_id not like", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIn(List<String> values) {
            addCriterion("detail_id in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotIn(List<String> values) {
            addCriterion("detail_id not in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdBetween(String value1, String value2) {
            addCriterion("detail_id between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotBetween(String value1, String value2) {
            addCriterion("detail_id not between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andResuestIdIsNull() {
            addCriterion("resuest_id is null");
            return (Criteria) this;
        }

        public Criteria andResuestIdIsNotNull() {
            addCriterion("resuest_id is not null");
            return (Criteria) this;
        }

        public Criteria andResuestIdEqualTo(String value) {
            addCriterion("resuest_id =", value, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdNotEqualTo(String value) {
            addCriterion("resuest_id <>", value, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdGreaterThan(String value) {
            addCriterion("resuest_id >", value, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdGreaterThanOrEqualTo(String value) {
            addCriterion("resuest_id >=", value, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdLessThan(String value) {
            addCriterion("resuest_id <", value, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdLessThanOrEqualTo(String value) {
            addCriterion("resuest_id <=", value, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdLike(String value) {
            addCriterion("resuest_id like", value, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdNotLike(String value) {
            addCriterion("resuest_id not like", value, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdIn(List<String> values) {
            addCriterion("resuest_id in", values, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdNotIn(List<String> values) {
            addCriterion("resuest_id not in", values, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdBetween(String value1, String value2) {
            addCriterion("resuest_id between", value1, value2, "resuestId");
            return (Criteria) this;
        }

        public Criteria andResuestIdNotBetween(String value1, String value2) {
            addCriterion("resuest_id not between", value1, value2, "resuestId");
            return (Criteria) this;
        }

        public Criteria andVendorIdIsNull() {
            addCriterion("vendor_id is null");
            return (Criteria) this;
        }

        public Criteria andVendorIdIsNotNull() {
            addCriterion("vendor_id is not null");
            return (Criteria) this;
        }

        public Criteria andVendorIdEqualTo(String value) {
            addCriterion("vendor_id =", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotEqualTo(String value) {
            addCriterion("vendor_id <>", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdGreaterThan(String value) {
            addCriterion("vendor_id >", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdGreaterThanOrEqualTo(String value) {
            addCriterion("vendor_id >=", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdLessThan(String value) {
            addCriterion("vendor_id <", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdLessThanOrEqualTo(String value) {
            addCriterion("vendor_id <=", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdLike(String value) {
            addCriterion("vendor_id like", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotLike(String value) {
            addCriterion("vendor_id not like", value, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdIn(List<String> values) {
            addCriterion("vendor_id in", values, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotIn(List<String> values) {
            addCriterion("vendor_id not in", values, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdBetween(String value1, String value2) {
            addCriterion("vendor_id between", value1, value2, "vendorId");
            return (Criteria) this;
        }

        public Criteria andVendorIdNotBetween(String value1, String value2) {
            addCriterion("vendor_id not between", value1, value2, "vendorId");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskIsNull() {
            addCriterion("CHILDREN1_TASK is null");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskIsNotNull() {
            addCriterion("CHILDREN1_TASK is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskEqualTo(String value) {
            addCriterion("CHILDREN1_TASK =", value, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskNotEqualTo(String value) {
            addCriterion("CHILDREN1_TASK <>", value, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskGreaterThan(String value) {
            addCriterion("CHILDREN1_TASK >", value, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_TASK >=", value, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskLessThan(String value) {
            addCriterion("CHILDREN1_TASK <", value, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_TASK <=", value, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskLike(String value) {
            addCriterion("CHILDREN1_TASK like", value, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskNotLike(String value) {
            addCriterion("CHILDREN1_TASK not like", value, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskIn(List<String> values) {
            addCriterion("CHILDREN1_TASK in", values, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskNotIn(List<String> values) {
            addCriterion("CHILDREN1_TASK not in", values, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskBetween(String value1, String value2) {
            addCriterion("CHILDREN1_TASK between", value1, value2, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1TaskNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_TASK not between", value1, value2, "children1Task");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeIsNull() {
            addCriterion("CHILDREN1_LAST_BUILDTIME is null");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeIsNotNull() {
            addCriterion("CHILDREN1_LAST_BUILDTIME is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeEqualTo(String value) {
            addCriterion("CHILDREN1_LAST_BUILDTIME =", value, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeNotEqualTo(String value) {
            addCriterion("CHILDREN1_LAST_BUILDTIME <>", value, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeGreaterThan(String value) {
            addCriterion("CHILDREN1_LAST_BUILDTIME >", value, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_LAST_BUILDTIME >=", value, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeLessThan(String value) {
            addCriterion("CHILDREN1_LAST_BUILDTIME <", value, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_LAST_BUILDTIME <=", value, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeLike(String value) {
            addCriterion("CHILDREN1_LAST_BUILDTIME like", value, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeNotLike(String value) {
            addCriterion("CHILDREN1_LAST_BUILDTIME not like", value, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeIn(List<String> values) {
            addCriterion("CHILDREN1_LAST_BUILDTIME in", values, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeNotIn(List<String> values) {
            addCriterion("CHILDREN1_LAST_BUILDTIME not in", values, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeBetween(String value1, String value2) {
            addCriterion("CHILDREN1_LAST_BUILDTIME between", value1, value2, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1LastBuildtimeNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_LAST_BUILDTIME not between", value1, value2, "children1LastBuildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeIsNull() {
            addCriterion("CHILDREN1_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeIsNotNull() {
            addCriterion("CHILDREN1_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeEqualTo(String value) {
            addCriterion("CHILDREN1_TYPE =", value, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeNotEqualTo(String value) {
            addCriterion("CHILDREN1_TYPE <>", value, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeGreaterThan(String value) {
            addCriterion("CHILDREN1_TYPE >", value, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_TYPE >=", value, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeLessThan(String value) {
            addCriterion("CHILDREN1_TYPE <", value, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_TYPE <=", value, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeLike(String value) {
            addCriterion("CHILDREN1_TYPE like", value, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeNotLike(String value) {
            addCriterion("CHILDREN1_TYPE not like", value, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeIn(List<String> values) {
            addCriterion("CHILDREN1_TYPE in", values, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeNotIn(List<String> values) {
            addCriterion("CHILDREN1_TYPE not in", values, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeBetween(String value1, String value2) {
            addCriterion("CHILDREN1_TYPE between", value1, value2, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1TypeNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_TYPE not between", value1, value2, "children1Type");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonIsNull() {
            addCriterion("CHILDREN1_REASON is null");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonIsNotNull() {
            addCriterion("CHILDREN1_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonEqualTo(String value) {
            addCriterion("CHILDREN1_REASON =", value, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonNotEqualTo(String value) {
            addCriterion("CHILDREN1_REASON <>", value, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonGreaterThan(String value) {
            addCriterion("CHILDREN1_REASON >", value, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_REASON >=", value, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonLessThan(String value) {
            addCriterion("CHILDREN1_REASON <", value, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_REASON <=", value, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonLike(String value) {
            addCriterion("CHILDREN1_REASON like", value, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonNotLike(String value) {
            addCriterion("CHILDREN1_REASON not like", value, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonIn(List<String> values) {
            addCriterion("CHILDREN1_REASON in", values, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonNotIn(List<String> values) {
            addCriterion("CHILDREN1_REASON not in", values, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonBetween(String value1, String value2) {
            addCriterion("CHILDREN1_REASON between", value1, value2, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1ReasonNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_REASON not between", value1, value2, "children1Reason");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaIsNull() {
            addCriterion("CHILDREN1_AREA is null");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaIsNotNull() {
            addCriterion("CHILDREN1_AREA is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaEqualTo(String value) {
            addCriterion("CHILDREN1_AREA =", value, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaNotEqualTo(String value) {
            addCriterion("CHILDREN1_AREA <>", value, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaGreaterThan(String value) {
            addCriterion("CHILDREN1_AREA >", value, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_AREA >=", value, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaLessThan(String value) {
            addCriterion("CHILDREN1_AREA <", value, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_AREA <=", value, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaLike(String value) {
            addCriterion("CHILDREN1_AREA like", value, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaNotLike(String value) {
            addCriterion("CHILDREN1_AREA not like", value, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaIn(List<String> values) {
            addCriterion("CHILDREN1_AREA in", values, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaNotIn(List<String> values) {
            addCriterion("CHILDREN1_AREA not in", values, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaBetween(String value1, String value2) {
            addCriterion("CHILDREN1_AREA between", value1, value2, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1AreaNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_AREA not between", value1, value2, "children1Area");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearIsNull() {
            addCriterion("CHILDREN1_LINEAR is null");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearIsNotNull() {
            addCriterion("CHILDREN1_LINEAR is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearEqualTo(String value) {
            addCriterion("CHILDREN1_LINEAR =", value, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearNotEqualTo(String value) {
            addCriterion("CHILDREN1_LINEAR <>", value, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearGreaterThan(String value) {
            addCriterion("CHILDREN1_LINEAR >", value, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_LINEAR >=", value, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearLessThan(String value) {
            addCriterion("CHILDREN1_LINEAR <", value, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_LINEAR <=", value, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearLike(String value) {
            addCriterion("CHILDREN1_LINEAR like", value, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearNotLike(String value) {
            addCriterion("CHILDREN1_LINEAR not like", value, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearIn(List<String> values) {
            addCriterion("CHILDREN1_LINEAR in", values, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearNotIn(List<String> values) {
            addCriterion("CHILDREN1_LINEAR not in", values, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearBetween(String value1, String value2) {
            addCriterion("CHILDREN1_LINEAR between", value1, value2, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1LinearNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_LINEAR not between", value1, value2, "children1Linear");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeIsNull() {
            addCriterion("CHILDREN1_BUILDTIME is null");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeIsNotNull() {
            addCriterion("CHILDREN1_BUILDTIME is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeEqualTo(String value) {
            addCriterion("CHILDREN1_BUILDTIME =", value, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeNotEqualTo(String value) {
            addCriterion("CHILDREN1_BUILDTIME <>", value, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeGreaterThan(String value) {
            addCriterion("CHILDREN1_BUILDTIME >", value, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_BUILDTIME >=", value, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeLessThan(String value) {
            addCriterion("CHILDREN1_BUILDTIME <", value, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_BUILDTIME <=", value, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeLike(String value) {
            addCriterion("CHILDREN1_BUILDTIME like", value, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeNotLike(String value) {
            addCriterion("CHILDREN1_BUILDTIME not like", value, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeIn(List<String> values) {
            addCriterion("CHILDREN1_BUILDTIME in", values, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeNotIn(List<String> values) {
            addCriterion("CHILDREN1_BUILDTIME not in", values, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeBetween(String value1, String value2) {
            addCriterion("CHILDREN1_BUILDTIME between", value1, value2, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1BuildtimeNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_BUILDTIME not between", value1, value2, "children1Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorIsNull() {
            addCriterion("CHILDREN1_VENDOR is null");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorIsNotNull() {
            addCriterion("CHILDREN1_VENDOR is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorEqualTo(String value) {
            addCriterion("CHILDREN1_VENDOR =", value, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorNotEqualTo(String value) {
            addCriterion("CHILDREN1_VENDOR <>", value, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorGreaterThan(String value) {
            addCriterion("CHILDREN1_VENDOR >", value, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_VENDOR >=", value, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorLessThan(String value) {
            addCriterion("CHILDREN1_VENDOR <", value, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_VENDOR <=", value, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorLike(String value) {
            addCriterion("CHILDREN1_VENDOR like", value, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorNotLike(String value) {
            addCriterion("CHILDREN1_VENDOR not like", value, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorIn(List<String> values) {
            addCriterion("CHILDREN1_VENDOR in", values, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorNotIn(List<String> values) {
            addCriterion("CHILDREN1_VENDOR not in", values, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorBetween(String value1, String value2) {
            addCriterion("CHILDREN1_VENDOR between", value1, value2, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1VendorNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_VENDOR not between", value1, value2, "children1Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerIsNull() {
            addCriterion("CHILDREN1_INNER is null");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerIsNotNull() {
            addCriterion("CHILDREN1_INNER is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerEqualTo(String value) {
            addCriterion("CHILDREN1_INNER =", value, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerNotEqualTo(String value) {
            addCriterion("CHILDREN1_INNER <>", value, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerGreaterThan(String value) {
            addCriterion("CHILDREN1_INNER >", value, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_INNER >=", value, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerLessThan(String value) {
            addCriterion("CHILDREN1_INNER <", value, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_INNER <=", value, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerLike(String value) {
            addCriterion("CHILDREN1_INNER like", value, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerNotLike(String value) {
            addCriterion("CHILDREN1_INNER not like", value, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerIn(List<String> values) {
            addCriterion("CHILDREN1_INNER in", values, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerNotIn(List<String> values) {
            addCriterion("CHILDREN1_INNER not in", values, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerBetween(String value1, String value2) {
            addCriterion("CHILDREN1_INNER between", value1, value2, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1InnerNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_INNER not between", value1, value2, "children1Inner");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterIsNull() {
            addCriterion("CHILDREN1_OUTER is null");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterIsNotNull() {
            addCriterion("CHILDREN1_OUTER is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterEqualTo(String value) {
            addCriterion("CHILDREN1_OUTER =", value, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterNotEqualTo(String value) {
            addCriterion("CHILDREN1_OUTER <>", value, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterGreaterThan(String value) {
            addCriterion("CHILDREN1_OUTER >", value, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_OUTER >=", value, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterLessThan(String value) {
            addCriterion("CHILDREN1_OUTER <", value, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_OUTER <=", value, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterLike(String value) {
            addCriterion("CHILDREN1_OUTER like", value, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterNotLike(String value) {
            addCriterion("CHILDREN1_OUTER not like", value, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterIn(List<String> values) {
            addCriterion("CHILDREN1_OUTER in", values, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterNotIn(List<String> values) {
            addCriterion("CHILDREN1_OUTER not in", values, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterBetween(String value1, String value2) {
            addCriterion("CHILDREN1_OUTER between", value1, value2, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1OuterNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_OUTER not between", value1, value2, "children1Outer");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountIsNull() {
            addCriterion("CHILDREN1_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountIsNotNull() {
            addCriterion("CHILDREN1_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountEqualTo(String value) {
            addCriterion("CHILDREN1_AMOUNT =", value, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountNotEqualTo(String value) {
            addCriterion("CHILDREN1_AMOUNT <>", value, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountGreaterThan(String value) {
            addCriterion("CHILDREN1_AMOUNT >", value, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_AMOUNT >=", value, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountLessThan(String value) {
            addCriterion("CHILDREN1_AMOUNT <", value, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN1_AMOUNT <=", value, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountLike(String value) {
            addCriterion("CHILDREN1_AMOUNT like", value, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountNotLike(String value) {
            addCriterion("CHILDREN1_AMOUNT not like", value, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountIn(List<String> values) {
            addCriterion("CHILDREN1_AMOUNT in", values, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountNotIn(List<String> values) {
            addCriterion("CHILDREN1_AMOUNT not in", values, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountBetween(String value1, String value2) {
            addCriterion("CHILDREN1_AMOUNT between", value1, value2, "children1Amount");
            return (Criteria) this;
        }

        public Criteria andChildren1AmountNotBetween(String value1, String value2) {
            addCriterion("CHILDREN1_AMOUNT not between", value1, value2, "children1Amount");
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