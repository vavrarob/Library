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
import sin.semestral_work.dto.BookPublicationDTO;
import sin.semestral_work.dto.ContractDTO;
import sin.semestral_work.model.*;
import sin.semestral_work.rest.BookPublicationController;
import sin.semestral_work.service.BookService;
import sin.semestral_work.service.ContractService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookPublicationControllerTest {

    @Autowired
    private BookPublicationController bookPublicationController;

    @Autowired
    private ContractService contractService;

    @Autowired
    private BookService bookService;

    @Test
    @Transactional
    public void complexBookPublicationControllerTest(){
        List<Author> authors = new ArrayList<>();
        Author author = new Author();
        authors.add(author);

        Genre genre = new Genre();
        genre.setName("genre");

        Book book = bookService.createBook("book", genre, authors);


        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("name");
        publishingHouse.setAddress("address");

        contractService.createContract(authors, publishingHouse, new Date());

        BookPublicationDTO bookPublicationDTO = new BookPublicationDTO();
        bookPublicationDTO.setBook(book);
        bookPublicationDTO.setPublishingHouse(publishingHouse);

        BookPublicationDTO bookPublication = bookPublicationController.createBookPublication(bookPublicationDTO);

        BookPublicationDTO result = bookPublicationController.findByBookAndPublishingHouse(bookPublicationDTO);

        Assert.assertEquals(bookPublication.getISBN(), result.getISBN());

    }
}
