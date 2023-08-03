package jpabook.jpashop.domain.order;

import jpabook.jpashop.web.order.dto.OrderSimpleQueryDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Order order) {
        em.persist(order);
        return order.getId();
    }

    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    public void deleteById(Long id) {
        em.remove(findById(id));
    }

    public List<Order> findAll() {
        return em.createQuery("select o from Order o", Order.class)
                .getResultList();
    }

    public List<Order> findAllFetch() {
        return em.createQuery("select o from Order o join fetch o.member m join fetch o.delivery d", Order.class)
                .getResultList();
    }

    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery("select new jpabook.jpashop.web.order.dto.OrderSimpleQueryDto(o.id, m.username, o.orderDate, o.status, d.address) from Order o " +
                        "join o.member m " +
                        "join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }

    public List<Order> findAllWithItem() {
        return em.createQuery("select distinct o from Order o" +
                        " join fetch o.member m" +
                        " join fetch o.delivery d" +
                        " join fetch o.orderItems oi" +
                        " join fetch oi.item", Order.class)
                .getResultList();
    }
}
