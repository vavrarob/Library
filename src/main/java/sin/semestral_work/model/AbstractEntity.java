package sin.semestral_work.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class AbstractEntity implements Serializable {
        @Id
        @GeneratedValue
        @Getter
        @Setter
        private Integer id;

}
