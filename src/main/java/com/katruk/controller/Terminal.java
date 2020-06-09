package com.katruk.controller;

import com.katruk.io.in.Reader;
import com.katruk.io.out.Display;

public class Terminal {

    private final Reader reader;
    private final Display display;

    public Terminal(final Reader reader, final Display display) {
        this.reader = reader;
        this.display = display;
    }

    public void show(String s) {
        this.display.print(s);
    }

    public String read() {
        return this.reader.read();
    }

}
