package jpabook.jpashop.delivery;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class DeliveryRepository {

    @PersistenceContext
    EntityManager em;


}
