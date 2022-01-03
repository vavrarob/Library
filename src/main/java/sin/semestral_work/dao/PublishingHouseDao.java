package sin.semestral_work.dao;

import org.springframework.stereotype.Repository;
import sin.semestral_work.model.PublishingHouse;

@Repository
public class PublishingHouseDao extends GenericDaoImpl<PublishingHouse> {
    public PublishingHouseDao() {
        super(PublishingHouse.class);
    }
}
