package com.katruk.domain.path;

import java.util.regex.Pattern;

public class FilePathName implements PathName {

    private final String absolutePath;
    private final String rootPath;
    private final String name;

    public FilePathName(final String absolutePath, final String rootPath, final String name) {
        this.absolutePath = absolutePath;
        this.rootPath = rootPath;
        this.name = name;
    }

    @Override
    public String absolutePath() {
        return this.absolutePath;
    }

    @Override
    public String relativePath() {
        String relativePath;
        if (this.absolutePath.equals(this.rootPath)) {
            String[] split = splitBySeparator(this.rootPath);
            relativePath = addLast(split);
        } else {
            String temp = this.absolutePath.replace(this.rootPath, "");
            String[] split = temp.split(this.name);
            relativePath = split[0] + this.name;
        }
        return relativePath;
    }

    @Override
    public String prettyName() {
        final String[] s = splitBySeparator(this.relativePath());
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            sb.append("\t");
        }
        sb.append(addLast(s));
        return sb.toString();
    }

    private String[] splitBySeparator(final String text) {
        final String pattern = Pattern.quote(System.getProperty("file.separator"));
        return text.split(pattern);
    }

    private String addLast(final String[] split) {
        return split[split.length - 1];
    }

}
