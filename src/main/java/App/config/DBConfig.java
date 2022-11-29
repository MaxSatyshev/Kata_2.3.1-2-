package App.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(value = "App")
public class DBConfig {

    @Bean
        public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/databasetest");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
        }

        @Bean
        public EntityManager makeEntityManager(EntityManagerFactory entityManagerFactory) {
            return entityManagerFactory.createEntityManager();
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean EntityManagerFactory(){
            LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
            entityManager.setDataSource(getDataSource());
            entityManager.setPackagesToScan("App");
            Properties properties = new Properties();
            properties.put("hibernate.show_sql", "true");
            properties.put("hibernate.hbm2ddl.auto", "update");
            entityManager.setJpaProperties(properties);
            entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
            return entityManager;
        }

        @Bean
        public JpaTransactionManager transactionManager() {
            JpaTransactionManager transactionManager = new JpaTransactionManager();
            transactionManager.setEntityManagerFactory(EntityManagerFactory().getObject());
            transactionManager.setDataSource(getDataSource());
            return transactionManager;
        }
}
