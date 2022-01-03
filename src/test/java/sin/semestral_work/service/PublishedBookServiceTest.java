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
import sin.semestral_work.model.PublishedBook;

import javax.persistence.EntityManager;
import java.util.Date;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PublishedBookServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private PublishedBookService publishedBookService;

    @Test
    @Transactional
    public void createBook(){
        BookPublication bookPublication = new BookPublication();
        bookPublication.setDateOfPublishing(new Date());
        em.persist(bookPublication);

        PublishedBook publishedBook = publishedBookService.createBook(bookPublication);

        Assert.assertNotNull(publishedBook);
        Assert.assertEquals(publishedBook.getBookPublication(), bookPublication);
    }

    @Test
    @Transactional
    public void deleteBook(){
        BookPublication bookPublication = new BookPublication();
        bookPublication.setDateOfPublishing(new Date());
        em.persist(bookPublication);

        PublishedBook publishedBook = publishedBookService.createBook(bookPublication);

        publishedBookService.deleteBook(publishedBook);

        PublishedBook result = em.find(PublishedBook.class, publishedBook.getId());

        Assert.assertNull(result);
    }
}
