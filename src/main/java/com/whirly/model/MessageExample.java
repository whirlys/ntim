package com.whirly.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageExample() {
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

        public Criteria andMessageIdIsNull() {
            addCriterion("message_id is null");
            return (Criteria) this;
        }

        public Criteria andMessageIdIsNotNull() {
            addCriterion("message_id is not null");
            return (Criteria) this;
        }

        public Criteria andMessageIdEqualTo(Integer value) {
            addCriterion("message_id =", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotEqualTo(Integer value) {
            addCriterion("message_id <>", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThan(Integer value) {
            addCriterion("message_id >", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("message_id >=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThan(Integer value) {
            addCriterion("message_id <", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdLessThanOrEqualTo(Integer value) {
            addCriterion("message_id <=", value, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdIn(List<Integer> values) {
            addCriterion("message_id in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotIn(List<Integer> values) {
            addCriterion("message_id not in", values, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdBetween(Integer value1, Integer value2) {
            addCriterion("message_id between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMessageIdNotBetween(Integer value1, Integer value2) {
            addCriterion("message_id not between", value1, value2, "messageId");
            return (Criteria) this;
        }

        public Criteria andMsgFromIsNull() {
            addCriterion("msg_from is null");
            return (Criteria) this;
        }

        public Criteria andMsgFromIsNotNull() {
            addCriterion("msg_from is not null");
            return (Criteria) this;
        }

        public Criteria andMsgFromEqualTo(Integer value) {
            addCriterion("msg_from =", value, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgFromNotEqualTo(Integer value) {
            addCriterion("msg_from <>", value, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgFromGreaterThan(Integer value) {
            addCriterion("msg_from >", value, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgFromGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_from >=", value, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgFromLessThan(Integer value) {
            addCriterion("msg_from <", value, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgFromLessThanOrEqualTo(Integer value) {
            addCriterion("msg_from <=", value, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgFromIn(List<Integer> values) {
            addCriterion("msg_from in", values, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgFromNotIn(List<Integer> values) {
            addCriterion("msg_from not in", values, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgFromBetween(Integer value1, Integer value2) {
            addCriterion("msg_from between", value1, value2, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgFromNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_from not between", value1, value2, "msgFrom");
            return (Criteria) this;
        }

        public Criteria andMsgToIsNull() {
            addCriterion("msg_to is null");
            return (Criteria) this;
        }

        public Criteria andMsgToIsNotNull() {
            addCriterion("msg_to is not null");
            return (Criteria) this;
        }

        public Criteria andMsgToEqualTo(Integer value) {
            addCriterion("msg_to =", value, "msgTo");
            return (Criteria) this;
        }

        public Criteria andMsgToNotEqualTo(Integer value) {
            addCriterion("msg_to <>", value, "msgTo");
            return (Criteria) this;
        }

        public Criteria andMsgToGreaterThan(Integer value) {
            addCriterion("msg_to >", value, "msgTo");
            return (Criteria) this;
        }

        public Criteria andMsgToGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_to >=", value, "msgTo");
            return (Criteria) this;
        }

        public Criteria andMsgToLessThan(Integer value) {
            addCriterion("msg_to <", value, "msgTo");
            return (Criteria) this;
        }

        public Criteria andMsgToLessThanOrEqualTo(Integer value) {
            addCriterion("msg_to <=", value, "msgTo");
            return (Criteria) this;
        }

        public Criteria andMsgToIn(List<Integer> values) {
            addCriterion("msg_to in", values, "msgTo");
            return (Criteria) this;
        }

        public Criteria andMsgToNotIn(List<Integer> values) {
            addCriterion("msg_to not in", values, "msgTo");
            return (Criteria) this;
        }

        public Criteria andMsgToBetween(Integer value1, Integer value2) {
            addCriterion("msg_to between", value1, value2, "msgTo");
            return (Criteria) this;
        }

        public Criteria andMsgToNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_to not between", value1, value2, "msgTo");
            return (Criteria) this;
        }

        public Criteria andCmdIdIsNull() {
            addCriterion("cmd_id is null");
            return (Criteria) this;
        }

        public Criteria andCmdIdIsNotNull() {
            addCriterion("cmd_id is not null");
            return (Criteria) this;
        }

        public Criteria andCmdIdEqualTo(Integer value) {
            addCriterion("cmd_id =", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotEqualTo(Integer value) {
            addCriterion("cmd_id <>", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdGreaterThan(Integer value) {
            addCriterion("cmd_id >", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("cmd_id >=", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdLessThan(Integer value) {
            addCriterion("cmd_id <", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdLessThanOrEqualTo(Integer value) {
            addCriterion("cmd_id <=", value, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdIn(List<Integer> values) {
            addCriterion("cmd_id in", values, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotIn(List<Integer> values) {
            addCriterion("cmd_id not in", values, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdBetween(Integer value1, Integer value2) {
            addCriterion("cmd_id between", value1, value2, "cmdId");
            return (Criteria) this;
        }

        public Criteria andCmdIdNotBetween(Integer value1, Integer value2) {
            addCriterion("cmd_id not between", value1, value2, "cmdId");
            return (Criteria) this;
        }

        public Criteria andMsgSeqIsNull() {
            addCriterion("msg_seq is null");
            return (Criteria) this;
        }

        public Criteria andMsgSeqIsNotNull() {
            addCriterion("msg_seq is not null");
            return (Criteria) this;
        }

        public Criteria andMsgSeqEqualTo(Integer value) {
            addCriterion("msg_seq =", value, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgSeqNotEqualTo(Integer value) {
            addCriterion("msg_seq <>", value, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgSeqGreaterThan(Integer value) {
            addCriterion("msg_seq >", value, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("msg_seq >=", value, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgSeqLessThan(Integer value) {
            addCriterion("msg_seq <", value, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgSeqLessThanOrEqualTo(Integer value) {
            addCriterion("msg_seq <=", value, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgSeqIn(List<Integer> values) {
            addCriterion("msg_seq in", values, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgSeqNotIn(List<Integer> values) {
            addCriterion("msg_seq not in", values, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgSeqBetween(Integer value1, Integer value2) {
            addCriterion("msg_seq between", value1, value2, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("msg_seq not between", value1, value2, "msgSeq");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIsNull() {
            addCriterion("msg_type is null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIsNotNull() {
            addCriterion("msg_type is not null");
            return (Criteria) this;
        }

        public Criteria andMsgTypeEqualTo(Short value) {
            addCriterion("msg_type =", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotEqualTo(Short value) {
            addCriterion("msg_type <>", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeGreaterThan(Short value) {
            addCriterion("msg_type >", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("msg_type >=", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLessThan(Short value) {
            addCriterion("msg_type <", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeLessThanOrEqualTo(Short value) {
            addCriterion("msg_type <=", value, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeIn(List<Short> values) {
            addCriterion("msg_type in", values, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotIn(List<Short> values) {
            addCriterion("msg_type not in", values, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeBetween(Short value1, Short value2) {
            addCriterion("msg_type between", value1, value2, "msgType");
            return (Criteria) this;
        }

        public Criteria andMsgTypeNotBetween(Short value1, Short value2) {
            addCriterion("msg_type not between", value1, value2, "msgType");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNull() {
            addCriterion("is_read is null");
            return (Criteria) this;
        }

        public Criteria andIsReadIsNotNull() {
            addCriterion("is_read is not null");
            return (Criteria) this;
        }

        public Criteria andIsReadEqualTo(Boolean value) {
            addCriterion("is_read =", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotEqualTo(Boolean value) {
            addCriterion("is_read <>", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThan(Boolean value) {
            addCriterion("is_read >", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_read >=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThan(Boolean value) {
            addCriterion("is_read <", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadLessThanOrEqualTo(Boolean value) {
            addCriterion("is_read <=", value, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadIn(List<Boolean> values) {
            addCriterion("is_read in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotIn(List<Boolean> values) {
            addCriterion("is_read not in", values, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadBetween(Boolean value1, Boolean value2) {
            addCriterion("is_read between", value1, value2, "isRead");
            return (Criteria) this;
        }

        public Criteria andIsReadNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_read not between", value1, value2, "isRead");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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