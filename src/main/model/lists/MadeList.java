package model.lists;

import model.Drink;

// Represents a drink list with all made drinks
public class MadeList extends DrinkList {

    // EFFECTS: constructor
    public MadeList() {
        super();
        type = Drink.Status.Made.toString();
    }
}
