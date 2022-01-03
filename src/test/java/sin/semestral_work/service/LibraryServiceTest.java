package sin.semestral_work.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sin.semestral_work.model.BookPublication;
import sin.semestral_work.model.Library;
import sin.semestral_work.model.PublishedBook;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LibraryServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private LibraryService libraryService;


    @Test
    @Transactional
    public void addBookToLibraryPassTest() {
        Library library = new Library();
        library.setName("library");
        library.setAddress("address");
        em.persist(library);

        BookPublication bookPublication =  new BookPublication();
        bookPublication.setDateOfPublishing(new Date());
        em.persist(bookPublication);

        PublishedBook publishedBook = new PublishedBook();
        publishedBook.setBookPublication(bookPublication);
        em.persist(publishedBook);

        Assert.assertNotNull(library);
        Assert.assertNotNull(publishedBook);
        Assert.assertNotNull(publishedBook.getBookPublication());

        libraryService.addBook(library.getId(), publishedBook.getId());

        library = em.find(Library.class, library.getId());
        publishedBook = em.find(PublishedBook.class, publishedBook.getId());

        Assert.assertEquals(publishedBook.getLibrary().getId(), library.getId());
    }

    @Test
    @Transactional
    public void addBookToLibraryFailsTest() {
        Library library = new Library();
        library.setName("library");
        library.setAddress("address");
        em.persist(library);

        BookPublication bookPublication =  new BookPublication();
        bookPublication.setDateOfPublishing(new Date());
        em.persist(bookPublication);

        for(int i = 0; i < 10; ++i) {
            PublishedBook publishedBook = new PublishedBook();
            publishedBook.setBookPublication(bookPublication);
            em.persist(publishedBook);
            libraryService.addBook(library.getId(), publishedBook.getId());
        }


        library = em.find(Library.class, library.getId());
        Assert.assertEquals(library.getBooks().size(), 5);
    }
}
