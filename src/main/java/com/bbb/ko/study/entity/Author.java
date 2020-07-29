package com.bbb.ko.study.entity;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class Author {
    @Id
    private Long id;
    private String name;
    private Set<BookRef> books = new HashSet<>();

    public void addBook(Book book) {
        books.add(createBookRef(book));
    }

    private BookRef createBookRef(Book book) {
        Assert.notNull(book, "Book must not be null");
        Assert.notNull(book.getId(), "Book id, must not be null");

        BookRef bookRef = new BookRef();
        bookRef.book = book.getId();
        return bookRef;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
