package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsOthertmSourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsOthertmSourceExample() {
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

        public Criteria andOtidIsNull() {
            addCriterion("otid is null");
            return (Criteria) this;
        }

        public Criteria andOtidIsNotNull() {
            addCriterion("otid is not null");
            return (Criteria) this;
        }

        public Criteria andOtidEqualTo(Integer value) {
            addCriterion("otid =", value, "otid");
            return (Criteria) this;
        }

        public Criteria andOtidNotEqualTo(Integer value) {
            addCriterion("otid <>", value, "otid");
            return (Criteria) this;
        }

        public Criteria andOtidGreaterThan(Integer value) {
            addCriterion("otid >", value, "otid");
            return (Criteria) this;
        }

        public Criteria andOtidGreaterThanOrEqualTo(Integer value) {
            addCriterion("otid >=", value, "otid");
            return (Criteria) this;
        }

        public Criteria andOtidLessThan(Integer value) {
            addCriterion("otid <", value, "otid");
            return (Criteria) this;
        }

        public Criteria andOtidLessThanOrEqualTo(Integer value) {
            addCriterion("otid <=", value, "otid");
            return (Criteria) this;
        }

        public Criteria andOtidIn(List<Integer> values) {
            addCriterion("otid in", values, "otid");
            return (Criteria) this;
        }

        public Criteria andOtidNotIn(List<Integer> values) {
            addCriterion("otid not in", values, "otid");
            return (Criteria) this;
        }

        public Criteria andOtidBetween(Integer value1, Integer value2) {
            addCriterion("otid between", value1, value2, "otid");
            return (Criteria) this;
        }

        public Criteria andOtidNotBetween(Integer value1, Integer value2) {
            addCriterion("otid not between", value1, value2, "otid");
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

        public Criteria andRequestIdIsNull() {
            addCriterion("request_id is null");
            return (Criteria) this;
        }

        public Criteria andRequestIdIsNotNull() {
            addCriterion("request_id is not null");
            return (Criteria) this;
        }

        public Criteria andRequestIdEqualTo(String value) {
            addCriterion("request_id =", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotEqualTo(String value) {
            addCriterion("request_id <>", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThan(String value) {
            addCriterion("request_id >", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdGreaterThanOrEqualTo(String value) {
            addCriterion("request_id >=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThan(String value) {
            addCriterion("request_id <", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLessThanOrEqualTo(String value) {
            addCriterion("request_id <=", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdLike(String value) {
            addCriterion("request_id like", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotLike(String value) {
            addCriterion("request_id not like", value, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdIn(List<String> values) {
            addCriterion("request_id in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotIn(List<String> values) {
            addCriterion("request_id not in", values, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdBetween(String value1, String value2) {
            addCriterion("request_id between", value1, value2, "requestId");
            return (Criteria) this;
        }

        public Criteria andRequestIdNotBetween(String value1, String value2) {
            addCriterion("request_id not between", value1, value2, "requestId");
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

        public Criteria andMrname3IsNull() {
            addCriterion("mrname3 is null");
            return (Criteria) this;
        }

        public Criteria andMrname3IsNotNull() {
            addCriterion("mrname3 is not null");
            return (Criteria) this;
        }

        public Criteria andMrname3EqualTo(String value) {
            addCriterion("mrname3 =", value, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3NotEqualTo(String value) {
            addCriterion("mrname3 <>", value, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3GreaterThan(String value) {
            addCriterion("mrname3 >", value, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3GreaterThanOrEqualTo(String value) {
            addCriterion("mrname3 >=", value, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3LessThan(String value) {
            addCriterion("mrname3 <", value, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3LessThanOrEqualTo(String value) {
            addCriterion("mrname3 <=", value, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3Like(String value) {
            addCriterion("mrname3 like", value, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3NotLike(String value) {
            addCriterion("mrname3 not like", value, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3In(List<String> values) {
            addCriterion("mrname3 in", values, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3NotIn(List<String> values) {
            addCriterion("mrname3 not in", values, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3Between(String value1, String value2) {
            addCriterion("mrname3 between", value1, value2, "mrname3");
            return (Criteria) this;
        }

        public Criteria andMrname3NotBetween(String value1, String value2) {
            addCriterion("mrname3 not between", value1, value2, "mrname3");
            return (Criteria) this;
        }

        public Criteria andCostIsNull() {
            addCriterion("cost is null");
            return (Criteria) this;
        }

        public Criteria andCostIsNotNull() {
            addCriterion("cost is not null");
            return (Criteria) this;
        }

        public Criteria andCostEqualTo(String value) {
            addCriterion("cost =", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotEqualTo(String value) {
            addCriterion("cost <>", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThan(String value) {
            addCriterion("cost >", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostGreaterThanOrEqualTo(String value) {
            addCriterion("cost >=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThan(String value) {
            addCriterion("cost <", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLessThanOrEqualTo(String value) {
            addCriterion("cost <=", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostLike(String value) {
            addCriterion("cost like", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotLike(String value) {
            addCriterion("cost not like", value, "cost");
            return (Criteria) this;
        }

        public Criteria andCostIn(List<String> values) {
            addCriterion("cost in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotIn(List<String> values) {
            addCriterion("cost not in", values, "cost");
            return (Criteria) this;
        }

        public Criteria andCostBetween(String value1, String value2) {
            addCriterion("cost between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andCostNotBetween(String value1, String value2) {
            addCriterion("cost not between", value1, value2, "cost");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceIsNull() {
            addCriterion("comparison_price is null");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceIsNotNull() {
            addCriterion("comparison_price is not null");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceEqualTo(String value) {
            addCriterion("comparison_price =", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceNotEqualTo(String value) {
            addCriterion("comparison_price <>", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceGreaterThan(String value) {
            addCriterion("comparison_price >", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceGreaterThanOrEqualTo(String value) {
            addCriterion("comparison_price >=", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceLessThan(String value) {
            addCriterion("comparison_price <", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceLessThanOrEqualTo(String value) {
            addCriterion("comparison_price <=", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceLike(String value) {
            addCriterion("comparison_price like", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceNotLike(String value) {
            addCriterion("comparison_price not like", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceIn(List<String> values) {
            addCriterion("comparison_price in", values, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceNotIn(List<String> values) {
            addCriterion("comparison_price not in", values, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceBetween(String value1, String value2) {
            addCriterion("comparison_price between", value1, value2, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceNotBetween(String value1, String value2) {
            addCriterion("comparison_price not between", value1, value2, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andSpecifications3IsNull() {
            addCriterion("specifications3 is null");
            return (Criteria) this;
        }

        public Criteria andSpecifications3IsNotNull() {
            addCriterion("specifications3 is not null");
            return (Criteria) this;
        }

        public Criteria andSpecifications3EqualTo(String value) {
            addCriterion("specifications3 =", value, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3NotEqualTo(String value) {
            addCriterion("specifications3 <>", value, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3GreaterThan(String value) {
            addCriterion("specifications3 >", value, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3GreaterThanOrEqualTo(String value) {
            addCriterion("specifications3 >=", value, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3LessThan(String value) {
            addCriterion("specifications3 <", value, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3LessThanOrEqualTo(String value) {
            addCriterion("specifications3 <=", value, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3Like(String value) {
            addCriterion("specifications3 like", value, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3NotLike(String value) {
            addCriterion("specifications3 not like", value, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3In(List<String> values) {
            addCriterion("specifications3 in", values, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3NotIn(List<String> values) {
            addCriterion("specifications3 not in", values, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3Between(String value1, String value2) {
            addCriterion("specifications3 between", value1, value2, "specifications3");
            return (Criteria) this;
        }

        public Criteria andSpecifications3NotBetween(String value1, String value2) {
            addCriterion("specifications3 not between", value1, value2, "specifications3");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailIsNull() {
            addCriterion("CHILDREN3_DETAIL is null");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailIsNotNull() {
            addCriterion("CHILDREN3_DETAIL is not null");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailEqualTo(String value) {
            addCriterion("CHILDREN3_DETAIL =", value, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailNotEqualTo(String value) {
            addCriterion("CHILDREN3_DETAIL <>", value, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailGreaterThan(String value) {
            addCriterion("CHILDREN3_DETAIL >", value, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_DETAIL >=", value, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailLessThan(String value) {
            addCriterion("CHILDREN3_DETAIL <", value, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_DETAIL <=", value, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailLike(String value) {
            addCriterion("CHILDREN3_DETAIL like", value, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailNotLike(String value) {
            addCriterion("CHILDREN3_DETAIL not like", value, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailIn(List<String> values) {
            addCriterion("CHILDREN3_DETAIL in", values, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailNotIn(List<String> values) {
            addCriterion("CHILDREN3_DETAIL not in", values, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailBetween(String value1, String value2) {
            addCriterion("CHILDREN3_DETAIL between", value1, value2, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3DetailNotBetween(String value1, String value2) {
            addCriterion("CHILDREN3_DETAIL not between", value1, value2, "children3Detail");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorIsNull() {
            addCriterion("CHILDREN3_VENDOR is null");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorIsNotNull() {
            addCriterion("CHILDREN3_VENDOR is not null");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorEqualTo(String value) {
            addCriterion("CHILDREN3_VENDOR =", value, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorNotEqualTo(String value) {
            addCriterion("CHILDREN3_VENDOR <>", value, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorGreaterThan(String value) {
            addCriterion("CHILDREN3_VENDOR >", value, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_VENDOR >=", value, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorLessThan(String value) {
            addCriterion("CHILDREN3_VENDOR <", value, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_VENDOR <=", value, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorLike(String value) {
            addCriterion("CHILDREN3_VENDOR like", value, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorNotLike(String value) {
            addCriterion("CHILDREN3_VENDOR not like", value, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorIn(List<String> values) {
            addCriterion("CHILDREN3_VENDOR in", values, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorNotIn(List<String> values) {
            addCriterion("CHILDREN3_VENDOR not in", values, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorBetween(String value1, String value2) {
            addCriterion("CHILDREN3_VENDOR between", value1, value2, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3VendorNotBetween(String value1, String value2) {
            addCriterion("CHILDREN3_VENDOR not between", value1, value2, "children3Vendor");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeIsNull() {
            addCriterion("CHILDREN3_BUILDTIME is null");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeIsNotNull() {
            addCriterion("CHILDREN3_BUILDTIME is not null");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeEqualTo(String value) {
            addCriterion("CHILDREN3_BUILDTIME =", value, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeNotEqualTo(String value) {
            addCriterion("CHILDREN3_BUILDTIME <>", value, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeGreaterThan(String value) {
            addCriterion("CHILDREN3_BUILDTIME >", value, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_BUILDTIME >=", value, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeLessThan(String value) {
            addCriterion("CHILDREN3_BUILDTIME <", value, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_BUILDTIME <=", value, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeLike(String value) {
            addCriterion("CHILDREN3_BUILDTIME like", value, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeNotLike(String value) {
            addCriterion("CHILDREN3_BUILDTIME not like", value, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeIn(List<String> values) {
            addCriterion("CHILDREN3_BUILDTIME in", values, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeNotIn(List<String> values) {
            addCriterion("CHILDREN3_BUILDTIME not in", values, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeBetween(String value1, String value2) {
            addCriterion("CHILDREN3_BUILDTIME between", value1, value2, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3BuildtimeNotBetween(String value1, String value2) {
            addCriterion("CHILDREN3_BUILDTIME not between", value1, value2, "children3Buildtime");
            return (Criteria) this;
        }

        public Criteria andChildren3CountIsNull() {
            addCriterion("CHILDREN3_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andChildren3CountIsNotNull() {
            addCriterion("CHILDREN3_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andChildren3CountEqualTo(String value) {
            addCriterion("CHILDREN3_COUNT =", value, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountNotEqualTo(String value) {
            addCriterion("CHILDREN3_COUNT <>", value, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountGreaterThan(String value) {
            addCriterion("CHILDREN3_COUNT >", value, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_COUNT >=", value, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountLessThan(String value) {
            addCriterion("CHILDREN3_COUNT <", value, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_COUNT <=", value, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountLike(String value) {
            addCriterion("CHILDREN3_COUNT like", value, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountNotLike(String value) {
            addCriterion("CHILDREN3_COUNT not like", value, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountIn(List<String> values) {
            addCriterion("CHILDREN3_COUNT in", values, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountNotIn(List<String> values) {
            addCriterion("CHILDREN3_COUNT not in", values, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountBetween(String value1, String value2) {
            addCriterion("CHILDREN3_COUNT between", value1, value2, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3CountNotBetween(String value1, String value2) {
            addCriterion("CHILDREN3_COUNT not between", value1, value2, "children3Count");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceIsNull() {
            addCriterion("CHILDREN3_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceIsNotNull() {
            addCriterion("CHILDREN3_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceEqualTo(String value) {
            addCriterion("CHILDREN3_PRICE =", value, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceNotEqualTo(String value) {
            addCriterion("CHILDREN3_PRICE <>", value, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceGreaterThan(String value) {
            addCriterion("CHILDREN3_PRICE >", value, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_PRICE >=", value, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceLessThan(String value) {
            addCriterion("CHILDREN3_PRICE <", value, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_PRICE <=", value, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceLike(String value) {
            addCriterion("CHILDREN3_PRICE like", value, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceNotLike(String value) {
            addCriterion("CHILDREN3_PRICE not like", value, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceIn(List<String> values) {
            addCriterion("CHILDREN3_PRICE in", values, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceNotIn(List<String> values) {
            addCriterion("CHILDREN3_PRICE not in", values, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceBetween(String value1, String value2) {
            addCriterion("CHILDREN3_PRICE between", value1, value2, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3PriceNotBetween(String value1, String value2) {
            addCriterion("CHILDREN3_PRICE not between", value1, value2, "children3Price");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountIsNull() {
            addCriterion("CHILDREN3_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountIsNotNull() {
            addCriterion("CHILDREN3_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountEqualTo(String value) {
            addCriterion("CHILDREN3_AMOUNT =", value, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountNotEqualTo(String value) {
            addCriterion("CHILDREN3_AMOUNT <>", value, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountGreaterThan(String value) {
            addCriterion("CHILDREN3_AMOUNT >", value, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountGreaterThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_AMOUNT >=", value, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountLessThan(String value) {
            addCriterion("CHILDREN3_AMOUNT <", value, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountLessThanOrEqualTo(String value) {
            addCriterion("CHILDREN3_AMOUNT <=", value, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountLike(String value) {
            addCriterion("CHILDREN3_AMOUNT like", value, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountNotLike(String value) {
            addCriterion("CHILDREN3_AMOUNT not like", value, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountIn(List<String> values) {
            addCriterion("CHILDREN3_AMOUNT in", values, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountNotIn(List<String> values) {
            addCriterion("CHILDREN3_AMOUNT not in", values, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountBetween(String value1, String value2) {
            addCriterion("CHILDREN3_AMOUNT between", value1, value2, "children3Amount");
            return (Criteria) this;
        }

        public Criteria andChildren3AmountNotBetween(String value1, String value2) {
            addCriterion("CHILDREN3_AMOUNT not between", value1, value2, "children3Amount");
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