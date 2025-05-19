package com.xc.pojo;

import java.util.ArrayList;
import java.util.List;

public class ListsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ListsExample() {
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

        public Criteria andListsIdIsNull() {
            addCriterion("lists_id is null");
            return (Criteria) this;
        }

        public Criteria andListsIdIsNotNull() {
            addCriterion("lists_id is not null");
            return (Criteria) this;
        }

        public Criteria andListsIdEqualTo(Integer value) {
            addCriterion("lists_id =", value, "listsId");
            return (Criteria) this;
        }

        public Criteria andListsIdNotEqualTo(Integer value) {
            addCriterion("lists_id <>", value, "listsId");
            return (Criteria) this;
        }

        public Criteria andListsIdGreaterThan(Integer value) {
            addCriterion("lists_id >", value, "listsId");
            return (Criteria) this;
        }

        public Criteria andListsIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("lists_id >=", value, "listsId");
            return (Criteria) this;
        }

        public Criteria andListsIdLessThan(Integer value) {
            addCriterion("lists_id <", value, "listsId");
            return (Criteria) this;
        }

        public Criteria andListsIdLessThanOrEqualTo(Integer value) {
            addCriterion("lists_id <=", value, "listsId");
            return (Criteria) this;
        }

        public Criteria andListsIdIn(List<Integer> values) {
            addCriterion("lists_id in", values, "listsId");
            return (Criteria) this;
        }

        public Criteria andListsIdNotIn(List<Integer> values) {
            addCriterion("lists_id not in", values, "listsId");
            return (Criteria) this;
        }

        public Criteria andListsIdBetween(Integer value1, Integer value2) {
            addCriterion("lists_id between", value1, value2, "listsId");
            return (Criteria) this;
        }

        public Criteria andListsIdNotBetween(Integer value1, Integer value2) {
            addCriterion("lists_id not between", value1, value2, "listsId");
            return (Criteria) this;
        }

        public Criteria andAgentIsNull() {
            addCriterion("agent is null");
            return (Criteria) this;
        }

        public Criteria andAgentIsNotNull() {
            addCriterion("agent is not null");
            return (Criteria) this;
        }

        public Criteria andAgentEqualTo(String value) {
            addCriterion("agent =", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotEqualTo(String value) {
            addCriterion("agent <>", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentGreaterThan(String value) {
            addCriterion("agent >", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentGreaterThanOrEqualTo(String value) {
            addCriterion("agent >=", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentLessThan(String value) {
            addCriterion("agent <", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentLessThanOrEqualTo(String value) {
            addCriterion("agent <=", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentLike(String value) {
            addCriterion("agent like", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotLike(String value) {
            addCriterion("agent not like", value, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentIn(List<String> values) {
            addCriterion("agent in", values, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotIn(List<String> values) {
            addCriterion("agent not in", values, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentBetween(String value1, String value2) {
            addCriterion("agent between", value1, value2, "agent");
            return (Criteria) this;
        }

        public Criteria andAgentNotBetween(String value1, String value2) {
            addCriterion("agent not between", value1, value2, "agent");
            return (Criteria) this;
        }

        public Criteria andZnameIsNull() {
            addCriterion("zname is null");
            return (Criteria) this;
        }

        public Criteria andZnameIsNotNull() {
            addCriterion("zname is not null");
            return (Criteria) this;
        }

        public Criteria andZnameEqualTo(String value) {
            addCriterion("zname =", value, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameNotEqualTo(String value) {
            addCriterion("zname <>", value, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameGreaterThan(String value) {
            addCriterion("zname >", value, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameGreaterThanOrEqualTo(String value) {
            addCriterion("zname >=", value, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameLessThan(String value) {
            addCriterion("zname <", value, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameLessThanOrEqualTo(String value) {
            addCriterion("zname <=", value, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameLike(String value) {
            addCriterion("zname like", value, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameNotLike(String value) {
            addCriterion("zname not like", value, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameIn(List<String> values) {
            addCriterion("zname in", values, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameNotIn(List<String> values) {
            addCriterion("zname not in", values, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameBetween(String value1, String value2) {
            addCriterion("zname between", value1, value2, "zname");
            return (Criteria) this;
        }

        public Criteria andZnameNotBetween(String value1, String value2) {
            addCriterion("zname not between", value1, value2, "zname");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andXgnameIsNull() {
            addCriterion("xgname is null");
            return (Criteria) this;
        }

        public Criteria andXgnameIsNotNull() {
            addCriterion("xgname is not null");
            return (Criteria) this;
        }

        public Criteria andXgnameEqualTo(String value) {
            addCriterion("xgname =", value, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameNotEqualTo(String value) {
            addCriterion("xgname <>", value, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameGreaterThan(String value) {
            addCriterion("xgname >", value, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameGreaterThanOrEqualTo(String value) {
            addCriterion("xgname >=", value, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameLessThan(String value) {
            addCriterion("xgname <", value, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameLessThanOrEqualTo(String value) {
            addCriterion("xgname <=", value, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameLike(String value) {
            addCriterion("xgname like", value, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameNotLike(String value) {
            addCriterion("xgname not like", value, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameIn(List<String> values) {
            addCriterion("xgname in", values, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameNotIn(List<String> values) {
            addCriterion("xgname not in", values, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameBetween(String value1, String value2) {
            addCriterion("xgname between", value1, value2, "xgname");
            return (Criteria) this;
        }

        public Criteria andXgnameNotBetween(String value1, String value2) {
            addCriterion("xgname not between", value1, value2, "xgname");
            return (Criteria) this;
        }

        public Criteria andCodesIsNull() {
            addCriterion("codes is null");
            return (Criteria) this;
        }

        public Criteria andCodesIsNotNull() {
            addCriterion("codes is not null");
            return (Criteria) this;
        }

        public Criteria andCodesEqualTo(String value) {
            addCriterion("codes =", value, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesNotEqualTo(String value) {
            addCriterion("codes <>", value, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesGreaterThan(String value) {
            addCriterion("codes >", value, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesGreaterThanOrEqualTo(String value) {
            addCriterion("codes >=", value, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesLessThan(String value) {
            addCriterion("codes <", value, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesLessThanOrEqualTo(String value) {
            addCriterion("codes <=", value, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesLike(String value) {
            addCriterion("codes like", value, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesNotLike(String value) {
            addCriterion("codes not like", value, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesIn(List<String> values) {
            addCriterion("codes in", values, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesNotIn(List<String> values) {
            addCriterion("codes not in", values, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesBetween(String value1, String value2) {
            addCriterion("codes between", value1, value2, "codes");
            return (Criteria) this;
        }

        public Criteria andCodesNotBetween(String value1, String value2) {
            addCriterion("codes not between", value1, value2, "codes");
            return (Criteria) this;
        }

        public Criteria andBzjIsNull() {
            addCriterion("bzj is null");
            return (Criteria) this;
        }

        public Criteria andBzjIsNotNull() {
            addCriterion("bzj is not null");
            return (Criteria) this;
        }

        public Criteria andBzjEqualTo(String value) {
            addCriterion("bzj =", value, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjNotEqualTo(String value) {
            addCriterion("bzj <>", value, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjGreaterThan(String value) {
            addCriterion("bzj >", value, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjGreaterThanOrEqualTo(String value) {
            addCriterion("bzj >=", value, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjLessThan(String value) {
            addCriterion("bzj <", value, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjLessThanOrEqualTo(String value) {
            addCriterion("bzj <=", value, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjLike(String value) {
            addCriterion("bzj like", value, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjNotLike(String value) {
            addCriterion("bzj not like", value, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjIn(List<String> values) {
            addCriterion("bzj in", values, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjNotIn(List<String> values) {
            addCriterion("bzj not in", values, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjBetween(String value1, String value2) {
            addCriterion("bzj between", value1, value2, "bzj");
            return (Criteria) this;
        }

        public Criteria andBzjNotBetween(String value1, String value2) {
            addCriterion("bzj not between", value1, value2, "bzj");
            return (Criteria) this;
        }

        public Criteria andZtsIsNull() {
            addCriterion("zts is null");
            return (Criteria) this;
        }

        public Criteria andZtsIsNotNull() {
            addCriterion("zts is not null");
            return (Criteria) this;
        }

        public Criteria andZtsEqualTo(Short value) {
            addCriterion("zts =", value, "zts");
            return (Criteria) this;
        }

        public Criteria andZtsNotEqualTo(Short value) {
            addCriterion("zts <>", value, "zts");
            return (Criteria) this;
        }

        public Criteria andZtsGreaterThan(Short value) {
            addCriterion("zts >", value, "zts");
            return (Criteria) this;
        }

        public Criteria andZtsGreaterThanOrEqualTo(Short value) {
            addCriterion("zts >=", value, "zts");
            return (Criteria) this;
        }

        public Criteria andZtsLessThan(Short value) {
            addCriterion("zts <", value, "zts");
            return (Criteria) this;
        }

        public Criteria andZtsLessThanOrEqualTo(Short value) {
            addCriterion("zts <=", value, "zts");
            return (Criteria) this;
        }

        public Criteria andZtsIn(List<Short> values) {
            addCriterion("zts in", values, "zts");
            return (Criteria) this;
        }

        public Criteria andZtsNotIn(List<Short> values) {
            addCriterion("zts not in", values, "zts");
            return (Criteria) this;
        }

        public Criteria andZtsBetween(Short value1, Short value2) {
            addCriterion("zts between", value1, value2, "zts");
            return (Criteria) this;
        }

        public Criteria andZtsNotBetween(Short value1, Short value2) {
            addCriterion("zts not between", value1, value2, "zts");
            return (Criteria) this;
        }

        public Criteria andMrsjIsNull() {
            addCriterion("mrsj is null");
            return (Criteria) this;
        }

        public Criteria andMrsjIsNotNull() {
            addCriterion("mrsj is not null");
            return (Criteria) this;
        }

        public Criteria andMrsjEqualTo(String value) {
            addCriterion("mrsj =", value, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjNotEqualTo(String value) {
            addCriterion("mrsj <>", value, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjGreaterThan(String value) {
            addCriterion("mrsj >", value, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjGreaterThanOrEqualTo(String value) {
            addCriterion("mrsj >=", value, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjLessThan(String value) {
            addCriterion("mrsj <", value, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjLessThanOrEqualTo(String value) {
            addCriterion("mrsj <=", value, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjLike(String value) {
            addCriterion("mrsj like", value, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjNotLike(String value) {
            addCriterion("mrsj not like", value, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjIn(List<String> values) {
            addCriterion("mrsj in", values, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjNotIn(List<String> values) {
            addCriterion("mrsj not in", values, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjBetween(String value1, String value2) {
            addCriterion("mrsj between", value1, value2, "mrsj");
            return (Criteria) this;
        }

        public Criteria andMrsjNotBetween(String value1, String value2) {
            addCriterion("mrsj not between", value1, value2, "mrsj");
            return (Criteria) this;
        }

        public Criteria andNumsIsNull() {
            addCriterion("nums is null");
            return (Criteria) this;
        }

        public Criteria andNumsIsNotNull() {
            addCriterion("nums is not null");
            return (Criteria) this;
        }

        public Criteria andNumsEqualTo(String value) {
            addCriterion("nums =", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotEqualTo(String value) {
            addCriterion("nums <>", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsGreaterThan(String value) {
            addCriterion("nums >", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsGreaterThanOrEqualTo(String value) {
            addCriterion("nums >=", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsLessThan(String value) {
            addCriterion("nums <", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsLessThanOrEqualTo(String value) {
            addCriterion("nums <=", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsLike(String value) {
            addCriterion("nums like", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotLike(String value) {
            addCriterion("nums not like", value, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsIn(List<String> values) {
            addCriterion("nums in", values, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotIn(List<String> values) {
            addCriterion("nums not in", values, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsBetween(String value1, String value2) {
            addCriterion("nums between", value1, value2, "nums");
            return (Criteria) this;
        }

        public Criteria andNumsNotBetween(String value1, String value2) {
            addCriterion("nums not between", value1, value2, "nums");
            return (Criteria) this;
        }

        public Criteria andWinumsIsNull() {
            addCriterion("winums is null");
            return (Criteria) this;
        }

        public Criteria andWinumsIsNotNull() {
            addCriterion("winums is not null");
            return (Criteria) this;
        }

        public Criteria andWinumsEqualTo(String value) {
            addCriterion("winums =", value, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsNotEqualTo(String value) {
            addCriterion("winums <>", value, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsGreaterThan(String value) {
            addCriterion("winums >", value, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsGreaterThanOrEqualTo(String value) {
            addCriterion("winums >=", value, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsLessThan(String value) {
            addCriterion("winums <", value, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsLessThanOrEqualTo(String value) {
            addCriterion("winums <=", value, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsLike(String value) {
            addCriterion("winums like", value, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsNotLike(String value) {
            addCriterion("winums not like", value, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsIn(List<String> values) {
            addCriterion("winums in", values, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsNotIn(List<String> values) {
            addCriterion("winums not in", values, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsBetween(String value1, String value2) {
            addCriterion("winums between", value1, value2, "winums");
            return (Criteria) this;
        }

        public Criteria andWinumsNotBetween(String value1, String value2) {
            addCriterion("winums not between", value1, value2, "winums");
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