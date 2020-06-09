package com.katruk.domain.file;

public class RemoveWhiteSpace implements File {

    private final File file;

    public RemoveWhiteSpace(final File file) {
        this.file = file;
    }

    @Override
    public String content() {
        final String content = this.file.content();
        final String cleanContent = content.replaceAll("\n+\\u0020+\n", "");
        return cleanContent.replaceAll("\n+", "\n");
    }

}
