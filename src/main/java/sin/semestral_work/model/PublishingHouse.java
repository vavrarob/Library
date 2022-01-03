package sin.semestral_work.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class PublishingHouse extends AbstractEntity{

    @Getter
    @Setter
    @NotNull
    @Column(unique = true)
    private String name;

    @Getter
    @Setter
    @NotNull
    private String address;

    @OneToMany(mappedBy = "publishingHouse", cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<Contract> contracts;

    @OneToMany(mappedBy = "publishingHouse")
    @Getter
    @Setter
    private List<BookPublication> publications;

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

    public boolean hasContract(List<Author> authors){
        if(contracts == null) return false;
        return contracts.stream().anyMatch(c -> c.getAuthors().containsAll(authors));
    }

    public String toString(){
        return "PublishingHouse{" +
                "id=" + getId() +
                ", name=" + name +
                ", address=" + address +
                "}";
    }
}
