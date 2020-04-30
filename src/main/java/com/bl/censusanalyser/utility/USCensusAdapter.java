package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.USCensus;

import java.io.IOException;
import java.util.HashMap;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public HashMap<Integer, CensusDAO> loadCensusData(String... csvFilePath) throws CSVBuilderException, IOException {
        return super.loadCensusData(USCensus.class, csvFilePath[0]);
    }
}