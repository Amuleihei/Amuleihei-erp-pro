/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.common.constans;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @ClassName: ActivitiConstants
 * @Description: 工作流模块常量类
 * @author: skyeye云系列--卫志强
 * @date: 2021/7/16 23:04
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public class ActivitiConstants {

    public static final String APPROVAL_PASS = "pass";

    public static final String APPROVAL_NO_PASS = "nopass";

    public static final String ASSIGNEE_USER = "assignee";

    public static final String ASSIGNEE_USER_MATION = "assigneeMation";

    public static final String ASSIGNEE_USER_LIST = "assigneeList";

    public static final String ASSIGNEE_USER_MATION_LIST = "assigneeMationList";

    public static final String DEFAULT_ASSIGNEE_LIST_EXP = "${" + ASSIGNEE_USER_LIST + "}";

    public static final String ASSIGNEE_USER_EXP = "${" + ASSIGNEE_USER + "}";

    /**
     * form表单数据存储在task的varables的key
     */
    public static final String PROCESSINSTANCEID_TASK_VARABLES = "baseTask";

    /**
     * 存储在流程信息中的审批/其他操作的历史意见
     */
    public static final String PROCESSINSTANCEID_TASK_LEAVE_OPINION_LIST_VARABLES = "leaveOpinionList";

    /**
     * 用户任务
     */
    public static final String USER_TASK = "userTask";

    /**
     * 排他网关
     */
    public static final String EXCLUSIVE_GATEWAY = "exclusiveGateway";

    /**
     * 后加签
     */
    public static final String AFTER_ADDSIGN = "after";

    /**
     * 前加签
     */
    public static final String BEFORE_ADDSIGN = "before";

    /**
     * 并行会签加签子实例的区分
     */
    public static final String PARALLEL_MULTILN_STANCE_EXECTTION_CHILD = "parallelMultilnStanceExecttionChild";

    /**
     * 会签节点必选评审人标识
     */
    public static final String PARALLEL_MULTILN_STANCE_EXECTTION_ISMANDATORY = "parallelMultilnStanceExecttionIsmandatory";

    /**
     * 串行会签存入变量中的主持人的key
     */
    public static final String SEQUENTIAL_MULTILN_STANCE_EXECTTION_HOST_ASSIGNEE = "sequentialMultilnStanceExecttionHostAssignee";

    /**
     * 工作流用户表格对象
     */
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public enum ActivitiUserElement {
        ROW_INDEX("rowIndex", null, "序号", "ro", "center", false, false,
            false, false, "eq", "70", "", false, true,
            "#rspan", true, true),
        IS_SELECTED("isSelected", null, "选择", "ro", "center", false, false,
            false, false, "eq", "50", "fnRenderSelectUser", false, true,
            "#rspan", true, true),
        ID("id", null, "用户ID", "ro", "center", false, false,
            false, false, "eq", "100", "", false, true,
            "#rspan", true, true),
        FIRST_NAME("firstName", null, "用户名", "ro", "center", false, false,
            false, false, "eq", "120", "", false, true,
            "#rspan", true, true),
        LAST_NAME("lastName", null, "登录名", "ro", "center", false, false,
            false, false, "eq", "120", "", false, true,
            "#rspan", true, true),
        EMAIL("email", null, "邮箱", "ro", "center", false, false,
            false, false, "eq", "120", "", false, true,
            "#rspan", true, true),
        NAME("name", null, "用户组", "ro", "center", false, false,
            false, false, "eq", "120", "", false, true,
            "#rspan", true, true);

        private String key;

        private String id;

        private String header;

        private String type;

        private String align;

        private boolean allowSort;

        private boolean allowSearch;

        private boolean hidden;

        private boolean enableTooltip;

        private String operator;

        private String width;

        private String fnRender;

        private boolean isServerCondition;

        private boolean isExport;

        private String subHeader;

        private boolean isQuote;

        private boolean isImport;

    }

    public static List<Map<String, Object>> getAllList() {
        List<Map<String, Object>> beans = new ArrayList<>();
        for (ActivitiUserElement value : ActivitiUserElement.values()) {
            Map<String, Object> bean = BeanUtil.beanToMap(value);
            beans.add(bean);
        }
        return beans;
    }

    public static Map<String, Object> getMap2PointKey(String key) {
        for (ActivitiUserElement value : ActivitiUserElement.values()) {
            if (key.equals(value.getKey())) {
                return BeanUtil.beanToMap(value);
            }
        }
        return null;
    }

    public static List<Map<String, Object>> getPointKeyList(String... keys) {
        List<Map<String, Object>> beans = new ArrayList<>();
        for (ActivitiUserElement value : ActivitiUserElement.values()) {
            if (Arrays.asList(keys).contains(value.getKey())) {
                Map<String, Object> bean = BeanUtil.beanToMap(value);
                beans.add(bean);
            }
        }
        return beans;
    }

    public static List<Map<String, Object>> getActivitiUserColumnList() {
        return getPointKeyList("rowIndex", "isSelected", "id", "firstName", "lastName", "email");
    }

    public static Map<String, Object> getActivitiUserColumnMap() {
        Map<String, Object> bean = new HashMap<>();
        bean.put("rowIndex", getMap2PointKey("rowIndex"));
        bean.put("isSelected", getMap2PointKey("isSelected"));
        bean.put("id", getMap2PointKey("id"));
        bean.put("firstName", getMap2PointKey("firstName"));
        bean.put("lastName", getMap2PointKey("lastName"));
        bean.put("email", getMap2PointKey("email"));
        return bean;
    }

    public static List<Map<String, Object>> getActivitiGroupColumnList() {
        return getPointKeyList("rowIndex", "isSelected", "id", "name");
    }

    public static Map<String, Object> getActivitiGroupColumnMap() {
        Map<String, Object> bean = new HashMap<>();
        bean.put("rowIndex", getMap2PointKey("rowIndex"));
        bean.put("isSelected", getMap2PointKey("isSelected"));
        bean.put("id", getMap2PointKey("id"));
        bean.put("name", getMap2PointKey("name"));
        return bean;
    }

    public static List<Map<String, Object>> getActivitiUserColumnListByGroupId() {
        return getPointKeyList("rowIndex", "firstName", "lastName", "email");
    }

    public static Map<String, Object> getActivitiUserColumnMapByGroupId() {
        Map<String, Object> bean = new HashMap<>();
        bean.put("rowIndex", getMap2PointKey("rowIndex"));
        bean.put("firstName", getMap2PointKey("firstName"));
        bean.put("lastName", getMap2PointKey("lastName"));
        bean.put("email", getMap2PointKey("email"));
        return bean;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public enum LeaveType {
        APPROVAL_COMMENTS(1, "审批意见类型"),
        TASK_DELEGATE(2, "任务委派类型"),
        TASK_DELEGATE_RESULT(21, "任务委派审批结果"),
        TASK_TRANSFER(3, "任务转办类型"),
        BEFORE_ADD_SIGN_TASK(4, "前加签类型"),
        AFTER_ADD_SIGN_TASK(5, "后加签类型"),
        JOINTLY_SIGN_TASK(6, "会签加减签");

        private Integer type;

        private String name;
    }

}
