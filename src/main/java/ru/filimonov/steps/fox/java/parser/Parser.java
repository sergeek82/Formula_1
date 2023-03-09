package ru.filimonov.steps.fox.java.parser;

import ru.filimonov.steps.fox.java.data.RaceData;
import ru.filimonov.steps.fox.java.data.Racer;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Parser implements DataParser<RaceData, String, LocalTime> {

    static final String UNDERSCORE = "_";

    public List<RaceData> parse (Map<String, LocalTime> startMap, Map<String, LocalTime> endMap, List<String> abbreviationList) {
        return abbreviationList.stream()
                .map(s -> {
                    String[] values = s.split(UNDERSCORE);
                    LocalTime startTime = startMap.get(values[0]);
                    LocalTime endTime = endMap.get(values[0]);
                    return new RaceData(new Racer(values[0], values[1], values[2]),
                            Duration.between(startTime, endTime));
                })
                .collect(Collectors.toList());
    }
}