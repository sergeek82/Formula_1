package ru.filimonov.steps.fox.java.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import ru.filimonov.steps.fox.java.formatter.Formatter;
import ru.filimonov.steps.fox.java.parser.Parser;
import ru.filimonov.steps.fox.java.reader.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

class FacadeTest {
    private Facade facade;
    private Reader reader;
    private Formatter formatter;
    private Parser parser;
    private static final String LOG_FILE_ONE = "src/test/resources/logFileOne.log";
    private static final String LOG_FILE_TWO = "src/test/resources/logFileTwo.log";
    private static final String TEXT_FILE = "src/test/resources/textFile.txt";

    @BeforeEach
    void init () {
        reader = mock(Reader.class);
        formatter = mock(Formatter.class);
        parser = mock(Parser.class);
        facade = new Facade(reader, parser, formatter);
    }

    @Test
    void processShouldCheckInvocations () throws IOException {
        when(reader.readLogFile(anyString())).thenReturn(new HashMap<>());
        when(reader.readTextFile(anyString())).thenReturn(new ArrayList<>());
        when(formatter.buildTable(new ArrayList<>())).thenReturn(TEXT_FILE);
        when(parser.parse(new HashMap<>(), new HashMap<>(), new ArrayList<>())).thenReturn(new ArrayList<>());
        facade.getList(LOG_FILE_ONE, LOG_FILE_TWO, TEXT_FILE);
        verify(reader, times(2)).readLogFile(anyString());
        verify(reader, times(1)).readTextFile(anyString());
        verify(formatter, times(1)).buildTable(anyList());
        verify(parser, times(1)).parse(anyMap(), anyMap(), anyList());
    }
}