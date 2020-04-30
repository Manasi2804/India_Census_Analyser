package com.bl.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class USCensus {
    @CsvBindByName(column = "State Id")
    public String stateID;

    @CsvBindByName(column = "State")
    public String state;

    @CsvBindByName(column = "Population")
    public Integer population;

    @CsvBindByName(column = "Total area")
    public Double area;

    @CsvBindByName(column = "Population Density")
    public Double populationDensity;

    public USCensus() {
    }

    public USCensus(String stateCode, String state, Integer population, Double area, Double populationDensity) {
        this.stateID = stateCode;
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
