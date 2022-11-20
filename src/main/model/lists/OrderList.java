package model.lists;

import model.Drink;

// Represents a drink list with orders of the drinks
public class OrderList extends DrinkList {

    // EFFECTS: constructor
    public OrderList() {
        super();
        type = Drink.Status.Ordered.toString();
    }
}
