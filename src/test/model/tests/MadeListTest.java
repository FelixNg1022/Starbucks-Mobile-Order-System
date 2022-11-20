package model.tests;

import model.lists.MadeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for MadeList
public class MadeListTest extends DrinkListTest{

    @BeforeEach
    public void runBefore() {
        testList =new MadeList();
    }

    @Test
    @Override
    public void testGetType() {
        assertEquals("Made", testList.getType());
    }
}
