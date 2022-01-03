package sin.semestral_work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import sin.semestral_work.dao.BookPublicationDao;
import sin.semestral_work.dao.PublishedBookDao;
import sin.semestral_work.dto.BookPublicationDTO;
import sin.semestral_work.model.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BookPublicationService {

    private final BookPublicationDao dao;


    @Autowired
    public BookPublicationService(BookPublicationDao dao){
        this.dao = dao;
    }

    @Transactional
    public BookPublicationDTO createBookPublication(Book book, PublishingHouse publishingHouse){
        Objects.requireNonNull(book);
        Objects.requireNonNull(publishingHouse);
        List<Author> authors = book.getAuthors();
        if(publishingHouse.hasContract(authors)){
            BookPublication bookPublication = new BookPublication();
            bookPublication.setBook(book);
            bookPublication.setDateOfPublishing(new Date());
            bookPublication.setPublishingHouse(publishingHouse);
            dao.persist(bookPublication);
            return new BookPublicationDTO(bookPublication.getISBN(), bookPublication.getDateOfPublishing(), bookPublication.getBook(), bookPublication.getPublishingHouse(), bookPublication.getBooks());

        } else {
            System.out.println("Can't create book publication, no contract exists!");
            return null;
        }
    }

    @Transactional
    public BookPublicationDTO findByBookAndPublishingHouse(Book book, PublishingHouse publishingHouse){
        Objects.requireNonNull(book);
        Objects.requireNonNull(publishingHouse);
        BookPublication bookPublication = dao.findByBookAndPublishingHouse(book, publishingHouse);
        return new BookPublicationDTO(bookPublication.getISBN(), bookPublication.getDateOfPublishing(), bookPublication.getBook(), bookPublication.getPublishingHouse(), bookPublication.getBooks());
    }

}
