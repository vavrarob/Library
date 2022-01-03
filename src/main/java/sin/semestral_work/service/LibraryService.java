package sin.semestral_work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.semestral_work.dao.LibraryDao;
import sin.semestral_work.dao.PublishedBookDao;
import sin.semestral_work.dto.LibraryDTO;
import sin.semestral_work.model.BookPublication;
import sin.semestral_work.model.Library;
import sin.semestral_work.model.PublishedBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LibraryService {

    private final LibraryDao dao;

    private final PublishedBookDao publishedBookDao;

    @Autowired
    public LibraryService(LibraryDao dao, PublishedBookDao publishedBookDao){
        this.dao = dao;
        this.publishedBookDao = publishedBookDao;
    }

    @Transactional
    public void addBook(Integer libraryId, Integer bookId){
        Objects.requireNonNull(bookId);
        Objects.requireNonNull(libraryId);
        Library library = dao.find(libraryId);
        PublishedBook book = publishedBookDao.find(bookId);
        Objects.requireNonNull(library);
        Objects.requireNonNull(book);
        BookPublication bookPublication = book.getBookPublication();
        Objects.requireNonNull(bookPublication);
        if(library.getBooks() == null || library.getBooks().stream().filter(b -> b.getBookPublication().getISBN().equals(bookPublication.getISBN())).count() < 5){
            if(book.getLibrary() != null) {
                Library prevLibrary = book.getLibrary();
                prevLibrary.removeBook(book);
            }
            library.addBook(book);
            book.setLibrary(library);
            publishedBookDao.update(book);
            publishedBookDao.flush();
        } else {
            System.out.println("Number of books with same ISBN in this library was reached!");
        }
    }

    @Transactional
    public void removeBook(Integer libraryId, Integer bookId){
        Objects.requireNonNull(bookId);
        Objects.requireNonNull(libraryId);
        Library library = dao.find(libraryId);
        PublishedBook book = publishedBookDao.find(bookId);
        Objects.requireNonNull(library);
        Objects.requireNonNull(book);
        library.removeBook(book);
        book.setLibrary(null);
        publishedBookDao.update(book);
    }

    @Transactional
    public LibraryDTO createLibrary(String name, String address){
        Objects.requireNonNull(name);
        Objects.requireNonNull(address);
        Library library = new Library();
        library.setName(name);
        library.setAddress(address);
        dao.persist(library);
        return new LibraryDTO(library.getId(), library.getName(), library.getAddress(), library.getBooks());
    }

    @Transactional
    public LibraryDTO deleteLibrary(Integer libraryId){
        Objects.requireNonNull(libraryId);
        Library library = dao.find(libraryId);
        dao.remove(library);
        return new LibraryDTO(library.getId(), library.getName(), library.getAddress(), library.getBooks());
    }

    @Transactional(readOnly = true)
    public List<LibraryDTO> getLibraries(){
        List<Library> libraries = dao.findAll();
        List<LibraryDTO> libraryDTOS = new ArrayList<>();
        for(Library library : libraries){
            libraryDTOS.add(new LibraryDTO(library.getId(), library.getName(), library.getAddress(), library.getBooks()));
        }
        return libraryDTOS;
    }

    @Transactional(readOnly = true)
    public LibraryDTO getLibrary(Integer libraryId){
        Objects.requireNonNull(libraryId);
        Library library =  dao.find(libraryId);
        if(library == null) return null;
        return new LibraryDTO(library.getId(), library.getName(), library.getAddress(), library.getBooks());
    }
}
