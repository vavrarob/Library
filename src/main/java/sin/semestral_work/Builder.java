package sin.semestral_work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sin.semestral_work.dao.*;
import sin.semestral_work.model.*;


import java.util.ArrayList;
import java.util.List;

@Component
public class Builder {

    private final AuthorDao authorDao;

    private final BookDao bookDao;

    private final BookPublicationDao bookPublicationDao;

    private final ContractDao contractDao;

    private final GenreDao genreDao;

    private final LibraryDao libraryDao;

    private final PublishedBookDao publishedBookDao;

    private final PublishingHouseDao publishingHouseDao;

    @Autowired
    public Builder(AuthorDao authorDao, BookDao bookDao, BookPublicationDao bookPublicationDao, ContractDao contractDao,
                   GenreDao genreDao, LibraryDao libraryDao, PublishedBookDao publishedBookDao, PublishingHouseDao publishingHouseDao) {
        this.authorDao = authorDao; //
        this.bookDao = bookDao; //
        this.bookPublicationDao = bookPublicationDao;
        this.contractDao = contractDao;
        this.genreDao = genreDao; //
        this.libraryDao = libraryDao; //
        this.publishedBookDao = publishedBookDao;
        this.publishingHouseDao = publishingHouseDao; //
    }

    @Transactional
    public void build(){
        List<Author> authors = createAuthors(5);
        List<Genre> genres = createGenres(6);
        List<Book> books = createBooks(10);
        List<Library> libraries = createLibraries(2);
        List<PublishingHouse> publishingHouses = createPublishingHouses(2);
    }

    @Transactional
    public List<Author> createAuthors(int count){
//        System.out.println("Creating authors");
        List<Author> authors = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            String firstName = "firstName" + i;
            String lastName = "lastName" + i;
            String email = "email" + i + "@gmail.com";
            Author author = new Author();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            author.setEmail(email);
            authors.add(author);
        }
        authorDao.persist(authors);
        return authors;
    }

    @Transactional
    public List<Genre> createGenres(int count){
//        System.out.println("Creating genres");
        List<Genre> genres = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            String name = "genre" + i;
            String description = "description" + i;
            Genre genre = new Genre();
            genre.setName(name);
            genre.setDescription(description);
            genres.add(genre);
        }
        genreDao.persist(genres);
        return genres;
    }

    @Transactional
    public List<Book> createBooks(int count){
//        System.out.println("Creating books");
        List<Book> books = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            String name = "bookName" + i;
            Book book = new Book();
            book.setName(name);
            books.add(book);
        }
        bookDao.persist(books);
        return books;
    }

    @Transactional
    public List<Library> createLibraries(int count){
//        System.out.println("Creating libraries");
        List<Library> libraries = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            String name = "library" + i;
            String address = "address" + i;
            Library library = new Library();
            library.setName(name);
            library.setAddress(address);
            libraries.add(library);
        }
        libraryDao.persist(libraries);
        return libraries;
    }

    @Transactional
    public List<PublishingHouse> createPublishingHouses(int count){
        List<PublishingHouse> publishingHouses = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            String name = "publishingHouse" + i;
            String address = "address" + i;
            PublishingHouse publishingHouse = new PublishingHouse();
            publishingHouse.setName(name);
            publishingHouse.setAddress(address);
            publishingHouses.add(publishingHouse);
        }
        publishingHouseDao.persist(publishingHouses);
        return publishingHouses;
    }

//    @Transactional
//    public List<Contract> createContracts(List<Author> authors, List<PublishingHouse> publishingHouses){
//        List<Contract> contracts = new ArrayList<>();
//
//        for(int i = 0; i < authors.size(); ++i){
//            Author author = authors.
//        }
//    }
}