package jpabook.jpashop.fixture.domain.item;

import jpabook.jpashop.domain.item.Album;
import jpabook.jpashop.domain.item.Item;

public class ItemFixture {

    public static Item createItem(String name, int price, int stockQuantity) {
        Item item = new Album();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);

        return item;
    }
}
