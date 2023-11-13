package com.diegofma.countriesdata.restclient.dto;

public class CountryDTO {
    private NameDTO name;
    private int population;

    public NameDTO getName() {
        return name;
    }

    public void setName(NameDTO name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}