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
import sin.semestral_work.dto.BookPublicationDTO;
import sin.semestral_work.dto.ContractDTO;
import sin.semestral_work.model.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookPublicationServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ContractService contractService;

    @Autowired
    private BookPublicationService bookPublicationService;

    @Test
    @Transactional
    public void createBookPublicationPassTest(){
        Author author = new Author();
        em.persist(author);

        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("name");
        publishingHouse.setAddress("address");
        em.persist(publishingHouse);

        Book book = new Book();
        book.setName("book");
        em.persist(book);

        book.addAuthor(author);
        author.addBook(book);
        em.merge(book);

        List<Author> authors = new ArrayList<>();
        authors.add(author);
        contractService.createContract(authors, publishingHouse, new Date());

        BookPublicationDTO bookPublicationDTO = bookPublicationService.createBookPublication(book, publishingHouse);
        Assert.assertNotNull(bookPublicationDTO);
        Assert.assertNotNull(bookPublicationDTO.getISBN());
    }

    @Test
    @Transactional
    public void createBookPublicationFailsTest(){
        Author author = new Author();
        em.persist(author);

        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("name");
        publishingHouse.setAddress("address");
        em.persist(publishingHouse);

        Book book = new Book();
        book.setName("book");
        em.persist(book);

        book.addAuthor(author);
        author.addBook(book);
        em.merge(book);

        BookPublicationDTO bookPublicationDTO = bookPublicationService.createBookPublication(book, publishingHouse);
        Assert.assertNull(bookPublicationDTO);

    }

    @Test
    @Transactional
    public void findByBookAndPublishingHouseTest(){
        Author author1 = new Author();
        em.persist(author1);

        PublishingHouse publishingHouseOne = new PublishingHouse();
        publishingHouseOne.setName("name1");
        publishingHouseOne.setAddress("address");
        em.persist(publishingHouseOne);

        PublishingHouse publishingHouseTwo = new PublishingHouse();
        publishingHouseTwo.setName("name2");
        publishingHouseTwo.setAddress("address");
        em.persist(publishingHouseTwo);

        Book book1 = new Book();
        book1.setName("book");
        em.persist(book1);

        book1.addAuthor(author1);
        author1.addBook(book1);
        em.merge(book1);

        Book book2 = new Book();
        book2.setName("book");
        em.persist(book2);

        book2.addAuthor(author1);
        author1.addBook(book2);
        em.merge(book2);

        List<Author> authors = new ArrayList<>();
        authors.add(author1);
        contractService.createContract(authors, publishingHouseOne, new Date());
        contractService.createContract(authors, publishingHouseTwo, new Date());

        BookPublicationDTO bookPublicationDTO1 = bookPublicationService.createBookPublication(book1, publishingHouseOne);
        BookPublicationDTO bookPublicationDTO2 = bookPublicationService.createBookPublication(book2, publishingHouseTwo);

        Assert.assertEquals(bookPublicationDTO1.getISBN(), bookPublicationService.findByBookAndPublishingHouse(book1, publishingHouseOne).getISBN());
    }
}
