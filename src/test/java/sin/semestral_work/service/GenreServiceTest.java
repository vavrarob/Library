package sin.semestral_work.service;

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

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class GenreServiceTest {


    @Autowired
    private EntityManager em;

    @Autowired
    private GenreService genreService;

    @Test
    @Transactional
    public void createGenreTest(){
        String name = "name";
        String description = "description";
        GenreDTO genreDTO = genreService.addGenre(name, description);

        Genre result = em.find(Genre.class, genreDTO.getName());

        Assert.assertEquals(genreDTO.getName(), result.getName());
        Assert.assertEquals(genreDTO.getDescription(), result.getDescription());
    }

    @Test
    @Transactional
    public void updateGenreTest(){
        String name = "name";
        String description = "description";
        GenreDTO genreDTO = genreService.addGenre(name, description);

        String description_final = "new_description";
        genreService.updateGenre(name, description_final);

        Genre result = em.find(Genre.class, genreDTO.getName());

        Assert.assertEquals(genreDTO.getName(), result.getName());
        Assert.assertEquals(result.getDescription(), description_final);
    }

}
