/*******************************************************************************
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved. 开源地址：https://gitee.com/doc_wei01/skyeye
 ******************************************************************************/

package com.skyeye.db.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.skyeye.common.constans.CommonConstants;
import com.skyeye.common.util.ToolUtil;
import com.skyeye.exception.CustomException;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.flowable.ui.common.service.exception.InternalServerErrorException;
import org.flowable.ui.modeler.properties.FlowableModelerAppProperties;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @ClassName: BaseDataSourceConfig
 * @Description: 数据库配置类 basePackages：星号代表任意个包，比如可以扫描com.skyeye.aaa.dao，也可以扫描com.skyeye.aaa.bbb.dao
 * @author: skyeye云系列--卫志强
 * @date: 2021/8/1 15:23
 * @Copyright: 2021 https://gitee.com/doc_wei01/skyeye Inc. All rights reserved.
 * 注意：本内容仅限购买后使用.禁止私自外泄以及用于其他的商业目的
 */
@Configuration
@MapperScan(basePackages = {
    "com.skyeye.*.dao",
    "com.skyeye.eve.*.dao",
    "com.skyeye.dao",
    "com.skyeye.activiti.mapper"}, sqlSessionFactoryRef = "baseSqlSessionFactory")
public class BaseDataSourceConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDataSourceConfig.class);

    protected static Properties databaseTypeMappings = CommonConstants.getDefaultDatabaseTypeMappings();

    protected static final String LIQUIBASE_CHANGELOG_PREFIX = "ACT_ADM_";

    @Autowired
    protected FlowableModelerAppProperties modelerAppProperties;

    @Autowired
    protected ResourceLoader resourceLoader;

    /**
     * Primary 必须加此注解，不然报错，下一个类则不需要添加，表示这个数据源是默认数据源
     * ConfigurationProperties(prefix)值必须是application.properteis中对应属性的前缀
     *
     * @return
     */
    @Bean(name = "baseDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource baseDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * a、SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由对它进行清除或重建。
     * b、使用 SqlSessionFactory 的最佳实践是在应用运行期间不要重复创建多次，多次重建 SqlSessionFactory 被视为一种代码“坏味道（bad smell）”。
     * c、因此 SqlSessionFactory 的最佳作用域是应用作用域。有很多方法可以做到，最简单的就是使用单例模式或者静态单例模式。
     *
     * @param dataSource
     * @return
     */
    @Primary
    @Bean(name = "baseSqlSessionFactory")
    public SqlSessionFactory baseSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) {
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();

        sqlSessionFactoryBean.setDataSource(dataSource);
        String databaseType = this.initDatabaseType(dataSource);
        if (ToolUtil.isBlank(databaseType)) {
            throw new CustomException("couldn't deduct database type");
        }
        try {
            // 添加XML目录
            sqlSessionFactoryBean.setMapperLocations(resolveMapperLocations());
            Properties properties = new Properties();
            properties.put("prefix", this.modelerAppProperties.getDataSourcePrefix());
            properties.put("blobType", "BLOB");
            properties.put("boolValue", "1");
            properties.load(this.getClass().getClassLoader().getResourceAsStream("org/flowable/db/properties/" + databaseType + ".properties"));
            sqlSessionFactoryBean.setConfigurationProperties(properties);
            sqlSessionFactoryBean.afterPropertiesSet();
            return sqlSessionFactoryBean.getObject();
        } catch (Exception var5) {
            throw new CustomException("Could not create sqlSessionFactory", var5);
        }
    }

    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();
        mapperLocations.add("classpath*:mapper/**/*.xml");
        mapperLocations.add("classpath:/META-INF/modeler-mybatis-mappings/*.xml");
        List<Resource> resources = new ArrayList();
        if (!CollectionUtils.isEmpty(mapperLocations)) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    LOGGER.error("Get myBatis resources happened exception", e);
                }
            }
        }
        return resources.toArray(new Resource[resources.size()]);
    }

    @Bean
    public Liquibase liquibase(@Qualifier("baseDataSource") DataSource dataSource) {
        LOGGER.info("Configuring Liquibase");
        Liquibase liquibase = null;
        try {
            DatabaseConnection connection = new JdbcConnection(dataSource.getConnection());
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(connection);
            database.setDatabaseChangeLogTableName(LIQUIBASE_CHANGELOG_PREFIX + database.getDatabaseChangeLogTableName());
            database.setDatabaseChangeLogLockTableName(LIQUIBASE_CHANGELOG_PREFIX + database.getDatabaseChangeLogLockTableName());

            liquibase = new Liquibase("META-INF/liquibase/flowable-modeler-app-db-changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.update("flowable");
            return liquibase;
        } catch (Exception var9) {
            throw new InternalServerErrorException("Error creating liquibase database", var9);
        } finally {
            this.closeDatabase(liquibase);
        }
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("baseDataSource") DataSource dataSourceOne) {
        return new DataSourceTransactionManager(dataSourceOne);
    }

    @Primary
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        // 使用上面配置的Factory
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 初始化
     *
     * @param dataSource
     * @return
     */
    protected String initDatabaseType(@Qualifier("baseDataSource") DataSource dataSource) {
        String databaseType = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            String databaseProductName = databaseMetaData.getDatabaseProductName();
            LOGGER.info("database product name: '{}'", databaseProductName);
            databaseType = databaseTypeMappings.getProperty(databaseProductName);
            if (ToolUtil.isBlank(databaseType)) {
                throw new CustomException("couldn't deduct database type from database product name '" + databaseProductName + "'");
            }

            LOGGER.info("using database type: {}", databaseType);
        } catch (SQLException var14) {
            LOGGER.error("Exception while initializing Database connection", var14);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException var13) {
                LOGGER.error("Exception while closing the Database connection", var13);
            }

        }
        databaseType = ("dm".equals(databaseType)) ? "oracle" : databaseType;
        return databaseType;
    }

    private void closeDatabase(Liquibase liquibase) {
        if (liquibase != null) {
            Database database = liquibase.getDatabase();
            if (database != null) {
                try {
                    database.close();
                } catch (DatabaseException var4) {
                    LOGGER.warn("Error closing database", var4);
                }
            }
        }
    }

}
