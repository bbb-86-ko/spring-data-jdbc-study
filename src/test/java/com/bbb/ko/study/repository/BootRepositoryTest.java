package com.bbb.ko.study.repository;

import com.bbb.ko.study.configuration.SpringDataJdbcConfiguration;
import com.bbb.ko.study.entity.Author;
import com.bbb.ko.study.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDataJdbcConfiguration.class)
public class BootRepositoryTest {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BootRepositoryTest(@Qualifier("bookRepository") BookRepository bookRepository, @Qualifier("authorRepository") AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Test
    @DisplayName("many-to-many 에서 Book -> Author insert와 delete 처리")
    @Transactional
    public void booksAndAuthors() {
        Author author = new Author();
        author.setName("Greg L. Turnquist");

        author = authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Spring Boot");
        book.addAuthor(author);

        bookRepository.save(book);

        bookRepository.deleteAll();

        assertThat(authorRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("many-to-many 에서 Author -> Book 기준 insert와 delete 처리")
    @Transactional
    public void authorsAndbooks() {
        Book book = new Book();
        book.setTitle("Spring Boot");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("Greg L. Turnquist");
        author.addBook(book);

        authorRepository.save(author);

        authorRepository.deleteAll();

        assertThat(bookRepository.count()).isEqualTo(1);
    }
}
