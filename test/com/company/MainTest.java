package com.company;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

class MainTest {
    @Test
    public void test() {
        // digitsBefore - количество цифр до запятой, digitsAfter - количество фир после запятой
        // rounding - количество цифр после запятой для округления

        // сложение
        assertEquals("0,036", Main.getOperation("0,007 + 0,029", 1, 3, 3)); // без округления
        assertEquals("0,04", Main.getOperation("0,007 + 0,029", 1, 3, 2)); // с округлением
        assertEquals("4808,7523", Main.getOperation("4796,4523 + 12,3", 4, 4, 4)); // без округления
        assertEquals("4808,8", Main.getOperation("4796,4523 + 12,3", 4, 4, 1)); // с округлением
        assertEquals("0,2", Main.getOperation("0,05 + 0,1", 1, 2, 1)); // округление
        assertEquals("1,82", Main.getOperation("0,10001 + 1,723", 1, 5, 2)); // округление
        assertEquals("668,0481", Main.getOperation("0,2391 + 667,809", 3, 4, 4));
        // вычитание
        assertEquals("8,86", Main.getOperation("12,34 - 3,48", 1, 2, 2)); // без округления
        assertEquals("8,9", Main.getOperation("12,34 - 3,48", 1, 2, 1)); // с округлением
        assertEquals("0,02867", Main.getOperation("0,21567 - 0,187", 1, 5, 5));
        assertEquals("0,029", Main.getOperation("0,21567 - 0,187", 1, 5, 3));
        assertEquals("266,1701", Main.getOperation("567,1802 - 301,0101", 3, 4, 4));
        assertEquals("266,2", Main.getOperation("567,1802 - 301,0101", 3, 4, 1));
        // умножение
        assertEquals("0,00001", Main.getOperation("0,1 * 0,0001", 1, 5, 5));
        assertEquals("0,0", Main.getOperation("0,1 * 0,0001", 1, 5, 1));
        assertEquals("4,2309", Main.getOperation("23,505 * 0,18", 1, 4, 4));
        assertEquals("4,231", Main.getOperation("23,505 * 0,18", 1, 4, 3));
        assertEquals("9427,6259", Main.getOperation("364,001 * 25,9", 4, 4, 4));
        assertEquals("9427,6", Main.getOperation("364,001 * 25,9", 4, 4, 1));
        // деление
        assertEquals("0,0625", Main.getOperation("0,01 / 0,16", 1, 4, 4));
        assertEquals("0,1", Main.getOperation("0,01 / 0,16", 1, 4, 1));
        assertEquals("50,128", Main.getOperation("125,32 / 2,5", 2, 3, 3));
        assertEquals("50,13", Main.getOperation("125,32 / 2,5", 2, 3, 2));
        assertEquals("1842,5625", Main.getOperation("5896,2 / 3,2", 4, 4, 4));
        assertEquals("1842,6", Main.getOperation("5896,2 / 3,2", 4, 4, 1));
    }
}