package sin.semestral_work.dto;

import lombok.Getter;
import lombok.Setter;
import sin.semestral_work.model.Book;
import sin.semestral_work.model.PublishedBook;
import sin.semestral_work.model.PublishingHouse;

import java.util.Date;
import java.util.List;

public class BookPublicationDTO {

    public BookPublicationDTO() {
    }

    public BookPublicationDTO(Integer ISBN, Date dateOfPublishing, Book book, PublishingHouse publishingHouse, List<PublishedBook> books) {
        this.ISBN = ISBN;
        this.dateOfPublishing = dateOfPublishing;
        this.book = book;
        this.publishingHouse = publishingHouse;
        this.books = books;
    }

    @Getter
    @Setter
    private Integer ISBN;

    @Getter
    @Setter
    private Date dateOfPublishing;

    @Getter
    @Setter
    private Book book;

    @Getter
    @Setter
    private PublishingHouse publishingHouse;

    @Getter
    @Setter
    private List<PublishedBook> books;
}
