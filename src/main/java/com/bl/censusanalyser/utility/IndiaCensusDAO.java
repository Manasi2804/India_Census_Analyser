package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.StateCode;

public class IndiaCensusDAO {
    public String state;
    public long population;
    public long AreaInSqKm;
    public int DensityPerSqkm;
    public String stateCode;
    public int tin;
    public int srNo;

    public IndiaCensusDAO(StateCode stateCode) {
        this.state = stateCode.stateName;
        this.srNo = stateCode.srNo;
        this.tin = stateCode.tin;
        this.stateCode = stateCode.stateCode;
    }
    public IndiaCensusDAO(CSVStateCensus csvStateCensus) {
        this.state = csvStateCensus.State;
        this.population = csvStateCensus.Population;
        this.AreaInSqKm = csvStateCensus.AreaInSqKm;
        this.DensityPerSqkm = csvStateCensus.DensityPerSqkm;
    }

}
