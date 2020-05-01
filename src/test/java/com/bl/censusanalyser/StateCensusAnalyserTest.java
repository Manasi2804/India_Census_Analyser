package com.bl.censusanalyser;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.IndiaStateCensusCSV;
import com.bl.censusanalyser.model.IndiaStateCodeCSV;
import com.bl.censusanalyser.model.USCensus;
import com.bl.censusanalyser.service.CensusAnalyser;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import static com.bl.censusanalyser.FilePath.*;


public class StateCensusAnalyserTest {
    CensusAnalyser censusAnalyser = new CensusAnalyser();

    //1.1
    @Test
    public void givenTheStateCensusCSVFile_WhenNoOfRecordMatch_ShouldReturnTrue() throws CSVBuilderException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_CSV_FILE, IndiaStateCensusCSV.class);
            Assert.assertEquals(29, noOfRecords);
        } catch (CSVBuilderException e) {
        }
    }
    //1.2
    @Test
    public void givenTheStateCensusCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION,IndiaStateCensusCSV.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_FILE,e.type);
        }
    }
    //1.3
    @Test
    public void givenTheStateCensusCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION,IndiaStateCensusCSV.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
    //1.4
    @Test
    public void givenTheStateCensusCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException, CSVBuilderException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER,IndiaStateCensusCSV.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }
    //1.5
    @Test
    public void givenTheStateCensusCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER,IndiaStateCensusCSV.class);
            Assert.assertEquals(28, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }
    //2.1
    @Test
    public void givenTheStateCodeCSVFile__WhenNoOfRecordMatches_ShouldReturnTrue() throws IOException, CSVBuilderException {
        try {
        int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_STATE_CODE_CSV_FILE, IndiaStateCodeCSV.class);
        Assert.assertEquals(37, noOfRecords);
        } catch (CSVBuilderException e) {
        }
    }
    //2.2
    @Test
    public void givenTheStateCodeCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData( PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND,IndiaStateCodeCSV.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }
    //2.3
    @Test
    public void givenTheStateCodeCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE,IndiaStateCodeCSV.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.FILE_NOT_FOUND, e.type);
        }
    }
    //2.4
    @Test
    public void givenTheStateCodeCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER,IndiaStateCodeCSV.class);
            Assert.assertEquals(37, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }
    //2.5
    @Test
    public void givenTheStateCodeCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER,IndiaStateCodeCSV.class);
            Assert.assertEquals(37, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }
    //3.1
    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnState_ShouldReturnSortedList() throws IOException,
            CSVBuilderException {
        try{
        censusAnalyser.loadCensusData(PATH_OF_CSV_FILE,IndiaStateCensusCSV.class);
        String sortedCensusData = censusAnalyser.getSortData(IndiaStateCensusCSV.class,2);
        IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaStateCensusCSV[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].State);
        Assert.assertEquals("West Bengal", censusCSV[28].State);
        } catch (CSVBuilderException e) {
        }
    }
    //4.1
    @Test
    public void givenTheStateCodeCSVFile_WhenSortedOnStateCode_ShouldReturnSortedList() throws IOException, CSVBuilderException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_CSV_FILE, IndiaStateCodeCSV.class);
            String sortedStateCodeData = censusAnalyser.getSortData(IndiaStateCodeCSV.class, 6);
            IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedStateCodeData, IndiaStateCensusCSV[].class);
            Assert.assertEquals("AD", censusCSV[0].stateCode);
            Assert.assertEquals("WB", censusCSV[36].stateCode);
        } catch (CSVBuilderException e) {
        }
    }
    //5.1
    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnPopulation_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_CSV_FILE,IndiaStateCensusCSV.class);
            String sortedStatePopulationData = censusAnalyser.getSortData(IndiaStateCensusCSV.class,3);
            IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedStatePopulationData, IndiaStateCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].State);
            Assert.assertEquals("Sikkim", censusCSV[28].State);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
    //6.1
    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnPopulationDensity_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_CSV_FILE,IndiaStateCensusCSV.class);
            String sortedStatePopulationData = censusAnalyser.getSortData(IndiaStateCensusCSV.class,7);
            IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedStatePopulationData, IndiaStateCensusCSV[].class);
            Assert.assertEquals("Bihar", censusCSV[0].State);
            Assert.assertEquals("Arunachal Pradesh", censusCSV[28].State);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
    //7.1
    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnArea_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_CSV_FILE,IndiaStateCensusCSV.class);
            String sortedStatePopulationData = censusAnalyser.getSortData(IndiaStateCensusCSV.class,12);
            IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedStatePopulationData, IndiaStateCensusCSV[].class);
            Assert.assertEquals("Rajasthan", censusCSV[0].State);
            Assert.assertEquals("Goa", censusCSV[28].State);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
    //8.1
    @Test
    public void givenUSCensusCSVFile__WhenNoOfRecordMatches_ShouldReturnTrue() throws IOException, CSVBuilderException {
        try{
        int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE,USCensus.class);
        Assert.assertEquals(51, noOfRecords);
        } catch (CSVBuilderException e) {
        }
    }
    //9.1
    @Test
    public void givenTheUSCensusCSVFile_WhenSortedOnPopulation_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE,USCensus.class);
            String sortedStatePopulationData = censusAnalyser.getSortData(USCensus.class,3);
            USCensus[] censusCSV = new Gson().fromJson(sortedStatePopulationData, USCensus[].class);
            Assert.assertEquals("California", censusCSV[0].state);
            Assert.assertEquals("Wyoming", censusCSV[50].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
    //10.1
    @Test
    public void givenTheUSCensusCSVFile_WhenSortedOnArea_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE,USCensus.class);
            String sortedStatePopulationData = censusAnalyser.getSortData(USCensus.class,10);
            USCensus[] censusCSV = new Gson().fromJson(sortedStatePopulationData, USCensus[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
            Assert.assertEquals("District of Columbia", censusCSV[50].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
    //10.2
    @Test
    public void givenTheUSCensusCSVFile_WhenSortedOnPopulationDensity_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE,USCensus.class);
            String sortedStatePopulationData = censusAnalyser.getSortData(USCensus.class,11);
            USCensus[] censusCSV = new Gson().fromJson(sortedStatePopulationData, USCensus[].class);
            Assert.assertEquals("District of Columbia", censusCSV[0].state);
            Assert.assertEquals("Alaska", censusCSV[50].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace(); }
    }
    //11.1
    @Test
    public void givenIndianStateCensusCSVFile_WhenSortedPopulationAndDensity_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_CSV_FILE,IndiaStateCensusCSV.class);
            String sortedPopulationAndDensityData = censusAnalyser.getSortData(IndiaStateCensusCSV.class,10);
            IndiaStateCensusCSV[] censusCSV = new Gson().fromJson(sortedPopulationAndDensityData, IndiaStateCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].State);
            Assert.assertEquals("Sikkim", censusCSV[28].State);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
    //11.2
    @Test
    public void givenUSCensusCSVFile_WhenSortedPopulationAndDensity_ShouldReturnSortedList() throws IOException {
        try {
            censusAnalyser.loadCensusData(PATH_OF_US_CENSUS_CSV_FILE,USCensus.class);
            String sortedPopulationAndDensityData = censusAnalyser.getSortData(USCensus.class,3);
            USCensus[] censusCSV = new Gson().fromJson(sortedPopulationAndDensityData, USCensus[].class);
            Assert.assertEquals("Alaska", censusCSV[0].state);
            Assert.assertEquals("District of Columbia", censusCSV[50].state);
        } catch (CSVBuilderException e) {
            e.printStackTrace();
        }
    }
}