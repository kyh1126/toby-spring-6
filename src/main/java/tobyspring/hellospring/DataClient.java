package tobyspring.hellospring;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.data.OrderRepository;
import tobyspring.hellospring.order.Order;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            new TransactionTemplate(transactionManager).execute((TransactionCallback<Order>) status -> {
                // transaction begin
                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                System.out.println(order);

                Order order2 = new Order("100", BigDecimal.ONE);
                repository.save(order2);
                // commit

                return null;
            });
        } catch (DataAccessException e) {
            // PersistenceException: JPA Exception
            // DataIntegrityViolationException: Spring Exception
            // ConstraintViolationException: Hibernate Exception
            if (e instanceof DataIntegrityViolationException || e.getCause() instanceof ConstraintViolationException) {
                System.out.println("주문번호 충돌을 복구하는 작업");
            } else {
                System.out.println("DataAccessException - not ConstraintViolationException !!");
            }
        }

//        try {
//            Order order2 = new Order("100", BigDecimal.ONE);
//            repository.save(order2);
//        } catch (PersistenceException e) {
//            if (e.getCause() instanceof ConstraintViolationException) {
//                System.out.println("주문번호 충돌을 복구하는 작업");
//            } else {
//                System.out.println("PersistenceException - not ConstraintViolationException !!");
//            }
//        }
    }
}
