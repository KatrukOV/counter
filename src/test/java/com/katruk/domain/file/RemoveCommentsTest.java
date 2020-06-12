package com.katruk.domain.file;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RemoveCommentsTest {

    @Mock
    private JavaFile javaFile;
    @InjectMocks
    private RemoveComments removeComments;

    @Test
    public void testRemoveJavaDoc() {
        //given
        final String content = "  /**\n" +
                "     * \n" +
                "     * @param file\n" +
                "     */\n" +
                "    public RemoveComments(final File file) {\n" +
                "        this.file = file;\n" +
                "    }";
        final String output = "  \n" +
                "    public RemoveComments(final File file) {\n" +
                "        this.file = file;\n" +
                "    }";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeComments.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

    @Test
    public void testRemoveLineComment() {
        //given
        final String content = "    // comment\n" +
                "    public RemoveComments(final File file) {\n" +
                "        this.file = file;\n" +
                "    }";
        final String output = "    \n" +
                "    public RemoveComments(final File file) {\n" +
                "        this.file = file;\n" +
                "    }";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeComments.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

    @Test
    public void testRemoveInLineComment() {
        //given
        final String content = "    public /* comment */ RemoveComments(final File file) {\n" +
                "        this.file = file;\n" +
                "    }";
        final String output = "    public  RemoveComments(final File file) {\n" +
                "        this.file = file;\n" +
                "    }";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeComments.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

    @Test
    public void testRemoveCommentExample1() {
        //given
        final String content = "// This file contains 3 lines of code\n" +
                "public interface Dave {\n" +
                "  /**\n" +
                "   * count the number of lines in a file\n" +
                "   */\n" +
                "   int countLines(File inFile); // not the real signature!\n" +
                "}\n";
        final String output = "\n" +
                "public interface Dave {\n" +
                "  \n" +
                "   int countLines(File inFile); \n" +
                "}\n";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeComments.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

    @Test
    public void testRemoveCommentExample2() {
        //given
        final String content = "/*****\n" +
                "   * This is a test program with 5 lines of code\n" +
                "   *  \\/* no nesting allowed!\n" +
                "   //*****//***/// Slightly pathological comment ending...\n" +
                "\n" +
                "  public class Hello {\n" +
                "      public static final void main(String [] args) { // gotta love Java\n" +
                "          // Say hello\n" +
                "        System./*wait*/out./*for*/println/*it*/(\"Hello/*\");\n" +
                "      }\n" +
                "\n" +
                "  }\n";
        final String output = "\n" +
                "\n" +
                "  public class Hello {\n" +
                "      public static final void main(String [] args) { \n" +
                "          \n" +
                "        System.out.println();\n" +
                "      }\n" +
                "\n" +
                "  }\n";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeComments.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

}
