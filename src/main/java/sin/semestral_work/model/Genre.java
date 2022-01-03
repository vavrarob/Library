package sin.semestral_work.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Genre{

    @Id
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;

    public String toString(){
        return "Genre{" +
                "name=" + name +
                ", description=" + description +
                "}";
    }
}
