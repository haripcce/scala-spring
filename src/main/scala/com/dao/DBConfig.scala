package com.dao

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
  * Contains database configurations.
  */
@Configuration
@EnableTransactionManagement
class DBConfig  {

  /**
    * DataSource definition for database connection. Settings are read from
    * the application.properties file (using the env object).
    */
  @Bean(name = Array("dataSource"))
  def dataSource1: DataSource = {
    var dataSource: DriverManagerDataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getProperty("db.driver"));
    dataSource.setUrl(env.getProperty("db.url"));
    dataSource.setUsername(env.getProperty("db.username"));
    dataSource.setPassword(env.getProperty("db.password"));
    return dataSource;
  }

  /**
    * Declare the JPA entity manager factory.
    */
  @Bean(name = Array("entityManagerFactory"))
  def entityManagerFactory1: LocalContainerEntityManagerFactoryBean = {
    var entityManagerFactory: LocalContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

    entityManagerFactory.setDataSource(dataSource);

    // Classpath scanning of @Component, @Service, etc annotated class
    entityManagerFactory.setPackagesToScan(env.getProperty("entitymanager.packagesToScan"));

    // Vendor adapter
    var vendorAdapter: HibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
    entityManagerFactory.setJpaVendorAdapter(vendorAdapter);

    // Hibernate properties
    var additionalProperties: Properties = new Properties();
    additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
    additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
    additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
    entityManagerFactory.setJpaProperties(additionalProperties);

    return entityManagerFactory;
  }

  /**
    * Declare the transaction manager.
    */
  @Bean
  def transactionManager: JpaTransactionManager = {
    var transactionManager1: JpaTransactionManager = new JpaTransactionManager
    transactionManager1.setEntityManagerFactory(entityManagerFactory.getObject())
    return transactionManager1;
  }


  /**
    * PersistenceExceptionTranslationPostProcessor is a bean post processor
    * which adds an advisor to any bean annotated with Repository so that any
    * platform-specific exceptions are caught and then rethrown as one
    * Spring's unchecked data access exceptions (i.e. a subclass of
    * DataAccessException).
    */
  @Bean
  def exceptionTranslation: PersistenceExceptionTranslationPostProcessor = {
    new PersistenceExceptionTranslationPostProcessor;
  }

  // ------------------------
  // PRIVATE FIELDS


  @Autowired private val env: Environment = null

  @Autowired private val dataSource: DataSource = null

  @Autowired private val entityManagerFactory: LocalContainerEntityManagerFactoryBean = null
}
