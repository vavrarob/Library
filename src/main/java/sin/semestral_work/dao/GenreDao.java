package sin.semestral_work.dao;

import org.springframework.stereotype.Repository;
import sin.semestral_work.model.Genre;

import java.util.Objects;

@Repository
public class GenreDao extends GenericDaoImpl<Genre> {
    public GenreDao() {
        super(Genre.class);
    }

    public Genre find(String name){
        Objects.requireNonNull(name);
        return em.find(Genre.class, name);
    }

    public boolean exists(String name){
        return name != null && find(name) != null;
    }

}
