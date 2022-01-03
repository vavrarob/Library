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
import sin.semestral_work.model.Author;
import sin.semestral_work.model.Book;
import sin.semestral_work.model.Genre;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private BookService bookService;

    @Test
    @Transactional
    public void createBookTest(){
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        Genre genre = new Genre();
        genre.setName("genre");
        genre.setDescription("description");
        String bookName = "book";

        Book book = bookService.createBook(bookName, genre, authors);

        Book result = em.find(Book.class, book.getId());

        Assert.assertEquals(book, result);
    }

    @Test
    @Transactional
    public void updateBookGenreTest(){
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        Genre genre = new Genre();
        genre.setName("genre");
        genre.setDescription("description");
        String bookName = "book";

        Book book = bookService.createBook(bookName, genre, authors);

        Assert.assertEquals(book.getGenre(), genre);

        Genre new_genre = new Genre();
        new_genre.setName("new_genre");
        new_genre.setDescription("description");
        em.persist(new_genre);

        bookService.updateGenre(book, new_genre);

        Book result = em.find(Book.class, book.getId());

        Assert.assertEquals(result.getGenre(), new_genre);

    }
}
