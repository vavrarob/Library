package sin.semestral_work.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sin.semestral_work.dto.GenreDTO;
import sin.semestral_work.model.Genre;
import sin.semestral_work.service.GenreService;

@RestController
@RequestMapping("/rest/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GenreDTO addGenre(@RequestBody GenreDTO genreDTO){
      return genreService.addGenre(genreDTO.getName(), genreDTO.getDescription());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateGenre(@RequestBody GenreDTO genreDTO) {
        genreService.updateGenre(genreDTO.getName(), genreDTO.getDescription());
    }
}
