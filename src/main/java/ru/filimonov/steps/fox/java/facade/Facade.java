package ru.filimonov.steps.fox.java.facade;

import ru.filimonov.steps.fox.java.data.RaceData;
import ru.filimonov.steps.fox.java.formatter.Formatter;
import ru.filimonov.steps.fox.java.parser.DataParser;
import ru.filimonov.steps.fox.java.parser.Parser;
import ru.filimonov.steps.fox.java.reader.FileReader;
import ru.filimonov.steps.fox.java.reader.Reader;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class Facade {
    private final FileReader<String, LocalTime> reader;
    private final DataParser<RaceData, String, LocalTime> parser;
    private final Formatter formatter;

    public Facade (Reader reader, Parser parser, Formatter formatter) {
        this.reader = reader;
        this.parser = parser;
        this.formatter = formatter;
    }

    public String getList (String startPath, String endPath, String abbreviationsPath) throws IOException {
        Map<String, LocalTime> startMap = reader.readLogFile(startPath);
        Map<String, LocalTime> endMap = reader.readLogFile(endPath);
        List<String> abbreviationsList = reader.readTextFile(abbreviationsPath);
        return formatter.buildTable(parser.parse(startMap, endMap, abbreviationsList));
    }
}
