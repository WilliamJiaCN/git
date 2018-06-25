package com.hivescm.tms.finance.server.dao.entity.sign;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SignRefuseDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SignRefuseDOExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeIsNull() {
            addCriterion("refuse_type is null");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeIsNotNull() {
            addCriterion("refuse_type is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeEqualTo(Integer value) {
            addCriterion("refuse_type =", value, "refuseType");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeNotEqualTo(Integer value) {
            addCriterion("refuse_type <>", value, "refuseType");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeGreaterThan(Integer value) {
            addCriterion("refuse_type >", value, "refuseType");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("refuse_type >=", value, "refuseType");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeLessThan(Integer value) {
            addCriterion("refuse_type <", value, "refuseType");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeLessThanOrEqualTo(Integer value) {
            addCriterion("refuse_type <=", value, "refuseType");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeIn(List<Integer> values) {
            addCriterion("refuse_type in", values, "refuseType");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeNotIn(List<Integer> values) {
            addCriterion("refuse_type not in", values, "refuseType");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeBetween(Integer value1, Integer value2) {
            addCriterion("refuse_type between", value1, value2, "refuseType");
            return (Criteria) this;
        }

        public Criteria andRefuseTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("refuse_type not between", value1, value2, "refuseType");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdIsNull() {
            addCriterion("sales_return_id is null");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdIsNotNull() {
            addCriterion("sales_return_id is not null");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdEqualTo(Long value) {
            addCriterion("sales_return_id =", value, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdNotEqualTo(Long value) {
            addCriterion("sales_return_id <>", value, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdGreaterThan(Long value) {
            addCriterion("sales_return_id >", value, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sales_return_id >=", value, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdLessThan(Long value) {
            addCriterion("sales_return_id <", value, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdLessThanOrEqualTo(Long value) {
            addCriterion("sales_return_id <=", value, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdIn(List<Long> values) {
            addCriterion("sales_return_id in", values, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdNotIn(List<Long> values) {
            addCriterion("sales_return_id not in", values, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdBetween(Long value1, Long value2) {
            addCriterion("sales_return_id between", value1, value2, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andSalesReturnIdNotBetween(Long value1, Long value2) {
            addCriterion("sales_return_id not between", value1, value2, "salesReturnId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdIsNull() {
            addCriterion("refuse_waybill_id is null");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdIsNotNull() {
            addCriterion("refuse_waybill_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdEqualTo(Long value) {
            addCriterion("refuse_waybill_id =", value, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdNotEqualTo(Long value) {
            addCriterion("refuse_waybill_id <>", value, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdGreaterThan(Long value) {
            addCriterion("refuse_waybill_id >", value, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdGreaterThanOrEqualTo(Long value) {
            addCriterion("refuse_waybill_id >=", value, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdLessThan(Long value) {
            addCriterion("refuse_waybill_id <", value, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdLessThanOrEqualTo(Long value) {
            addCriterion("refuse_waybill_id <=", value, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdIn(List<Long> values) {
            addCriterion("refuse_waybill_id in", values, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdNotIn(List<Long> values) {
            addCriterion("refuse_waybill_id not in", values, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdBetween(Long value1, Long value2) {
            addCriterion("refuse_waybill_id between", value1, value2, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andRefuseWaybillIdNotBetween(Long value1, Long value2) {
            addCriterion("refuse_waybill_id not between", value1, value2, "refuseWaybillId");
            return (Criteria) this;
        }

        public Criteria andSignIdIsNull() {
            addCriterion("sign_id is null");
            return (Criteria) this;
        }

        public Criteria andSignIdIsNotNull() {
            addCriterion("sign_id is not null");
            return (Criteria) this;
        }

        public Criteria andSignIdEqualTo(Long value) {
            addCriterion("sign_id =", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdNotEqualTo(Long value) {
            addCriterion("sign_id <>", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdGreaterThan(Long value) {
            addCriterion("sign_id >", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdGreaterThanOrEqualTo(Long value) {
            addCriterion("sign_id >=", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdLessThan(Long value) {
            addCriterion("sign_id <", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdLessThanOrEqualTo(Long value) {
            addCriterion("sign_id <=", value, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdIn(List<Long> values) {
            addCriterion("sign_id in", values, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdNotIn(List<Long> values) {
            addCriterion("sign_id not in", values, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdBetween(Long value1, Long value2) {
            addCriterion("sign_id between", value1, value2, "signId");
            return (Criteria) this;
        }

        public Criteria andSignIdNotBetween(Long value1, Long value2) {
            addCriterion("sign_id not between", value1, value2, "signId");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeIsNull() {
            addCriterion("refuse_time is null");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeIsNotNull() {
            addCriterion("refuse_time is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeEqualTo(Date value) {
            addCriterion("refuse_time =", value, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeNotEqualTo(Date value) {
            addCriterion("refuse_time <>", value, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeGreaterThan(Date value) {
            addCriterion("refuse_time >", value, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("refuse_time >=", value, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeLessThan(Date value) {
            addCriterion("refuse_time <", value, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeLessThanOrEqualTo(Date value) {
            addCriterion("refuse_time <=", value, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeIn(List<Date> values) {
            addCriterion("refuse_time in", values, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeNotIn(List<Date> values) {
            addCriterion("refuse_time not in", values, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeBetween(Date value1, Date value2) {
            addCriterion("refuse_time between", value1, value2, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefuseTimeNotBetween(Date value1, Date value2) {
            addCriterion("refuse_time not between", value1, value2, "refuseTime");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleIsNull() {
            addCriterion("refuse_people is null");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleIsNotNull() {
            addCriterion("refuse_people is not null");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleEqualTo(String value) {
            addCriterion("refuse_people =", value, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleNotEqualTo(String value) {
            addCriterion("refuse_people <>", value, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleGreaterThan(String value) {
            addCriterion("refuse_people >", value, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleGreaterThanOrEqualTo(String value) {
            addCriterion("refuse_people >=", value, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleLessThan(String value) {
            addCriterion("refuse_people <", value, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleLessThanOrEqualTo(String value) {
            addCriterion("refuse_people <=", value, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleLike(String value) {
            addCriterion("refuse_people like", value, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleNotLike(String value) {
            addCriterion("refuse_people not like", value, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleIn(List<String> values) {
            addCriterion("refuse_people in", values, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleNotIn(List<String> values) {
            addCriterion("refuse_people not in", values, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleBetween(String value1, String value2) {
            addCriterion("refuse_people between", value1, value2, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePeopleNotBetween(String value1, String value2) {
            addCriterion("refuse_people not between", value1, value2, "refusePeople");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneIsNull() {
            addCriterion("refuse_phone is null");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneIsNotNull() {
            addCriterion("refuse_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneEqualTo(String value) {
            addCriterion("refuse_phone =", value, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneNotEqualTo(String value) {
            addCriterion("refuse_phone <>", value, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneGreaterThan(String value) {
            addCriterion("refuse_phone >", value, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("refuse_phone >=", value, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneLessThan(String value) {
            addCriterion("refuse_phone <", value, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneLessThanOrEqualTo(String value) {
            addCriterion("refuse_phone <=", value, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneLike(String value) {
            addCriterion("refuse_phone like", value, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneNotLike(String value) {
            addCriterion("refuse_phone not like", value, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneIn(List<String> values) {
            addCriterion("refuse_phone in", values, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneNotIn(List<String> values) {
            addCriterion("refuse_phone not in", values, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneBetween(String value1, String value2) {
            addCriterion("refuse_phone between", value1, value2, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefusePhoneNotBetween(String value1, String value2) {
            addCriterion("refuse_phone not between", value1, value2, "refusePhone");
            return (Criteria) this;
        }

        public Criteria andRefuseCardIsNull() {
            addCriterion("refuse_card is null");
            return (Criteria) this;
        }

        public Criteria andRefuseCardIsNotNull() {
            addCriterion("refuse_card is not null");
            return (Criteria) this;
        }

        public Criteria andRefuseCardEqualTo(String value) {
            addCriterion("refuse_card =", value, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardNotEqualTo(String value) {
            addCriterion("refuse_card <>", value, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardGreaterThan(String value) {
            addCriterion("refuse_card >", value, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardGreaterThanOrEqualTo(String value) {
            addCriterion("refuse_card >=", value, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardLessThan(String value) {
            addCriterion("refuse_card <", value, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardLessThanOrEqualTo(String value) {
            addCriterion("refuse_card <=", value, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardLike(String value) {
            addCriterion("refuse_card like", value, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardNotLike(String value) {
            addCriterion("refuse_card not like", value, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardIn(List<String> values) {
            addCriterion("refuse_card in", values, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardNotIn(List<String> values) {
            addCriterion("refuse_card not in", values, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardBetween(String value1, String value2) {
            addCriterion("refuse_card between", value1, value2, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andRefuseCardNotBetween(String value1, String value2) {
            addCriterion("refuse_card not between", value1, value2, "refuseCard");
            return (Criteria) this;
        }

        public Criteria andWaybillIdIsNull() {
            addCriterion("waybill_id is null");
            return (Criteria) this;
        }

        public Criteria andWaybillIdIsNotNull() {
            addCriterion("waybill_id is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillIdEqualTo(Long value) {
            addCriterion("waybill_id =", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdNotEqualTo(Long value) {
            addCriterion("waybill_id <>", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdGreaterThan(Long value) {
            addCriterion("waybill_id >", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdGreaterThanOrEqualTo(Long value) {
            addCriterion("waybill_id >=", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdLessThan(Long value) {
            addCriterion("waybill_id <", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdLessThanOrEqualTo(Long value) {
            addCriterion("waybill_id <=", value, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdIn(List<Long> values) {
            addCriterion("waybill_id in", values, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdNotIn(List<Long> values) {
            addCriterion("waybill_id not in", values, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdBetween(Long value1, Long value2) {
            addCriterion("waybill_id between", value1, value2, "waybillId");
            return (Criteria) this;
        }

        public Criteria andWaybillIdNotBetween(Long value1, Long value2) {
            addCriterion("waybill_id not between", value1, value2, "waybillId");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNull() {
            addCriterion("order_code is null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIsNotNull() {
            addCriterion("order_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCodeEqualTo(String value) {
            addCriterion("order_code =", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotEqualTo(String value) {
            addCriterion("order_code <>", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThan(String value) {
            addCriterion("order_code >", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeGreaterThanOrEqualTo(String value) {
            addCriterion("order_code >=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThan(String value) {
            addCriterion("order_code <", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLessThanOrEqualTo(String value) {
            addCriterion("order_code <=", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeLike(String value) {
            addCriterion("order_code like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotLike(String value) {
            addCriterion("order_code not like", value, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeIn(List<String> values) {
            addCriterion("order_code in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotIn(List<String> values) {
            addCriterion("order_code not in", values, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeBetween(String value1, String value2) {
            addCriterion("order_code between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andOrderCodeNotBetween(String value1, String value2) {
            addCriterion("order_code not between", value1, value2, "orderCode");
            return (Criteria) this;
        }

        public Criteria andCarrierNameIsNull() {
            addCriterion("carrier_name is null");
            return (Criteria) this;
        }

        public Criteria andCarrierNameIsNotNull() {
            addCriterion("carrier_name is not null");
            return (Criteria) this;
        }

        public Criteria andCarrierNameEqualTo(String value) {
            addCriterion("carrier_name =", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameNotEqualTo(String value) {
            addCriterion("carrier_name <>", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameGreaterThan(String value) {
            addCriterion("carrier_name >", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameGreaterThanOrEqualTo(String value) {
            addCriterion("carrier_name >=", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameLessThan(String value) {
            addCriterion("carrier_name <", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameLessThanOrEqualTo(String value) {
            addCriterion("carrier_name <=", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameLike(String value) {
            addCriterion("carrier_name like", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameNotLike(String value) {
            addCriterion("carrier_name not like", value, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameIn(List<String> values) {
            addCriterion("carrier_name in", values, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameNotIn(List<String> values) {
            addCriterion("carrier_name not in", values, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameBetween(String value1, String value2) {
            addCriterion("carrier_name between", value1, value2, "carrierName");
            return (Criteria) this;
        }

        public Criteria andCarrierNameNotBetween(String value1, String value2) {
            addCriterion("carrier_name not between", value1, value2, "carrierName");
            return (Criteria) this;
        }

        public Criteria andOrderPayIsNull() {
            addCriterion("order_pay is null");
            return (Criteria) this;
        }

        public Criteria andOrderPayIsNotNull() {
            addCriterion("order_pay is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPayEqualTo(BigDecimal value) {
            addCriterion("order_pay =", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayNotEqualTo(BigDecimal value) {
            addCriterion("order_pay <>", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayGreaterThan(BigDecimal value) {
            addCriterion("order_pay >", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_pay >=", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayLessThan(BigDecimal value) {
            addCriterion("order_pay <", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_pay <=", value, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayIn(List<BigDecimal> values) {
            addCriterion("order_pay in", values, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayNotIn(List<BigDecimal> values) {
            addCriterion("order_pay not in", values, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_pay between", value1, value2, "orderPay");
            return (Criteria) this;
        }

        public Criteria andOrderPayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_pay not between", value1, value2, "orderPay");
            return (Criteria) this;
        }

        public Criteria andRefusePayIsNull() {
            addCriterion("refuse_pay is null");
            return (Criteria) this;
        }

        public Criteria andRefusePayIsNotNull() {
            addCriterion("refuse_pay is not null");
            return (Criteria) this;
        }

        public Criteria andRefusePayEqualTo(BigDecimal value) {
            addCriterion("refuse_pay =", value, "refusePay");
            return (Criteria) this;
        }

        public Criteria andRefusePayNotEqualTo(BigDecimal value) {
            addCriterion("refuse_pay <>", value, "refusePay");
            return (Criteria) this;
        }

        public Criteria andRefusePayGreaterThan(BigDecimal value) {
            addCriterion("refuse_pay >", value, "refusePay");
            return (Criteria) this;
        }

        public Criteria andRefusePayGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refuse_pay >=", value, "refusePay");
            return (Criteria) this;
        }

        public Criteria andRefusePayLessThan(BigDecimal value) {
            addCriterion("refuse_pay <", value, "refusePay");
            return (Criteria) this;
        }

        public Criteria andRefusePayLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refuse_pay <=", value, "refusePay");
            return (Criteria) this;
        }

        public Criteria andRefusePayIn(List<BigDecimal> values) {
            addCriterion("refuse_pay in", values, "refusePay");
            return (Criteria) this;
        }

        public Criteria andRefusePayNotIn(List<BigDecimal> values) {
            addCriterion("refuse_pay not in", values, "refusePay");
            return (Criteria) this;
        }

        public Criteria andRefusePayBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refuse_pay between", value1, value2, "refusePay");
            return (Criteria) this;
        }

        public Criteria andRefusePayNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refuse_pay not between", value1, value2, "refusePay");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeIsNull() {
            addCriterion("distribution_type is null");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeIsNotNull() {
            addCriterion("distribution_type is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeEqualTo(Integer value) {
            addCriterion("distribution_type =", value, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeNotEqualTo(Integer value) {
            addCriterion("distribution_type <>", value, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeGreaterThan(Integer value) {
            addCriterion("distribution_type >", value, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("distribution_type >=", value, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeLessThan(Integer value) {
            addCriterion("distribution_type <", value, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeLessThanOrEqualTo(Integer value) {
            addCriterion("distribution_type <=", value, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeIn(List<Integer> values) {
            addCriterion("distribution_type in", values, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeNotIn(List<Integer> values) {
            addCriterion("distribution_type not in", values, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeBetween(Integer value1, Integer value2) {
            addCriterion("distribution_type between", value1, value2, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDistributionTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("distribution_type not between", value1, value2, "distributionType");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeIsNull() {
            addCriterion("dispacher_code is null");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeIsNotNull() {
            addCriterion("dispacher_code is not null");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeEqualTo(Long value) {
            addCriterion("dispacher_code =", value, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeNotEqualTo(Long value) {
            addCriterion("dispacher_code <>", value, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeGreaterThan(Long value) {
            addCriterion("dispacher_code >", value, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("dispacher_code >=", value, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeLessThan(Long value) {
            addCriterion("dispacher_code <", value, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeLessThanOrEqualTo(Long value) {
            addCriterion("dispacher_code <=", value, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeIn(List<Long> values) {
            addCriterion("dispacher_code in", values, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeNotIn(List<Long> values) {
            addCriterion("dispacher_code not in", values, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeBetween(Long value1, Long value2) {
            addCriterion("dispacher_code between", value1, value2, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andDispacherCodeNotBetween(Long value1, Long value2) {
            addCriterion("dispacher_code not between", value1, value2, "dispacherCode");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameIsNull() {
            addCriterion("invoiceway_name is null");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameIsNotNull() {
            addCriterion("invoiceway_name is not null");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameEqualTo(String value) {
            addCriterion("invoiceway_name =", value, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameNotEqualTo(String value) {
            addCriterion("invoiceway_name <>", value, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameGreaterThan(String value) {
            addCriterion("invoiceway_name >", value, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameGreaterThanOrEqualTo(String value) {
            addCriterion("invoiceway_name >=", value, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameLessThan(String value) {
            addCriterion("invoiceway_name <", value, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameLessThanOrEqualTo(String value) {
            addCriterion("invoiceway_name <=", value, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameLike(String value) {
            addCriterion("invoiceway_name like", value, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameNotLike(String value) {
            addCriterion("invoiceway_name not like", value, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameIn(List<String> values) {
            addCriterion("invoiceway_name in", values, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameNotIn(List<String> values) {
            addCriterion("invoiceway_name not in", values, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameBetween(String value1, String value2) {
            addCriterion("invoiceway_name between", value1, value2, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andInvoicewayNameNotBetween(String value1, String value2) {
            addCriterion("invoiceway_name not between", value1, value2, "invoicewayName");
            return (Criteria) this;
        }

        public Criteria andCarNumberIsNull() {
            addCriterion("car_number is null");
            return (Criteria) this;
        }

        public Criteria andCarNumberIsNotNull() {
            addCriterion("car_number is not null");
            return (Criteria) this;
        }

        public Criteria andCarNumberEqualTo(String value) {
            addCriterion("car_number =", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotEqualTo(String value) {
            addCriterion("car_number <>", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThan(String value) {
            addCriterion("car_number >", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberGreaterThanOrEqualTo(String value) {
            addCriterion("car_number >=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThan(String value) {
            addCriterion("car_number <", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLessThanOrEqualTo(String value) {
            addCriterion("car_number <=", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberLike(String value) {
            addCriterion("car_number like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotLike(String value) {
            addCriterion("car_number not like", value, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberIn(List<String> values) {
            addCriterion("car_number in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotIn(List<String> values) {
            addCriterion("car_number not in", values, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberBetween(String value1, String value2) {
            addCriterion("car_number between", value1, value2, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNumberNotBetween(String value1, String value2) {
            addCriterion("car_number not between", value1, value2, "carNumber");
            return (Criteria) this;
        }

        public Criteria andCarNameIsNull() {
            addCriterion("car_name is null");
            return (Criteria) this;
        }

        public Criteria andCarNameIsNotNull() {
            addCriterion("car_name is not null");
            return (Criteria) this;
        }

        public Criteria andCarNameEqualTo(String value) {
            addCriterion("car_name =", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotEqualTo(String value) {
            addCriterion("car_name <>", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameGreaterThan(String value) {
            addCriterion("car_name >", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameGreaterThanOrEqualTo(String value) {
            addCriterion("car_name >=", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameLessThan(String value) {
            addCriterion("car_name <", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameLessThanOrEqualTo(String value) {
            addCriterion("car_name <=", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameLike(String value) {
            addCriterion("car_name like", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotLike(String value) {
            addCriterion("car_name not like", value, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameIn(List<String> values) {
            addCriterion("car_name in", values, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotIn(List<String> values) {
            addCriterion("car_name not in", values, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameBetween(String value1, String value2) {
            addCriterion("car_name between", value1, value2, "carName");
            return (Criteria) this;
        }

        public Criteria andCarNameNotBetween(String value1, String value2) {
            addCriterion("car_name not between", value1, value2, "carName");
            return (Criteria) this;
        }

        public Criteria andCarPhoneIsNull() {
            addCriterion("car_phone is null");
            return (Criteria) this;
        }

        public Criteria andCarPhoneIsNotNull() {
            addCriterion("car_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCarPhoneEqualTo(String value) {
            addCriterion("car_phone =", value, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneNotEqualTo(String value) {
            addCriterion("car_phone <>", value, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneGreaterThan(String value) {
            addCriterion("car_phone >", value, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("car_phone >=", value, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneLessThan(String value) {
            addCriterion("car_phone <", value, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneLessThanOrEqualTo(String value) {
            addCriterion("car_phone <=", value, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneLike(String value) {
            addCriterion("car_phone like", value, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneNotLike(String value) {
            addCriterion("car_phone not like", value, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneIn(List<String> values) {
            addCriterion("car_phone in", values, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneNotIn(List<String> values) {
            addCriterion("car_phone not in", values, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneBetween(String value1, String value2) {
            addCriterion("car_phone between", value1, value2, "carPhone");
            return (Criteria) this;
        }

        public Criteria andCarPhoneNotBetween(String value1, String value2) {
            addCriterion("car_phone not between", value1, value2, "carPhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyIsNull() {
            addCriterion("invoice_company is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyIsNotNull() {
            addCriterion("invoice_company is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyEqualTo(String value) {
            addCriterion("invoice_company =", value, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyNotEqualTo(String value) {
            addCriterion("invoice_company <>", value, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyGreaterThan(String value) {
            addCriterion("invoice_company >", value, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_company >=", value, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyLessThan(String value) {
            addCriterion("invoice_company <", value, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyLessThanOrEqualTo(String value) {
            addCriterion("invoice_company <=", value, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyLike(String value) {
            addCriterion("invoice_company like", value, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyNotLike(String value) {
            addCriterion("invoice_company not like", value, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyIn(List<String> values) {
            addCriterion("invoice_company in", values, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyNotIn(List<String> values) {
            addCriterion("invoice_company not in", values, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyBetween(String value1, String value2) {
            addCriterion("invoice_company between", value1, value2, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceCompanyNotBetween(String value1, String value2) {
            addCriterion("invoice_company not between", value1, value2, "invoiceCompany");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIsNull() {
            addCriterion("invoice_name is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIsNotNull() {
            addCriterion("invoice_name is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameEqualTo(String value) {
            addCriterion("invoice_name =", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotEqualTo(String value) {
            addCriterion("invoice_name <>", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameGreaterThan(String value) {
            addCriterion("invoice_name >", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_name >=", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLessThan(String value) {
            addCriterion("invoice_name <", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLessThanOrEqualTo(String value) {
            addCriterion("invoice_name <=", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameLike(String value) {
            addCriterion("invoice_name like", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotLike(String value) {
            addCriterion("invoice_name not like", value, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameIn(List<String> values) {
            addCriterion("invoice_name in", values, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotIn(List<String> values) {
            addCriterion("invoice_name not in", values, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameBetween(String value1, String value2) {
            addCriterion("invoice_name between", value1, value2, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNameNotBetween(String value1, String value2) {
            addCriterion("invoice_name not between", value1, value2, "invoiceName");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneIsNull() {
            addCriterion("invoice_name_phone is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneIsNotNull() {
            addCriterion("invoice_name_phone is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneEqualTo(String value) {
            addCriterion("invoice_name_phone =", value, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneNotEqualTo(String value) {
            addCriterion("invoice_name_phone <>", value, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneGreaterThan(String value) {
            addCriterion("invoice_name_phone >", value, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_name_phone >=", value, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneLessThan(String value) {
            addCriterion("invoice_name_phone <", value, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneLessThanOrEqualTo(String value) {
            addCriterion("invoice_name_phone <=", value, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneLike(String value) {
            addCriterion("invoice_name_phone like", value, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneNotLike(String value) {
            addCriterion("invoice_name_phone not like", value, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneIn(List<String> values) {
            addCriterion("invoice_name_phone in", values, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneNotIn(List<String> values) {
            addCriterion("invoice_name_phone not in", values, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneBetween(String value1, String value2) {
            addCriterion("invoice_name_phone between", value1, value2, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceNamePhoneNotBetween(String value1, String value2) {
            addCriterion("invoice_name_phone not between", value1, value2, "invoiceNamePhone");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressIsNull() {
            addCriterion("invoice_address is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressIsNotNull() {
            addCriterion("invoice_address is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressEqualTo(String value) {
            addCriterion("invoice_address =", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressNotEqualTo(String value) {
            addCriterion("invoice_address <>", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressGreaterThan(String value) {
            addCriterion("invoice_address >", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_address >=", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressLessThan(String value) {
            addCriterion("invoice_address <", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressLessThanOrEqualTo(String value) {
            addCriterion("invoice_address <=", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressLike(String value) {
            addCriterion("invoice_address like", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressNotLike(String value) {
            addCriterion("invoice_address not like", value, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressIn(List<String> values) {
            addCriterion("invoice_address in", values, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressNotIn(List<String> values) {
            addCriterion("invoice_address not in", values, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressBetween(String value1, String value2) {
            addCriterion("invoice_address between", value1, value2, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andInvoiceAddressNotBetween(String value1, String value2) {
            addCriterion("invoice_address not between", value1, value2, "invoiceAddress");
            return (Criteria) this;
        }

        public Criteria andLoadedIsNull() {
            addCriterion("loaded is null");
            return (Criteria) this;
        }

        public Criteria andLoadedIsNotNull() {
            addCriterion("loaded is not null");
            return (Criteria) this;
        }

        public Criteria andLoadedEqualTo(Boolean value) {
            addCriterion("loaded =", value, "loaded");
            return (Criteria) this;
        }

        public Criteria andLoadedNotEqualTo(Boolean value) {
            addCriterion("loaded <>", value, "loaded");
            return (Criteria) this;
        }

        public Criteria andLoadedGreaterThan(Boolean value) {
            addCriterion("loaded >", value, "loaded");
            return (Criteria) this;
        }

        public Criteria andLoadedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("loaded >=", value, "loaded");
            return (Criteria) this;
        }

        public Criteria andLoadedLessThan(Boolean value) {
            addCriterion("loaded <", value, "loaded");
            return (Criteria) this;
        }

        public Criteria andLoadedLessThanOrEqualTo(Boolean value) {
            addCriterion("loaded <=", value, "loaded");
            return (Criteria) this;
        }

        public Criteria andLoadedIn(List<Boolean> values) {
            addCriterion("loaded in", values, "loaded");
            return (Criteria) this;
        }

        public Criteria andLoadedNotIn(List<Boolean> values) {
            addCriterion("loaded not in", values, "loaded");
            return (Criteria) this;
        }

        public Criteria andLoadedBetween(Boolean value1, Boolean value2) {
            addCriterion("loaded between", value1, value2, "loaded");
            return (Criteria) this;
        }

        public Criteria andLoadedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("loaded not between", value1, value2, "loaded");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Long value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Long value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Long value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Long value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Long> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Long> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Long value1, Long value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(Integer value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(Integer value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(Integer value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(Integer value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(Integer value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<Integer> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<Integer> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(Integer value1, Integer value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
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

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(Integer value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(Integer value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(Integer value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(Integer value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(Integer value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<Integer> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<Integer> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(Integer value1, Integer value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(Integer value1, Integer value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
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

        public Criteria andUpdateTimeEqualTo(Long value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Long value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Long value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Long> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     */
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