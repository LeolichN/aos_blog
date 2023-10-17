package com.aos.core.config;

import com.aos.core.repository.impl.BaseRepositoryImpl;
import com.zaxxer.hikari.HikariDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    repositoryBaseClass = BaseRepositoryImpl.class,
    basePackages = {"com.aos"},
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager")
public class DatasourceConfig {

  protected Map<String, Object> jpaProperties() {
    Map<String, Object> props = new HashMap<>();
    props.put(
        "hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
    props.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
    return props;
  }

  @Primary
  @Bean(name = "dataSourceProperties")
  @ConfigurationProperties("spring.datasource")
  public DataSourceProperties dataSourceProperties() {
    return new DataSourceProperties();
  }

  @Primary
  @Bean(name = "datasource")
  @ConfigurationProperties("spring.datasource.configuration")
  public DataSource dataSource() {
    return dataSourceProperties()
        .initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
  }

  @Bean(name = "entityManagerFactory")
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      EntityManagerFactoryBuilder builder) {
    return builder
        .dataSource(dataSource())
        .packages("com.aos.repo")
        .properties(jpaProperties())
        .build();
  }

  @Primary
  @Bean(name = "transactionManager")
  public PlatformTransactionManager adminTransactionManager(
      final @Qualifier("entityManagerFactory") LocalContainerEntityManagerFactoryBean
              entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory.getObject());
  }
}
