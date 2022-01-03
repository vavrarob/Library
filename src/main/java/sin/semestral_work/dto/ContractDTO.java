package sin.semestral_work.dto;

import lombok.Getter;
import lombok.Setter;
import sin.semestral_work.model.Author;
import sin.semestral_work.model.PublishingHouse;

import java.util.Date;
import java.util.List;

public class ContractDTO {

    public ContractDTO() {}

    public ContractDTO(Integer id, List<Author> authors, PublishingHouse publishingHouse, Date endDate) {
        this.id = id;
        this.authors = authors;
        this.publishingHouse = publishingHouse;
        this.endDate = endDate;
    }

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private List<Author> authors;

    @Getter
    @Setter
    private PublishingHouse publishingHouse;

    @Getter
    @Setter
    private Date endDate;
}
