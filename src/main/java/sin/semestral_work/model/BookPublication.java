package sin.semestral_work.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "BookPublication.findByBookAndPublishingHouse", query = "SELECT b FROM BookPublication b WHERE b.publishingHouse = :publishingHouse AND b.book = :book")
})
public class BookPublication {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer ISBN;

    @Getter
    @NotNull
    private Date dateOfPublishing;

    @ManyToOne
    @Getter
    @Setter
    private Book book;

    @ManyToOne
    @Getter
    @Setter
    private PublishingHouse publishingHouse;

    @OneToMany(mappedBy = "bookPublication")
    @Getter
    @Setter
    private List<PublishedBook> books;

    public void addBook(PublishedBook book){
        Objects.requireNonNull(book);
        if(books == null){
            this.books = new ArrayList<>();
        }
        books.add(book);
    }

    public void removeBook(PublishedBook book){
        Objects.requireNonNull(book);
        if(books == null) {
            return;
        }
        books.removeIf(c -> Objects.equals(c.getId(), book.getId()));
    }

    public void setDateOfPublishing(Date dateOfPublishing){
        this.dateOfPublishing = new Date(dateOfPublishing.getTime());
    }

    public String toString(){
        return "BookPublication{" +
                "ISBN=" + ISBN +
                ", dateOfPublishing=" + dateOfPublishing +
                "}";
    }
}
