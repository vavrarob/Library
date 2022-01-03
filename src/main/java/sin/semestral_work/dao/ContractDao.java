package sin.semestral_work.dao;

import org.springframework.stereotype.Repository;
import sin.semestral_work.model.Contract;

@Repository
public class ContractDao extends GenericDaoImpl<Contract> {
    public ContractDao() {
        super(Contract.class);
    }
}
