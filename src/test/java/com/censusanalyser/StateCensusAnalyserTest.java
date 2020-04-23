package com.censusanalyser;

import com.opencsv.exceptions.CsvException;
import com.sun.tools.jdeprscan.CSV;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {

//TC1.1:Given the States Census CSV file, Check to ensure the Number of Record matches

    private static final String PATH_OF_CSV_FILE = "./src/test/resources/StateCensusData.csv";

    @Test
    public void givenTheStateCensusCSVFile_WhenNoOfRecordMatch_ShouldReturnTrue() throws IOException, CsvException, IOException
    {
        int noOfRecords = StateCensusAnalyser.loadCSVFileData(PATH_OF_CSV_FILE);
        Assert.assertEquals(29,noOfRecords);
    }


    }

