package com.demo.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
public class DruidConfiguration {

    @Bean(name = "dataSource", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dateSource() {
        DruidDataSource dataSource = DruidDataSourceBuilder.create().build();
        List<Filter> filters = newArrayList();
        filters.add(wallFilter());
        dataSource.setProxyFilters(filters);
        return dataSource;
    }

    @Bean
    @DependsOn("dataSource")
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.wall.config")
    public WallConfig wallConfig() {
        WallConfig wallConfig = new WallConfig();
        wallConfig.setFunctionCheck(false);
        return wallConfig;
    }

    @Bean
    public WallFilter wallFilter() {
        WallFilter filter = new WallFilter();
        filter.setConfig(wallConfig());
        filter.setDbType("mysql");
        return filter;
    }

}
