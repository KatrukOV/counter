package com.katruk;

import com.katruk.controller.Terminal;
import com.katruk.domain.DirectoryTree;
import com.katruk.io.in.ConsoleReader;
import com.katruk.io.out.ConsoleDisplay;

public class Main {

    public static void main(String[] args) {
        final Terminal terminal = new Terminal(new ConsoleReader(), new ConsoleDisplay());
        terminal.show("Enter a path : ");
        final String path = terminal.read();
        final DirectoryTree directoryTree = new DirectoryTree(path);
        final String tree = directoryTree.show();
        terminal.show(String.format("Result: %s", tree));
    }

}
