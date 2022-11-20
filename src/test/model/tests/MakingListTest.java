package model.tests;

import model.lists.MakingList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Unit tests for MakingList
public class MakingListTest extends DrinkListTest{

    @BeforeEach
    public void runBefore() {
        testList = new MakingList();
    }

    @Test
    @Override
    public void testGetType() {
        assertEquals("Making", testList.getType());
    }
}
