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
import sin.semestral_work.dto.ContractDTO;
import sin.semestral_work.model.Author;
import sin.semestral_work.model.Contract;
import sin.semestral_work.model.PublishingHouse;
import sin.semestral_work.rest.ContractController;
import sin.semestral_work.service.ContractService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest()
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ContractControllerTest {

    @Autowired
    private ContractController contractController;

    @Test
    @Transactional
    public void complexContractControllerTest() {
        PublishingHouse publishingHouse = new PublishingHouse();
        publishingHouse.setName("name");
        publishingHouse.setAddress("address");
        List<Author> authors = new ArrayList<>();
        authors.add(new Author());
        Date date = new Date();
        ContractDTO contractDTO = new ContractDTO();
        contractDTO.setAuthors(authors);
        contractDTO.setPublishingHouse(publishingHouse);
        contractDTO.setEndDate(date);

       ContractDTO contractDTO1 = contractController.createContract(contractDTO);

       List<ContractDTO> contracts = contractController.getContracts();
       boolean result = contracts.stream().anyMatch((c) -> c.getId().equals(contractDTO1.getId()));
       Assert.assertTrue(result);
    }

}
