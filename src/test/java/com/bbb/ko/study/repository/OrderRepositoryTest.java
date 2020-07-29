package com.bbb.ko.study.repository;

import com.bbb.ko.study.configuration.SpringDataJdbcConfiguration;
import com.bbb.ko.study.entity.PurchaseOrder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringDataJdbcConfiguration.class)
public class OrderRepositoryTest {

    private final OrderRepository orderRepository;

    public OrderRepositoryTest(@Qualifier("orderRepository") OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Test
    @Transactional
    public void createUpdateDeleteOrder() {
        PurchaseOrder order = new PurchaseOrder();
        order.addItem(4, "Captain Future Comet Lego set");
        order.addItem(2, "Cute blue angler fish plush toy");

        PurchaseOrder saved = orderRepository.save(order);

        assertThat(orderRepository.findAll()).isNotEmpty();
        assertThat(orderRepository.count()).isEqualTo(1);
        assertThat(orderRepository.countItems()).isEqualTo(2);

        orderRepository.delete(saved);

        assertThat(orderRepository.findAll()).isEmpty();
        assertThat(orderRepository.count()).isEqualTo(0);
        assertThat(orderRepository.countItems()).isEqualTo(0);
    }

    @Test
    @Transactional
    public void findBySomeColum() {
        PurchaseOrder order = new PurchaseOrder();
        order.addItem(4, "Captain Future Comet Lego set");

        PurchaseOrder saved = orderRepository.save(order);

        assertThat(orderRepository.findAllIds())
                .isNotEmpty()
                .satisfies(list -> assertThat(list.size()).isEqualTo(1));
    }
}
