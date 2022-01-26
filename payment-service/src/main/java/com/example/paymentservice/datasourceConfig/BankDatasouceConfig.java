package com.example.paymentservice.datasourceConfig;

import com.example.paymentservice.bank.BankAccountRepository;
import com.example.paymentservice.bank.BankAccount;
import com.example.paymentservice.bank.Transaction;
import com.example.paymentservice.bank.TransactionRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;


@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(
       entityManagerFactoryRef = "localConnectionFactoryBean",
        transactionManagerRef = "transactionManager",
        basePackageClasses = {BankAccountRepository.class, TransactionRepository.class})
@EntityScan(basePackageClasses = {BankAccount.class,Transaction.class})
public class BankDatasouceConfig {

    @Primary
    @Bean(name = "seconddatasource")
    @ConfigurationProperties(prefix="spring.second-datasource")
    public DataSource seconddatasource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name ="localConnectionFactoryBean" )
    public LocalContainerEntityManagerFactoryBean localConnectionFactoryBean(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder, @Qualifier("seconddatasource") DataSource seconddatasource){
        return entityManagerFactoryBuilder.dataSource(seconddatasource)
                .packages("com.example.paymentservice.bank")
                .persistenceUnit("bank_db")
                .build();
    }


    @Primary
    @Bean("transactionManager")
    public PlatformTransactionManager transactionManager(EntityManagerFactory localConnectionFactoryBean) {
        return new JpaTransactionManager(localConnectionFactoryBean);
    }
}

