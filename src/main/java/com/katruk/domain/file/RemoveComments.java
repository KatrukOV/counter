package com.katruk.domain.file;

public class RemoveComments implements File {

    private final File file;

    public RemoveComments(final File file) {
        this.file = file;
    }

    @Override
    public String content() {
        String content = this.file.content();
        return content.replaceAll("//.*|(\"(?:\\\\[^\"]|\\\\\"|.)*?\")|(?s)/\\*.*?\\*/", "");
    }

}
