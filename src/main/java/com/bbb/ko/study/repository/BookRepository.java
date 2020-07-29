package com.bbb.ko.study.repository;

import com.bbb.ko.study.entity.Book;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Long> {

}
