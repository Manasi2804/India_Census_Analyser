package com.bl.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class USCensus {
    @CsvBindByName(column = "State Id")
    public String stateID;

    @CsvBindByName(column = "State")
    public String state;

    @CsvBindByName(column = "Population")
    public long population;

    @CsvBindByName(column = "Total area")
    public double area;

    @CsvBindByName(column = "Population Density")
    public float populationDensity;

    public USCensus() {
    }

    public USCensus(String stateID, String state, long population, double area, float populationDensity) {
        this.stateID = stateID;
        this.state = state;
        this.population = population;
        this.area = area;
        this.populationDensity = populationDensity;
    }

    @Override
    public String toString() {
        return "USCensus{" +
                "stateID='" + stateID + '\'' +
                ", state='" + state + '\'' +
                ", population=" + population +
                ", area=" + area +
                ", populationDensity='" + populationDensity + '\'' +
                '}';
    }
}
