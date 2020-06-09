package com.katruk.domain;

import com.katruk.domain.file.Directory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DirectoryTree {

    private final String rootPath;
    private final Directory root;

    public DirectoryTree(final String rootPath, final Directory root) {
        this.rootPath = rootPath;
        this.root = root;
    }

    public DirectoryTree(final String rootPath) {
        this(
                rootPath,
                new Directory(rootPath, rootPath, rootPath)
        );
    }

    public void build(final Set<String> paths) {
        paths.forEach(absolutePath -> {
            final String[] parts = getPartsOfPath(absolutePath);
            Directory parent = this.root;
            for (String name : parts) {
                parent = addOneDirectoryNode(absolutePath, parent, name);
            }
        });
    }

    public String show() {
        Set<String> show = this.findJavaFile();
        this.build(show);
        final StringBuilder sb = new StringBuilder();
        return this.root.show(sb).toString();
    }

    public Set<String> findJavaFile() {
        Set<String> stringSet = new TreeSet<>();
        try (Stream<Path> paths = Files.walk(Paths.get(this.rootPath))) {
            paths.forEach(e -> {
                if (e.toString().endsWith(".java")) {
                    stringSet.add(e.toString());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringSet;
    }

    private Directory addOneDirectoryNode(final String absolutePath, Directory parent, final String name) {
        final Directory one = new Directory(absolutePath, this.rootPath, name);
        if (parent.subDirectories().contains(one)) {
            parent = parent.getByName(name);
        } else {
            parent.subDirectories().add(one);
            parent = one;
        }
        return parent;
    }

    private String[] getPartsOfPath(final String absolutePath) {
        final String relativePath = absolutePath.replace(this.rootPath, "");
        final String pattern = Pattern.quote(System.getProperty("file.separator"));
        return relativePath.split(pattern);
    }

}
