package com.katruk.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;

public class DirectoryTreeTest {

    private String rootPath;
    private DirectoryTree directoryTree;

    @Before
    public void setUp() {
        this.rootPath = "c:\\GitHub\\counter\\src\\test\\";
        this.directoryTree = new DirectoryTree(this.rootPath);
    }

    @Test
    public void build() {
        //given
        final Set<String> paths = Collections.singleton(
                "c:\\GitHub\\counter\\src\\test\\resources\\RemoveWhiteSpace.java"
        );

        //when
        this.directoryTree.build(paths);
    }

}