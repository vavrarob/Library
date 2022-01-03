package sin.semestral_work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.semestral_work.dao.PublishedBookDao;
import sin.semestral_work.model.BookPublication;
import sin.semestral_work.model.Library;
import sin.semestral_work.model.PublishedBook;

import java.util.Objects;

@Service
public class PublishedBookService {

    private final PublishedBookDao dao;

    @Autowired
    public PublishedBookService(PublishedBookDao publishedBookDao) {
        this.dao = publishedBookDao;
    }

    @Transactional
    public PublishedBook createBook(BookPublication bookPublication){
        Objects.requireNonNull(bookPublication);
        PublishedBook book = new PublishedBook();
        book.setBookPublication(bookPublication);
        bookPublication.addBook(book);
        dao.persist(book);
        return book;
    }

    @Transactional
    public void deleteBook(PublishedBook book){
        Objects.requireNonNull(book);
        BookPublication bookPublication = book.getBookPublication();
        Library library = book.getLibrary();
        Objects.requireNonNull(bookPublication);
        if(library != null) {
            library.removeBook(book);
        }
        bookPublication.removeBook(book);
        dao.remove(book);
    }
}
