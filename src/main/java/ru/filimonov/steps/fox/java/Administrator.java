package ru.filimonov.steps.fox.java;

import ru.filimonov.steps.fox.java.facade.Facade;
import ru.filimonov.steps.fox.java.formatter.Formatter;
import ru.filimonov.steps.fox.java.parser.Parser;
import ru.filimonov.steps.fox.java.reader.Reader;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Administrator {
    private static final String START_PATH = "src/main/resources/start.log";
    private static final String END_PATH = "src/main/resources/end.log";
    private static final String ABBREVIATIONS_PATH = "src/main/resources/abbreviations.txt";

    public static void main (String[] args) throws IOException {
        System.out.println(new Facade(new Reader(),new Parser(),new Formatter())
                .getList(START_PATH, END_PATH, ABBREVIATIONS_PATH));
    }
}