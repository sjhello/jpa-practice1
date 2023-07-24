package jpabook.jpashop.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @Test
    void saveItemTest() {
        Album album = new Album();
        album.setName("sjhello album");
        album.setPrice(2000);
        album.setStockQuantity(20);

        itemService.saveItem(album);

        Item findItem = itemRepository.findOne(album.getId());
        assertThat(findItem.getId()).isEqualTo(album.getId());
    }

    @Test
    void findAllTest() {
        Album album1 = new Album();
        album1.setName("sjhello album");
        album1.setPrice(2000);
        album1.setStockQuantity(20);

        Album album2 = new Album();
        album2.setName("sjhello album");
        album2.setPrice(2000);
        album2.setStockQuantity(20);
        itemService.saveItem(album1);
        itemService.saveItem(album2);

        List<Item> items = itemService.findAll();

        assertThat(items).containsExactly(album1, album2);
    }
}