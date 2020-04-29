package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.StateCode;
import com.bl.censusanalyser.model.USCensus;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusLoader {
    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    public HashMap<Integer, CensusDAO> loadStateCensusData(HashMap<Integer, CensusDAO> censusHashMap, String... filePath)
            throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath[0]))) {
            Iterator<CSVStateCensus> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
            Iterable<CSVStateCensus> csvIterable = () -> csvFileIterator;
            final Integer[] count = {0};
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(censusCSV -> {
                        censusHashMap.put(count[0], new CensusDAO(censusCSV));
                        count[0]++;
                    });
            if (filePath.length == 1)
                return censusHashMap;
            return loadStateCodeData(censusHashMap, filePath[1]);
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }

    public HashMap<Integer, CensusDAO> loadStateCodeData(HashMap<Integer, CensusDAO> censusHashMap, String filePath) throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterator<StateCode> csvFileIterator = csvBuilder.getCSVFileIterator(reader, StateCode.class);
            Iterable<StateCode> csvIterable = () -> csvFileIterator;
            final Integer[] count = {0};
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(censusCSV -> {
                        censusHashMap.put(count[0], new CensusDAO(censusCSV));
                        count[0]++;
                    });
            return censusHashMap;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }

    public HashMap<Integer, CensusDAO> loadUSCensusData(HashMap<Integer, CensusDAO> censusHashMap, String filePath)
            throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterator<USCensus> csvFileIterator = csvBuilder.getCSVFileIterator(reader, USCensus.class);
            Iterable<USCensus> csvIterable = () -> csvFileIterator;
            final Integer[] count = {0};
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(censusCSV -> {
                        censusHashMap.put(count[0], new CensusDAO(censusCSV));
                        count[0]++;
                    });
            return censusHashMap;
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }
}
