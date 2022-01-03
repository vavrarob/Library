package sin.semestral_work.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import sin.semestral_work.dto.GenreDTO;
import sin.semestral_work.model.Genre;
import sin.semestral_work.rest.GenreController;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GenreControllerTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private GenreController genreController;

    @Test
    @Transactional
    public void complexGenreControllerTest(){
        String name = "genre";
        String description = "description";

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setName(name);
        genreDTO.setDescription(description);

        genreDTO = genreController.addGenre(genreDTO);

        Assert.assertNotNull(genreDTO);

        genreDTO.setDescription("new_description");
        genreController.updateGenre(genreDTO);

        Genre result = em.find(Genre.class, genreDTO.getName());

        Assert.assertEquals(genreDTO.getDescription(), result.getDescription());
    }
}
