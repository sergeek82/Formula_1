package ru.filimonov.steps.fox.java.data;

public class Racer {
    private final String abbreviation;
    private final String name;
    private final String team;

    public Racer (String abbreviation, String name, String team) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.team = team;
    }

    public String getAbbreviation () {
        return abbreviation;
    }

    public String getName () {
        return name;
    }

    public String getTeam () {
        return team;
    }
}