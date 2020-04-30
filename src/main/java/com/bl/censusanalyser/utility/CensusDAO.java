package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.model.IndiaStateCensusCSV;
import com.bl.censusanalyser.model.IndiaStateCodeCSV;
import com.bl.censusanalyser.model.USCensus;
import com.bl.censusanalyser.service.CensusAnalyser;

import java.util.Comparator;

public class CensusDAO {
    public String state;
    public Integer tin;
    public Integer srNo;
    public String stateCode;
    public Double AreaInSqKm;
    public Double DensityPerSqkm;
    public Integer population;

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
        this.DensityPerSqkm =indiaStateCensusCSV.DensityPerSqkm;
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
    public Double getDensityPerSqkm()
    {
        return DensityPerSqkm;
    }
    public Integer getPopulation()
    {
        return population;
    }
    public static Comparator<? super CensusDAO> getSortComparator(CensusAnalyser.SortingMode mode) {
        if (mode.equals(CensusAnalyser.SortingMode.STATE))
            return Comparator.comparing(census -> census.state);
        if (mode.equals(CensusAnalyser.SortingMode.STATECODE))
            return Comparator.comparing(census -> census.stateCode);
        if (mode.equals(CensusAnalyser.SortingMode.POPULATION))
            return Comparator.comparing(CensusDAO::getPopulation).reversed();
        if (mode.equals(CensusAnalyser.SortingMode.DENSITY))
            return Comparator.comparing(CensusDAO::getDensityPerSqkm).reversed();
        if (mode.equals(CensusAnalyser.SortingMode.AREA))
            return Comparator.comparing(CensusDAO::getAreaInSqKm).reversed();
        return null;
    }

    public Object getCensusDTO(CensusAnalyser.COUNTRY country) {
        if (country.equals(CensusAnalyser.COUNTRY.INDIA))
            return new IndiaStateCensusCSV(state, stateCode, population, AreaInSqKm, DensityPerSqkm);
        if (country.equals(CensusAnalyser.COUNTRY.US))
            return new USCensus(stateCode, state, population, AreaInSqKm, DensityPerSqkm);
        return null;
    }
}
