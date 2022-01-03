package sin.semestral_work.dao;

import org.springframework.stereotype.Repository;
import sin.semestral_work.model.PublishedBook;

@Repository
public class PublishedBookDao extends GenericDaoImpl<PublishedBook>{
    public PublishedBookDao() { super(PublishedBook.class); }
}
