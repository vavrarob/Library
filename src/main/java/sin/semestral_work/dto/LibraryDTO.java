package sin.semestral_work.dto;

import lombok.Getter;
import lombok.Setter;
import sin.semestral_work.model.PublishedBook;
import sin.semestral_work.model.PublishingHouse;

import java.util.List;

public class LibraryDTO {

    public LibraryDTO() {}

    public LibraryDTO(Integer id, String name, String address, List<PublishedBook> books) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.books = books;
    }

    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private List<PublishedBook> books;

}
