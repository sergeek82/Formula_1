package ru.filimonov.steps.fox.java.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.filimonov.steps.fox.java.data.RaceData;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private Parser parser;
    private static final String ABBREVIATION = "SVF";
    private static final String NAME = "Sebastian Vettel";
    private static final String TEAM = "FERRARI";
    private static final String START_TIME = "12:02:58.917";
    private static final String END_TIME = "12:04:03.332";
    private static final String DURATION = "PT1M4.415S";
    private static final String TEST_STRING = "SVF_Sebastian Vettel_FERRARI";
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    @BeforeEach
    void initialization () {
        parser = new Parser();
    }

    @Test
    void processShouldReturnListOfRaceDataObjects () {
        assertEquals(new ArrayList<RaceData>(), parser.parse(new HashMap<>(), new HashMap<>(), new ArrayList<>()));
    }

    @Test
    void processShouldReturnCorrectRacer () {
        assertEquals(ABBREVIATION, parser
                .parse(getStartMap(), getEndMap(), getAbbreviations())
                .stream()
                .map(e -> e
                        .getRacer()
                        .getAbbreviation())
                .collect(Collectors.joining()));
        assertEquals(NAME, parser
                .parse(getStartMap(), getEndMap(), getAbbreviations())
                .stream()
                .map(e -> e
                        .getRacer()
                        .getName())
                .collect(Collectors.joining()));
        assertEquals(TEAM, parser
                .parse(getStartMap(), getEndMap(), getAbbreviations())
                .stream()
                .map(e -> e
                        .getRacer()
                        .getTeam())
                .collect(Collectors.joining()));
    }

    @Test
    void processShouldReturnCorrectRaceDuration () {
        assertEquals(DURATION, parser
                .parse(getStartMap(), getEndMap(), getAbbreviations())
                .stream()
                .map(e -> e
                        .getTimeLap()
                        .toString())
                .collect(Collectors.joining()));
    }

    private Map<String, LocalTime> getStartMap () {
        return Collections.singletonMap(ABBREVIATION, LocalTime.parse(START_TIME, FORMAT));
    }

    private Map<String, LocalTime> getEndMap () {
        return Collections.singletonMap(ABBREVIATION, LocalTime.parse(END_TIME, FORMAT));
    }

    private List<String> getAbbreviations () {
        return new ArrayList<>(Collections.singletonList(TEST_STRING));
    }
}