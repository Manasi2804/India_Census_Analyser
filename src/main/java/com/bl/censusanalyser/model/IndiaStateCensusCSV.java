package com.bl.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class IndiaStateCensusCSV
{
    @CsvBindByName(column = "State")
    public String State;

    @CsvBindByName(column = "Population")
    public Integer Population;

    @CsvBindByName(column = "AreaInSqKm")
    public Double AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public Double DensityPerSqkm;
    public String statecode =new IndiaStateCodeCSV().getStateCode();
    public String stateCode;

    public IndiaStateCensusCSV() {
    }

    public IndiaStateCensusCSV(String state, String stateCode, Integer population, Double areaInSqKm, Double densityPerSqkm) {
        this.State = state;
        this.Population = population;
        this.AreaInSqKm = areaInSqKm;
        this.DensityPerSqkm = densityPerSqkm;
        this.stateCode = stateCode;
    }

    @Override
    public String toString()
    {
        return "CSVStateCensus{" +
                "State='" + State+ '\'' +
                ", Population=" + Population +
                ", AreaInSqKm=" + AreaInSqKm +
                ", DensityPerSqkm=" + DensityPerSqkm +
                '}';
    }
}
