package sin.semestral_work.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sin.semestral_work.dao.ContractDao;
import sin.semestral_work.dto.ContractDTO;
import sin.semestral_work.model.Author;
import sin.semestral_work.model.Contract;
import sin.semestral_work.model.PublishingHouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ContractService {

    private final ContractDao dao;

    @Autowired
    public ContractService(ContractDao dao) {
        this.dao = dao;
    }

    @Transactional
    public ContractDTO createContract(List<Author> authors, PublishingHouse publishingHouse, Date endDate){
        Objects.requireNonNull(authors);
        Objects.requireNonNull(publishingHouse);
        Contract contract = new Contract();
        contract.setAuthors(authors);
        contract.setPublishingHouse(publishingHouse);
        contract.setEndDate(endDate);
        for (Author a : authors){
            a.addContract(contract);
        }
        publishingHouse.addContract(contract);
        dao.persist(contract);
        return new ContractDTO(contract.getId(), contract.getAuthors(), contract.getPublishingHouse(), contract.getEndDate());
    }

    @Transactional(readOnly = true)
    public List<ContractDTO> getContracts(){
        List<Contract> contracts = dao.findAll();
        List<ContractDTO> contractDTOS = new ArrayList<>();
        for(Contract c : contracts){
            contractDTOS.add(new ContractDTO(c.getId(), c.getAuthors(), c.getPublishingHouse(), c.getEndDate()));
        }
        return contractDTOS;
    }
}
