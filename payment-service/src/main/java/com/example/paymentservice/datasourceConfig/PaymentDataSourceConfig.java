package com.example.paymentservice.datasourceConfig;

import com.example.paymentservice.payment.PaymentRepository;
import com.example.paymentservice.payment.Payment;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef = "localConnectionFactoryBean1",
        transactionManagerRef = "transactionManager1",
        basePackageClasses = PaymentRepository.class)
public class PaymentDataSourceConfig {

    @Bean(name = "dataSourcefirst")
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSourcefirst(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "localConnectionFactoryBean1")
    public LocalContainerEntityManagerFactoryBean localConnectionFactoryBean1(
            EntityManagerFactoryBuilder entityManagerFactoryBuilder1
            , @Qualifier("dataSourcefirst") DataSource dataSourcefirst){
        return entityManagerFactoryBuilder1
                .dataSource(dataSourcefirst)
                .packages("com.example.paymentservice.payment")
                .persistenceUnit("payment_db")
                .build();
    }

    @Bean("transactionManager1")
    public PlatformTransactionManager transactionManager(EntityManagerFactory localConnectionFactoryBean1) {
        return new JpaTransactionManager(localConnectionFactoryBean1);
    }
}
