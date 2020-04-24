package com.bl.censusanalyser.service;

import com.bl.censusanalyser.exception.CensusAnalyserException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.StateCode;
import com.bl.censusanalyser.utility.CSVBuilderFactory;
import com.bl.censusanalyser.utility.ICSVBuilder;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;

    public class CensusAnalyser
    {
        ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
        public int loadStateCensusCSVFileData(String filePath) throws CensusAnalyserException, IOException
        {
            try (Reader reader = Files.newBufferedReader(Paths.get(filePath)))
            {
                Iterator<CSVStateCensus> csvRecords = csvBuilder.getCSVFileIterator(reader , CSVStateCensus.class);
                return csvBuilder.getCount(csvRecords);
            }
            catch (NoSuchFileException e)
            {
                throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                        "FILE NAME IS INCORRECT");
            }
            catch (RuntimeException e)
            {
                throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                        "FILE DELIMITER OR HEADER IS INCORRECT");
            }
        }
        public int loadStateCodeCSVFileData(String filePath) throws IOException, CensusAnalyserException
        {
            try (Reader reader = Files.newBufferedReader(Paths.get(filePath)))
            {
                Iterator<StateCode> csvRecords = csvBuilder.getCSVFileIterator(reader, StateCode.class);
                return csvBuilder.getCount(csvRecords);
            }
            catch (NoSuchFileException e)
            {
                throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                        "FILE NAME IS INCORRECT");
            }
            catch (RuntimeException e)
            {
                throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                        "FILE DELIMITER OR HEADER IS INCORRECT");
            }
        }
        public static void getFileExtension(File filePath) throws CensusAnalyserException
        {
            String fileName = filePath.getName();
            String extension = null;
            if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            {
                extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            }
            if (!(extension.equals("csv")))
            {
                throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_TYPE,
                        "FILE TYPE IS INCORRECT");
            }
        }
    }








