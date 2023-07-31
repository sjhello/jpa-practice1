package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.exception.NotEnoughStockException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ItemTest {

    @Test
    void addStockTest() {
        Album album = new Album();
        album.setStockQuantity(20);

        album.addStock(1);

        assertThat(album.getStockQuantity()).isEqualTo(21);
    }

    @Test
    void removeStockTest() {
        Album album = new Album();
        album.setStockQuantity(20);

        album.removeStock(1);

        assertThat(album.getStockQuantity()).isEqualTo(19);
    }

    @Test
    void removeStockExceptionTest() {
        Album album = new Album();
        album.setStockQuantity(0);

        assertThatThrownBy(() -> album.removeStock(1))
                .isInstanceOf(NotEnoughStockException.class)
                .hasMessage("재고는 0이 될 수 없습니다.");

    }
}
