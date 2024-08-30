/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.eve.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.skyeye.annotation.api.ApiModel;
import com.skyeye.annotation.api.Property;
import com.skyeye.common.entity.CommonInfo;
import lombok.Data;

/**
 * @ClassName: EmailEnclosure
 * @Description: 邮件附件实体类
 * @author: skyeye云系列--卫志强
 * @date: 2024/4/8 9:26
 * @Copyright: 2024 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Data
@TableName(value = "email_enclosure", autoResultMap = true)
@ApiModel("邮件附件实体类")
public class EmailEnclosure extends CommonInfo {

    @TableId("id")
    @Property("主键id。为空时新增，不为空时编辑")
    private String id;

    @TableField("file_name")
    @Property(value = "文件名称")
    private String fileName;

    @TableField("file_ext_name")
    @Property(value = "文件后缀")
    private String fileExtName;

    @TableField("file_path")
    @Property(value = "文件存储路径")
    private String filePath;

    @TableField("file_size")
    @Property(value = "文件大小")
    private String fileSize;

    @TableField("parent_id")
    @Property(value = "所属邮件")
    private String parentId;

}
