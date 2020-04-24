package com.bl.censusanalyser.utility;

public class CSVBuilderFactory {

    public ICSVBuilder createCSVBuilder() {
        return new CSVBuilder();
    }
}