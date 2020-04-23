package com.censusanalyser;

import com.opencsv.exceptions.CsvException;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class StateCensusAnalyserTest
{
    private static final String PATH_OF_CSV_FILE = "./src/test/resources/StateCensusData.csv";
    private static final String PATH_OF_CSV_FILE_FOR_EXCEPTION = "./src/test/resources/StateCensusData.csv";
    private static final String PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION = "./src/test/resources/StateCensus.csv";
    private static final String PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION = "/home/Desktop/IndiaCensusAnalyser/src/test/resources/StateCensusData.docx";

    @Test
    public void givenTheStateCensusCSVFile_WhenNoOfRecordMatch_ShouldReturnTrue() throws IOException, CsvException, IOException,CensusAnalyserException {
        int noOfRecords = StateCensusAnalyser.loadCSVFileData(PATH_OF_CSV_FILE);
        Assert.assertEquals(29, noOfRecords);
    }
    @Test
    public void givenTheStateCensusCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException
    {
        try
        {
            int noOfRecords = StateCensusAnalyser.loadCSVFileData(PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION);
            System.out.println("records--> "+ noOfRecords);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
    @Test
    public void givenTheStateCensusCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws CensusAnalyserException, IOException
    {
        try
        {
            StateCensusAnalyser.getFileExtension(new File(PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION));
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_TYPE,e.type);
        }
    }
}
