package jpabook.jpashop.unit;

import jpabook.jpashop.order.OrderStatus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class OrderStatusTest {


    @Test
    void enumTypeOrdinalTest() {
        List<Integer> enumIntegers = Arrays.stream(OrderStatus.values())
                .map(Enum::ordinal)
                .collect(Collectors.toList());

        assertThat(enumIntegers).contains(OrderStatus.ORDER.ordinal());
        assertThat(enumIntegers).contains(OrderStatus.CANCEL.ordinal());
    }

    @Test
    void enumTypeStringTest() {
        List<String> enumStrings = Arrays.stream(OrderStatus.values())
                .map(Enum::toString)
                .collect(Collectors.toList());

        assertThat(enumStrings).contains("ORDER");
        assertThat(enumStrings).contains("CANCEL");
    }
}