package sin.semestral_work.dao;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import sin.semestral_work.SemestralWorkApplication;
import sin.semestral_work.model.Book;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ComponentScan(basePackageClasses = SemestralWorkApplication.class)
public class BaseDaoTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookDao bookDao;

    private static Book generateBook(){
        final Book book = new Book();
        Random RAND = new Random();
        book.setName("Test book" + RAND.nextInt());
        return book;
    }

    @Test
    public void persistSavesSpecifiedInstance() {
        final Book book = generateBook();
        bookDao.persist(book);
        assertNotNull(book.getId());

        final Book result = entityManager.find(Book.class, book.getId());
        assertNotNull(result);
        assertEquals(book.getId(), result.getId());
        assertEquals(book.getName(), result.getName());
    }

    @Test
    public void findRetrievesInstanceByIdentifier() {
        final Book book = generateBook();
        entityManager.persistAndFlush(book);
        assertNotNull(book.getId());

        final Book result = bookDao.find(book.getId());
        assertNotNull(result);
        assertEquals(book.getId(), result.getId());
        assertEquals(book.getName(), result.getName());
    }

    @Test
    public void findAllRetrievesAllInstancesOfType() {
        final Book bookOne = generateBook();
        entityManager.persistAndFlush(bookOne);
        final Book bookTwo = generateBook();
        entityManager.persistAndFlush(bookTwo);

        final List<Book> result = bookDao.findAll();
        assertTrue(result.stream().anyMatch(c -> c.equals(bookOne)));
        assertTrue(result.stream().anyMatch(c -> c.equals(bookTwo)));
    }

    @Test
    public void updateUpdatesExistingInstance() {
        final Book book = generateBook();
        entityManager.persistAndFlush(book);

        final Book update = new Book();
        update.setId(book.getId());
        final String newName = "New book name";
        update.setName(newName);
        bookDao.update(update);

        final Book result = bookDao.find(book.getId());
        assertNotNull(result);
        assertEquals(book.getName(), result.getName());
    }

    @Test
    public void removeRemovesSpecifiedInstance() {
        final Book book = generateBook();
        entityManager.persistAndFlush(book);
        assertNotNull(entityManager.find(Book.class, book.getId()));
        entityManager.detach(book);

        bookDao.remove(book);
        assertNull(entityManager.find(Book.class, book.getId()));
    }

    @Test
    public void removeDoesNothingWhenInstanceDoesNotExist() {
        final Book book = generateBook();
        book.setId(123);
        assertNull(entityManager.find(Book.class, book.getId()));

        bookDao.remove(book);
        assertNull(entityManager.find(Book.class, book.getId()));
    }

    @Test
    public void exceptionOnPersistInWrappedInPersistenceException() {
        final Book book = generateBook();
        entityManager.persistAndFlush(book);
        entityManager.remove(book);
        assertThrows(PersistenceException.class, () -> bookDao.update(book));
    }

    @Test
    public void existsReturnsTrueForExistingIdentifier() {
        final Book book = generateBook();
        entityManager.persistAndFlush(book);
        assertTrue(bookDao.exists(book.getId()));
        assertFalse(bookDao.exists(-1));
    }
}
