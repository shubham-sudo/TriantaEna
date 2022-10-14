package games.inventory;

import java.util.*;

public abstract class Inventory<T> {
    private static int ID = 0;
    private final int id;
    protected List<T> items;

    public Inventory(){
        this.id = ++ID;
    }

    abstract void addItem(T item);
    abstract void reset();
    abstract void display(String name);
}
