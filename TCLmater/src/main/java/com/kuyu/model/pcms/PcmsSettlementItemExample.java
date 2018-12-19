package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsSettlementItemExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsSettlementItemExample() {
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

        public Criteria andSetidIsNull() {
            addCriterion("setid is null");
            return (Criteria) this;
        }

        public Criteria andSetidIsNotNull() {
            addCriterion("setid is not null");
            return (Criteria) this;
        }

        public Criteria andSetidEqualTo(Integer value) {
            addCriterion("setid =", value, "setid");
            return (Criteria) this;
        }

        public Criteria andSetidNotEqualTo(Integer value) {
            addCriterion("setid <>", value, "setid");
            return (Criteria) this;
        }

        public Criteria andSetidGreaterThan(Integer value) {
            addCriterion("setid >", value, "setid");
            return (Criteria) this;
        }

        public Criteria andSetidGreaterThanOrEqualTo(Integer value) {
            addCriterion("setid >=", value, "setid");
            return (Criteria) this;
        }

        public Criteria andSetidLessThan(Integer value) {
            addCriterion("setid <", value, "setid");
            return (Criteria) this;
        }

        public Criteria andSetidLessThanOrEqualTo(Integer value) {
            addCriterion("setid <=", value, "setid");
            return (Criteria) this;
        }

        public Criteria andSetidIn(List<Integer> values) {
            addCriterion("setid in", values, "setid");
            return (Criteria) this;
        }

        public Criteria andSetidNotIn(List<Integer> values) {
            addCriterion("setid not in", values, "setid");
            return (Criteria) this;
        }

        public Criteria andSetidBetween(Integer value1, Integer value2) {
            addCriterion("setid between", value1, value2, "setid");
            return (Criteria) this;
        }

        public Criteria andSetidNotBetween(Integer value1, Integer value2) {
            addCriterion("setid not between", value1, value2, "setid");
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

        public Criteria andItidEqualTo(Integer value) {
            addCriterion("itid =", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidNotEqualTo(Integer value) {
            addCriterion("itid <>", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidGreaterThan(Integer value) {
            addCriterion("itid >", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidGreaterThanOrEqualTo(Integer value) {
            addCriterion("itid >=", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidLessThan(Integer value) {
            addCriterion("itid <", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidLessThanOrEqualTo(Integer value) {
            addCriterion("itid <=", value, "itid");
            return (Criteria) this;
        }

        public Criteria andItidIn(List<Integer> values) {
            addCriterion("itid in", values, "itid");
            return (Criteria) this;
        }

        public Criteria andItidNotIn(List<Integer> values) {
            addCriterion("itid not in", values, "itid");
            return (Criteria) this;
        }

        public Criteria andItidBetween(Integer value1, Integer value2) {
            addCriterion("itid between", value1, value2, "itid");
            return (Criteria) this;
        }

        public Criteria andItidNotBetween(Integer value1, Integer value2) {
            addCriterion("itid not between", value1, value2, "itid");
            return (Criteria) this;
        }

        public Criteria andSedetailIdIsNull() {
            addCriterion("sedetail_id is null");
            return (Criteria) this;
        }

        public Criteria andSedetailIdIsNotNull() {
            addCriterion("sedetail_id is not null");
            return (Criteria) this;
        }

        public Criteria andSedetailIdEqualTo(String value) {
            addCriterion("sedetail_id =", value, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdNotEqualTo(String value) {
            addCriterion("sedetail_id <>", value, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdGreaterThan(String value) {
            addCriterion("sedetail_id >", value, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdGreaterThanOrEqualTo(String value) {
            addCriterion("sedetail_id >=", value, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdLessThan(String value) {
            addCriterion("sedetail_id <", value, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdLessThanOrEqualTo(String value) {
            addCriterion("sedetail_id <=", value, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdLike(String value) {
            addCriterion("sedetail_id like", value, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdNotLike(String value) {
            addCriterion("sedetail_id not like", value, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdIn(List<String> values) {
            addCriterion("sedetail_id in", values, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdNotIn(List<String> values) {
            addCriterion("sedetail_id not in", values, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdBetween(String value1, String value2) {
            addCriterion("sedetail_id between", value1, value2, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailIdNotBetween(String value1, String value2) {
            addCriterion("sedetail_id not between", value1, value2, "sedetailId");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyIsNull() {
            addCriterion("sedetail_money is null");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyIsNotNull() {
            addCriterion("sedetail_money is not null");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyEqualTo(String value) {
            addCriterion("sedetail_money =", value, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyNotEqualTo(String value) {
            addCriterion("sedetail_money <>", value, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyGreaterThan(String value) {
            addCriterion("sedetail_money >", value, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyGreaterThanOrEqualTo(String value) {
            addCriterion("sedetail_money >=", value, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyLessThan(String value) {
            addCriterion("sedetail_money <", value, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyLessThanOrEqualTo(String value) {
            addCriterion("sedetail_money <=", value, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyLike(String value) {
            addCriterion("sedetail_money like", value, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyNotLike(String value) {
            addCriterion("sedetail_money not like", value, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyIn(List<String> values) {
            addCriterion("sedetail_money in", values, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyNotIn(List<String> values) {
            addCriterion("sedetail_money not in", values, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyBetween(String value1, String value2) {
            addCriterion("sedetail_money between", value1, value2, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMoneyNotBetween(String value1, String value2) {
            addCriterion("sedetail_money not between", value1, value2, "sedetailMoney");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoIsNull() {
            addCriterion("sedetail_memo is null");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoIsNotNull() {
            addCriterion("sedetail_memo is not null");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoEqualTo(String value) {
            addCriterion("sedetail_memo =", value, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoNotEqualTo(String value) {
            addCriterion("sedetail_memo <>", value, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoGreaterThan(String value) {
            addCriterion("sedetail_memo >", value, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoGreaterThanOrEqualTo(String value) {
            addCriterion("sedetail_memo >=", value, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoLessThan(String value) {
            addCriterion("sedetail_memo <", value, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoLessThanOrEqualTo(String value) {
            addCriterion("sedetail_memo <=", value, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoLike(String value) {
            addCriterion("sedetail_memo like", value, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoNotLike(String value) {
            addCriterion("sedetail_memo not like", value, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoIn(List<String> values) {
            addCriterion("sedetail_memo in", values, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoNotIn(List<String> values) {
            addCriterion("sedetail_memo not in", values, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoBetween(String value1, String value2) {
            addCriterion("sedetail_memo between", value1, value2, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andSedetailMemoNotBetween(String value1, String value2) {
            addCriterion("sedetail_memo not between", value1, value2, "sedetailMemo");
            return (Criteria) this;
        }

        public Criteria andTaxTypeIsNull() {
            addCriterion("tax_type is null");
            return (Criteria) this;
        }

        public Criteria andTaxTypeIsNotNull() {
            addCriterion("tax_type is not null");
            return (Criteria) this;
        }

        public Criteria andTaxTypeEqualTo(String value) {
            addCriterion("tax_type =", value, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeNotEqualTo(String value) {
            addCriterion("tax_type <>", value, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeGreaterThan(String value) {
            addCriterion("tax_type >", value, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeGreaterThanOrEqualTo(String value) {
            addCriterion("tax_type >=", value, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeLessThan(String value) {
            addCriterion("tax_type <", value, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeLessThanOrEqualTo(String value) {
            addCriterion("tax_type <=", value, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeLike(String value) {
            addCriterion("tax_type like", value, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeNotLike(String value) {
            addCriterion("tax_type not like", value, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeIn(List<String> values) {
            addCriterion("tax_type in", values, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeNotIn(List<String> values) {
            addCriterion("tax_type not in", values, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeBetween(String value1, String value2) {
            addCriterion("tax_type between", value1, value2, "taxType");
            return (Criteria) this;
        }

        public Criteria andTaxTypeNotBetween(String value1, String value2) {
            addCriterion("tax_type not between", value1, value2, "taxType");
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

        public Criteria andTaxRateIsNull() {
            addCriterion("tax_rate is null");
            return (Criteria) this;
        }

        public Criteria andTaxRateIsNotNull() {
            addCriterion("tax_rate is not null");
            return (Criteria) this;
        }

        public Criteria andTaxRateEqualTo(String value) {
            addCriterion("tax_rate =", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotEqualTo(String value) {
            addCriterion("tax_rate <>", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThan(String value) {
            addCriterion("tax_rate >", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateGreaterThanOrEqualTo(String value) {
            addCriterion("tax_rate >=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThan(String value) {
            addCriterion("tax_rate <", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLessThanOrEqualTo(String value) {
            addCriterion("tax_rate <=", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateLike(String value) {
            addCriterion("tax_rate like", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotLike(String value) {
            addCriterion("tax_rate not like", value, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateIn(List<String> values) {
            addCriterion("tax_rate in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotIn(List<String> values) {
            addCriterion("tax_rate not in", values, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateBetween(String value1, String value2) {
            addCriterion("tax_rate between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andTaxRateNotBetween(String value1, String value2) {
            addCriterion("tax_rate not between", value1, value2, "taxRate");
            return (Criteria) this;
        }

        public Criteria andIsLastIsNull() {
            addCriterion("is_last is null");
            return (Criteria) this;
        }

        public Criteria andIsLastIsNotNull() {
            addCriterion("is_last is not null");
            return (Criteria) this;
        }

        public Criteria andIsLastEqualTo(Integer value) {
            addCriterion("is_last =", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastNotEqualTo(Integer value) {
            addCriterion("is_last <>", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastGreaterThan(Integer value) {
            addCriterion("is_last >", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_last >=", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastLessThan(Integer value) {
            addCriterion("is_last <", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastLessThanOrEqualTo(Integer value) {
            addCriterion("is_last <=", value, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastIn(List<Integer> values) {
            addCriterion("is_last in", values, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastNotIn(List<Integer> values) {
            addCriterion("is_last not in", values, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastBetween(Integer value1, Integer value2) {
            addCriterion("is_last between", value1, value2, "isLast");
            return (Criteria) this;
        }

        public Criteria andIsLastNotBetween(Integer value1, Integer value2) {
            addCriterion("is_last not between", value1, value2, "isLast");
            return (Criteria) this;
        }

        public Criteria andSettlementIdIsNull() {
            addCriterion("settlement_id is null");
            return (Criteria) this;
        }

        public Criteria andSettlementIdIsNotNull() {
            addCriterion("settlement_id is not null");
            return (Criteria) this;
        }

        public Criteria andSettlementIdEqualTo(Integer value) {
            addCriterion("settlement_id =", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdNotEqualTo(Integer value) {
            addCriterion("settlement_id <>", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdGreaterThan(Integer value) {
            addCriterion("settlement_id >", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("settlement_id >=", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdLessThan(Integer value) {
            addCriterion("settlement_id <", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdLessThanOrEqualTo(Integer value) {
            addCriterion("settlement_id <=", value, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdIn(List<Integer> values) {
            addCriterion("settlement_id in", values, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdNotIn(List<Integer> values) {
            addCriterion("settlement_id not in", values, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdBetween(Integer value1, Integer value2) {
            addCriterion("settlement_id between", value1, value2, "settlementId");
            return (Criteria) this;
        }

        public Criteria andSettlementIdNotBetween(Integer value1, Integer value2) {
            addCriterion("settlement_id not between", value1, value2, "settlementId");
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