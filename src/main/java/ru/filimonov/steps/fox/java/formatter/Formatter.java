package ru.filimonov.steps.fox.java.formatter;

import ru.filimonov.steps.fox.java.data.RaceData;

import java.time.Duration;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Formatter {
    private static final String STRING_FORMAT_ONE_DIGIT = "%d. (%s) | %-17s | %-25s | %s%n";
    private static final String STRING_FORMAT_TWO_DIGIT = "%d.(%s) | %-17s | %-25s | %s%n";
    private static final String STRING_DURATION_FORMAT = "%d:%d.%d";
    private static final String EMPTY_STRING = "";
    private static final String NEXT_LINE = "\n";
    private static final String DASH = "-";
    private static final int ITERATIONS = 67;
    private static final int MAX_COUNT = 15;

    public String buildTable (List<RaceData> data) {
        AtomicInteger lineCounter = new AtomicInteger();
        return data.stream()
                .sorted(Comparator.comparing(RaceData::getTimeLap))
                .map(e -> {
                    StringBuilder sb = new StringBuilder();
                    lineCounter.getAndIncrement();
                    sb.append(String.format(getStringFormat(lineCounter), lineCounter.get(), e.getRacer()
                            .getAbbreviation(), e.getRacer()
                            .getName(), e.getRacer()
                            .getTeam(), getDurationFormat(e.getTimeLap())));
                    if (lineCounter.get() == MAX_COUNT) {
                        sb.append(drawTheLine())
                                .append(NEXT_LINE);
                    }
                    return sb.toString();
                })
                .collect(Collectors.joining());
    }

    private String drawTheLine () {
        return String.join(EMPTY_STRING, Collections.nCopies(ITERATIONS, DASH));
    }

    private String getStringFormat (AtomicInteger lineCounter) {
        if (lineCounter.get() < 10) {
            return STRING_FORMAT_ONE_DIGIT;
        }
        return STRING_FORMAT_TWO_DIGIT;
    }

    private String getDurationFormat (Duration timeLap) {
        long min = timeLap.toMinutes();
        long sec = (timeLap.toMillis() / 1000) % 60;
        long mills = timeLap.toMillis() % 1000;
        return String.format(STRING_DURATION_FORMAT, min, sec, mills);
    }
}

