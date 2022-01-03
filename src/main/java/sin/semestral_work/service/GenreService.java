package sin.semestral_work.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.semestral_work.dao.GenreDao;
import sin.semestral_work.dto.GenreDTO;
import sin.semestral_work.model.Genre;

import java.util.Objects;

@Service
public class GenreService {

    private final GenreDao dao;

    public GenreService(GenreDao dao){
        this.dao = dao;
    }

    @Transactional
    public GenreDTO addGenre(String name, String description){
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        if(dao.find(name) == null) {
            Genre genre = new Genre();
            genre.setName(name);
            genre.setDescription(description);
            dao.persist(genre);
            return new GenreDTO(genre.getName(), genre.getDescription());
        } else return null;
    }

    @Transactional
    public void updateGenre(String name, String description){
        Objects.requireNonNull(name);
        Objects.requireNonNull(description);
        Genre genre = dao.find(name);
        genre.setDescription(description);
        dao.update(genre);
    }

}
