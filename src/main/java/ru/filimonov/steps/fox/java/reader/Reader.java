package ru.filimonov.steps.fox.java.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Reader implements FileReader<String, LocalTime> {
    private static final String TIME_FORMAT = "HH:mm:ss.SSS";
    private static final String UNDERSCORE = "_";

    @Override
    public List<String> readTextFile (String fileName) throws IOException {
        try (Stream<String> textFileStream = Files.lines(Paths.get(fileName))) {
            return textFileStream.collect(Collectors.toList());
        }
    }

    @Override
    public Map<String, LocalTime> readLogFile (String fileName) throws IOException {
        try (Stream<String> logFileStream = Files.lines(Paths.get(fileName))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_FORMAT);
            return logFileStream.map(s -> s.split(UNDERSCORE))
                    .collect(Collectors.toMap(p -> p[0].substring(0, 3), p -> LocalTime.parse(p[1], formatter)));
        }
    }
}
