package com.katruk.io.in;

import java.util.Scanner;

public class ConsoleReader implements Reader {

    @Override
    public String read() {
        final Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
