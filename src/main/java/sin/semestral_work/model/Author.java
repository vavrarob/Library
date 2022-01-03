package sin.semestral_work.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Author extends AbstractEntity{

    @Getter
    @Setter
    @NotNull
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String email;

    @ManyToMany(mappedBy = "authors")
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @Getter
    @Setter
    private List<Book> books;

    @ManyToMany(mappedBy = "authors")
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @Getter
    @Setter
    private List<Contract> contracts;

    public void addBook(Book book){
        Objects.requireNonNull(book);
        if(books == null){
            this.books = new ArrayList<>();
        }
        books.add(book);
    }

    public void removeBook(Book book){
        Objects.requireNonNull(book);
        if(books == null) {
            return;
        }
        books.removeIf(c -> Objects.equals(c.getId(), book.getId()));
    }

    public void addContract(Contract contract){
        Objects.requireNonNull(contract);
        if(contracts == null){
            this.contracts = new ArrayList<>();
        }
        contracts.add(contract);
    }

    public void removeContract(Contract contract){
        Objects.requireNonNull(contract);
        if(contracts == null) {
            return;
        }
        contracts.removeIf(c -> Objects.equals(c.getId(), contract.getId()));
    }

    public String toString(){
        return "Author{" +
                "id=" + getId() +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", email=" + email +
                "}";
    }
}
