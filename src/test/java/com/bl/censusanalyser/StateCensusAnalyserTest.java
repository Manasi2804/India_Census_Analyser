package com.bl.censusanalyser;

import com.bl.censusanalyser.exception.CensusAnalyserException;
import com.bl.censusanalyser.exception.CensusAnalyserException.ExceptionType;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.StateCode;
import com.bl.censusanalyser.service.CensusAnalyser;
import com.sun.tools.jdeprscan.CSV;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.bl.censusanalyser.Constant.*;

public class StateCensusAnalyserTest {

    CensusAnalyser censusAnalyser = new CensusAnalyser();

    //1.1
    @Test
    public void givenTheStateCensusCSVFile_WhenNoOfRecordMatch_ShouldReturnTrue() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusCSVFileData(PATH_OF_CSV_FILE, CensusAnalyser.class);
            Assert.assertEquals(29, noOfRecords);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }
    //1.2
    @Test
    public void givenTheStateCensusCSVFile_IfIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            int noOfRecords = censusAnalyser.loadCensusCSVFileData(PATH_OF_CSV_FILE_FOR_FILE_NOT_FOUND_EXCEPTION, CSVStateCensus.class);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
    //1.3
    @Test
    public void givenTheStateCensusCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException() throws IOException {
        try {
            censusAnalyser.getFileExtension(new File(PATH_OF_CSV_FILE_FOR_INCORRECT_TYPE_EXCEPTION, String.valueOf(CSVStateCensus.class)));
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(ExceptionType.ENTERED_WRONG_FILE_TYPE, e.type);
        }
    }
    //2.4
    @Test
    public void givenTheStateCensusCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException () throws IOException
    {
        try {
            int noOfRecords = censusAnalyser.loadCensusCSVFileData(PATH_OF_CSV_FILE_FOR_INCORRECT_DELIMITER, CSVStateCensus.class);
            Assert.assertEquals(37, noOfRecords);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
        }
    }
        //1.5
        @Test
        public void givenTheStateCensusCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException () throws IOException {
            try {
                int noOfRecords = censusAnalyser.loadCensusCSVFileData(PATH_OF_CSV_FILE_FOR_INCORRECT_HEADER, CSVStateCensus.class);
                Assert.assertEquals(28, noOfRecords);
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
            }
        }

        //2.1
        @Test
        public void givenTheStateCodeCSVFile__WhenNoOfRecordMatches_ShouldReturnTrue () throws
        CensusAnalyserException {
            try {
                int noOfRecords = censusAnalyser.loadCensusCSVFileData(PATH_OF_STATE_CODE_CSV_FILE, StateCode.class);
                Assert.assertEquals(37, noOfRecords);
            } catch (CensusAnalyserException e) {
                e.printStackTrace();
            }
        }
        //2.2
        @Test
        public void givenTheStateCodeCSVFile_IfIncorrect_ShouldThrowCustomException () throws IOException {
            try {
                int noOfRecords = censusAnalyser.loadCensusCSVFileData(PATH_OF_STATE_CODE_CSV_FILE_FOR_FILE_NOT_FOUND, StateCode.class);
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
            }
        }
        //2.3
        @Test
        public void givenTheStateCodeCSVFile_IfTypeIsIncorrect_ShouldThrowCustomException () throws
        CensusAnalyserException, IOException {
            try {
                censusAnalyser.getFileExtension(new File(PATH_OF_STATE_CODE_CSV_FILE_FOR_INCORRECT_TYPE, String.valueOf(StateCode.class)));
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(ExceptionType.ENTERED_WRONG_FILE_TYPE, e.type);
            }
        }
        //2.4
        @Test
        public void givenTheStateCodeCSVFile_IfDelimiterIsIncorrect_ShouldThrowCustomException () throws IOException
        {
            try {
                int noOfRecords = censusAnalyser.loadCensusCSVFileData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_DELIMITER, StateCode.class);
                Assert.assertEquals(37, noOfRecords);
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
            }
        }
        //2.5
        @Test
        public void givenTheStateCodeCSVFile_IfHeaderIsIncorrect_ShouldThrowCustomException () throws IOException
        {
            try {
                int noOfRecords = censusAnalyser.loadCensusCSVFileData(PATH_OF_STATE_CODE_CSV_FILE_INCORRECT_HEADER, StateCode.class);
                Assert.assertEquals(37, noOfRecords);
            } catch (CensusAnalyserException e) {
                Assert.assertEquals(ExceptionType.INCORRECT_DELIMITER_OR_HEADER, e.type);
            }
        }

    }
