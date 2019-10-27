package seedu.hustler.game.avatar;

import seedu.hustler.Hustler;
import seedu.hustler.game.shop.ShopList;
import seedu.hustler.game.shop.items.ShopItem;

import java.io.IOException;
import java.util.ArrayList;

public class Inventory {

    private ArrayList<ShopItem> items;

    public Inventory() {
        ShopList shop = new ShopList();
    }

    public Inventory updateInventory() {
        this.items = Hustler.shopList.getPurchasedItems();
        return this;
    }

    public int getSize() { return items.size(); }

    public ShopItem get(int i) { return items.get(i); }

    public void getToEquip(int index) throws IOException {
        Hustler.avatar.equip(items.get(index));
        System.out.println("You are equipped with " + items.get(index));
    }

    public void list() {
        System.out.println("********** You currently have these items: **********");
        if (items.size() == 0) {
            System.out.println();
            System.out.println("You have no items in your inventory.");
            System.out.println();
        } else {
            for (int i = 0; i < items.size(); i++) {
                System.out.println(i + 1 + ". " + items.get(i).toString());
            }
        }
        System.out.println("******************************************************");
    }
}
