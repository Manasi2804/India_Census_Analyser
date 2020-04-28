package com.bl.censusanalyser.service;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.StateCode;
import com.bl.censusanalyser.model.USCensus;
import com.bl.censusanalyser.utility.CSVBuilderFactory;
import com.bl.censusanalyser.utility.ICSVBuilder;
import com.bl.censusanalyser.utility.CensusDAO;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    Collection<CensusDAO> censusRecords = null;
    HashMap<Integer, CensusDAO> censusHashMap = new HashMap<>();

    public int loadStateCensusData(String filePath) throws IOException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterator<CSVStateCensus> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
            Integer count = 0;
            while (csvFileIterator.hasNext()) {
                CensusDAO censusDAO = new CensusDAO(csvFileIterator.next());
                this.censusHashMap.put(count, censusDAO);
                count++;
            }
            return this.censusHashMap.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }
    public int loadStateCodeData(String filePath) throws IOException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterator<StateCode> csvFileIterator = csvBuilder.getCSVFileIterator(reader, StateCode.class);
            Iterable<StateCode> csvIterable = () -> csvFileIterator;
            final Integer[] count = {0};
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(censusCSV -> {
                        censusHashMap.put(count[0], new CensusDAO(censusCSV));
                        count[0]++;
                    });
            return this.censusHashMap.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }
    public static void getFileExtension(File filePath) throws CSVBuilderException {
        String fileName = filePath.getName();
        String extension = null;
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        if (!(extension.equals("csv"))) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_TYPE,
                    "FILE TYPE IS INCORRECT");
        }
    }
    public String getStateWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().state);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        censusRecords = sortedByValue.values();
        String sortedStateCensusJson = new Gson().toJson(censusRecords);
        return sortedStateCensusJson;
    }
    public String getStateCodeWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().stateCode);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        censusRecords = sortedByValue.values();
        String sortedStateCodeJson = new Gson().toJson(censusRecords);
        return sortedStateCodeJson;
    }
    public String getStatePopulationWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().population);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        List<CensusDAO> sortedList = new ArrayList<CensusDAO>(sortedByValue.values());
        Collections.reverse(sortedList);
        String sortedStatePopulationJson = new Gson().toJson(sortedList);
        return sortedStatePopulationJson;
    }
    public String getStatePopulationDensityWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().DensityPerSqkm);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        List<CensusDAO> sortedList = new ArrayList<CensusDAO>(sortedByValue.values());
        Collections.reverse(sortedList);
        String sortedStatePopulationDensityJson = new Gson().toJson(sortedList);
        return sortedStatePopulationDensityJson;
    }
    public String getStateAreaWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, CensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().AreaInSqKm);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = this.sort(censusComparator);
        List<CensusDAO> sortedList = new ArrayList<CensusDAO>(sortedByValue.values());
        Collections.reverse(sortedList);
        String sortedStateAreaJson = new Gson().toJson(sortedList);
        return sortedStateAreaJson;
    }
    public LinkedHashMap<Integer, CensusDAO> sort(Comparator censusCSVComparator) {
        Set<Map.Entry<Integer, CensusDAO>> entries = censusHashMap.entrySet();
        List<Map.Entry<Integer, CensusDAO>> listOfEntries = new ArrayList<Map.Entry<Integer, CensusDAO>>(entries);
        Collections.sort(listOfEntries, censusCSVComparator);
        LinkedHashMap<Integer, CensusDAO> sortedByValue = new LinkedHashMap<Integer, CensusDAO>(listOfEntries.size());
        // copying entries from List to Map
        for (Map.Entry<Integer, CensusDAO> entry : listOfEntries) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }
        return sortedByValue;
    }
    public int loadUSCensusData(String filePath) throws IOException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterator<USCensus> csvFileIterator = csvBuilder.getCSVFileIterator(reader, USCensus.class);
            Integer count = 0;
            while (csvFileIterator.hasNext()) {
                CensusDAO censusDAO = new CensusDAO(csvFileIterator.next());
                this.censusHashMap.put(count, censusDAO);
                count++;
            }
            return this.censusHashMap.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }
}