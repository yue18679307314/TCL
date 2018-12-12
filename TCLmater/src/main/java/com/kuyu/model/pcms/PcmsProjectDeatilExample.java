package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.List;

public class PcmsProjectDeatilExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsProjectDeatilExample() {
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

        public Criteria andDetailMemoIsNull() {
            addCriterion("DETAIL_MEMO is null");
            return (Criteria) this;
        }

        public Criteria andDetailMemoIsNotNull() {
            addCriterion("DETAIL_MEMO is not null");
            return (Criteria) this;
        }

        public Criteria andDetailMemoEqualTo(String value) {
            addCriterion("DETAIL_MEMO =", value, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoNotEqualTo(String value) {
            addCriterion("DETAIL_MEMO <>", value, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoGreaterThan(String value) {
            addCriterion("DETAIL_MEMO >", value, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_MEMO >=", value, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoLessThan(String value) {
            addCriterion("DETAIL_MEMO <", value, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_MEMO <=", value, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoLike(String value) {
            addCriterion("DETAIL_MEMO like", value, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoNotLike(String value) {
            addCriterion("DETAIL_MEMO not like", value, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoIn(List<String> values) {
            addCriterion("DETAIL_MEMO in", values, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoNotIn(List<String> values) {
            addCriterion("DETAIL_MEMO not in", values, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoBetween(String value1, String value2) {
            addCriterion("DETAIL_MEMO between", value1, value2, "detailMemo");
            return (Criteria) this;
        }

        public Criteria andDetailMemoNotBetween(String value1, String value2) {
            addCriterion("DETAIL_MEMO not between", value1, value2, "detailMemo");
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

        public Criteria andDetailCostIsNull() {
            addCriterion("DETAIL_COST is null");
            return (Criteria) this;
        }

        public Criteria andDetailCostIsNotNull() {
            addCriterion("DETAIL_COST is not null");
            return (Criteria) this;
        }

        public Criteria andDetailCostEqualTo(String value) {
            addCriterion("DETAIL_COST =", value, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostNotEqualTo(String value) {
            addCriterion("DETAIL_COST <>", value, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostGreaterThan(String value) {
            addCriterion("DETAIL_COST >", value, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_COST >=", value, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostLessThan(String value) {
            addCriterion("DETAIL_COST <", value, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_COST <=", value, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostLike(String value) {
            addCriterion("DETAIL_COST like", value, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostNotLike(String value) {
            addCriterion("DETAIL_COST not like", value, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostIn(List<String> values) {
            addCriterion("DETAIL_COST in", values, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostNotIn(List<String> values) {
            addCriterion("DETAIL_COST not in", values, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostBetween(String value1, String value2) {
            addCriterion("DETAIL_COST between", value1, value2, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailCostNotBetween(String value1, String value2) {
            addCriterion("DETAIL_COST not between", value1, value2, "detailCost");
            return (Criteria) this;
        }

        public Criteria andDetailWbsIsNull() {
            addCriterion("DETAIL_WBS is null");
            return (Criteria) this;
        }

        public Criteria andDetailWbsIsNotNull() {
            addCriterion("DETAIL_WBS is not null");
            return (Criteria) this;
        }

        public Criteria andDetailWbsEqualTo(String value) {
            addCriterion("DETAIL_WBS =", value, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsNotEqualTo(String value) {
            addCriterion("DETAIL_WBS <>", value, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsGreaterThan(String value) {
            addCriterion("DETAIL_WBS >", value, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_WBS >=", value, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsLessThan(String value) {
            addCriterion("DETAIL_WBS <", value, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_WBS <=", value, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsLike(String value) {
            addCriterion("DETAIL_WBS like", value, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsNotLike(String value) {
            addCriterion("DETAIL_WBS not like", value, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsIn(List<String> values) {
            addCriterion("DETAIL_WBS in", values, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsNotIn(List<String> values) {
            addCriterion("DETAIL_WBS not in", values, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsBetween(String value1, String value2) {
            addCriterion("DETAIL_WBS between", value1, value2, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailWbsNotBetween(String value1, String value2) {
            addCriterion("DETAIL_WBS not between", value1, value2, "detailWbs");
            return (Criteria) this;
        }

        public Criteria andDetailChannelIsNull() {
            addCriterion("DETAIL_CHANNEL is null");
            return (Criteria) this;
        }

        public Criteria andDetailChannelIsNotNull() {
            addCriterion("DETAIL_CHANNEL is not null");
            return (Criteria) this;
        }

        public Criteria andDetailChannelEqualTo(String value) {
            addCriterion("DETAIL_CHANNEL =", value, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelNotEqualTo(String value) {
            addCriterion("DETAIL_CHANNEL <>", value, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelGreaterThan(String value) {
            addCriterion("DETAIL_CHANNEL >", value, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_CHANNEL >=", value, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelLessThan(String value) {
            addCriterion("DETAIL_CHANNEL <", value, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_CHANNEL <=", value, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelLike(String value) {
            addCriterion("DETAIL_CHANNEL like", value, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelNotLike(String value) {
            addCriterion("DETAIL_CHANNEL not like", value, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelIn(List<String> values) {
            addCriterion("DETAIL_CHANNEL in", values, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelNotIn(List<String> values) {
            addCriterion("DETAIL_CHANNEL not in", values, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelBetween(String value1, String value2) {
            addCriterion("DETAIL_CHANNEL between", value1, value2, "detailChannel");
            return (Criteria) this;
        }

        public Criteria andDetailChannelNotBetween(String value1, String value2) {
            addCriterion("DETAIL_CHANNEL not between", value1, value2, "detailChannel");
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

        public Criteria andDetailAmountIsNull() {
            addCriterion("DETAIL_AMOUNT is null");
            return (Criteria) this;
        }

        public Criteria andDetailAmountIsNotNull() {
            addCriterion("DETAIL_AMOUNT is not null");
            return (Criteria) this;
        }

        public Criteria andDetailAmountEqualTo(String value) {
            addCriterion("DETAIL_AMOUNT =", value, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountNotEqualTo(String value) {
            addCriterion("DETAIL_AMOUNT <>", value, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountGreaterThan(String value) {
            addCriterion("DETAIL_AMOUNT >", value, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_AMOUNT >=", value, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountLessThan(String value) {
            addCriterion("DETAIL_AMOUNT <", value, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_AMOUNT <=", value, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountLike(String value) {
            addCriterion("DETAIL_AMOUNT like", value, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountNotLike(String value) {
            addCriterion("DETAIL_AMOUNT not like", value, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountIn(List<String> values) {
            addCriterion("DETAIL_AMOUNT in", values, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountNotIn(List<String> values) {
            addCriterion("DETAIL_AMOUNT not in", values, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountBetween(String value1, String value2) {
            addCriterion("DETAIL_AMOUNT between", value1, value2, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailAmountNotBetween(String value1, String value2) {
            addCriterion("DETAIL_AMOUNT not between", value1, value2, "detailAmount");
            return (Criteria) this;
        }

        public Criteria andDetailStandardIsNull() {
            addCriterion("DETAIL_STANDARD is null");
            return (Criteria) this;
        }

        public Criteria andDetailStandardIsNotNull() {
            addCriterion("DETAIL_STANDARD is not null");
            return (Criteria) this;
        }

        public Criteria andDetailStandardEqualTo(String value) {
            addCriterion("DETAIL_STANDARD =", value, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardNotEqualTo(String value) {
            addCriterion("DETAIL_STANDARD <>", value, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardGreaterThan(String value) {
            addCriterion("DETAIL_STANDARD >", value, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_STANDARD >=", value, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardLessThan(String value) {
            addCriterion("DETAIL_STANDARD <", value, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_STANDARD <=", value, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardLike(String value) {
            addCriterion("DETAIL_STANDARD like", value, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardNotLike(String value) {
            addCriterion("DETAIL_STANDARD not like", value, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardIn(List<String> values) {
            addCriterion("DETAIL_STANDARD in", values, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardNotIn(List<String> values) {
            addCriterion("DETAIL_STANDARD not in", values, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardBetween(String value1, String value2) {
            addCriterion("DETAIL_STANDARD between", value1, value2, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailStandardNotBetween(String value1, String value2) {
            addCriterion("DETAIL_STANDARD not between", value1, value2, "detailStandard");
            return (Criteria) this;
        }

        public Criteria andDetailVendorIsNull() {
            addCriterion("DETAIL_VENDOR is null");
            return (Criteria) this;
        }

        public Criteria andDetailVendorIsNotNull() {
            addCriterion("DETAIL_VENDOR is not null");
            return (Criteria) this;
        }

        public Criteria andDetailVendorEqualTo(String value) {
            addCriterion("DETAIL_VENDOR =", value, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorNotEqualTo(String value) {
            addCriterion("DETAIL_VENDOR <>", value, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorGreaterThan(String value) {
            addCriterion("DETAIL_VENDOR >", value, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorGreaterThanOrEqualTo(String value) {
            addCriterion("DETAIL_VENDOR >=", value, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorLessThan(String value) {
            addCriterion("DETAIL_VENDOR <", value, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorLessThanOrEqualTo(String value) {
            addCriterion("DETAIL_VENDOR <=", value, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorLike(String value) {
            addCriterion("DETAIL_VENDOR like", value, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorNotLike(String value) {
            addCriterion("DETAIL_VENDOR not like", value, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorIn(List<String> values) {
            addCriterion("DETAIL_VENDOR in", values, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorNotIn(List<String> values) {
            addCriterion("DETAIL_VENDOR not in", values, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorBetween(String value1, String value2) {
            addCriterion("DETAIL_VENDOR between", value1, value2, "detailVendor");
            return (Criteria) this;
        }

        public Criteria andDetailVendorNotBetween(String value1, String value2) {
            addCriterion("DETAIL_VENDOR not between", value1, value2, "detailVendor");
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