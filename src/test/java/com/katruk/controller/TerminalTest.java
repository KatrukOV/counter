package com.katruk.controller;

import com.katruk.io.in.ConsoleReader;
import com.katruk.io.out.ConsoleDisplay;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TerminalTest {

    @Mock
    private ConsoleReader consoleReader;
    @Mock
    private ConsoleDisplay consoleDisplay;
    @InjectMocks
    private Terminal terminal;

    @Test
    public void show() {
        //given
        final String input = "input";

        //when
        doNothing().when(this.consoleDisplay).print(anyString());
        this.terminal.show(input);

        //then
        verify(this.consoleDisplay, times(1)).print(anyString());
    }

    @Test
    public void read() {
        //given
        final String output = "output";

        //when
        when(this.consoleReader.read()).thenReturn(output);
        final String result = this.terminal.read();

        //then
        verify(this.consoleReader, times(1)).read();
        Assert.assertEquals(output, result);
    }

}