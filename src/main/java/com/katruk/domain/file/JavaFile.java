package com.katruk.domain.file;

public class JavaFile implements File {

    private final String source;

    public JavaFile(final String source) {
        this.source = source;
    }

    @Override
    public String content() {
        return this.source;
    }

}
