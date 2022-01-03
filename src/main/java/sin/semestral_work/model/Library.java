package sin.semestral_work.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Library extends AbstractEntity{

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String address;

    @OneToMany(mappedBy = "library")
    @Getter
    @Setter
    private List<PublishedBook> books;

    public String toString(){
        return "Library{" +
                "id=" + getId() +
                ", name=" + name +
                ", address=" + address +
                "}";
    }

    public void addBook(PublishedBook book){
        Objects.requireNonNull(book);
        if(books == null) {
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

}
