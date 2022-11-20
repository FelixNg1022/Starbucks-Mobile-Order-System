package model.tests;

import model.Drink;
import model.lists.DrinkList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for DrinkList
public class DrinkListTest {
    DrinkList testList;
    private static Drink d1;
    private static Drink d2;
    private static Drink d3;

    @BeforeEach
    public void runBefore1() {
        testList = new DrinkList();
    }

    @BeforeAll
    public static void runBefore2() {
        d1 = new Drink("Felix", Drink.Size.Tall, Drink.Type.Americano);
        d2 = new Drink("David", Drink.Size.Grande, Drink.Type.Latte);
        d3 = new Drink("Eugene", Drink.Size.Venti, Drink.Type.Cappuccino);
    }

    @Test
    public void testAddDrink() {
        assertTrue(testList.addDrink(d1));
        assertEquals(1, testList.listSize());

        assertTrue(testList.addDrink(d2));
        assertEquals(2, testList.listSize());

        assertFalse(testList.addDrink(d2));
        assertEquals(2, testList.listSize());
    }

    @Test
    public void testRemoveDrink() {
        testList.addDrink(d1);
        testList.addDrink(d2);

        assertTrue(testList.removeDrink(1));
        assertEquals(1, testList.listSize());
        assertFalse(testList.removeDrink(10));
        assertEquals(1, testList.listSize());
    }

    @Test
    public void testListSize() {
        testList.addDrink(d1);
        assertEquals(1, testList.listSize());

        testList.addDrink(d2);
        testList.addDrink(d3);
        assertEquals(3, testList.listSize());
    }

    @Test
    public void testHasDrink() {
        testList.addDrink(d1);
        testList.addDrink(d2);

        assertTrue(testList.hasDrink(1));
        assertTrue(testList.hasDrink(2));
    }

    @Test
    public void testGetDrink() {
        testList.addDrink(d1);
        testList.addDrink(d2);

        assertEquals(d1, testList.getDrink(1));
        assertEquals(d2, testList.getDrink(2));
        assertNull(testList.getDrink(10));
    }

    @Test
    public void testChangeDrinkStatus() {
        testList.addDrink(d1);
        testList.addDrink(d2);

        assertTrue(testList.changeDrinkStatus(1));
        assertEquals(Drink.Status.Making, testList.getDrink(1).getStatus());
        assertTrue(testList.changeDrinkStatus(1));
        assertEquals(Drink.Status.Made, testList.getDrink(1).getStatus());
        assertFalse(testList.changeDrinkStatus(1));
    }

    @Test
    public void testGetType() {
        assertEquals("none", testList.getType());
    }
}
