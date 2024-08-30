/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.activiti.cmd.rollback;

import org.flowable.task.api.history.HistoricTaskInstance;

/**
 * @ClassName: RollbackStrategyFactory
 * @Description:
 * @author: skyeye云系列--卫志强
 * @date: 2021/12/26 19:49
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
public interface RollbackStrategyFactory {

    /**
     * 创建回滚策略
     *
     * @param hisTask
     * @return
     */
    RollbackOperateStrategy createStrategy(HistoricTaskInstance hisTask);

    /**
     * 当前任务未完成判定
     *
     * @param template
     * @return
     */
    boolean currentMultiInstanceTaskUnfinished(RollbackParamsTemplate template);
}
