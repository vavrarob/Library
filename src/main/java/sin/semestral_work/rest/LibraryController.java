package sin.semestral_work.rest;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sin.semestral_work.dto.LibraryDTO;
import sin.semestral_work.model.Library;
import sin.semestral_work.model.PublishedBook;
import sin.semestral_work.service.LibraryService;

import java.util.List;

@RestController
@RequestMapping("/rest/libraries")
public class LibraryController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping(value = "/libraryId/{libraryId}/bookId/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@PathVariable Integer libraryId, @PathVariable Integer bookId){
        libraryService.addBook(libraryId, bookId);
    }

    @DeleteMapping(value = "/libraryId/{libraryId}/bookId/{bookId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void removeBook(@PathVariable Integer libraryId, @PathVariable Integer bookId){
        libraryService.removeBook(libraryId, bookId);
    }

    @GetMapping(value = "/libraryId/{libraryId}")
    public LibraryDTO getLibrary(@PathVariable Integer libraryId){
        return libraryService.getLibrary(libraryId);
    }

    @GetMapping
    public List<LibraryDTO> getLibraries(){
        return libraryService.getLibraries();
    }

    @PostMapping
    public LibraryDTO createLibrary(@RequestBody LibraryDTO libraryDTO){
        return libraryService.createLibrary(libraryDTO.getName(), libraryDTO.getAddress());
    }

    @DeleteMapping("/libraryId/{libraryId}")
    public LibraryDTO deleteLibrary(@PathVariable Integer libraryId){
        return libraryService.deleteLibrary(libraryId);
    }

}
