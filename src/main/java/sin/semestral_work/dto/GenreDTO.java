package sin.semestral_work.dto;

import lombok.Getter;
import lombok.Setter;

public class GenreDTO {

    public GenreDTO() {}

    public GenreDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String description;
}
