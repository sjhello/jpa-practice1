package jpabook.jpashop.fixture.item;

import jpabook.jpashop.item.Album;
import jpabook.jpashop.item.Book;
import jpabook.jpashop.item.Item;

public class ItemFixture {

    public static Item createItem(String name, int price, int stockQuantity) {
        Item item = new Album();
        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);

        return item;
    }
}
