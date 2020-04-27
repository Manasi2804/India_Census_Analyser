package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CSVBuilder implements ICSVBuilder {
    @Override
    public <E> HashMap<E, E> getCSVFileMap(Reader reader, Class csvClass) throws CSVBuilderException {
        return null;
    }
    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException
    {
        try {
            CSVReader csvReader = new CSVReader(reader);
            CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(csvReader).withType(csvClass).build();
            return csvToBean.iterator();
        } catch (IllegalStateException e){
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.UNABLE_TO_PARSE, e.getMessage());
        }
    }
    @Override
    public <E> int getCount(Iterator<E> csvRecords)
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
