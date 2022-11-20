package model.tests;

import model.Drink;

import static org.junit.jupiter.api.Assertions.assertEquals;

// This class references code from CPSC 210 JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// checks an anime entry's info against the json object
public class JsonTest {
    protected void checkDrink(String customerName, Drink.Status status, Drink.Size size, Drink.Type type, Drink drink) {
        assertEquals(customerName, drink.getCustomerName());
        assertEquals(status, drink.getStatus());
        assertEquals(size, drink.getSize());
        assertEquals(type, drink.getType());
    }
}
