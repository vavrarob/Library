package sin.semestral_work.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sin.semestral_work.dto.ContractDTO;
import sin.semestral_work.model.Contract;
import sin.semestral_work.service.ContractService;

import java.util.List;

@RestController
@RequestMapping("/rest/contracts")
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public ContractDTO createContract(@RequestBody ContractDTO contractDTO){
        return contractService.createContract(contractDTO.getAuthors(), contractDTO.getPublishingHouse(), contractDTO.getEndDate());
    }

    @GetMapping
    public List<ContractDTO> getContracts(){
        return contractService.getContracts();
    }
}
