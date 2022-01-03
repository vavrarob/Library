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
import sin.semestral_work.dto.ContractDTO;
import sin.semestral_work.model.Author;
import sin.semestral_work.model.Contract;
import sin.semestral_work.model.PublishingHouse;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ContractServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ContractService contractService;

    @Test
    @Transactional
    public void createContractTest(){
        Author author = new Author();
        em.persist(author);
        List<Author> authors = new ArrayList<>();
        authors.add(author);

        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("name");
        publishingHouse.setAddress("address");
        em.persist(publishingHouse);

        ContractDTO contractDTO = contractService.createContract(authors, publishingHouse, new Date());

        Contract result = em.find(Contract.class, contractDTO.getId());

        Assert.assertEquals(contractDTO.getId(), result.getId());
    }

    @Test
    @Transactional
    public void getContractsTest(){
        List<Author> authors = new ArrayList<>();

        Author author1 = new Author();
        em.persist(author1);
        authors.add(author1);

        Author author2 = new Author();
        em.persist(author2);

        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("name");
        publishingHouse.setAddress("address");
        em.persist(publishingHouse);

        ContractDTO contractDTO = contractService.createContract(authors, publishingHouse, new Date());
        authors.add(author2);
        ContractDTO contractDTO1 = contractService.createContract(authors, publishingHouse, new Date());

        List<ContractDTO> contractDTOS = contractService.getContracts();

        Assert.assertTrue(contractDTOS.stream().anyMatch((c) -> c.getId().equals(contractDTO.getId())));
        Assert.assertTrue(contractDTOS.stream().anyMatch((c) -> c.getId().equals(contractDTO1.getId())));
    }
}
