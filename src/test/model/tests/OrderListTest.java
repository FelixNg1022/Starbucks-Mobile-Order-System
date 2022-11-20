package model.tests;

import model.lists.DrinkList;
import model.lists.OrderList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for OrderList
public class OrderListTest extends DrinkListTest {

    @BeforeEach
    public void runBefore() {
        testList = new OrderList();
    }

    @Test
    @Override
    public void testGetType() {
        assertEquals("Ordered", testList.getType());
    }
}
