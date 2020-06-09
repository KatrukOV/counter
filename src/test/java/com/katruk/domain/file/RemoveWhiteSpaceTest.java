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
        final String output = "    public String absolutePath() {}      \n" +
                "    public String Path() {}\n";

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
        final String output = "    public String absolutePath() {}\n";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.removeWhiteSpace.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(output, result);
    }

}
