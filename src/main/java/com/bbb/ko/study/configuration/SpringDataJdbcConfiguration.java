package com.bbb.ko.study.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJdbcRepositories(basePackages = "com.bbb.ko.study.repository")
public class SpringDataJdbcConfiguration extends AbstractJdbcConfiguration {

    /**
     * Creates a DataSource connecting to a database.
     * @return
     */
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.H2)
                .addScript("create-customer-schema.sql")
                .build();
    }

    /**
     * Creates the NamedParameterJdbcOperations used by Spring Data JDBC to access the database.
     * @param dataSource
     * @return
     */
    @Bean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * Spring Data JDBC utilizes the transaction management provided by Spring JDBC.
     * @param dataSource
     * @return
     */
    @Bean
    public TransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
