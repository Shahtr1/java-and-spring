package Structural.adapter;

import java.util.ArrayList;
import java.util.List;

public class SwiggyStore {
    List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }
}
