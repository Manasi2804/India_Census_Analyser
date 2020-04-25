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
import java.util.Comparator;
import java.util.List;

public class CensusAnalyser {

    ICSVBuilder csvBuilder = new CSVBuilderFactory().createCSVBuilder();
    List<CSVStateCensus> StateCensusRecord = null;
    List<StateCode> StateCodeRecord = null;


    public int loadStateCensusCSVFileData(String filePath) throws CSVBuilderException, IOException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            StateCensusRecord = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
            return StateCensusRecord.size();
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
            StateCodeRecord = csvBuilder.getCSVFileList(reader, StateCode.class);
            return StateCodeRecord.size();
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
    public String getStateWiseSortedData() throws CSVBuilderException
    {
        if (StateCensusRecord == null || StateCensusRecord.size() == 0)
        {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.NO_CENSUS_DATA,"Data empty");
        }
        Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(csvStateCensus ->
                csvStateCensus.getState());
        this.sort(censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(StateCensusRecord);
        return sortedStateCensusJson;
    }

    public void sort(Comparator<CSVStateCensus> censusCSVComparator)
    {
        for (int iterate = 0; iterate < StateCensusRecord.size() - 1; iterate++)
        {
            for (int Inneriterate = 0; Inneriterate < StateCensusRecord.size() - iterate - 1; Inneriterate++)
            {
                CSVStateCensus census1 =StateCensusRecord.get(Inneriterate);
                CSVStateCensus census2 = StateCensusRecord.get(Inneriterate + 1);
                if (censusCSVComparator.compare(census1, census2) > 0)
                {
                    StateCensusRecord.set(Inneriterate, census2);
                    StateCensusRecord.set(Inneriterate + 1, census1);
                }
            }
        }
    }
}
