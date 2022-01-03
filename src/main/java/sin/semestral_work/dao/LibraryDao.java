package sin.semestral_work.dao;


import org.springframework.stereotype.Repository;
import sin.semestral_work.model.Library;

@Repository
public class LibraryDao extends GenericDaoImpl<Library> {
    public LibraryDao() {
        super(Library.class);
    }
}
