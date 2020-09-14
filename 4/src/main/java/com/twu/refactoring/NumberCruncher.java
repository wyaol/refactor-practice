package com.twu.refactoring;

import java.net.InterfaceAddress;

public class NumberCruncher {
    private final int[] numbers;

    public NumberCruncher(int... numbers) {
        this.numbers = numbers;
    }

    public int countEven() {
        return getCount(number -> number % 2 ==0);
    }

    public int countOdd() {
        return getCount(number -> number % 2 ==1);
    }

    public int countPositive() {
        return getCount(number -> number > 0);
    }

    public int countNegative() {
        return getCount(number -> number < 0);
    }

    private int getCount(Counter counter) {
        int count = 0;
        for (int number : numbers) {
            if (counter.count(number)) count++;
        }
        return count;
    }
}

interface Counter {
    public Boolean count(int number);
}
