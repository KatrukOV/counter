package com.katruk.domain.file;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CacheLineCountTest {

    @Mock
    private JavaFile javaFile;
    @InjectMocks
    private CacheLineCount cacheLineCount;

    @Test
    public void testContent() {
        //given
        final String content = "content";

        //when
        when(this.javaFile.content()).thenReturn(content);
        final String result = this.cacheLineCount.content();

        //then
        verify(this.javaFile, times(1)).content();
        Assert.assertEquals(content, result);
    }

    @Test
    public void lineCountOne() {
        //given
        final int lineCount = 1;

        //when
        when(this.javaFile.lineCount()).thenReturn(lineCount);
        final int result = this.cacheLineCount.lineCount();

        //then
        verify(this.javaFile, times(1)).lineCount();
        Assert.assertEquals(lineCount, result);
    }

    @Test
    public void lineCountMany() {
        //given
        final int lineCount = 2;

        //when
        when(this.javaFile.lineCount()).thenReturn(lineCount);
        this.cacheLineCount.lineCount();
        this.cacheLineCount.lineCount();
        this.cacheLineCount.lineCount();
        final int result = this.cacheLineCount.lineCount();

        //then
        verify(this.javaFile, times(1)).lineCount();
        Assert.assertEquals(lineCount, result);
    }

}