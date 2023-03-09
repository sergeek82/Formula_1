package ru.filimonov.steps.fox.java.reader;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {
    private static final String KEY = "SVF";
    private static final String LOG_FILE_ONE = "src/test/resources/logFileOne.log";
    private static final String TEXT_FILE = "src/test/resources/textFile.txt";
    private static final String STRING = "SVF_Sebastian Vettel_FERRARI";
    private final LocalTime VALUE = LocalTime.parse("12:02:58.917");
    private FileReader<String, LocalTime> reader;

    @BeforeEach
    void init () {
        reader = new Reader();
    }

    @Test
    @DisplayName("getStrings_fileReading_returnStringList")
    void readTextFile () throws IOException {
        assertEquals(getList(), reader.readTextFile(TEXT_FILE));
    }

    @Test
    @DisplayName("getCountValues_fileReading_returnMapOfValues")
    void readLogFile () throws IOException {
        assertEquals(getMap(), reader.readLogFile(LOG_FILE_ONE)
        );
    }

    private List<String> getList () {
        return new ArrayList<>(Collections.singletonList(STRING));
    }

    private Map<String, LocalTime> getMap () {
        return Collections.singletonMap(KEY, VALUE);
    }
}