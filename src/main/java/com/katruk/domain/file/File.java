package com.katruk.domain.file;

public interface File {

    String content();

    default int lineCount() {
        String[] lines = content().split("\n");
        return lines.length;
    }

}
