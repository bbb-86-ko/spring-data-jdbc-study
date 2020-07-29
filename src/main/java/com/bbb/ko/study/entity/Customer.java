package com.bbb.ko.study.entity;

import org.springframework.data.annotation.Id;
import java.time.LocalDate;

public class Customer {
    @Id
    public Long id;
    public String firstName;
    public LocalDate dob;
}
