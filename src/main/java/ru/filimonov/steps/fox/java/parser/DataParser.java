package ru.filimonov.steps.fox.java.parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DataParser<R, K, V> {
    List<R> parse (Map<K, V> start, Map<K, V> end, List<K> abbreviation) throws IOException;
}
