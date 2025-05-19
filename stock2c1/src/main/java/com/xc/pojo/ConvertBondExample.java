package com.xc.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConvertBondExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConvertBondExample() {
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

        public Criteria andBondBuyCodeIsNull() {
            addCriterion("bond_buy_code is null");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeIsNotNull() {
            addCriterion("bond_buy_code is not null");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeEqualTo(String value) {
            addCriterion("bond_buy_code =", value, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeNotEqualTo(String value) {
            addCriterion("bond_buy_code <>", value, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeGreaterThan(String value) {
            addCriterion("bond_buy_code >", value, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bond_buy_code >=", value, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeLessThan(String value) {
            addCriterion("bond_buy_code <", value, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeLessThanOrEqualTo(String value) {
            addCriterion("bond_buy_code <=", value, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeLike(String value) {
            addCriterion("bond_buy_code like", value, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeNotLike(String value) {
            addCriterion("bond_buy_code not like", value, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeIn(List<String> values) {
            addCriterion("bond_buy_code in", values, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeNotIn(List<String> values) {
            addCriterion("bond_buy_code not in", values, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeBetween(String value1, String value2) {
            addCriterion("bond_buy_code between", value1, value2, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondBuyCodeNotBetween(String value1, String value2) {
            addCriterion("bond_buy_code not between", value1, value2, "bondBuyCode");
            return (Criteria) this;
        }

        public Criteria andBondNameIsNull() {
            addCriterion("bond_name is null");
            return (Criteria) this;
        }

        public Criteria andBondNameIsNotNull() {
            addCriterion("bond_name is not null");
            return (Criteria) this;
        }

        public Criteria andBondNameEqualTo(String value) {
            addCriterion("bond_name =", value, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameNotEqualTo(String value) {
            addCriterion("bond_name <>", value, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameGreaterThan(String value) {
            addCriterion("bond_name >", value, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameGreaterThanOrEqualTo(String value) {
            addCriterion("bond_name >=", value, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameLessThan(String value) {
            addCriterion("bond_name <", value, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameLessThanOrEqualTo(String value) {
            addCriterion("bond_name <=", value, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameLike(String value) {
            addCriterion("bond_name like", value, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameNotLike(String value) {
            addCriterion("bond_name not like", value, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameIn(List<String> values) {
            addCriterion("bond_name in", values, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameNotIn(List<String> values) {
            addCriterion("bond_name not in", values, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameBetween(String value1, String value2) {
            addCriterion("bond_name between", value1, value2, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondNameNotBetween(String value1, String value2) {
            addCriterion("bond_name not between", value1, value2, "bondName");
            return (Criteria) this;
        }

        public Criteria andBondTypeIsNull() {
            addCriterion("bond_type is null");
            return (Criteria) this;
        }

        public Criteria andBondTypeIsNotNull() {
            addCriterion("bond_type is not null");
            return (Criteria) this;
        }

        public Criteria andBondTypeEqualTo(String value) {
            addCriterion("bond_type =", value, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeNotEqualTo(String value) {
            addCriterion("bond_type <>", value, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeGreaterThan(String value) {
            addCriterion("bond_type >", value, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeGreaterThanOrEqualTo(String value) {
            addCriterion("bond_type >=", value, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeLessThan(String value) {
            addCriterion("bond_type <", value, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeLessThanOrEqualTo(String value) {
            addCriterion("bond_type <=", value, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeLike(String value) {
            addCriterion("bond_type like", value, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeNotLike(String value) {
            addCriterion("bond_type not like", value, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeIn(List<String> values) {
            addCriterion("bond_type in", values, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeNotIn(List<String> values) {
            addCriterion("bond_type not in", values, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeBetween(String value1, String value2) {
            addCriterion("bond_type between", value1, value2, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondTypeNotBetween(String value1, String value2) {
            addCriterion("bond_type not between", value1, value2, "bondType");
            return (Criteria) this;
        }

        public Criteria andBondCodeIsNull() {
            addCriterion("bond_code is null");
            return (Criteria) this;
        }

        public Criteria andBondCodeIsNotNull() {
            addCriterion("bond_code is not null");
            return (Criteria) this;
        }

        public Criteria andBondCodeEqualTo(String value) {
            addCriterion("bond_code =", value, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeNotEqualTo(String value) {
            addCriterion("bond_code <>", value, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeGreaterThan(String value) {
            addCriterion("bond_code >", value, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bond_code >=", value, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeLessThan(String value) {
            addCriterion("bond_code <", value, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeLessThanOrEqualTo(String value) {
            addCriterion("bond_code <=", value, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeLike(String value) {
            addCriterion("bond_code like", value, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeNotLike(String value) {
            addCriterion("bond_code not like", value, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeIn(List<String> values) {
            addCriterion("bond_code in", values, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeNotIn(List<String> values) {
            addCriterion("bond_code not in", values, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeBetween(String value1, String value2) {
            addCriterion("bond_code between", value1, value2, "bondCode");
            return (Criteria) this;
        }

        public Criteria andBondCodeNotBetween(String value1, String value2) {
            addCriterion("bond_code not between", value1, value2, "bondCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNull() {
            addCriterion("stock_code is null");
            return (Criteria) this;
        }

        public Criteria andStockCodeIsNotNull() {
            addCriterion("stock_code is not null");
            return (Criteria) this;
        }

        public Criteria andStockCodeEqualTo(String value) {
            addCriterion("stock_code =", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotEqualTo(String value) {
            addCriterion("stock_code <>", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThan(String value) {
            addCriterion("stock_code >", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeGreaterThanOrEqualTo(String value) {
            addCriterion("stock_code >=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThan(String value) {
            addCriterion("stock_code <", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLessThanOrEqualTo(String value) {
            addCriterion("stock_code <=", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeLike(String value) {
            addCriterion("stock_code like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotLike(String value) {
            addCriterion("stock_code not like", value, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeIn(List<String> values) {
            addCriterion("stock_code in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotIn(List<String> values) {
            addCriterion("stock_code not in", values, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeBetween(String value1, String value2) {
            addCriterion("stock_code between", value1, value2, "stockCode");
            return (Criteria) this;
        }

        public Criteria andStockCodeNotBetween(String value1, String value2) {
            addCriterion("stock_code not between", value1, value2, "stockCode");
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

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
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

        public Criteria andPubDateIsNull() {
            addCriterion("pub_date is null");
            return (Criteria) this;
        }

        public Criteria andPubDateIsNotNull() {
            addCriterion("pub_date is not null");
            return (Criteria) this;
        }

        public Criteria andPubDateEqualTo(Date value) {
            addCriterion("pub_date =", value, "pubDate");
            return (Criteria) this;
        }

        public Criteria andPubDateNotEqualTo(Date value) {
            addCriterion("pub_date <>", value, "pubDate");
            return (Criteria) this;
        }

        public Criteria andPubDateGreaterThan(Date value) {
            addCriterion("pub_date >", value, "pubDate");
            return (Criteria) this;
        }

        public Criteria andPubDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pub_date >=", value, "pubDate");
            return (Criteria) this;
        }

        public Criteria andPubDateLessThan(Date value) {
            addCriterion("pub_date <", value, "pubDate");
            return (Criteria) this;
        }

        public Criteria andPubDateLessThanOrEqualTo(Date value) {
            addCriterion("pub_date <=", value, "pubDate");
            return (Criteria) this;
        }

        public Criteria andPubDateIn(List<Date> values) {
            addCriterion("pub_date in", values, "pubDate");
            return (Criteria) this;
        }

        public Criteria andPubDateNotIn(List<Date> values) {
            addCriterion("pub_date not in", values, "pubDate");
            return (Criteria) this;
        }

        public Criteria andPubDateBetween(Date value1, Date value2) {
            addCriterion("pub_date between", value1, value2, "pubDate");
            return (Criteria) this;
        }

        public Criteria andPubDateNotBetween(Date value1, Date value2) {
            addCriterion("pub_date not between", value1, value2, "pubDate");
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

        public Criteria andSurplusIsNull() {
            addCriterion("surplus is null");
            return (Criteria) this;
        }

        public Criteria andSurplusIsNotNull() {
            addCriterion("surplus is not null");
            return (Criteria) this;
        }

        public Criteria andSurplusEqualTo(Integer value) {
            addCriterion("surplus =", value, "surplus");
            return (Criteria) this;
        }

        public Criteria andSurplusNotEqualTo(Integer value) {
            addCriterion("surplus <>", value, "surplus");
            return (Criteria) this;
        }

        public Criteria andSurplusGreaterThan(Integer value) {
            addCriterion("surplus >", value, "surplus");
            return (Criteria) this;
        }

        public Criteria andSurplusGreaterThanOrEqualTo(Integer value) {
            addCriterion("surplus >=", value, "surplus");
            return (Criteria) this;
        }

        public Criteria andSurplusLessThan(Integer value) {
            addCriterion("surplus <", value, "surplus");
            return (Criteria) this;
        }

        public Criteria andSurplusLessThanOrEqualTo(Integer value) {
            addCriterion("surplus <=", value, "surplus");
            return (Criteria) this;
        }

        public Criteria andSurplusIn(List<Integer> values) {
            addCriterion("surplus in", values, "surplus");
            return (Criteria) this;
        }

        public Criteria andSurplusNotIn(List<Integer> values) {
            addCriterion("surplus not in", values, "surplus");
            return (Criteria) this;
        }

        public Criteria andSurplusBetween(Integer value1, Integer value2) {
            addCriterion("surplus between", value1, value2, "surplus");
            return (Criteria) this;
        }

        public Criteria andSurplusNotBetween(Integer value1, Integer value2) {
            addCriterion("surplus not between", value1, value2, "surplus");
            return (Criteria) this;
        }

        public Criteria andApplyLimitIsNull() {
            addCriterion("apply_limit is null");
            return (Criteria) this;
        }

        public Criteria andApplyLimitIsNotNull() {
            addCriterion("apply_limit is not null");
            return (Criteria) this;
        }

        public Criteria andApplyLimitEqualTo(Integer value) {
            addCriterion("apply_limit =", value, "applyLimit");
            return (Criteria) this;
        }

        public Criteria andApplyLimitNotEqualTo(Integer value) {
            addCriterion("apply_limit <>", value, "applyLimit");
            return (Criteria) this;
        }

        public Criteria andApplyLimitGreaterThan(Integer value) {
            addCriterion("apply_limit >", value, "applyLimit");
            return (Criteria) this;
        }

        public Criteria andApplyLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("apply_limit >=", value, "applyLimit");
            return (Criteria) this;
        }

        public Criteria andApplyLimitLessThan(Integer value) {
            addCriterion("apply_limit <", value, "applyLimit");
            return (Criteria) this;
        }

        public Criteria andApplyLimitLessThanOrEqualTo(Integer value) {
            addCriterion("apply_limit <=", value, "applyLimit");
            return (Criteria) this;
        }

        public Criteria andApplyLimitIn(List<Integer> values) {
            addCriterion("apply_limit in", values, "applyLimit");
            return (Criteria) this;
        }

        public Criteria andApplyLimitNotIn(List<Integer> values) {
            addCriterion("apply_limit not in", values, "applyLimit");
            return (Criteria) this;
        }

        public Criteria andApplyLimitBetween(Integer value1, Integer value2) {
            addCriterion("apply_limit between", value1, value2, "applyLimit");
            return (Criteria) this;
        }

        public Criteria andApplyLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("apply_limit not between", value1, value2, "applyLimit");
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