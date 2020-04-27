package com.bl.censusanalyser.service;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.bl.censusanalyser.model.StateCode;
import com.bl.censusanalyser.utility.CSVBuilderFactory;
import com.bl.censusanalyser.utility.ICSVBuilder;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static java.nio.file.Paths.*;

public class CensusAnalyser {
    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    Collection<Object> stateCensusRecords = null;
    Collection<Object> stateCodeRecords = null;
    HashMap<Object, Object> stateCodeHashMap = null;
    HashMap<Object, Object> stateCensusHashMap = null;

    public int loadStateCensusCSVFileData(String filePath) throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            stateCensusHashMap = csvBuilder.getCSVFileMap(reader, CSVStateCensus.class);
            return stateCensusHashMap.size();
        } catch (NoSuchFileException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                    "FILE NAME IS INCORRECT");
        } catch (RuntimeException e) {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                    "FILE DELIMITER OR HEADER IS INCORRECT");
        }
    }
    public int loadStateCodeCSVFileData(String filePath) throws IOException, CSVBuilderException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            stateCodeHashMap = csvBuilder.getCSVFileMap(reader, StateCode.class);
            return stateCodeHashMap.size();
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
        if (stateCensusRecords == null || stateCensusRecords.size() == 0)
            if (stateCensusHashMap == null || stateCensusHashMap.size() == 0)
                throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
        Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(csvStateCensus -> csvStateCensus.State);
        this.sort(censusCSVComparator , stateCensusHashMap);
        stateCensusRecords = stateCensusHashMap.values();
        String sortedStateCensusJson = new Gson().toJson(stateCensusRecords);
        return sortedStateCensusJson;
    }
        public String getStateCodeWiseSortedData () throws CSVBuilderException {
            if (stateCodeHashMap == null || stateCodeHashMap.size() == 0)
                throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA, "Data empty");
            Comparator<StateCode> stateCodeCSVComparator = Comparator.comparing(stateCode -> stateCode.stateCode);
            this.sort(stateCodeCSVComparator, stateCodeHashMap);
            stateCodeRecords = stateCodeHashMap.values();
            String sortedStateCodeJson = new Gson().toJson(stateCodeRecords);
            return sortedStateCodeJson;
        }
        public <E > void sort (Comparator < E > censusCSVComparator, HashMap < Object, Object > censusRecords){
            for (int iterate = 0; iterate < censusRecords.size() - 1; iterate++) {
                for (int Inneriterate = 0; Inneriterate < censusRecords.size() - iterate - 1; Inneriterate++) {
                    E census1 = (E) censusRecords.get(Inneriterate);
                    E census2 = (E) censusRecords.get(Inneriterate + 1);
                    if (censusCSVComparator.compare(census1, census2) > 0) {
                        censusRecords.put(Inneriterate, census2);
                        censusRecords.put(Inneriterate + 1, census1);
                    }
                }
            }
        }
    }

