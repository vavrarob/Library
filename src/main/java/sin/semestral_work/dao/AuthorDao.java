package sin.semestral_work.dao;

import org.springframework.stereotype.Repository;
import sin.semestral_work.model.Author;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

@Repository
public class AuthorDao extends GenericDaoImpl<Author> {
    public AuthorDao() {
        super(Author.class);
    }

}
