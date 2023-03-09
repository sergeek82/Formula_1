package ru.filimonov.steps.fox.java.reader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileReader<K, V> {
    List<K> readTextFile (K fileName) throws IOException;

    Map<K, V> readLogFile (K fileName) throws IOException;
}
