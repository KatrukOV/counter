package com.katruk.domain.file;

import com.katruk.domain.path.FilePathName;
import com.katruk.domain.path.PathName;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

public class Directory implements File {

    private final Set<Directory> subDirectories = new HashSet<>();
    private final PathName path;
    private final String name;
    private File file;

    public Directory(final PathName path, String name) {
        this.path = path;
        this.name = name;
    }

    public Directory(final String absolutePath, final String rootPath, final String name) {
        this(
                new FilePathName(absolutePath, rootPath, name),
                name
        );
    }

    @Override
    public String content() {
        return this.file().content();
    }

    @Override
    public int lineCount() {
        int count;
        if (this.subDirectories.isEmpty()) {
            count = this.file().lineCount();
        } else {
            count = this.subDirectories.stream()
                    .mapToInt(Directory::lineCount)
                    .sum();
        }
        return count;
    }

    public Set<Directory> subDirectories() {
        return this.subDirectories;
    }

    public Directory getByName(final String name) {
        return this.subDirectories.stream()
                .filter(e -> name.equals(e.name))
                .findAny()
                .orElse(null);
    }

    public StringBuilder show(final StringBuilder sb) {
        sb.append(this.path.prettyName());
        sb.append(":");
        if (this.subDirectories.isEmpty()) {
            sb.append(this.file().lineCount());
            sb.append("\n");
        } else {
            int count = this.subDirectories.stream()
                    .mapToInt(Directory::lineCount)
                    .sum();
            sb.append(count);
            sb.append("\n");
            this.subDirectories.forEach(e -> e.show(sb));
        }
        return sb;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directory that = (Directory) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name);
    }

    private File file() {
        if (isNull(this.file)) {
            this.file =
                    new CacheLineCount(
                            new RemoveWhiteSpace(
                                    new RemoveComments(
                                            readFile(this.path.absolutePath())
                                    )
                            )
                    );
        }
        return this.file;
    }

    private File readFile(final String absolutePath) {
        final StringBuilder sb = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(absolutePath))) {
            stream.forEach(e -> {
                sb.append(e);
                sb.append("\n");
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new JavaFile(sb.toString());
    }

}