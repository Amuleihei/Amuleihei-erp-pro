package com.skyeye.upload.enums;

import cn.hutool.core.util.ArrayUtil;
import com.skyeye.common.base.classenum.SkyeyeEnumClass;
import com.skyeye.framework.file.core.client.FileClient;
import com.skyeye.framework.file.core.client.FileClientConfig;
import com.skyeye.framework.file.core.client.db.DBFileClient;
import com.skyeye.framework.file.core.client.db.DBFileClientConfig;
import com.skyeye.framework.file.core.client.ftp.FtpFileClient;
import com.skyeye.framework.file.core.client.ftp.FtpFileClientConfig;
import com.skyeye.framework.file.core.client.local.LocalFileClient;
import com.skyeye.framework.file.core.client.local.LocalFileClientConfig;
import com.skyeye.framework.file.core.client.s3.S3FileClient;
import com.skyeye.framework.file.core.client.s3.S3FileClientConfig;
import com.skyeye.framework.file.core.client.sftp.SftpFileClient;
import com.skyeye.framework.file.core.client.sftp.SftpFileClientConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName: FileStorageEnum
 * @Description: 文件存储器枚举
 * @author: skyeye云系列--卫志强
 * @date: 2024/8/18 9:18
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FileStorageEnum implements SkyeyeEnumClass {

    DB(1, "数据库存储", DBFileClientConfig.class, DBFileClient.class, true, true),

    LOCAL(10, "本地存储", LocalFileClientConfig.class, LocalFileClient.class, true, false),
    FTP(11, "FTP存储", FtpFileClientConfig.class, FtpFileClient.class, true, false),
    SFTP(12, "SFTP存储", SftpFileClientConfig.class, SftpFileClient.class, true, false),

    S3(20, "S3存储", S3FileClientConfig.class, S3FileClient.class, true, false);

    /**
     * 存储器
     */
    private Integer key;

    private String value;

    /**
     * 配置类
     */
    private Class<? extends FileClientConfig> configClass;

    /**
     * 客户端类
     */
    private Class<? extends FileClient> clientClass;

    private Boolean show;

    private Boolean isDefault;

    public static FileStorageEnum getByStorage(Integer storage) {
        return ArrayUtil.firstMatch(o -> o.getKey().equals(storage), values());
    }

}
