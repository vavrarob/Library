package sin.semestral_work.dao;

import org.springframework.stereotype.Repository;
import sin.semestral_work.model.Book;
import sin.semestral_work.model.BookPublication;
import sin.semestral_work.model.PublishingHouse;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class BookPublicationDao extends GenericDaoImpl<BookPublication>{

    public BookPublicationDao() { super(BookPublication.class); }

    public BookPublication find(String ISBN){
        Objects.requireNonNull(ISBN);
        return em.find(BookPublication.class, ISBN);
    }

    public BookPublication findByBookAndPublishingHouse(Book book, PublishingHouse publishingHouse){
        Objects.requireNonNull(book);
        Objects.requireNonNull(publishingHouse);
        try {
            return em
                    .createNamedQuery("BookPublication.findByBookAndPublishingHouse", BookPublication.class)
                    .setParameter("book", book)
                    .setParameter("publishingHouse", publishingHouse)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean exists(String ISBN) {
        return ISBN != null && find(ISBN) != null;
    }
}
