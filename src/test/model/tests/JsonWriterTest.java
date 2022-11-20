package model.tests;

import model.Drink;
import model.lists.DrinkList;
import model.lists.OrderList;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Unit test for JsonWriter
// This class references code from CPSC 210 JsonSerializationDemo
// Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            DrinkList list = new DrinkList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyList() {
        try {
            DrinkList list = new OrderList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyList.json");
            list = reader.read();
            assertEquals("Ordered", list.getType());
            assertEquals(0, list.listSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralList() {
        try {
            DrinkList list = new OrderList();
            DrinkList.addDrink(new Drink("Arthur", Drink.Status.Ordered, Drink.Size.Grande, Drink.Type.Cappuccino));
            DrinkList.addDrink(new Drink("Shaun", Drink.Status.Ordered, Drink.Size.Tall, Drink.Type.Latte));
            DrinkList.addDrink(new Drink("Mandy", Drink.Status.Ordered, Drink.Size.Venti, Drink.Type.Americano));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralList.json");
            writer.open();
            writer.write(list);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralList.json");
            list = reader.read();
            assertEquals("Ordered", list.getType());
            List<Drink> drinks = list.getList();
            assertEquals(3, drinks.size());
            checkDrink("Arthur", Drink.Status.Ordered, Drink.Size.Grande, Drink.Type.Cappuccino, drinks.get(0));
            checkDrink("Shaun", Drink.Status.Ordered, Drink.Size.Tall, Drink.Type.Latte, drinks.get(1));
            checkDrink("Mandy", Drink.Status.Ordered, Drink.Size.Venti, Drink.Type.Americano, drinks.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
