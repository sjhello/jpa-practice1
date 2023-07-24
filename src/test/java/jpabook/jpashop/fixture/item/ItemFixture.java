package jpabook.jpashop.fixture.item;

import jpabook.jpashop.item.Album;
import jpabook.jpashop.item.Item;

public class ItemFixture {

    public static Item createItem(String name, int price, int stockQuantity) {
        Album album = new Album();
        album.setName(name);
        album.setPrice(price);
        album.setStockQuantity(stockQuantity);

        return album;
    }
}
