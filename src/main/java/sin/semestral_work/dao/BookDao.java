package sin.semestral_work.dao;

import org.springframework.stereotype.Repository;
import sin.semestral_work.model.Book;

@Repository
public class BookDao extends GenericDaoImpl<Book>{
    public BookDao() { super(Book.class); }
}
