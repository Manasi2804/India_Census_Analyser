package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.IndiaStateCensusCSV;
import com.bl.censusanalyser.model.IndiaStateCodeCSV;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class IndiaCensusAdapter extends CensusAdapter {
    @Override
    public HashMap<Integer, CensusDAO> loadCensusData(String... filePath) throws CSVBuilderException, IOException {
        HashMap<Integer, CensusDAO> censusHashMap = super.loadCensusData(IndiaStateCensusCSV.class, filePath[0]);
        if (filePath.length == 1)
            return censusHashMap;
        return this.loadStateCodeData(censusHashMap, filePath[1]);
    }

    private HashMap<Integer, CensusDAO> loadStateCodeData(HashMap<Integer, CensusDAO> censusHashMap, String filePath) throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterator<IndiaStateCodeCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCodeCSV.class);
            Iterable<IndiaStateCodeCSV> csvIterable = () -> csvFileIterator;
            final Integer[] count = {0};
            StreamSupport.stream(csvIterable.spliterator(), false).map(IndiaStateCodeCSV.class::cast)
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