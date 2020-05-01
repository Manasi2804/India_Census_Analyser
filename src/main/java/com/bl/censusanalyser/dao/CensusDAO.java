package com.bl.censusanalyser.dao;
import com.bl.censusanalyser.model.IndiaStateCensusCSV;
import com.bl.censusanalyser.model.IndiaStateCodeCSV;
import com.bl.censusanalyser.model.USCensus;

public class CensusDAO {

    public String state;
    public Integer tin;
    public Integer srNo;
    public String stateCode;
    public Double AreaInSqKm;
    public Double DensityPerSqkm;
    public Integer population;
    public String stateName;

    public CensusDAO(IndiaStateCodeCSV indiaStateCodeCSV) {
        this.state = indiaStateCodeCSV.stateName;
        this.srNo = indiaStateCodeCSV.srNo;
        this.tin = indiaStateCodeCSV.tin;
        this.stateCode = indiaStateCodeCSV.stateCode;
    }

    public CensusDAO(IndiaStateCensusCSV indiaStateCensusCSV) {
        this.state = indiaStateCensusCSV.State;
        this.population = indiaStateCensusCSV.Population;
        this.AreaInSqKm = indiaStateCensusCSV.AreaInSqKm;
        this.DensityPerSqkm = indiaStateCensusCSV.DensityPerSqkm;
    }

    public CensusDAO(USCensus usCensus) {
        this.state = usCensus.state;
        this.population = usCensus.population;
        this.AreaInSqKm = usCensus.area;
        this.DensityPerSqkm = usCensus.populationDensity;
        this.stateCode = usCensus.stateID;
    }

    public Double getAreaInSqKm() {
        return AreaInSqKm;
    }

    public Double getDensityPerSqkm() {
        return DensityPerSqkm;
    }

    public Integer getPopulation() {
        return population;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "CensusDAO{" +
                "state='" + state + '\'' +
                ", tin=" + tin +
                ", srNo=" + srNo +
                ", stateCode='" + stateCode + '\'' +
                ", AreaInSqKm=" + AreaInSqKm +
                ", DensityPerSqkm=" + DensityPerSqkm +
                ", population=" + population +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}