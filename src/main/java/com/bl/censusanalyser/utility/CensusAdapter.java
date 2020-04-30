package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.USCensus;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public  abstract class CensusAdapter {
    HashMap<Integer, CensusDAO> censusHashMap = new HashMap<Integer, CensusDAO>();
    CSVBuilderFactory csvBuilderFactory = new CSVBuilderFactory();
    ICSVBuilder csvBuilder = csvBuilderFactory.createCSVBuilder();

    public abstract HashMap<Integer, CensusDAO> loadCensusData(String... csvFilePath) throws CSVBuilderException, IOException;

    public <E> HashMap<Integer, CensusDAO> loadCensusData(Class<E> CSVClass, String... filePath) throws CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath[0]))) {
            Iterator<E> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVClass);
            Iterable<E> csvIterable = () -> csvFileIterator;

            if (CSVClass.getName().equals("com.bl.censusanalyser.model.CSVStateCensus")) {
                final Integer[] count = {0};
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .forEach(censusCSV -> {
                            censusHashMap.put(count[0], new CensusDAO((CSVStateCensus) censusCSV));
                            count[0]++;
                        });
            }
            if (CSVClass.getName().equals("com.bl.censusanalyser.model.USCensus")) {
                final Integer[] count = {0};
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .forEach(censusCSV -> {
                            censusHashMap.put(count[0], new CensusDAO(new USCensus()));
                            count[0]++;
                        });
            }
            return censusHashMap;
        } catch (IOException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (CSVBuilderException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }
}
