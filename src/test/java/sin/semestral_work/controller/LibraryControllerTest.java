package sin.semestral_work.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sin.semestral_work.dto.LibraryDTO;
import sin.semestral_work.model.Book;
import sin.semestral_work.model.BookPublication;
import sin.semestral_work.model.Library;
import sin.semestral_work.model.PublishedBook;
import sin.semestral_work.rest.LibraryController;

import javax.persistence.EntityManager;
import java.util.List;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LibraryControllerTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private LibraryController libraryController;

    @Test
    @Transactional
    public void complexLibraryTest(){
        String name = "library";
        String address = "address";

        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setName(name);
        libraryDTO.setAddress(address);

        libraryDTO = libraryController.createLibrary(libraryDTO);

        Assert.assertNotNull(libraryDTO);

        libraryController.deleteLibrary(libraryDTO.getId());

        libraryDTO = libraryController.getLibrary(libraryDTO.getId());

        Assert.assertNull(libraryDTO);

    }

    @Test
    @Transactional
    public void complexLibraryBookTest(){
        String name = "library";
        String address = "address";

        LibraryDTO libraryDTO = new LibraryDTO();
        libraryDTO.setName(name);
        libraryDTO.setAddress(address);

        libraryDTO = libraryController.createLibrary(libraryDTO);

        BookPublication bookPublication = new BookPublication();
        em.persist(bookPublication);

        PublishedBook publishedBook = new PublishedBook();
        publishedBook.setBookPublication(bookPublication);
        em.persist(publishedBook);

        libraryController.addBook(libraryDTO.getId(), publishedBook.getId());

        libraryDTO = libraryController.getLibrary(libraryDTO.getId());

        Assert.assertTrue(libraryDTO.getBooks().contains(publishedBook));

        libraryController.removeBook(libraryDTO.getId(), publishedBook.getId());

        libraryDTO = libraryController.getLibrary(libraryDTO.getId());

        Assert.assertFalse(libraryDTO.getBooks().contains(publishedBook));

    }
}
