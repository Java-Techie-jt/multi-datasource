package com.javatechie.config;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "departmentEntityManagerFactory",
        transactionManagerRef = "departmentTransactionManager",
        basePackages = { "com.javatechie.repository.department" })
public class DepartmentDataSourceConfig {

    @Bean(name = "departmentProperties")
    @ConfigurationProperties("spring.datasource.department")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "departmentDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.department")
    public DataSource datasource(@Qualifier("departmentProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }



    @Bean(name = "departmentEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
            (EntityManagerFactoryBuilder builder, @Qualifier("departmentDatasource") DataSource dataSource) {
        Map<String, Object> jpaProps = new HashMap<>();
        jpaProps.put("hibernate.hbm2ddl.auto", "update");
        jpaProps.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        return builder.dataSource(dataSource)
                .properties(jpaProps)
                .packages("com.javatechie.entity.department")
                .persistenceUnit("department").build();
    }

    @Bean(name = "departmentTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager transactionManager(
            @Qualifier("departmentEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
