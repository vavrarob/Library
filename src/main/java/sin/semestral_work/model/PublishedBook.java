package sin.semestral_work.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class PublishedBook extends AbstractEntity{

    @ManyToOne
    @Getter
    @Setter
    private BookPublication bookPublication;

    @ManyToOne
    @Getter
    @Setter
    private Library library;

    public String toString() {
        return "PublishedBook{" +
                "id=" + getId() +
                "}";
    }
}
