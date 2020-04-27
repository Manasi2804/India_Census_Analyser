package com.bl.censusanalyser;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.StateCode;
import com.bl.censusanalyser.service.CensusAnalyser;
import com.google.gson.Gson;
import com.opencsv.bean.CsvBindAndSplitByPosition;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.bl.censusanalyser.FileLocation.*;

public class StateCensusAnalyserTest {
    CensusAnalyser censusAnalyser = new CensusAnalyser();

    //1.1
    @Test
    public void givenTheStateCensusCSVFile_WhenNoOfRecordMatch_ShouldReturnTrue() throws IOException, CSVBuilderException {
        int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_CSV_FILE, CSVStateCensus.class);
        Assert.assertEquals(29, noOfRecords);
    }
    //1.2
    @Test
    public void givenTheStateCensusCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION,CSVStateCensus.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
    //1,3
    @Test
    public void givenTheStateCensusCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException {
        try {
            censusAnalyser.getFileExtension(new File(PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION));
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }
    //1.4
    @Test
    public void givenTheStateCensusCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException, CSVBuilderException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER,CSVStateCensus.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER, e.type);
        }
    }
    //1.5
    @Test
    public void givenTheStateCensusCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER,CSVStateCensus.class);
            Assert.assertEquals(28, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
    //2.1
    @Test
    public void givenTheStateCodeCSVFile__WhenNoOfRecordMatches_ShouldReturnTrue() throws IOException, CSVBuilderException {
        int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_STATE_CODE_CSV_FILE,StateCode.class);
        Assert.assertEquals(37, noOfRecords);
    }
    //2.2
    @Test
    public void givenTheStateCodeCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND,StateCode.class);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
    //2.3
    @Test
    public void givenTheStateCodeCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CSVBuilderException, IOException {
        try {
            censusAnalyser.getFileExtension(new File(PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE));
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }
    //2.4
    @Test
    public void givenTheStateCodeCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER,StateCode.class);
            Assert.assertEquals(37, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
    //2.5
    @Test
    public void givenTheStateCodeCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER,StateCode.class);
            Assert.assertEquals(37, noOfRecords);
        } catch (CSVBuilderException e) {
            Assert.assertEquals(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
    //3.1
    @Test
    public void givenTheStateCensusCSVFile_WhenSortedOnState_ShouldReturnSortedList() throws IOException,
            CSVBuilderException{
        censusAnalyser.loadCensusData(PATH_OF_CSV_FILE, CSVStateCensus.class);
        String sortedCensusData = censusAnalyser.getStateWiseSortedData();
        CSVStateCensus[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
        Assert.assertEquals("Andhra Pradesh", censusCSV[0].getState());
        Assert.assertEquals("West Bengal", censusCSV[28].getState());
    }
    //4.1
    @Test
    public void givenTheStateCodeCSVFile_WhenSortedOnStateCode_ShouldReturnSortedList() throws IOException, CSVBuilderException {
        censusAnalyser.loadCensusData(PATH_OF_STATE_CODE_CSV_FILE,StateCode.class);
        String sortedStateCodeData = censusAnalyser.getStateCodeWiseSortedData();
        StateCode[] stateCodes = new Gson().fromJson(sortedStateCodeData, StateCode[].class);
        Assert.assertEquals("AD", stateCodes[0].stateCode);
        Assert.assertEquals("WB", stateCodes[36].stateCode);
    }
}