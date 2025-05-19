package com.xc.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConvertBondApplyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConvertBondApplyExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNull() {
            addCriterion("agent_id is null");
            return (Criteria) this;
        }

        public Criteria andAgentIdIsNotNull() {
            addCriterion("agent_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgentIdEqualTo(Integer value) {
            addCriterion("agent_id =", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotEqualTo(Integer value) {
            addCriterion("agent_id <>", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThan(Integer value) {
            addCriterion("agent_id >", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("agent_id >=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThan(Integer value) {
            addCriterion("agent_id <", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdLessThanOrEqualTo(Integer value) {
            addCriterion("agent_id <=", value, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdIn(List<Integer> values) {
            addCriterion("agent_id in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotIn(List<Integer> values) {
            addCriterion("agent_id not in", values, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdBetween(Integer value1, Integer value2) {
            addCriterion("agent_id between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andAgentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("agent_id not between", value1, value2, "agentId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andBondIdIsNull() {
            addCriterion("bond_id is null");
            return (Criteria) this;
        }

        public Criteria andBondIdIsNotNull() {
            addCriterion("bond_id is not null");
            return (Criteria) this;
        }

        public Criteria andBondIdEqualTo(Integer value) {
            addCriterion("bond_id =", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdNotEqualTo(Integer value) {
            addCriterion("bond_id <>", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdGreaterThan(Integer value) {
            addCriterion("bond_id >", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("bond_id >=", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdLessThan(Integer value) {
            addCriterion("bond_id <", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdLessThanOrEqualTo(Integer value) {
            addCriterion("bond_id <=", value, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdIn(List<Integer> values) {
            addCriterion("bond_id in", values, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdNotIn(List<Integer> values) {
            addCriterion("bond_id not in", values, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdBetween(Integer value1, Integer value2) {
            addCriterion("bond_id between", value1, value2, "bondId");
            return (Criteria) this;
        }

        public Criteria andBondIdNotBetween(Integer value1, Integer value2) {
            addCriterion("bond_id not between", value1, value2, "bondId");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyIsNull() {
            addCriterion("apply_money is null");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyIsNotNull() {
            addCriterion("apply_money is not null");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyEqualTo(BigDecimal value) {
            addCriterion("apply_money =", value, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyNotEqualTo(BigDecimal value) {
            addCriterion("apply_money <>", value, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyGreaterThan(BigDecimal value) {
            addCriterion("apply_money >", value, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("apply_money >=", value, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyLessThan(BigDecimal value) {
            addCriterion("apply_money <", value, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("apply_money <=", value, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyIn(List<BigDecimal> values) {
            addCriterion("apply_money in", values, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyNotIn(List<BigDecimal> values) {
            addCriterion("apply_money not in", values, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apply_money between", value1, value2, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apply_money not between", value1, value2, "applyMoney");
            return (Criteria) this;
        }

        public Criteria andApplyNumIsNull() {
            addCriterion("apply_num is null");
            return (Criteria) this;
        }

        public Criteria andApplyNumIsNotNull() {
            addCriterion("apply_num is not null");
            return (Criteria) this;
        }

        public Criteria andApplyNumEqualTo(Integer value) {
            addCriterion("apply_num =", value, "applyNum");
            return (Criteria) this;
        }

        public Criteria andApplyNumNotEqualTo(Integer value) {
            addCriterion("apply_num <>", value, "applyNum");
            return (Criteria) this;
        }

        public Criteria andApplyNumGreaterThan(Integer value) {
            addCriterion("apply_num >", value, "applyNum");
            return (Criteria) this;
        }

        public Criteria andApplyNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_num >=", value, "applyNum");
            return (Criteria) this;
        }

        public Criteria andApplyNumLessThan(Integer value) {
            addCriterion("apply_num <", value, "applyNum");
            return (Criteria) this;
        }

        public Criteria andApplyNumLessThanOrEqualTo(Integer value) {
            addCriterion("apply_num <=", value, "applyNum");
            return (Criteria) this;
        }

        public Criteria andApplyNumIn(List<Integer> values) {
            addCriterion("apply_num in", values, "applyNum");
            return (Criteria) this;
        }

        public Criteria andApplyNumNotIn(List<Integer> values) {
            addCriterion("apply_num not in", values, "applyNum");
            return (Criteria) this;
        }

        public Criteria andApplyNumBetween(Integer value1, Integer value2) {
            addCriterion("apply_num between", value1, value2, "applyNum");
            return (Criteria) this;
        }

        public Criteria andApplyNumNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_num not between", value1, value2, "applyNum");
            return (Criteria) this;
        }

        public Criteria andSucNumIsNull() {
            addCriterion("suc_num is null");
            return (Criteria) this;
        }

        public Criteria andSucNumIsNotNull() {
            addCriterion("suc_num is not null");
            return (Criteria) this;
        }

        public Criteria andSucNumEqualTo(Integer value) {
            addCriterion("suc_num =", value, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucNumNotEqualTo(Integer value) {
            addCriterion("suc_num <>", value, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucNumGreaterThan(Integer value) {
            addCriterion("suc_num >", value, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("suc_num >=", value, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucNumLessThan(Integer value) {
            addCriterion("suc_num <", value, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucNumLessThanOrEqualTo(Integer value) {
            addCriterion("suc_num <=", value, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucNumIn(List<Integer> values) {
            addCriterion("suc_num in", values, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucNumNotIn(List<Integer> values) {
            addCriterion("suc_num not in", values, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucNumBetween(Integer value1, Integer value2) {
            addCriterion("suc_num between", value1, value2, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucNumNotBetween(Integer value1, Integer value2) {
            addCriterion("suc_num not between", value1, value2, "sucNum");
            return (Criteria) this;
        }

        public Criteria andSucMonyIsNull() {
            addCriterion("suc_mony is null");
            return (Criteria) this;
        }

        public Criteria andSucMonyIsNotNull() {
            addCriterion("suc_mony is not null");
            return (Criteria) this;
        }

        public Criteria andSucMonyEqualTo(BigDecimal value) {
            addCriterion("suc_mony =", value, "sucMony");
            return (Criteria) this;
        }

        public Criteria andSucMonyNotEqualTo(BigDecimal value) {
            addCriterion("suc_mony <>", value, "sucMony");
            return (Criteria) this;
        }

        public Criteria andSucMonyGreaterThan(BigDecimal value) {
            addCriterion("suc_mony >", value, "sucMony");
            return (Criteria) this;
        }

        public Criteria andSucMonyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("suc_mony >=", value, "sucMony");
            return (Criteria) this;
        }

        public Criteria andSucMonyLessThan(BigDecimal value) {
            addCriterion("suc_mony <", value, "sucMony");
            return (Criteria) this;
        }

        public Criteria andSucMonyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("suc_mony <=", value, "sucMony");
            return (Criteria) this;
        }

        public Criteria andSucMonyIn(List<BigDecimal> values) {
            addCriterion("suc_mony in", values, "sucMony");
            return (Criteria) this;
        }

        public Criteria andSucMonyNotIn(List<BigDecimal> values) {
            addCriterion("suc_mony not in", values, "sucMony");
            return (Criteria) this;
        }

        public Criteria andSucMonyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("suc_mony between", value1, value2, "sucMony");
            return (Criteria) this;
        }

        public Criteria andSucMonyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("suc_mony not between", value1, value2, "sucMony");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNull() {
            addCriterion("apply_date is null");
            return (Criteria) this;
        }

        public Criteria andApplyDateIsNotNull() {
            addCriterion("apply_date is not null");
            return (Criteria) this;
        }

        public Criteria andApplyDateEqualTo(Date value) {
            addCriterion("apply_date =", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotEqualTo(Date value) {
            addCriterion("apply_date <>", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThan(Date value) {
            addCriterion("apply_date >", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateGreaterThanOrEqualTo(Date value) {
            addCriterion("apply_date >=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThan(Date value) {
            addCriterion("apply_date <", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateLessThanOrEqualTo(Date value) {
            addCriterion("apply_date <=", value, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateIn(List<Date> values) {
            addCriterion("apply_date in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotIn(List<Date> values) {
            addCriterion("apply_date not in", values, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateBetween(Date value1, Date value2) {
            addCriterion("apply_date between", value1, value2, "applyDate");
            return (Criteria) this;
        }

        public Criteria andApplyDateNotBetween(Date value1, Date value2) {
            addCriterion("apply_date not between", value1, value2, "applyDate");
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

        public Criteria andRefundMonyIsNull() {
            addCriterion("refund_mony is null");
            return (Criteria) this;
        }

        public Criteria andRefundMonyIsNotNull() {
            addCriterion("refund_mony is not null");
            return (Criteria) this;
        }

        public Criteria andRefundMonyEqualTo(BigDecimal value) {
            addCriterion("refund_mony =", value, "refundMony");
            return (Criteria) this;
        }

        public Criteria andRefundMonyNotEqualTo(BigDecimal value) {
            addCriterion("refund_mony <>", value, "refundMony");
            return (Criteria) this;
        }

        public Criteria andRefundMonyGreaterThan(BigDecimal value) {
            addCriterion("refund_mony >", value, "refundMony");
            return (Criteria) this;
        }

        public Criteria andRefundMonyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_mony >=", value, "refundMony");
            return (Criteria) this;
        }

        public Criteria andRefundMonyLessThan(BigDecimal value) {
            addCriterion("refund_mony <", value, "refundMony");
            return (Criteria) this;
        }

        public Criteria andRefundMonyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("refund_mony <=", value, "refundMony");
            return (Criteria) this;
        }

        public Criteria andRefundMonyIn(List<BigDecimal> values) {
            addCriterion("refund_mony in", values, "refundMony");
            return (Criteria) this;
        }

        public Criteria andRefundMonyNotIn(List<BigDecimal> values) {
            addCriterion("refund_mony not in", values, "refundMony");
            return (Criteria) this;
        }

        public Criteria andRefundMonyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_mony between", value1, value2, "refundMony");
            return (Criteria) this;
        }

        public Criteria andRefundMonyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("refund_mony not between", value1, value2, "refundMony");
            return (Criteria) this;
        }

        public Criteria andSucDateIsNull() {
            addCriterion("suc_date is null");
            return (Criteria) this;
        }

        public Criteria andSucDateIsNotNull() {
            addCriterion("suc_date is not null");
            return (Criteria) this;
        }

        public Criteria andSucDateEqualTo(Date value) {
            addCriterion("suc_date =", value, "sucDate");
            return (Criteria) this;
        }

        public Criteria andSucDateNotEqualTo(Date value) {
            addCriterion("suc_date <>", value, "sucDate");
            return (Criteria) this;
        }

        public Criteria andSucDateGreaterThan(Date value) {
            addCriterion("suc_date >", value, "sucDate");
            return (Criteria) this;
        }

        public Criteria andSucDateGreaterThanOrEqualTo(Date value) {
            addCriterion("suc_date >=", value, "sucDate");
            return (Criteria) this;
        }

        public Criteria andSucDateLessThan(Date value) {
            addCriterion("suc_date <", value, "sucDate");
            return (Criteria) this;
        }

        public Criteria andSucDateLessThanOrEqualTo(Date value) {
            addCriterion("suc_date <=", value, "sucDate");
            return (Criteria) this;
        }

        public Criteria andSucDateIn(List<Date> values) {
            addCriterion("suc_date in", values, "sucDate");
            return (Criteria) this;
        }

        public Criteria andSucDateNotIn(List<Date> values) {
            addCriterion("suc_date not in", values, "sucDate");
            return (Criteria) this;
        }

        public Criteria andSucDateBetween(Date value1, Date value2) {
            addCriterion("suc_date between", value1, value2, "sucDate");
            return (Criteria) this;
        }

        public Criteria andSucDateNotBetween(Date value1, Date value2) {
            addCriterion("suc_date not between", value1, value2, "sucDate");
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