/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.win.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.skyeye.annotation.service.SkyeyeService;
import com.skyeye.base.business.service.impl.SkyeyeBusinessServiceImpl;
import com.skyeye.cache.redis.RedisCache;
import com.skyeye.common.constans.Constants;
import com.skyeye.common.constans.RedisConstants;
import com.skyeye.common.entity.search.CommonPageInfo;
import com.skyeye.common.object.InputObject;
import com.skyeye.common.object.OutputObject;
import com.skyeye.common.util.FileUtil;
import com.skyeye.common.util.mybatisplus.MybatisPlusUtil;
import com.skyeye.win.dao.SysEveWinLockBgPicDao;
import com.skyeye.win.entity.SysEveWinLockBgPic;
import com.skyeye.win.enums.PicTypeEnum;
import com.skyeye.win.service.SysEveWinLockBgPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: SysEveWinLockBgPicServiceImpl
 * @Description: win系统桌面锁屏图片服务层
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/20 21:22
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Service
@SkyeyeService(name = "win系统桌面锁屏图片", groupName = "win系统桌面锁屏图片")
public class SysEveWinLockBgPicServiceImpl extends SkyeyeBusinessServiceImpl<SysEveWinLockBgPicDao, SysEveWinLockBgPic> implements SysEveWinLockBgPicService {

    @Value("${IMAGES_PATH}")
    private String tPath;

    @Autowired
    private RedisCache redisCache;

    @Override
    public QueryWrapper<SysEveWinLockBgPic> getQueryWrapper(CommonPageInfo commonPageInfo) {
        QueryWrapper<SysEveWinLockBgPic> queryWrapper = super.getQueryWrapper(commonPageInfo);
        queryWrapper.eq(MybatisPlusUtil.toColumns(SysEveWinLockBgPic::getPicType), PicTypeEnum.SYSTEM_PUBLISH.getKey());
        return queryWrapper;
    }

    @Override
    public void createPostpose(SysEveWinLockBgPic entity, String userId) {
        jedisClientService.del(Constants.SYS_WIN_LOCK_BG_PIC_REDIS_KEY);
    }

    @Override
    public void deletePostpose(SysEveWinLockBgPic entity) {
        String basePath = tPath + entity.getPicUrl().replace("/images/", "");
        FileUtil.deleteFile(basePath);
        jedisClientService.del(Constants.SYS_WIN_LOCK_BG_PIC_REDIS_KEY);
    }

    /**
     * 获取用户自定义的win系统桌面锁屏图片列表
     *
     * @param inputObject  入参以及用户信息等获取对象
     * @param outputObject 出参以及提示信息的返回值对象
     */
    @Override
    public void querySysEveWinLockBgPicCustomList(InputObject inputObject, OutputObject outputObject) {
        String userId = inputObject.getLogParams().get("id").toString();
        QueryWrapper<SysEveWinLockBgPic> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(MybatisPlusUtil.toColumns(SysEveWinLockBgPic::getCreateId), userId);
        queryWrapper.eq(MybatisPlusUtil.toColumns(SysEveWinLockBgPic::getPicType), PicTypeEnum.USER_UPLOAD.getKey());
        List<SysEveWinLockBgPic> list = list(queryWrapper);
        outputObject.setBeans(list);
        outputObject.settotal(list.size());
    }

    @Override
    public List<SysEveWinLockBgPic> querySystemSysEveWinLockBgPicList() {
        return redisCache.getList(Constants.SYS_WIN_LOCK_BG_PIC_REDIS_KEY, key -> {
            QueryWrapper<SysEveWinLockBgPic> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(MybatisPlusUtil.toColumns(SysEveWinLockBgPic::getPicType), PicTypeEnum.SYSTEM_PUBLISH.getKey());
            List<SysEveWinLockBgPic> list = list(queryWrapper);
            return list;
        }, RedisConstants.THIRTY_DAY_SECONDS, SysEveWinLockBgPic.class);
    }

}
