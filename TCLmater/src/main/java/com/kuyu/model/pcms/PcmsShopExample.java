package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsShopExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsShopExample() {
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

        public Criteria andSidIsNull() {
            addCriterion("sid is null");
            return (Criteria) this;
        }

        public Criteria andSidIsNotNull() {
            addCriterion("sid is not null");
            return (Criteria) this;
        }

        public Criteria andSidEqualTo(Integer value) {
            addCriterion("sid =", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotEqualTo(Integer value) {
            addCriterion("sid <>", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThan(Integer value) {
            addCriterion("sid >", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidGreaterThanOrEqualTo(Integer value) {
            addCriterion("sid >=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThan(Integer value) {
            addCriterion("sid <", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidLessThanOrEqualTo(Integer value) {
            addCriterion("sid <=", value, "sid");
            return (Criteria) this;
        }

        public Criteria andSidIn(List<Integer> values) {
            addCriterion("sid in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotIn(List<Integer> values) {
            addCriterion("sid not in", values, "sid");
            return (Criteria) this;
        }

        public Criteria andSidBetween(Integer value1, Integer value2) {
            addCriterion("sid between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andSidNotBetween(Integer value1, Integer value2) {
            addCriterion("sid not between", value1, value2, "sid");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeIsNull() {
            addCriterion("SHOP_CRM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeIsNotNull() {
            addCriterion("SHOP_CRM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeEqualTo(String value) {
            addCriterion("SHOP_CRM_CODE =", value, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeNotEqualTo(String value) {
            addCriterion("SHOP_CRM_CODE <>", value, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeGreaterThan(String value) {
            addCriterion("SHOP_CRM_CODE >", value, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_CRM_CODE >=", value, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeLessThan(String value) {
            addCriterion("SHOP_CRM_CODE <", value, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeLessThanOrEqualTo(String value) {
            addCriterion("SHOP_CRM_CODE <=", value, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeLike(String value) {
            addCriterion("SHOP_CRM_CODE like", value, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeNotLike(String value) {
            addCriterion("SHOP_CRM_CODE not like", value, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeIn(List<String> values) {
            addCriterion("SHOP_CRM_CODE in", values, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeNotIn(List<String> values) {
            addCriterion("SHOP_CRM_CODE not in", values, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeBetween(String value1, String value2) {
            addCriterion("SHOP_CRM_CODE between", value1, value2, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopCrmCodeNotBetween(String value1, String value2) {
            addCriterion("SHOP_CRM_CODE not between", value1, value2, "shopCrmCode");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("SHOP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("SHOP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("SHOP_NAME =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("SHOP_NAME <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("SHOP_NAME >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("SHOP_NAME <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("SHOP_NAME like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("SHOP_NAME not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("SHOP_NAME in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("SHOP_NAME not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("SHOP_NAME between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("SHOP_NAME not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopLevelIsNull() {
            addCriterion("SHOP_LEVEL is null");
            return (Criteria) this;
        }

        public Criteria andShopLevelIsNotNull() {
            addCriterion("SHOP_LEVEL is not null");
            return (Criteria) this;
        }

        public Criteria andShopLevelEqualTo(String value) {
            addCriterion("SHOP_LEVEL =", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelNotEqualTo(String value) {
            addCriterion("SHOP_LEVEL <>", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelGreaterThan(String value) {
            addCriterion("SHOP_LEVEL >", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_LEVEL >=", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelLessThan(String value) {
            addCriterion("SHOP_LEVEL <", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelLessThanOrEqualTo(String value) {
            addCriterion("SHOP_LEVEL <=", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelLike(String value) {
            addCriterion("SHOP_LEVEL like", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelNotLike(String value) {
            addCriterion("SHOP_LEVEL not like", value, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelIn(List<String> values) {
            addCriterion("SHOP_LEVEL in", values, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelNotIn(List<String> values) {
            addCriterion("SHOP_LEVEL not in", values, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelBetween(String value1, String value2) {
            addCriterion("SHOP_LEVEL between", value1, value2, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopLevelNotBetween(String value1, String value2) {
            addCriterion("SHOP_LEVEL not between", value1, value2, "shopLevel");
            return (Criteria) this;
        }

        public Criteria andShopBranchIsNull() {
            addCriterion("SHOP_BRANCH is null");
            return (Criteria) this;
        }

        public Criteria andShopBranchIsNotNull() {
            addCriterion("SHOP_BRANCH is not null");
            return (Criteria) this;
        }

        public Criteria andShopBranchEqualTo(String value) {
            addCriterion("SHOP_BRANCH =", value, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchNotEqualTo(String value) {
            addCriterion("SHOP_BRANCH <>", value, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchGreaterThan(String value) {
            addCriterion("SHOP_BRANCH >", value, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_BRANCH >=", value, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchLessThan(String value) {
            addCriterion("SHOP_BRANCH <", value, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchLessThanOrEqualTo(String value) {
            addCriterion("SHOP_BRANCH <=", value, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchLike(String value) {
            addCriterion("SHOP_BRANCH like", value, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchNotLike(String value) {
            addCriterion("SHOP_BRANCH not like", value, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchIn(List<String> values) {
            addCriterion("SHOP_BRANCH in", values, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchNotIn(List<String> values) {
            addCriterion("SHOP_BRANCH not in", values, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchBetween(String value1, String value2) {
            addCriterion("SHOP_BRANCH between", value1, value2, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopBranchNotBetween(String value1, String value2) {
            addCriterion("SHOP_BRANCH not between", value1, value2, "shopBranch");
            return (Criteria) this;
        }

        public Criteria andShopCustomerIsNull() {
            addCriterion("SHOP_CUSTOMER is null");
            return (Criteria) this;
        }

        public Criteria andShopCustomerIsNotNull() {
            addCriterion("SHOP_CUSTOMER is not null");
            return (Criteria) this;
        }

        public Criteria andShopCustomerEqualTo(String value) {
            addCriterion("SHOP_CUSTOMER =", value, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerNotEqualTo(String value) {
            addCriterion("SHOP_CUSTOMER <>", value, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerGreaterThan(String value) {
            addCriterion("SHOP_CUSTOMER >", value, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_CUSTOMER >=", value, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerLessThan(String value) {
            addCriterion("SHOP_CUSTOMER <", value, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerLessThanOrEqualTo(String value) {
            addCriterion("SHOP_CUSTOMER <=", value, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerLike(String value) {
            addCriterion("SHOP_CUSTOMER like", value, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerNotLike(String value) {
            addCriterion("SHOP_CUSTOMER not like", value, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerIn(List<String> values) {
            addCriterion("SHOP_CUSTOMER in", values, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerNotIn(List<String> values) {
            addCriterion("SHOP_CUSTOMER not in", values, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerBetween(String value1, String value2) {
            addCriterion("SHOP_CUSTOMER between", value1, value2, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopCustomerNotBetween(String value1, String value2) {
            addCriterion("SHOP_CUSTOMER not between", value1, value2, "shopCustomer");
            return (Criteria) this;
        }

        public Criteria andShopChannelIsNull() {
            addCriterion("SHOP_CHANNEL is null");
            return (Criteria) this;
        }

        public Criteria andShopChannelIsNotNull() {
            addCriterion("SHOP_CHANNEL is not null");
            return (Criteria) this;
        }

        public Criteria andShopChannelEqualTo(String value) {
            addCriterion("SHOP_CHANNEL =", value, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelNotEqualTo(String value) {
            addCriterion("SHOP_CHANNEL <>", value, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelGreaterThan(String value) {
            addCriterion("SHOP_CHANNEL >", value, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_CHANNEL >=", value, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelLessThan(String value) {
            addCriterion("SHOP_CHANNEL <", value, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelLessThanOrEqualTo(String value) {
            addCriterion("SHOP_CHANNEL <=", value, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelLike(String value) {
            addCriterion("SHOP_CHANNEL like", value, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelNotLike(String value) {
            addCriterion("SHOP_CHANNEL not like", value, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelIn(List<String> values) {
            addCriterion("SHOP_CHANNEL in", values, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelNotIn(List<String> values) {
            addCriterion("SHOP_CHANNEL not in", values, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelBetween(String value1, String value2) {
            addCriterion("SHOP_CHANNEL between", value1, value2, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopChannelNotBetween(String value1, String value2) {
            addCriterion("SHOP_CHANNEL not between", value1, value2, "shopChannel");
            return (Criteria) this;
        }

        public Criteria andShopProvinceIsNull() {
            addCriterion("SHOP_PROVINCE is null");
            return (Criteria) this;
        }

        public Criteria andShopProvinceIsNotNull() {
            addCriterion("SHOP_PROVINCE is not null");
            return (Criteria) this;
        }

        public Criteria andShopProvinceEqualTo(String value) {
            addCriterion("SHOP_PROVINCE =", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNotEqualTo(String value) {
            addCriterion("SHOP_PROVINCE <>", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceGreaterThan(String value) {
            addCriterion("SHOP_PROVINCE >", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_PROVINCE >=", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceLessThan(String value) {
            addCriterion("SHOP_PROVINCE <", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceLessThanOrEqualTo(String value) {
            addCriterion("SHOP_PROVINCE <=", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceLike(String value) {
            addCriterion("SHOP_PROVINCE like", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNotLike(String value) {
            addCriterion("SHOP_PROVINCE not like", value, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceIn(List<String> values) {
            addCriterion("SHOP_PROVINCE in", values, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNotIn(List<String> values) {
            addCriterion("SHOP_PROVINCE not in", values, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceBetween(String value1, String value2) {
            addCriterion("SHOP_PROVINCE between", value1, value2, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopProvinceNotBetween(String value1, String value2) {
            addCriterion("SHOP_PROVINCE not between", value1, value2, "shopProvince");
            return (Criteria) this;
        }

        public Criteria andShopCityIsNull() {
            addCriterion("SHOP_CITY is null");
            return (Criteria) this;
        }

        public Criteria andShopCityIsNotNull() {
            addCriterion("SHOP_CITY is not null");
            return (Criteria) this;
        }

        public Criteria andShopCityEqualTo(String value) {
            addCriterion("SHOP_CITY =", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityNotEqualTo(String value) {
            addCriterion("SHOP_CITY <>", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityGreaterThan(String value) {
            addCriterion("SHOP_CITY >", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_CITY >=", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityLessThan(String value) {
            addCriterion("SHOP_CITY <", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityLessThanOrEqualTo(String value) {
            addCriterion("SHOP_CITY <=", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityLike(String value) {
            addCriterion("SHOP_CITY like", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityNotLike(String value) {
            addCriterion("SHOP_CITY not like", value, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityIn(List<String> values) {
            addCriterion("SHOP_CITY in", values, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityNotIn(List<String> values) {
            addCriterion("SHOP_CITY not in", values, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityBetween(String value1, String value2) {
            addCriterion("SHOP_CITY between", value1, value2, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCityNotBetween(String value1, String value2) {
            addCriterion("SHOP_CITY not between", value1, value2, "shopCity");
            return (Criteria) this;
        }

        public Criteria andShopCountyIsNull() {
            addCriterion("SHOP_COUNTY is null");
            return (Criteria) this;
        }

        public Criteria andShopCountyIsNotNull() {
            addCriterion("SHOP_COUNTY is not null");
            return (Criteria) this;
        }

        public Criteria andShopCountyEqualTo(String value) {
            addCriterion("SHOP_COUNTY =", value, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyNotEqualTo(String value) {
            addCriterion("SHOP_COUNTY <>", value, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyGreaterThan(String value) {
            addCriterion("SHOP_COUNTY >", value, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_COUNTY >=", value, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyLessThan(String value) {
            addCriterion("SHOP_COUNTY <", value, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyLessThanOrEqualTo(String value) {
            addCriterion("SHOP_COUNTY <=", value, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyLike(String value) {
            addCriterion("SHOP_COUNTY like", value, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyNotLike(String value) {
            addCriterion("SHOP_COUNTY not like", value, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyIn(List<String> values) {
            addCriterion("SHOP_COUNTY in", values, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyNotIn(List<String> values) {
            addCriterion("SHOP_COUNTY not in", values, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyBetween(String value1, String value2) {
            addCriterion("SHOP_COUNTY between", value1, value2, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andShopCountyNotBetween(String value1, String value2) {
            addCriterion("SHOP_COUNTY not between", value1, value2, "shopCounty");
            return (Criteria) this;
        }

        public Criteria andDetailTaskIsNull() {
            addCriterion("DETAIL_TASK is null");
            return (Criteria) this;
        }

        public Criteria andDetailTaskIsNotNull() {
            addCriterion("DETAIL_TASK is not null");
            return (Criteria) this;
        }

        public Criteria andDetailTaskEqualTo(String value) {
            addCriterion("DETAIL_TASK =", value, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskNotEqualTo(String value) {
            addCriterion("DETAIL_TASK <>", value, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskGreaterThan(String value) {
            addCriterion("DETAIL_TASK >", value, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_TASK >=", value, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskLessThan(String value) {
            addCriterion("DETAIL_TASK <", value, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_TASK <=", value, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskLike(String value) {
            addCriterion("DETAIL_TASK like", value, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskNotLike(String value) {
            addCriterion("DETAIL_TASK not like", value, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskIn(List<String> values) {
            addCriterion("DETAIL_TASK in", values, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskNotIn(List<String> values) {
            addCriterion("DETAIL_TASK not in", values, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskBetween(String value1, String value2) {
            addCriterion("DETAIL_TASK between", value1, value2, "detailTask");
            return (Criteria) this;
        }

        public Criteria andDetailTaskNotBetween(String value1, String value2) {
            addCriterion("DETAIL_TASK not between", value1, value2, "detailTask");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountIsNull() {
            addCriterion("SHOP_SALES_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountIsNotNull() {
            addCriterion("SHOP_SALES_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountEqualTo(String value) {
            addCriterion("SHOP_SALES_AMOUNT =", value, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountNotEqualTo(String value) {
            addCriterion("SHOP_SALES_AMOUNT <>", value, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountGreaterThan(String value) {
            addCriterion("SHOP_SALES_AMOUNT >", value, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_SALES_AMOUNT >=", value, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountLessThan(String value) {
            addCriterion("SHOP_SALES_AMOUNT <", value, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountLessThanOrEqualTo(String value) {
            addCriterion("SHOP_SALES_AMOUNT <=", value, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountLike(String value) {
            addCriterion("SHOP_SALES_AMOUNT like", value, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountNotLike(String value) {
            addCriterion("SHOP_SALES_AMOUNT not like", value, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountIn(List<String> values) {
            addCriterion("SHOP_SALES_AMOUNT in", values, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountNotIn(List<String> values) {
            addCriterion("SHOP_SALES_AMOUNT not in", values, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountBetween(String value1, String value2) {
            addCriterion("SHOP_SALES_AMOUNT between", value1, value2, "shopSalesAmount");
            return (Criteria) this;
        }

        public Criteria andShopSalesAmountNotBetween(String value1, String value2) {
            addCriterion("SHOP_SALES_AMOUNT not between", value1, value2, "shopSalesAmount");
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