package sin.semestral_work.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import sin.semestral_work.dto.BookPublicationDTO;
import sin.semestral_work.model.Book;
import sin.semestral_work.model.BookPublication;
import sin.semestral_work.model.PublishingHouse;
import sin.semestral_work.service.BookPublicationService;

import java.util.Objects;

@RestController
@RequestMapping("/rest/bookPublications")
public class BookPublicationController {

    private final BookPublicationService bookPublicationService;

    @Autowired
    public BookPublicationController(BookPublicationService bookPublicationService) {
        this.bookPublicationService = bookPublicationService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookPublicationDTO createBookPublication(@RequestBody BookPublicationDTO bookPublicationDTO){
        return bookPublicationService.createBookPublication(bookPublicationDTO.getBook(), bookPublicationDTO.getPublishingHouse());
    }


    @GetMapping(value = "/bookAndPublishingHouse")
    public BookPublicationDTO findByBookAndPublishingHouse(@RequestBody BookPublicationDTO bookPublicationDTO) {
        return bookPublicationService.findByBookAndPublishingHouse(bookPublicationDTO.getBook(), bookPublicationDTO.getPublishingHouse());
    }
}
