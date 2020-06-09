package com.katruk.io.out;

public class ConsoleDisplay implements Display {

    @Override
    public void print(final String s) {
        System.out.println(s);
    }

}
