package ru.filimonov.steps.fox.java.formatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.filimonov.steps.fox.java.data.RaceData;
import ru.filimonov.steps.fox.java.data.Racer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class FormatterTest {
    private Formatter formatter;
    private Duration duration;
    private static final String ABBREVIATION = "SVF";
    private static final String NAME = "Sebastian Vettel";
    private static final String TEAM = "FERRARI";
    private static final String START_TIME = "12:02:58.917";
    private static final String END_TIME = "12:04:03.332";
    private static final String EXPECTED = "1. (SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "2. (SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "3. (SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "4. (SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "5. (SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "6. (SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "7. (SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "8. (SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "9. (SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "10.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "11.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "12.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "13.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "14.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "15.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "-------------------------------------------------------------------\n" +
            "16.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "17.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "18.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n" +
            "19.(SVF) | Sebastian Vettel  | FERRARI                   | 1:4.415\n";

    @BeforeEach
    void init () {
        formatter = new Formatter();
        duration = Duration.between(LocalTime.parse(START_TIME), LocalTime.parse(END_TIME));
    }

    @Test
    void processShouldReturnFormattedStringList () {
        assertEquals(EXPECTED, formatter.buildTable(getRaceDataList()));
    }

    private List<RaceData> getRaceDataList () {
        return IntStream.rangeClosed(1, 19)
                .mapToObj(e ->
                        new RaceData(new Racer(ABBREVIATION, NAME, TEAM), duration)
                )
                .collect(Collectors.toList());
    }
}