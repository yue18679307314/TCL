package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsMaterialSourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsMaterialSourceExample() {
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

        public Criteria andMrtypeIsNull() {
            addCriterion("mrtype is null");
            return (Criteria) this;
        }

        public Criteria andMrtypeIsNotNull() {
            addCriterion("mrtype is not null");
            return (Criteria) this;
        }

        public Criteria andMrtypeEqualTo(Integer value) {
            addCriterion("mrtype =", value, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrtypeNotEqualTo(Integer value) {
            addCriterion("mrtype <>", value, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrtypeGreaterThan(Integer value) {
            addCriterion("mrtype >", value, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrtypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mrtype >=", value, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrtypeLessThan(Integer value) {
            addCriterion("mrtype <", value, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrtypeLessThanOrEqualTo(Integer value) {
            addCriterion("mrtype <=", value, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrtypeIn(List<Integer> values) {
            addCriterion("mrtype in", values, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrtypeNotIn(List<Integer> values) {
            addCriterion("mrtype not in", values, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrtypeBetween(Integer value1, Integer value2) {
            addCriterion("mrtype between", value1, value2, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrtypeNotBetween(Integer value1, Integer value2) {
            addCriterion("mrtype not between", value1, value2, "mrtype");
            return (Criteria) this;
        }

        public Criteria andMrcodeIsNull() {
            addCriterion("mrcode is null");
            return (Criteria) this;
        }

        public Criteria andMrcodeIsNotNull() {
            addCriterion("mrcode is not null");
            return (Criteria) this;
        }

        public Criteria andMrcodeEqualTo(Integer value) {
            addCriterion("mrcode =", value, "mrcode");
            return (Criteria) this;
        }

        public Criteria andMrcodeNotEqualTo(Integer value) {
            addCriterion("mrcode <>", value, "mrcode");
            return (Criteria) this;
        }

        public Criteria andMrcodeGreaterThan(Integer value) {
            addCriterion("mrcode >", value, "mrcode");
            return (Criteria) this;
        }

        public Criteria andMrcodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("mrcode >=", value, "mrcode");
            return (Criteria) this;
        }

        public Criteria andMrcodeLessThan(Integer value) {
            addCriterion("mrcode <", value, "mrcode");
            return (Criteria) this;
        }

        public Criteria andMrcodeLessThanOrEqualTo(Integer value) {
            addCriterion("mrcode <=", value, "mrcode");
            return (Criteria) this;
        }

        public Criteria andMrcodeIn(List<Integer> values) {
            addCriterion("mrcode in", values, "mrcode");
            return (Criteria) this;
        }

        public Criteria andMrcodeNotIn(List<Integer> values) {
            addCriterion("mrcode not in", values, "mrcode");
            return (Criteria) this;
        }

        public Criteria andMrcodeBetween(Integer value1, Integer value2) {
            addCriterion("mrcode between", value1, value2, "mrcode");
            return (Criteria) this;
        }

        public Criteria andMrcodeNotBetween(Integer value1, Integer value2) {
            addCriterion("mrcode not between", value1, value2, "mrcode");
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

        public Criteria andMrnameIsNull() {
            addCriterion("mrname is null");
            return (Criteria) this;
        }

        public Criteria andMrnameIsNotNull() {
            addCriterion("mrname is not null");
            return (Criteria) this;
        }

        public Criteria andMrnameEqualTo(String value) {
            addCriterion("mrname =", value, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameNotEqualTo(String value) {
            addCriterion("mrname <>", value, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameGreaterThan(String value) {
            addCriterion("mrname >", value, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameGreaterThanOrEqualTo(String value) {
            addCriterion("mrname >=", value, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameLessThan(String value) {
            addCriterion("mrname <", value, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameLessThanOrEqualTo(String value) {
            addCriterion("mrname <=", value, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameLike(String value) {
            addCriterion("mrname like", value, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameNotLike(String value) {
            addCriterion("mrname not like", value, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameIn(List<String> values) {
            addCriterion("mrname in", values, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameNotIn(List<String> values) {
            addCriterion("mrname not in", values, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameBetween(String value1, String value2) {
            addCriterion("mrname between", value1, value2, "mrname");
            return (Criteria) this;
        }

        public Criteria andMrnameNotBetween(String value1, String value2) {
            addCriterion("mrname not between", value1, value2, "mrname");
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

        public Criteria andSpecificationsIsNull() {
            addCriterion("specifications is null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIsNotNull() {
            addCriterion("specifications is not null");
            return (Criteria) this;
        }

        public Criteria andSpecificationsEqualTo(String value) {
            addCriterion("specifications =", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotEqualTo(String value) {
            addCriterion("specifications <>", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThan(String value) {
            addCriterion("specifications >", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsGreaterThanOrEqualTo(String value) {
            addCriterion("specifications >=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThan(String value) {
            addCriterion("specifications <", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLessThanOrEqualTo(String value) {
            addCriterion("specifications <=", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsLike(String value) {
            addCriterion("specifications like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotLike(String value) {
            addCriterion("specifications not like", value, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsIn(List<String> values) {
            addCriterion("specifications in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotIn(List<String> values) {
            addCriterion("specifications not in", values, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsBetween(String value1, String value2) {
            addCriterion("specifications between", value1, value2, "specifications");
            return (Criteria) this;
        }

        public Criteria andSpecificationsNotBetween(String value1, String value2) {
            addCriterion("specifications not between", value1, value2, "specifications");
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

        public Criteria andUnitPriceIsNull() {
            addCriterion("unit_price is null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIsNotNull() {
            addCriterion("unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andUnitPriceEqualTo(Double value) {
            addCriterion("unit_price =", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotEqualTo(Double value) {
            addCriterion("unit_price <>", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThan(Double value) {
            addCriterion("unit_price >", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("unit_price >=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThan(Double value) {
            addCriterion("unit_price <", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceLessThanOrEqualTo(Double value) {
            addCriterion("unit_price <=", value, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceIn(List<Double> values) {
            addCriterion("unit_price in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotIn(List<Double> values) {
            addCriterion("unit_price not in", values, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceBetween(Double value1, Double value2) {
            addCriterion("unit_price between", value1, value2, "unitPrice");
            return (Criteria) this;
        }

        public Criteria andUnitPriceNotBetween(Double value1, Double value2) {
            addCriterion("unit_price not between", value1, value2, "unitPrice");
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

        public Criteria andMinRangeIsNull() {
            addCriterion("min_range is null");
            return (Criteria) this;
        }

        public Criteria andMinRangeIsNotNull() {
            addCriterion("min_range is not null");
            return (Criteria) this;
        }

        public Criteria andMinRangeEqualTo(Integer value) {
            addCriterion("min_range =", value, "minRange");
            return (Criteria) this;
        }

        public Criteria andMinRangeNotEqualTo(Integer value) {
            addCriterion("min_range <>", value, "minRange");
            return (Criteria) this;
        }

        public Criteria andMinRangeGreaterThan(Integer value) {
            addCriterion("min_range >", value, "minRange");
            return (Criteria) this;
        }

        public Criteria andMinRangeGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_range >=", value, "minRange");
            return (Criteria) this;
        }

        public Criteria andMinRangeLessThan(Integer value) {
            addCriterion("min_range <", value, "minRange");
            return (Criteria) this;
        }

        public Criteria andMinRangeLessThanOrEqualTo(Integer value) {
            addCriterion("min_range <=", value, "minRange");
            return (Criteria) this;
        }

        public Criteria andMinRangeIn(List<Integer> values) {
            addCriterion("min_range in", values, "minRange");
            return (Criteria) this;
        }

        public Criteria andMinRangeNotIn(List<Integer> values) {
            addCriterion("min_range not in", values, "minRange");
            return (Criteria) this;
        }

        public Criteria andMinRangeBetween(Integer value1, Integer value2) {
            addCriterion("min_range between", value1, value2, "minRange");
            return (Criteria) this;
        }

        public Criteria andMinRangeNotBetween(Integer value1, Integer value2) {
            addCriterion("min_range not between", value1, value2, "minRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeIsNull() {
            addCriterion("max_range is null");
            return (Criteria) this;
        }

        public Criteria andMaxRangeIsNotNull() {
            addCriterion("max_range is not null");
            return (Criteria) this;
        }

        public Criteria andMaxRangeEqualTo(Integer value) {
            addCriterion("max_range =", value, "maxRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeNotEqualTo(Integer value) {
            addCriterion("max_range <>", value, "maxRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeGreaterThan(Integer value) {
            addCriterion("max_range >", value, "maxRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_range >=", value, "maxRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeLessThan(Integer value) {
            addCriterion("max_range <", value, "maxRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeLessThanOrEqualTo(Integer value) {
            addCriterion("max_range <=", value, "maxRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeIn(List<Integer> values) {
            addCriterion("max_range in", values, "maxRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeNotIn(List<Integer> values) {
            addCriterion("max_range not in", values, "maxRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeBetween(Integer value1, Integer value2) {
            addCriterion("max_range between", value1, value2, "maxRange");
            return (Criteria) this;
        }

        public Criteria andMaxRangeNotBetween(Integer value1, Integer value2) {
            addCriterion("max_range not between", value1, value2, "maxRange");
            return (Criteria) this;
        }

        public Criteria andRangePriceIsNull() {
            addCriterion("range_price is null");
            return (Criteria) this;
        }

        public Criteria andRangePriceIsNotNull() {
            addCriterion("range_price is not null");
            return (Criteria) this;
        }

        public Criteria andRangePriceEqualTo(String value) {
            addCriterion("range_price =", value, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceNotEqualTo(String value) {
            addCriterion("range_price <>", value, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceGreaterThan(String value) {
            addCriterion("range_price >", value, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceGreaterThanOrEqualTo(String value) {
            addCriterion("range_price >=", value, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceLessThan(String value) {
            addCriterion("range_price <", value, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceLessThanOrEqualTo(String value) {
            addCriterion("range_price <=", value, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceLike(String value) {
            addCriterion("range_price like", value, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceNotLike(String value) {
            addCriterion("range_price not like", value, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceIn(List<String> values) {
            addCriterion("range_price in", values, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceNotIn(List<String> values) {
            addCriterion("range_price not in", values, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceBetween(String value1, String value2) {
            addCriterion("range_price between", value1, value2, "rangePrice");
            return (Criteria) this;
        }

        public Criteria andRangePriceNotBetween(String value1, String value2) {
            addCriterion("range_price not between", value1, value2, "rangePrice");
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

        public Criteria andComparisonPriceEqualTo(Double value) {
            addCriterion("comparison_price =", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceNotEqualTo(Double value) {
            addCriterion("comparison_price <>", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceGreaterThan(Double value) {
            addCriterion("comparison_price >", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("comparison_price >=", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceLessThan(Double value) {
            addCriterion("comparison_price <", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceLessThanOrEqualTo(Double value) {
            addCriterion("comparison_price <=", value, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceIn(List<Double> values) {
            addCriterion("comparison_price in", values, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceNotIn(List<Double> values) {
            addCriterion("comparison_price not in", values, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceBetween(Double value1, Double value2) {
            addCriterion("comparison_price between", value1, value2, "comparisonPrice");
            return (Criteria) this;
        }

        public Criteria andComparisonPriceNotBetween(Double value1, Double value2) {
            addCriterion("comparison_price not between", value1, value2, "comparisonPrice");
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