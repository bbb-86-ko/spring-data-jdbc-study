package com.bbb.ko.study.repository;

import com.bbb.ko.study.entity.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
