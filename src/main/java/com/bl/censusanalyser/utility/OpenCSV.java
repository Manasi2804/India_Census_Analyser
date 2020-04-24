package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CensusAnalyserException;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSV {

    public static <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CensusAnalyserException
    {
        try
        {
            CSVReader csvReader = new CSVReader(reader);
            CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(csvReader).withType(csvClass).build();
            return csvToBean.iterator();
        }
        catch (IllegalStateException e)
        {
            throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.getMessage());
        }
    }
    public static <E> int getCount(Iterator<E> csvRecords)
    {
        int noOfRecords = 0;
        while (csvRecords.hasNext())
        {
            noOfRecords++;
            csvRecords.next();
        }
        return noOfRecords;
    }
}
