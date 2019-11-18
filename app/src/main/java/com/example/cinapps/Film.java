package com.example.cinapps;

public class Film {
    public Film() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScenario() {
        return scenario;
    }

    public void setScenario(int scenario) {
        this.scenario = scenario;
    }

    public int getRealisation() {
        return realisation;
    }

    public void setRealisation(int realisation) {
        this.realisation = realisation;
    }

    public int getMusique() {
        return musique;
    }

    public void setMusique(int musique) {
        this.musique = musique;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String title;
    private String date;
    private int scenario;
    private int realisation;
    private int musique;
    private String description;

    public Film(String title, String date, int scenario, int realisation, int musique, String description) {
        this.title = title;
        this.date = date;
        this.scenario = scenario;
        this.realisation = realisation;
        this.musique = musique;
        this.description = description;
    }
}
