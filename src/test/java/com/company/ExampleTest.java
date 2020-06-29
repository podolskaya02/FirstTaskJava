package com.company;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ExampleTest {

    private Example e = new Example();

    @Test
    void getPlus() {
        Assert.assertEquals("2,22", e.doSome("1,1 + 1,12",1, 2, 2)); // без округления
        Assert.assertEquals("2,2", e.doSome("1,1 + 1,12",1, 2, 1)); // с округлением
        Assert.assertEquals("0,036", e.doSome("0,007 + 0,029", 1, 3, 3)); // без округления
        Assert.assertEquals("0,04", e.doSome("0,007 + 0,029", 1, 3, 2)); // с округлением
        Assert.assertEquals("4808,7523", e.doSome("4796,4523 + 12,3", 4, 4, 4)); // без округления
        Assert.assertEquals("4808,8", e.doSome("4796,4523 + 12,3", 4, 4, 1)); // с округлением
        Assert.assertEquals("0,2", e.doSome("0,05 + 0,1", 1, 2, 1)); // округление
        Assert.assertEquals("1,82", e.doSome("0,10001 + 1,723", 1, 5, 2)); // округление
        Assert.assertEquals("668,0481", e.doSome("0,2391 + 667,809", 3, 4, 4));
    }

    @Test
    void getMinus() {
        Assert.assertEquals("8,86", e.doSome("12,34 - 3,48", 1, 2, 2)); // без округления
        Assert.assertEquals("8,9", e.doSome("12,34 - 3,48", 1, 2, 1)); // с округлением
        Assert.assertEquals("0,02867", e.doSome("0,21567 - 0,187", 1, 5, 5));
        Assert.assertEquals("0,029", e.doSome("0,21567 - 0,187", 1, 5, 3));
        Assert.assertEquals("266,1701", e.doSome("567,1802 - 301,0101", 3, 4, 4));
        Assert. assertEquals("266,2", e.doSome("567,1802 - 301,0101", 3, 4, 1));
    }

    @Test
    void getMultiplay() {
        Assert.assertEquals("0,00001", e.doSome("0,1 * 0,0001", 1, 5, 5));
        Assert.assertEquals("0,0", e.doSome("0,1 * 0,0001", 1, 5, 1));
        Assert.assertEquals("4,2309", e.doSome("23,505 * 0,18", 1, 4, 4));
        Assert.assertEquals("4,231", e.doSome("23,505 * 0,18", 1, 4, 3));
        Assert.assertEquals("9427,6259", e.doSome("364,001 * 25,9", 4, 4, 4));
        Assert.assertEquals("9427,6", e.doSome("364,001 * 25,9", 4, 4, 1));
    }

    @Test
    void getDivide() {
        Assert.assertEquals("0,0625", e.doSome("0,01 / 0,16", 1, 4, 4));
        Assert.assertEquals("0,1", e.doSome("0,01 / 0,16", 1, 4, 1));
        Assert.assertEquals("50,128", e.doSome("125,32 / 2,5", 2, 3, 3));
        Assert.assertEquals("50,13", e.doSome("125,32 / 2,5", 2, 3, 2));
        Assert.assertEquals("1842,5625", e.doSome("5896,2 / 3,2", 4, 4, 4));
        Assert.assertEquals("1842,6", e.doSome("5896,2 / 3,2", 4, 4, 1));
    }
}