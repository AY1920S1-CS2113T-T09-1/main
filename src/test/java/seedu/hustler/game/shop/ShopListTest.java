package seedu.hustler.game.shop;

import org.junit.jupiter.api.Test;
import seedu.hustler.game.shop.items.ShopItem;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ShopListTest {

    @Test
    public void checkSize() {
        ShopList emptyShopList = new ShopList();

        // When no item is populated in the shop.
        assertEquals(emptyShopList.size(), 0);

        // When one item is populated in the shop.
        ShopList oneItemShopList = emptyShopList.addItem(new ShopItemStub());
        assertEquals(oneItemShopList.size(), 1);

        // When fifty items are populated in the shop.
        ShopList fiftyItemsShopList = new ShopList();
        for (int i = 0; i < 50; i++) {
            fiftyItemsShopList = fiftyItemsShopList.addItem(new ShopItemStub());
        }
        assertEquals(fiftyItemsShopList.size(), 50);
    }

    @Test
    public void purchasableTest() {
        ShopList oneItemShopList = new ShopList().addItem(new ShopItemStub());

        // Insufficient total points means will give back an Optional.empty().
        Optional<ShopItem> empty = oneItemShopList.buy(0, 0);
        assertTrue(empty.isEmpty());

        //Sufficient total points means will give back a Shop Item stub.
        Optional<ShopItem> present = oneItemShopList.buy(0, 1);
        assertTrue(present.isPresent());

        // Sufficient total points but item has been purchased gives back an Optional.empty().
        oneItemShopList.getItem(0).setIsPurchased(true);
        Optional<ShopItem> alsoEmpty = oneItemShopList.buy(0, 1);
        assertTrue(alsoEmpty.isEmpty());
    }

    @Test
    public void checkToTxtFormat() {
        ShopList emptyShopList = new ShopList();

        // Should not contain any text.
        assertEquals("", emptyShopList.itemsStatus());

        // Should return all false for new item stubs that are not purchased.
        ShopList shopListTest = new ShopList();
        for (int i = 0; i < 3; i++) {
            shopListTest = shopListTest.addItem(new ShopItemStub());
        }
        assertEquals("false\nfalse\nfalse", shopListTest.itemsStatus());

        // Should return the first item stub as true.
        shopListTest.getItem(0).setIsPurchased(true);
        assertEquals("true\nfalse\nfalse", shopListTest.itemsStatus());
    }

    @Test
    public void checkObtainedPurchasedItems() {
        ShopList shopListTest = new ShopList();

        // Should return an epmty arrayList if adding an item that has yet to be purchased.
        shopListTest = shopListTest.addItem(new ShopItemStub());
        assertEquals(0, shopListTest.getPurchasedItems().size());

        // Should return the arraylist with size of one.
        ShopItemStub purchasedStub = new ShopItemStub();
        purchasedStub.setIsPurchased(true);
        shopListTest = shopListTest.addItem(purchasedStub);
        assertEquals(1, shopListTest.getPurchasedItems().size());
    }

    @Test
    public void immutabilityCheck() {
        ShopList emptyShopList = new ShopList();

        // Should still return an empty shop list.
        emptyShopList.addItem(new ShopItemStub()).addItem(new ShopItemStub());
        assertEquals(0, emptyShopList.size());

        // Supports chaining when creating a new instance.
        assertEquals(1, new ShopList().addItem(new ShopItemStub()).size());
    }


    private class ShopItemStub implements ShopItem {

        Boolean isPurchased = false;

        @Override
        public int getCost() {
            return 1;
        }

        @Override
        public Boolean isPurchased() {
            return isPurchased;
        }

        @Override
        public String getType() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ShopItem setIsPurchased(boolean isPurchased) {
            this.isPurchased = true;
            return this;
        }

        @Override
        public Boolean isSameType(ShopItem other) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getDamageIncr() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getDefenceIncr() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getStaminaIncr() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canPurchase(int points) {
            return points >= this.getCost();
        }
    }


}
