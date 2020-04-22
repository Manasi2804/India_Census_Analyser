package com.censusanalyser;

import Model.CSVStateCensus;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser
{
    public static int loadCSVFileData(String filePath) throws IOException
    {
        int noOfRecords = 0;
        try(Reader reader = Files.newBufferedReader(Paths.get(filePath)))
        {
            CSVReader csvReader = new CSVReader(reader);
            CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder<CSVStateCensus>(csvReader).
                    withType(CSVStateCensus.class).build();
            Iterator<CSVStateCensus> csvRecords = csvToBean.iterator();
            while (csvRecords.hasNext())
            {
                noOfRecords++;
               csvRecords.next();
            }
        }
        return noOfRecords;
    }
}
