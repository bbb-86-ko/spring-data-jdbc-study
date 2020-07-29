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


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDataJdbcConfiguration.class)
public class FindCustomerRepositoryTest {

    private final CustomerRepository customerRepository;

    FindCustomerRepositoryTest(@Qualifier("customerRepository") CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    @Transactional
    public void findByName() {

        Customer customer = new Customer();
        customer.dob = LocalDate.of(1904, 5, 14);
        customer.firstName = "Albert";

        Customer saved = customerRepository.save(customer);

        assertThat(saved.id).isNotNull();

        customer.id= null;
        customer.firstName = "Bertram";

        customerRepository.save(customer);

        customer.id= null;
        customer.firstName = "Beth";

        customerRepository.save(customer);

        assertThat(customerRepository.findByName("bert")).hasSize(2);
    }
}
