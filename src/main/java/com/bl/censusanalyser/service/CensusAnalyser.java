package com.bl.censusanalyser.service;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.StateCode;
import com.bl.censusanalyser.utility.CSVBuilderFactory;
import com.bl.censusanalyser.utility.ICSVBuilder;
import com.bl.censusanalyser.utility.IndiaCensusDAO;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.Paths.*;

public class CensusAnalyser {
    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    Collection<IndiaCensusDAO> censusRecords = null;
    HashMap<Integer, IndiaCensusDAO> censusHashMap = new HashMap<>();

    public int loadStateCensusData(String filePath) throws IOException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            Iterator<CSVStateCensus> csvFileIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
            Integer count = 0;
            while (csvFileIterator.hasNext()) {
                IndiaCensusDAO indiaCensusDAO = new IndiaCensusDAO(csvFileIterator.next());
                this.censusHashMap.put(count, indiaCensusDAO);
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
            Iterator<StateCode> csvFileIterator = csvBuilder.getCSVFileIterator(reader,StateCode.class);
            Integer count = 0;
            while (csvFileIterator.hasNext()) {
                IndiaCensusDAO indiaCensusDAO = new IndiaCensusDAO(csvFileIterator.next());
                this.censusHashMap.put(count,indiaCensusDAO);
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
        Comparator<Map.Entry<Integer, IndiaCensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().state);
        LinkedHashMap<Integer, IndiaCensusDAO> sortedByValue = this.sort(censusComparator);
        censusRecords = sortedByValue.values();
        String sortedStateCensusJson = new Gson().toJson(censusRecords);
        return sortedStateCensusJson;
    }
    public String getStateCodeWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, IndiaCensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().stateCode);
        LinkedHashMap<Integer, IndiaCensusDAO> sortedByValue = this.sort(censusComparator);
        censusRecords = sortedByValue.values();
        String sortedStateCodeJson = new Gson().toJson(censusRecords);
        return sortedStateCodeJson;
    }
    public LinkedHashMap<Integer, IndiaCensusDAO> sort(Comparator censusCSVComparator) {
        Set<Map.Entry<Integer, IndiaCensusDAO>> entries = censusHashMap.entrySet();
        List<Map.Entry<Integer, IndiaCensusDAO>> listOfEntries = new ArrayList<Map.Entry<Integer, IndiaCensusDAO>>(entries);
        Collections.sort(listOfEntries, censusCSVComparator);
        LinkedHashMap<Integer, IndiaCensusDAO> sortedByValue = new LinkedHashMap<Integer, IndiaCensusDAO>(listOfEntries.size());
        // copying entries from List to Map
        for (Map.Entry<Integer, IndiaCensusDAO> entry : listOfEntries) {
            sortedByValue.put(entry.getKey(), entry.getValue());
        }
        return sortedByValue;
    }
    public String getStatePopulationWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, IndiaCensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().population);
        LinkedHashMap<Integer, IndiaCensusDAO> sortedByValue = this.sort(censusComparator);
        List<IndiaCensusDAO> sortedList = new ArrayList<IndiaCensusDAO>(sortedByValue.values());
        Collections.reverse(sortedList);
        String sortedStatePopulationJson = new Gson().toJson(sortedList);
        return sortedStatePopulationJson;
    }
    public String getStatePopulationDensityWiseSortedData() throws CSVBuilderException {
        if (censusHashMap == null || censusHashMap.size() == 0)
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<Map.Entry<Integer, IndiaCensusDAO>> censusComparator = Comparator.comparing(census -> census.getValue().DensityPerSqkm);
        LinkedHashMap<Integer, IndiaCensusDAO> sortedByValue = this.sort(censusComparator);
        List<IndiaCensusDAO> sortedList = new ArrayList<IndiaCensusDAO>(sortedByValue.values());
        Collections.reverse(sortedList);
        String sortedStatePopulationDensityJson = new Gson().toJson(sortedList);
        return sortedStatePopulationDensityJson;
    }
}