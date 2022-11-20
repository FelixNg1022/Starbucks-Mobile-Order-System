package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a drink with customer's name, its  size, and type.
public class Drink implements Writable {
    private int drinkID;
    private static int drinkIDCounter = 1;
    private final String customerName;

    public enum Status {
        Ordered, Making, Made
    }

    // Size can be one of Tall, Grande, or Venti
    public enum Size {
        Tall, Grande, Venti
    }

    // Type can be one of Americano, Latte, or Cappuccino
    public enum Type {
        Americano, Latte, Cappuccino
    }

    private Status status;
    private final Size size;
    private final Type type;

    // EFFECTS: Drink has given customer name, size, and type
    public Drink(String customerName, Size size, Type type) {
        this.customerName = customerName;
        this.status = Status.Ordered;
        this.size = size;
        this.type = type;
        drinkID = drinkIDCounter;
        drinkIDCounter++;
    }

    // EFFECTS: Drink has given customer name, status, size, and type
    public Drink(String customerName, Status status, Size size, Type type) {
        this.customerName = customerName;
        this.status = status;
        this.size = size;
        this.type = type;
        drinkID = drinkIDCounter;
        drinkIDCounter++;
    }

    // EFFECTS: returns the customer name
    public String getCustomerName() {
        return customerName;
    }

    // EFFECTS: returns the status
    public Status getStatus() {
        return status;
    }

    // EFFECTS: returns the size
    public Size getSize() {
        return size;
    }

    // EFFECTS: returns the type
    public Type getType() {
        return type;
    }

    // EFFECTS: returns the drink ID
    public int getDrinkID() {
        return drinkID;
    }

    // EFFECTS: returns true if the status of a drink is changed, false otherwise
    public boolean changeStatus() {
        if (status.toString().equals("Ordered")) {
            status = Status.Making;
            return true;
        } else if (status.toString().equals("Making")) {
            status = Status.Made;
            return true;
        }
        return false;
    }

    @Override
    // EFFECTS: output the information of the drink
    public String toString() {
        return "[ID: " + drinkID + " - " + customerName + "'s " + size + " " + type + "]" + " [" + status + "]";
    }

    @Override
    // EFFECTS: creates a json object from a drink
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("customer name", customerName);
        json.put("status", status);
        json.put("size", size);
        json.put("type", type);
        json.put("drink ID", drinkID);
        return json;
    }
}
