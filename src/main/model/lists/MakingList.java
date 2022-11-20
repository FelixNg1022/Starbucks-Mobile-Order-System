package model.lists;

import model.Drink;

// Represents a drink list with all drinks currently making
public class MakingList extends DrinkList {

    // EFFECTS: constructor
    public MakingList() {
        super();
        type = Drink.Status.Making.toString();
    }
}
