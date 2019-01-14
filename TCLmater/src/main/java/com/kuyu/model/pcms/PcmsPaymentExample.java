package com.kuyu.model.pcms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PcmsPaymentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PcmsPaymentExample() {
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

        public Criteria andPmidIsNull() {
            addCriterion("pmid is null");
            return (Criteria) this;
        }

        public Criteria andPmidIsNotNull() {
            addCriterion("pmid is not null");
            return (Criteria) this;
        }

        public Criteria andPmidEqualTo(Integer value) {
            addCriterion("pmid =", value, "pmid");
            return (Criteria) this;
        }

        public Criteria andPmidNotEqualTo(Integer value) {
            addCriterion("pmid <>", value, "pmid");
            return (Criteria) this;
        }

        public Criteria andPmidGreaterThan(Integer value) {
            addCriterion("pmid >", value, "pmid");
            return (Criteria) this;
        }

        public Criteria andPmidGreaterThanOrEqualTo(Integer value) {
            addCriterion("pmid >=", value, "pmid");
            return (Criteria) this;
        }

        public Criteria andPmidLessThan(Integer value) {
            addCriterion("pmid <", value, "pmid");
            return (Criteria) this;
        }

        public Criteria andPmidLessThanOrEqualTo(Integer value) {
            addCriterion("pmid <=", value, "pmid");
            return (Criteria) this;
        }

        public Criteria andPmidIn(List<Integer> values) {
            addCriterion("pmid in", values, "pmid");
            return (Criteria) this;
        }

        public Criteria andPmidNotIn(List<Integer> values) {
            addCriterion("pmid not in", values, "pmid");
            return (Criteria) this;
        }

        public Criteria andPmidBetween(Integer value1, Integer value2) {
            addCriterion("pmid between", value1, value2, "pmid");
            return (Criteria) this;
        }

        public Criteria andPmidNotBetween(Integer value1, Integer value2) {
            addCriterion("pmid not between", value1, value2, "pmid");
            return (Criteria) this;
        }

        public Criteria andRequestDeptIsNull() {
            addCriterion("request_dept is null");
            return (Criteria) this;
        }

        public Criteria andRequestDeptIsNotNull() {
            addCriterion("request_dept is not null");
            return (Criteria) this;
        }

        public Criteria andRequestDeptEqualTo(String value) {
            addCriterion("request_dept =", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNotEqualTo(String value) {
            addCriterion("request_dept <>", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptGreaterThan(String value) {
            addCriterion("request_dept >", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptGreaterThanOrEqualTo(String value) {
            addCriterion("request_dept >=", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptLessThan(String value) {
            addCriterion("request_dept <", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptLessThanOrEqualTo(String value) {
            addCriterion("request_dept <=", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptLike(String value) {
            addCriterion("request_dept like", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNotLike(String value) {
            addCriterion("request_dept not like", value, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptIn(List<String> values) {
            addCriterion("request_dept in", values, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNotIn(List<String> values) {
            addCriterion("request_dept not in", values, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptBetween(String value1, String value2) {
            addCriterion("request_dept between", value1, value2, "requestDept");
            return (Criteria) this;
        }

        public Criteria andRequestDeptNotBetween(String value1, String value2) {
            addCriterion("request_dept not between", value1, value2, "requestDept");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andAccountNumberIsNull() {
            addCriterion("account_number is null");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIsNotNull() {
            addCriterion("account_number is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNumberEqualTo(String value) {
            addCriterion("account_number =", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotEqualTo(String value) {
            addCriterion("account_number <>", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberGreaterThan(String value) {
            addCriterion("account_number >", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberGreaterThanOrEqualTo(String value) {
            addCriterion("account_number >=", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLessThan(String value) {
            addCriterion("account_number <", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLessThanOrEqualTo(String value) {
            addCriterion("account_number <=", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberLike(String value) {
            addCriterion("account_number like", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotLike(String value) {
            addCriterion("account_number not like", value, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberIn(List<String> values) {
            addCriterion("account_number in", values, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotIn(List<String> values) {
            addCriterion("account_number not in", values, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberBetween(String value1, String value2) {
            addCriterion("account_number between", value1, value2, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNumberNotBetween(String value1, String value2) {
            addCriterion("account_number not between", value1, value2, "accountNumber");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("account_name =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("account_name >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("account_name <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("account_name like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("account_name not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("account_name in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameIsNull() {
            addCriterion("payee_name is null");
            return (Criteria) this;
        }

        public Criteria andPayeeNameIsNotNull() {
            addCriterion("payee_name is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeNameEqualTo(String value) {
            addCriterion("payee_name =", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotEqualTo(String value) {
            addCriterion("payee_name <>", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameGreaterThan(String value) {
            addCriterion("payee_name >", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("payee_name >=", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameLessThan(String value) {
            addCriterion("payee_name <", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameLessThanOrEqualTo(String value) {
            addCriterion("payee_name <=", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameLike(String value) {
            addCriterion("payee_name like", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotLike(String value) {
            addCriterion("payee_name not like", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameIn(List<String> values) {
            addCriterion("payee_name in", values, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotIn(List<String> values) {
            addCriterion("payee_name not in", values, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameBetween(String value1, String value2) {
            addCriterion("payee_name between", value1, value2, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotBetween(String value1, String value2) {
            addCriterion("payee_name not between", value1, value2, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNull() {
            addCriterion("payment_type is null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIsNotNull() {
            addCriterion("payment_type is not null");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeEqualTo(String value) {
            addCriterion("payment_type =", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotEqualTo(String value) {
            addCriterion("payment_type <>", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThan(String value) {
            addCriterion("payment_type >", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeGreaterThanOrEqualTo(String value) {
            addCriterion("payment_type >=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThan(String value) {
            addCriterion("payment_type <", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLessThanOrEqualTo(String value) {
            addCriterion("payment_type <=", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeLike(String value) {
            addCriterion("payment_type like", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotLike(String value) {
            addCriterion("payment_type not like", value, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeIn(List<String> values) {
            addCriterion("payment_type in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotIn(List<String> values) {
            addCriterion("payment_type not in", values, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeBetween(String value1, String value2) {
            addCriterion("payment_type between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andPaymentTypeNotBetween(String value1, String value2) {
            addCriterion("payment_type not between", value1, value2, "paymentType");
            return (Criteria) this;
        }

        public Criteria andRecommentDateIsNull() {
            addCriterion("recomment_date is null");
            return (Criteria) this;
        }

        public Criteria andRecommentDateIsNotNull() {
            addCriterion("recomment_date is not null");
            return (Criteria) this;
        }

        public Criteria andRecommentDateEqualTo(String value) {
            addCriterion("recomment_date =", value, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateNotEqualTo(String value) {
            addCriterion("recomment_date <>", value, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateGreaterThan(String value) {
            addCriterion("recomment_date >", value, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateGreaterThanOrEqualTo(String value) {
            addCriterion("recomment_date >=", value, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateLessThan(String value) {
            addCriterion("recomment_date <", value, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateLessThanOrEqualTo(String value) {
            addCriterion("recomment_date <=", value, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateLike(String value) {
            addCriterion("recomment_date like", value, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateNotLike(String value) {
            addCriterion("recomment_date not like", value, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateIn(List<String> values) {
            addCriterion("recomment_date in", values, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateNotIn(List<String> values) {
            addCriterion("recomment_date not in", values, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateBetween(String value1, String value2) {
            addCriterion("recomment_date between", value1, value2, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andRecommentDateNotBetween(String value1, String value2) {
            addCriterion("recomment_date not between", value1, value2, "recommentDate");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayAmountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayAmountEqualTo(String value) {
            addCriterion("pay_amount =", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotEqualTo(String value) {
            addCriterion("pay_amount <>", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThan(String value) {
            addCriterion("pay_amount >", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountGreaterThanOrEqualTo(String value) {
            addCriterion("pay_amount >=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThan(String value) {
            addCriterion("pay_amount <", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLessThanOrEqualTo(String value) {
            addCriterion("pay_amount <=", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountLike(String value) {
            addCriterion("pay_amount like", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotLike(String value) {
            addCriterion("pay_amount not like", value, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountIn(List<String> values) {
            addCriterion("pay_amount in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotIn(List<String> values) {
            addCriterion("pay_amount not in", values, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountBetween(String value1, String value2) {
            addCriterion("pay_amount between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayAmountNotBetween(String value1, String value2) {
            addCriterion("pay_amount not between", value1, value2, "payAmount");
            return (Criteria) this;
        }

        public Criteria andPayStandardIsNull() {
            addCriterion("pay_standard is null");
            return (Criteria) this;
        }

        public Criteria andPayStandardIsNotNull() {
            addCriterion("pay_standard is not null");
            return (Criteria) this;
        }

        public Criteria andPayStandardEqualTo(String value) {
            addCriterion("pay_standard =", value, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardNotEqualTo(String value) {
            addCriterion("pay_standard <>", value, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardGreaterThan(String value) {
            addCriterion("pay_standard >", value, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardGreaterThanOrEqualTo(String value) {
            addCriterion("pay_standard >=", value, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardLessThan(String value) {
            addCriterion("pay_standard <", value, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardLessThanOrEqualTo(String value) {
            addCriterion("pay_standard <=", value, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardLike(String value) {
            addCriterion("pay_standard like", value, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardNotLike(String value) {
            addCriterion("pay_standard not like", value, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardIn(List<String> values) {
            addCriterion("pay_standard in", values, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardNotIn(List<String> values) {
            addCriterion("pay_standard not in", values, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardBetween(String value1, String value2) {
            addCriterion("pay_standard between", value1, value2, "payStandard");
            return (Criteria) this;
        }

        public Criteria andPayStandardNotBetween(String value1, String value2) {
            addCriterion("pay_standard not between", value1, value2, "payStandard");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateIsNull() {
            addCriterion("bill_expire_date is null");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateIsNotNull() {
            addCriterion("bill_expire_date is not null");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateEqualTo(String value) {
            addCriterion("bill_expire_date =", value, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateNotEqualTo(String value) {
            addCriterion("bill_expire_date <>", value, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateGreaterThan(String value) {
            addCriterion("bill_expire_date >", value, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateGreaterThanOrEqualTo(String value) {
            addCriterion("bill_expire_date >=", value, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateLessThan(String value) {
            addCriterion("bill_expire_date <", value, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateLessThanOrEqualTo(String value) {
            addCriterion("bill_expire_date <=", value, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateLike(String value) {
            addCriterion("bill_expire_date like", value, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateNotLike(String value) {
            addCriterion("bill_expire_date not like", value, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateIn(List<String> values) {
            addCriterion("bill_expire_date in", values, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateNotIn(List<String> values) {
            addCriterion("bill_expire_date not in", values, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateBetween(String value1, String value2) {
            addCriterion("bill_expire_date between", value1, value2, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBillExpireDateNotBetween(String value1, String value2) {
            addCriterion("bill_expire_date not between", value1, value2, "billExpireDate");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberIsNull() {
            addCriterion("bank_account_number is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberIsNotNull() {
            addCriterion("bank_account_number is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberEqualTo(String value) {
            addCriterion("bank_account_number =", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotEqualTo(String value) {
            addCriterion("bank_account_number <>", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberGreaterThan(String value) {
            addCriterion("bank_account_number >", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_number >=", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberLessThan(String value) {
            addCriterion("bank_account_number <", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberLessThanOrEqualTo(String value) {
            addCriterion("bank_account_number <=", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberLike(String value) {
            addCriterion("bank_account_number like", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotLike(String value) {
            addCriterion("bank_account_number not like", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberIn(List<String> values) {
            addCriterion("bank_account_number in", values, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotIn(List<String> values) {
            addCriterion("bank_account_number not in", values, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberBetween(String value1, String value2) {
            addCriterion("bank_account_number between", value1, value2, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotBetween(String value1, String value2) {
            addCriterion("bank_account_number not between", value1, value2, "bankAccountNumber");
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

        public Criteria andStopReasonIsNull() {
            addCriterion("stop_reason is null");
            return (Criteria) this;
        }

        public Criteria andStopReasonIsNotNull() {
            addCriterion("stop_reason is not null");
            return (Criteria) this;
        }

        public Criteria andStopReasonEqualTo(String value) {
            addCriterion("stop_reason =", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonNotEqualTo(String value) {
            addCriterion("stop_reason <>", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonGreaterThan(String value) {
            addCriterion("stop_reason >", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonGreaterThanOrEqualTo(String value) {
            addCriterion("stop_reason >=", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonLessThan(String value) {
            addCriterion("stop_reason <", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonLessThanOrEqualTo(String value) {
            addCriterion("stop_reason <=", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonLike(String value) {
            addCriterion("stop_reason like", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonNotLike(String value) {
            addCriterion("stop_reason not like", value, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonIn(List<String> values) {
            addCriterion("stop_reason in", values, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonNotIn(List<String> values) {
            addCriterion("stop_reason not in", values, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonBetween(String value1, String value2) {
            addCriterion("stop_reason between", value1, value2, "stopReason");
            return (Criteria) this;
        }

        public Criteria andStopReasonNotBetween(String value1, String value2) {
            addCriterion("stop_reason not between", value1, value2, "stopReason");
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