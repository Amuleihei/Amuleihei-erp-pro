/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.dao;

import java.util.List;
import java.util.Map;

public interface StickyNotesDao {

    int insertStickyNotesMation(Map<String, Object> map);

    List<Map<String, Object>> selectStickyNotesMation(Map<String, Object> map);

    int editStickyNotesMation(Map<String, Object> map);

    int deleteStickyNotesMation(Map<String, Object> map);

}
