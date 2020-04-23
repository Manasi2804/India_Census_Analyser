package com.censusanalyser;

import com.opencsv.exceptions.CsvException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest
{
    private static final String PATH_OF_CSV_FILE = "./src/test/resources/StateCensusData.csv";
    private static final String PATH_OF_CSV_FILE_FOR_EXCEPTION = "./src/test/resources/StateCensus.csv";

    @Test
    public void givenTheStateCensusCSVFile_WhenNoOfRecordMatch_ShouldReturnTrue() throws IOException, CsvException, IOException,CensusAnalyserException {
        int noOfRecords = StateCensusAnalyser.loadCSVFileData(PATH_OF_CSV_FILE);
        Assert.assertEquals(29, noOfRecords);
    }

    @Test
    public void givenTheStateCensusCSVFile_IfIncorrect_ShouldThrowCustomException()
    {
        try
        {
            int noOfRecords = StateCensusAnalyser.loadCSVFileData(PATH_OF_CSV_FILE_FOR_EXCEPTION);
        }
        catch (CensusAnalyserException e)
        {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME, e.type);
        }
    }
}
