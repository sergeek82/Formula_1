package ru.filimonov.steps.fox.java.data;

import java.time.Duration;

public class RaceData {
    private final Racer racer;
    private final Duration timeLap;

    public RaceData (Racer racer, Duration timeLap) {
        this.racer = racer;
        this.timeLap = timeLap;
    }

    public Racer getRacer () {
        return racer;
    }

    public Duration getTimeLap () {
        return timeLap;
    }
}