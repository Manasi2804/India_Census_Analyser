package com.bl.censusanalyser.service;
import com.bl.censusanalyser.dao.CensusDAO;
import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.builder.*;
import com.bl.censusanalyser.model.IndiaStateCensusCSV;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CensusAnalyser <T> {
    List<T> csvFileList = null;
    Map<Object, T> csvStateCodeMap = new HashMap<>();

    // Read State Census Data CSV file
    public  int loadCensusData(String csvFilePath, Class<T> csvClass) throws CSVBuilderException {
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder icsvBuilder = CSVBuilderFactory.ISCVBuilder();
            Iterator<T> csvStateCensusIterator = icsvBuilder.getFileIterator(reader, csvClass);
            while (csvStateCensusIterator.hasNext()) {
                CensusDAO value = new CensusDAO((IndiaStateCensusCSV) csvStateCensusIterator.next());
                this.csvStateCodeMap.put(value.getState(), (T) value);
                csvFileList = csvStateCodeMap.values().stream().collect(Collectors.toList());
            }
            int noOfRecords = csvStateCodeMap.size();
            return noOfRecords;
        } catch (IOException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.FILE_NOT_FOUND);
        } catch (RuntimeException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.INCORRECT_FILE);
        }
    }
    // Sort The Data From Csv File
    public String getSortData(Object T, int Number) throws CSVBuilderException {
        if (csvFileList.size() == 0 | csvFileList == null) {
            throw new CSVBuilderException("No Census Data", CSVBuilderException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<T> stateCensusAnalyserComparator = Comparator.comparing(csvCounter -> T.toString());
        this.sort(csvFileList, Number);
        String sortedData = new Gson().toJson(csvFileList);
        return sortedData;
    }
    //Sorting Method
    public void sort(List<T> csvFileList, int number) {
        for (int i = 0; i < csvFileList.size(); i++) {
            for (int j = 0; j < csvFileList.size() - i - 1; j++) {
                String census1[] = csvFileList.get(i).toString().split(",");
                String census2[] = csvFileList.get(j).toString().split(",");
                if (census1[1].compareToIgnoreCase(census2[1]) > 0) {
                    T censusData = csvFileList.get(i);
                    T censusData1 = csvFileList.get(j);
                    csvFileList.set(j, censusData);
                    csvFileList.set(i, censusData1);
                }
            }
        }
    }
}