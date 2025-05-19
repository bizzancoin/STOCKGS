package com.xc.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewListExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NewListExample() {
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

        public Criteria andNewlistIdIsNull() {
            addCriterion("newlist_id is null");
            return (Criteria) this;
        }

        public Criteria andNewlistIdIsNotNull() {
            addCriterion("newlist_id is not null");
            return (Criteria) this;
        }

        public Criteria andNewlistIdEqualTo(Integer value) {
            addCriterion("newlist_id =", value, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNewlistIdNotEqualTo(Integer value) {
            addCriterion("newlist_id <>", value, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNewlistIdGreaterThan(Integer value) {
            addCriterion("newlist_id >", value, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNewlistIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("newlist_id >=", value, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNewlistIdLessThan(Integer value) {
            addCriterion("newlist_id <", value, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNewlistIdLessThanOrEqualTo(Integer value) {
            addCriterion("newlist_id <=", value, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNewlistIdIn(List<Integer> values) {
            addCriterion("newlist_id in", values, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNewlistIdNotIn(List<Integer> values) {
            addCriterion("newlist_id not in", values, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNewlistIdBetween(Integer value1, Integer value2) {
            addCriterion("newlist_id between", value1, value2, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNewlistIdNotBetween(Integer value1, Integer value2) {
            addCriterion("newlist_id not between", value1, value2, "newlistId");
            return (Criteria) this;
        }

        public Criteria andNamesIsNull() {
            addCriterion("names is null");
            return (Criteria) this;
        }

        public Criteria andNamesIsNotNull() {
            addCriterion("names is not null");
            return (Criteria) this;
        }

        public Criteria andNamesEqualTo(String value) {
            addCriterion("names =", value, "names");
            return (Criteria) this;
        }

        public Criteria andNamesNotEqualTo(String value) {
            addCriterion("names <>", value, "names");
            return (Criteria) this;
        }

        public Criteria andNamesGreaterThan(String value) {
            addCriterion("names >", value, "names");
            return (Criteria) this;
        }

        public Criteria andNamesGreaterThanOrEqualTo(String value) {
            addCriterion("names >=", value, "names");
            return (Criteria) this;
        }

        public Criteria andNamesLessThan(String value) {
            addCriterion("names <", value, "names");
            return (Criteria) this;
        }

        public Criteria andNamesLessThanOrEqualTo(String value) {
            addCriterion("names <=", value, "names");
            return (Criteria) this;
        }

        public Criteria andNamesLike(String value) {
            addCriterion("names like", value, "names");
            return (Criteria) this;
        }

        public Criteria andNamesNotLike(String value) {
            addCriterion("names not like", value, "names");
            return (Criteria) this;
        }

        public Criteria andNamesIn(List<String> values) {
            addCriterion("names in", values, "names");
            return (Criteria) this;
        }

        public Criteria andNamesNotIn(List<String> values) {
            addCriterion("names not in", values, "names");
            return (Criteria) this;
        }

        public Criteria andNamesBetween(String value1, String value2) {
            addCriterion("names between", value1, value2, "names");
            return (Criteria) this;
        }

        public Criteria andNamesNotBetween(String value1, String value2) {
            addCriterion("names not between", value1, value2, "names");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(String value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(String value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(String value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(String value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(String value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(String value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLike(String value) {
            addCriterion("price like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotLike(String value) {
            addCriterion("price not like", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<String> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<String> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(String value1, String value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(String value1, String value2) {
            addCriterion("price not between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andZtIsNull() {
            addCriterion("zt is null");
            return (Criteria) this;
        }

        public Criteria andZtIsNotNull() {
            addCriterion("zt is not null");
            return (Criteria) this;
        }

        public Criteria andZtEqualTo(Byte value) {
            addCriterion("zt =", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotEqualTo(Byte value) {
            addCriterion("zt <>", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThan(Byte value) {
            addCriterion("zt >", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtGreaterThanOrEqualTo(Byte value) {
            addCriterion("zt >=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThan(Byte value) {
            addCriterion("zt <", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtLessThanOrEqualTo(Byte value) {
            addCriterion("zt <=", value, "zt");
            return (Criteria) this;
        }

        public Criteria andZtIn(List<Byte> values) {
            addCriterion("zt in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotIn(List<Byte> values) {
            addCriterion("zt not in", values, "zt");
            return (Criteria) this;
        }

        public Criteria andZtBetween(Byte value1, Byte value2) {
            addCriterion("zt between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andZtNotBetween(Byte value1, Byte value2) {
            addCriterion("zt not between", value1, value2, "zt");
            return (Criteria) this;
        }

        public Criteria andPeIsNull() {
            addCriterion("pe is null");
            return (Criteria) this;
        }

        public Criteria andPeIsNotNull() {
            addCriterion("pe is not null");
            return (Criteria) this;
        }

        public Criteria andPeEqualTo(String value) {
            addCriterion("pe =", value, "pe");
            return (Criteria) this;
        }

        public Criteria andPeNotEqualTo(String value) {
            addCriterion("pe <>", value, "pe");
            return (Criteria) this;
        }

        public Criteria andPeGreaterThan(String value) {
            addCriterion("pe >", value, "pe");
            return (Criteria) this;
        }

        public Criteria andPeGreaterThanOrEqualTo(String value) {
            addCriterion("pe >=", value, "pe");
            return (Criteria) this;
        }

        public Criteria andPeLessThan(String value) {
            addCriterion("pe <", value, "pe");
            return (Criteria) this;
        }

        public Criteria andPeLessThanOrEqualTo(String value) {
            addCriterion("pe <=", value, "pe");
            return (Criteria) this;
        }

        public Criteria andPeLike(String value) {
            addCriterion("pe like", value, "pe");
            return (Criteria) this;
        }

        public Criteria andPeNotLike(String value) {
            addCriterion("pe not like", value, "pe");
            return (Criteria) this;
        }

        public Criteria andPeIn(List<String> values) {
            addCriterion("pe in", values, "pe");
            return (Criteria) this;
        }

        public Criteria andPeNotIn(List<String> values) {
            addCriterion("pe not in", values, "pe");
            return (Criteria) this;
        }

        public Criteria andPeBetween(String value1, String value2) {
            addCriterion("pe between", value1, value2, "pe");
            return (Criteria) this;
        }

        public Criteria andPeNotBetween(String value1, String value2) {
            addCriterion("pe not between", value1, value2, "pe");
            return (Criteria) this;
        }

        public Criteria andIssueDateIsNull() {
            addCriterion("issue_date is null");
            return (Criteria) this;
        }

        public Criteria andIssueDateIsNotNull() {
            addCriterion("issue_date is not null");
            return (Criteria) this;
        }

        public Criteria andIssueDateEqualTo(Date value) {
            addCriterion("issue_date =", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateNotEqualTo(Date value) {
            addCriterion("issue_date <>", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateGreaterThan(Date value) {
            addCriterion("issue_date >", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateGreaterThanOrEqualTo(Date value) {
            addCriterion("issue_date >=", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateLessThan(Date value) {
            addCriterion("issue_date <", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateLessThanOrEqualTo(Date value) {
            addCriterion("issue_date <=", value, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateIn(List<Date> values) {
            addCriterion("issue_date in", values, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateNotIn(List<Date> values) {
            addCriterion("issue_date not in", values, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateBetween(Date value1, Date value2) {
            addCriterion("issue_date between", value1, value2, "issueDate");
            return (Criteria) this;
        }

        public Criteria andIssueDateNotBetween(Date value1, Date value2) {
            addCriterion("issue_date not between", value1, value2, "issueDate");
            return (Criteria) this;
        }

        public Criteria andListDateIsNull() {
            addCriterion("list_date is null");
            return (Criteria) this;
        }

        public Criteria andListDateIsNotNull() {
            addCriterion("list_date is not null");
            return (Criteria) this;
        }

        public Criteria andListDateEqualTo(Date value) {
            addCriterion("list_date =", value, "listDate");
            return (Criteria) this;
        }

        public Criteria andListDateNotEqualTo(Date value) {
            addCriterion("list_date <>", value, "listDate");
            return (Criteria) this;
        }

        public Criteria andListDateGreaterThan(Date value) {
            addCriterion("list_date >", value, "listDate");
            return (Criteria) this;
        }

        public Criteria andListDateGreaterThanOrEqualTo(Date value) {
            addCriterion("list_date >=", value, "listDate");
            return (Criteria) this;
        }

        public Criteria andListDateLessThan(Date value) {
            addCriterion("list_date <", value, "listDate");
            return (Criteria) this;
        }

        public Criteria andListDateLessThanOrEqualTo(Date value) {
            addCriterion("list_date <=", value, "listDate");
            return (Criteria) this;
        }

        public Criteria andListDateIn(List<Date> values) {
            addCriterion("list_date in", values, "listDate");
            return (Criteria) this;
        }

        public Criteria andListDateNotIn(List<Date> values) {
            addCriterion("list_date not in", values, "listDate");
            return (Criteria) this;
        }

        public Criteria andListDateBetween(Date value1, Date value2) {
            addCriterion("list_date between", value1, value2, "listDate");
            return (Criteria) this;
        }

        public Criteria andListDateNotBetween(Date value1, Date value2) {
            addCriterion("list_date not between", value1, value2, "listDate");
            return (Criteria) this;
        }

        public Criteria andWinDateIsNull() {
            addCriterion("win_date is null");
            return (Criteria) this;
        }

        public Criteria andWinDateIsNotNull() {
            addCriterion("win_date is not null");
            return (Criteria) this;
        }

        public Criteria andWinDateEqualTo(Date value) {
            addCriterion("win_date =", value, "winDate");
            return (Criteria) this;
        }

        public Criteria andWinDateNotEqualTo(Date value) {
            addCriterion("win_date <>", value, "winDate");
            return (Criteria) this;
        }

        public Criteria andWinDateGreaterThan(Date value) {
            addCriterion("win_date >", value, "winDate");
            return (Criteria) this;
        }

        public Criteria andWinDateGreaterThanOrEqualTo(Date value) {
            addCriterion("win_date >=", value, "winDate");
            return (Criteria) this;
        }

        public Criteria andWinDateLessThan(Date value) {
            addCriterion("win_date <", value, "winDate");
            return (Criteria) this;
        }

        public Criteria andWinDateLessThanOrEqualTo(Date value) {
            addCriterion("win_date <=", value, "winDate");
            return (Criteria) this;
        }

        public Criteria andWinDateIn(List<Date> values) {
            addCriterion("win_date in", values, "winDate");
            return (Criteria) this;
        }

        public Criteria andWinDateNotIn(List<Date> values) {
            addCriterion("win_date not in", values, "winDate");
            return (Criteria) this;
        }

        public Criteria andWinDateBetween(Date value1, Date value2) {
            addCriterion("win_date between", value1, value2, "winDate");
            return (Criteria) this;
        }

        public Criteria andWinDateNotBetween(Date value1, Date value2) {
            addCriterion("win_date not between", value1, value2, "winDate");
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