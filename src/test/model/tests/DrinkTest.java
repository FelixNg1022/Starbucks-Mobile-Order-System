package model.tests;

import model.Drink;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for Drink
class DrinkTest {
    private static Drink d1;
    private static Drink d2;
    private static Drink d3;

    @BeforeAll
    public static void runBefore() {
        d1 = new Drink("Felix", Drink.Size.Tall, Drink.Type.Americano);
        d2 = new Drink("David", Drink.Status.Making, Drink.Size.Grande, Drink.Type.Latte);
        d3 = new Drink("Eugene", Drink.Status.Made, Drink.Size.Venti, Drink.Type.Cappuccino);
    }

    @Test
    public void testGetCustomerName() {
        assertEquals("Felix", d1.getCustomerName());
        assertEquals("David", d2.getCustomerName());
        assertEquals("Eugene", d3.getCustomerName());
    }

    @Test
    public void testGetStatus() {
        assertEquals(Drink.Status.Made, d1.getStatus());
        assertEquals(Drink.Status.Making, d2.getStatus());
        assertEquals(Drink.Status.Made, d3.getStatus());
    }

    @Test
    public void testGetSize() {
        assertEquals(Drink.Size.Tall, d1.getSize());
        assertEquals(Drink.Size.Grande, d2.getSize());
        assertEquals(Drink.Size.Venti, d3.getSize());
    }

    @Test
    public void testGetType() {
        assertEquals(Drink.Type.Americano, d1.getType());
        assertEquals(Drink.Type.Latte, d2.getType());
        assertEquals(Drink.Type.Cappuccino, d3.getType());
    }

    @Test
    public void testGetDrinkID() {
        assertEquals(1, d1.getDrinkID());
        assertEquals(2, d2.getDrinkID());
        assertEquals(3, d3.getDrinkID());
    }

    @Test
    public void testChangeStatus() {
        assertEquals(Drink.Status.Making, d1.getStatus());

        d1.changeStatus();
        assertEquals(Drink.Status.Making, d1.getStatus());

        d1.changeStatus();
        assertEquals(Drink.Status.Made, d1.getStatus());
    }

    @Test
    public void testToString() {
        assertEquals("[ID: 1 - Felix's Tall Americano] [Ordered]", d1.toString());
        assertEquals("[ID: 2 - David's Grande Latte] [Making]", d2.toString());
        assertEquals("[ID: 3 - Eugene's Venti Cappuccino] [Made]", d3.toString());
    }
}