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
public class SaveCustomerRepositoryTest {

    private final CustomerRepository customerRepository;

    SaveCustomerRepositoryTest(@Qualifier("customerRepository") CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Test
    @Transactional
    public void createSimpleCustomer() {

        var customer = new Customer();
        customer.dob = LocalDate.of(1904, 5, 14);
        customer.firstName = "Albert";

        var saved = customerRepository.save(customer);
        assertThat(saved.id).isNotNull();

        saved.firstName = "Hans Albert";
        customerRepository.save(saved);

        var  reloaded = customerRepository.findById(saved.id);

        assertThat(reloaded).isNotEmpty();
        assertThat(reloaded.get().firstName).isEqualTo("Hans Albert");
    }
}
