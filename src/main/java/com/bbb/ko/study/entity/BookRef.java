package com.bbb.ko.study.entity;

import org.springframework.data.relational.core.mapping.Table;

@Table("BOOK_AUTHOR")
public class BookRef {
    Long book;
}
