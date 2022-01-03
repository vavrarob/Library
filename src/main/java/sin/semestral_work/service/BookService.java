package sin.semestral_work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.semestral_work.dao.BookDao;
import sin.semestral_work.model.Author;
import sin.semestral_work.model.Book;
import sin.semestral_work.model.Genre;

import java.util.List;
import java.util.Objects;

@Service
public class BookService {

    private final BookDao dao;

    @Autowired
    public BookService(BookDao dao){
        this.dao = dao;
    }

    @Transactional
    public void updateGenre(Book book, Genre genre){
        Objects.requireNonNull(book);
        Objects.requireNonNull(genre);
        book.setGenre(genre);
        dao.update(book);
    }

    @Transactional
    public Book createBook(String name, Genre genre, List<Author> authors){
        Objects.requireNonNull(name);
        Objects.requireNonNull(genre);
        Objects.requireNonNull(authors);
        Book book = new Book();
        book.setName(name);
        book.setGenre(genre);
        book.setAuthors(authors);
        for (Author a : authors){
            a.addBook(book);
        }
        dao.persist(book);
        return book;
    }
}
