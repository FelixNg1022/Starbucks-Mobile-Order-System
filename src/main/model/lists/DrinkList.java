package model.lists;

import model.Drink;
import model.Event;
import model.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a list of drinks
public class DrinkList implements Writable {
    static ArrayList<Drink> list;
    static String type;

    // constructor
    public DrinkList() {
        list = new ArrayList<>();
        type = "none";
    }

    // MODIFIES: this
    // EFFECTS: returns true and adds the drink to the list if it's not already there, false otherwise
    public static boolean addDrink(Drink drink) {
        for (Drink d: list) {
            if (drink.getDrinkID() == d.getDrinkID()) {
                return false;
            }
        }
        list.add(drink);
        EventLog.getInstance().logEvent(new Event("Added " + drink.toString() + " to " + type));
        return true;
    }

    // MODIFIES: this
    // EFFECTS: returns true and removes the drink from the list if it's already there, false otherwise
    public boolean removeDrink(int id) {
        for (Drink d: list) {
            if (id == d.getDrinkID()) {
                list.remove(d);
                EventLog.getInstance().logEvent(new Event("Removed " + getDrink(id).toString() + " from " + type));
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the number of drinks in the list
    public int listSize() {
        return list.size();
    }

    // EFFECTS: returns true if drink is in the list
    public boolean hasDrink(int id) {
        for (Drink d: list) {
            if (id == d.getDrinkID()) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: Drink with given drink ID to exist in the list
    // EFFECTS: returns the drink given the drink ID
    public Drink getDrink(int id) {
        for (Drink d: list) {
            if (id == d.getDrinkID()) {
                return d;
            }
        }
        return null;
    }

    // EFFECTS: returns the array list
    public ArrayList<Drink> getList() {
        return list;
    }

    // REQUIRES: Drink with given drink ID to exist in the list
    // EFFECTS: returns true if the drink exists in the list, and we change the status of the drink, false otherwise
    public boolean changeDrinkStatus(int id) {
        for (Drink d: list) {
            if (id == d.getDrinkID()) {
                return d.changeStatus();
            }
        }
        return false;
    }

    // EFFECTS: returns the type of the drink list
    public static String getType() {
        return type;
    }

    @Override
    // EFFECTS: creates a new json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("list", listToJson());
        return json;
    }

    // EFFECTS: returns drinks in this list as a JSON array
    private JSONArray listToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Drink d: list) {
            jsonArray.put(d.toJson());
        }

        return jsonArray;
    }
}
