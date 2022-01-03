package sin.semestral_work.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Contract extends AbstractEntity{

    @Getter
    @Setter
    private Date startDate;

    @Getter
    private Date endDate;

    @ManyToOne
    @Getter
    @Setter
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private PublishingHouse publishingHouse;

    @ManyToMany
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.PERSIST, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @Getter
    @Setter
    private List<Author> authors;

    public void addAuthor(Author author){
        Objects.requireNonNull(author);
        if(authors == null){
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

    public void setEndDate(Date endDate){
        this.endDate = new Date(endDate.getTime());
    }

    public String toString(){
        return "Contract{" +
                "id=" + getId() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                "}";
    }

}
