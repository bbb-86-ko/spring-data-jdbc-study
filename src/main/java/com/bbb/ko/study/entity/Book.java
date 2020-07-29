package com.bbb.ko.study.entity;

import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;

public class Book {
    @Id
    private Long id;
    private String title;
    private Set<AuthorRef> authors = new HashSet<>();

    public void addAuthor(Author author) {
        authors.add(createAuthorRef(author));
    }

    private AuthorRef createAuthorRef(Author author) {
        Assert.notNull(author, "Author must not be null");
        Assert.notNull(author.getId(), "Author id, must not be null");

        AuthorRef authorRef = new AuthorRef();
        authorRef.author = author.getId();
        return authorRef;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }
}
