package model.tests;

import model.Drink;
import model.lists.DrinkList;
import org.junit.jupiter.api.Test;

import persistence.JsonReader;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Unit tests for JsonReader
// This class references code from CPSC 210 JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DrinkList list = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyList.json");
        try {
            DrinkList list = reader.read();
            assertEquals("Ordered", list.getType());
            assertEquals(0, list.listSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderOrderlist() {
        JsonReader reader = new JsonReader("./data/testReaderOrderList.json");
        try {
            DrinkList list = reader.read();
            assertEquals("Ordered", list.getType());
            List<Drink> drinks = list.getList();
            assertEquals(3, drinks.size());
            checkDrink("Arthur", Drink.Status.Ordered, Drink.Size.Grande, Drink.Type.Cappuccino, drinks.get(0));
            checkDrink("Shaun", Drink.Status.Ordered, Drink.Size.Tall, Drink.Type.Latte, drinks.get(1));
            checkDrink("Mandy", Drink.Status.Ordered, Drink.Size.Venti, Drink.Type.Americano, drinks.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderMakinglist() {
        JsonReader reader = new JsonReader("./data/testReaderMakingList.json");
        try {
            DrinkList list = reader.read();
            assertEquals("Making", list.getType());
            List<Drink> drinks = list.getList();
            assertEquals(3, drinks.size());
            checkDrink("Arthur", Drink.Status.Making, Drink.Size.Grande, Drink.Type.Cappuccino, drinks.get(0));
            checkDrink("Shaun", Drink.Status.Making, Drink.Size.Tall, Drink.Type.Latte, drinks.get(1));
            checkDrink("Mandy", Drink.Status.Making, Drink.Size.Venti, Drink.Type.Americano, drinks.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderMadelist() {
        JsonReader reader = new JsonReader("./data/testReaderMadeList.json");
        try {
            DrinkList list = reader.read();
            assertEquals("Made", list.getType());
            List<Drink> drinks = list.getList();
            assertEquals(3, drinks.size());
            checkDrink("Arthur", Drink.Status.Made, Drink.Size.Grande, Drink.Type.Cappuccino, drinks.get(0));
            checkDrink("Shaun", Drink.Status.Made, Drink.Size.Tall, Drink.Type.Latte, drinks.get(1));
            checkDrink("Mandy", Drink.Status.Made, Drink.Size.Venti, Drink.Type.Americano, drinks.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderInvalidTypeList() {
        JsonReader reader = new JsonReader("./data/testReaderInvalidTypeList.json");
        try {
            DrinkList list = reader.read();
            assertEquals("none", list.getType());
            List<Drink> drinks = list.getList();
            assertEquals(3, drinks.size());
            checkDrink("Arthur", Drink.Status.Made, Drink.Size.Grande, Drink.Type.Cappuccino, drinks.get(0));
            checkDrink("Shaun", Drink.Status.Made, Drink.Size.Tall, Drink.Type.Latte, drinks.get(1));
            checkDrink("Mandy", Drink.Status.Made, Drink.Size.Venti, Drink.Type.Americano, drinks.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (IllegalStateException i) {
            // pass
        }
    }
}
