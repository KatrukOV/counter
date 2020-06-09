package com.katruk.domain.path;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FilePathNameTest {

    private String absolutePath;
    private String rootPath;
    private String name;
    private FilePathName filePathName;

    @Before
    public void setUp() {
        this.absolutePath = "c:\\GitHub\\counter\\src\\test\\resources\\RemoveWhiteSpace.java";
        this.rootPath = "c:\\GitHub\\counter";
        this.name = "test";
        this.filePathName = new FilePathName(absolutePath, rootPath, name);
    }

    @Test
    public void absolutePath() {
        //when
        final String result = this.filePathName.absolutePath();

        //then
        Assert.assertEquals(this.absolutePath, result);
    }

    @Test
    public void relativePath() {
        //given
        final String relativePath = "\\src\\test";

        //when
        final String result = this.filePathName.relativePath();

        //then
        Assert.assertEquals(relativePath, result);
    }

    @Test
    public void prettyName() {
        //given
        final String prettyName = "\t\t\ttest";

        //when
        final String result = this.filePathName.prettyName();

        //then
        Assert.assertEquals(prettyName, result);
    }

}