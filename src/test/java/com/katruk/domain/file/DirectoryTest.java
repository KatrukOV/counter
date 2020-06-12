package com.katruk.domain.file;

import com.katruk.domain.path.FilePathName;
import com.katruk.domain.path.PathName;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
public class DirectoryTest {

    private PathName pathName;
    private String directoryName;
    private Directory directory;

    @Before
    public void setUp() {
        String absolutePath = "c:\\GitHub\\counter\\src\\test\\resources\\RemoveWhiteSpace.java";
        String rootPath = "c:\\GitHub\\counter";
        String name = "test";
        this.directoryName = name;
        this.pathName = new FilePathName(absolutePath, rootPath, name);
        this.directory = new Directory(this.pathName, this.directoryName);
    }


    @Test
    public void subDirectories() {
        //when
        final Set<Directory> result = this.directory.subDirectories();

        //then
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void getByName() {
        //given
        final String nameDirectory = "nameDirectory";

        //when
        final Directory result = this.directory.getByName(nameDirectory);

        //then
        Assert.assertNull(result);
    }

    @Test
    public void content() {
        //given
        final String content = "package com.katruk.domain.file;\n" +
                "public class RemoveWhiteSpace implements File {\n" +
                "    private final File file;\n" +
                "    public RemoveWhiteSpace(final File file) {\n" +
                "        this.file = file;\n" +
                "    }\n" +
                "    @Override\n" +
                "    public String content() {\n" +
                "        final String content = this.file.content();\n" +
                "        final String cleanContent = content.replaceAll(, );\n" +
                "        return cleanContent.replaceAll(, );\n" +
                "    }\n" +
                "}";

        //when
        final String result = this.directory.content();

        //then
        Assert.assertEquals(content, result);
    }

    @Test
    public void lineCount() {
        //given
        final int lineCount = 13;

        //when
        final int result = this.directory.lineCount();

        //then
        Assert.assertEquals(lineCount, result);
    }

    @Test
    public void show() {
        //given
        final StringBuilder sb = new StringBuilder();
        final String show = "\t\t\ttest:13\n";

        //when
        final StringBuilder result = this.directory.show(sb);

        //then
        Assert.assertEquals(show, result.toString());
    }

}