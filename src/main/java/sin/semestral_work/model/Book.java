package sin.semestral_work.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Book.findByName", query = "SELECT b FROM Book b WHERE b.name = :name"),
        @NamedQuery(name = "Book.findByGenre", query = "SELECT b FROM Book b WHERE b.genre = :genre"),
})
public class Book extends AbstractEntity{

    @Getter
    @Setter
    private String name;

    @ManyToOne
    @Getter
    @Setter
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Genre genre;

    @ManyToMany
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @Getter
    @Setter
    private List<Author> authors;

    @OneToMany(mappedBy = "book")
    @Getter
    @Setter
    private List<BookPublication> publications;

    public void addAuthor(Author author){
        Objects.requireNonNull(author);
        if(authors == null) {
            this.authors = new ArrayList<>();
        }
        authors.add(author);
    }

    public void removeAuthor(Author author){
        Objects.requireNonNull(author);
        if(authors == null) {
            return;
        }
        authors.removeIf(c -> Objects.equals(c.getId(), author.getId()));
    }

    public void addPublication(BookPublication bookPublication){
        Objects.requireNonNull(bookPublication);
        if(publications == null) {
            this.publications = new ArrayList<>();
        }
        publications.add(bookPublication);
    }

    public void removePublication(BookPublication bookPublication){
        Objects.requireNonNull(bookPublication);
        if(publications == null) {
            return;
        }
        publications.removeIf(c -> Objects.equals(c.getISBN(), bookPublication.getISBN()));
    }

    public String toString(){
        return "Book{" +
                "id=" + getId() +
                ", name=" + name +
                "}";
    }

}
