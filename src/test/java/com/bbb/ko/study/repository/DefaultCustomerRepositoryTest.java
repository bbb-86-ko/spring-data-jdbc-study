package com.bbb.ko.study.repository;

import com.bbb.ko.study.configuration.SpringDataJdbcConfiguration;
import com.bbb.ko.study.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test Annotation 선언..
 * 1) DataJdbcTest
 * @DataJdbcTest
 * @Sql("classpath:/create-customer-schema.sql") // : 실행 Query 지정
 *
 * 2) SpringBootTest
 * @SpringBootTest(classes = SpringDataJdbcConfiguration.class)
 *
 * 3) ExtendWith + ContextConfiguration
 * @ExtendWith(SpringExtension.class)
 * @ContextConfiguration(classes = SpringDataJdbcConfiguration.class)
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDataJdbcConfiguration.class)
public class DefaultCustomerRepositoryTest {

    private final CustomerRepository customerRepository;

    DefaultCustomerRepositoryTest(@Qualifier("customerRepository") CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    @Transactional
    public void createCustomer() {
        var customer = new Customer();
        customer.dob = LocalDate.of(1904, 5, 14);
        customer.firstName = "Albert";

        var saved = customerRepository.save(customer);
        assertThat(saved.id).isNotNull();
    }
}
