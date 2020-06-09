package com.katruk.domain.file;

import static java.util.Objects.isNull;

public class CacheLineCount implements File {

    private final File file;
    private Integer cache;

    public CacheLineCount(File file) {
        this.file = file;
    }

    @Override
    public String content() {
        return file.content();
    }

    @Override
    public int lineCount() {
        if (isNull(cache)) {
            this.cache = this.file.lineCount();
        }
        return this.cache;
    }

}
