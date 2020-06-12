package com.katruk.domain.file;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveWhiteSpaceTest {

    @Mock
    private JavaFile javaFile;
    @InjectMocks
    private RemoveWhiteSpace removeWhiteSpace;

    @Test
    public void testContentWithSpace() {
        //given
        final String content = "    public String absolutePath() {}\n" +
                "   \n" +
                "   \n" +
                "   \n" +
                "\n" +
                "\n" +
                "   \n" +
                "   \n" +
                "    public String Path() {}\n";
        final String output = "public String absolutePath() {}\n" +
                "   \n" +
                "   \n" +
                "    public String Path() {}";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeWhiteSpace.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

    @Test
    public void testManyNewLine() {
        //given
        final String content = "    public String absolutePath() {}\n" +
                "\n" +
                "\n" +
                "\n";
        final String output = "public String absolutePath() {}";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeWhiteSpace.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

    @Test
    public void testRemoveWhiteSpaceExample1() {
        //given
        final String content = "\n" +
                "public interface Dave {\n" +
                "  \n" +
                "   int countLines(File inFile); \n" +
                "}\n";
        final String output = "public interface Dave {\n" +
                "   int countLines(File inFile); \n" +
                "}";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeWhiteSpace.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

    @Test
    public void testRemoveWhiteSpaceExample2() {
        //given
        final String content = "\n" +
                "\n" +
                "  public class Hello {\n" +
                "      public static final void main(String [] args) { \n" +
                "          \n" +
                "        System.out.println();\n" +
                "      }\n" +
                "\n" +
                "  }\n";
        final String output = "public class Hello {\n" +
                "      public static final void main(String [] args) { \n" +
                "        System.out.println();\n" +
                "      }\n" +
                "  }";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeWhiteSpace.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

}
