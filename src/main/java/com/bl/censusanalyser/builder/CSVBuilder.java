package com.bl.censusanalyser.builder;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.Iterator;

public class CSVBuilder implements ICSVBuilder {
    @Override
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<E> csvToBean = csvToBeanBuilder.build();
            Iterator<E> censusCSVIterator = csvToBean.iterator();
            return censusCSVIterator;
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(),CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    @Override
    public <T> Iterator<T> getFileIterator(BufferedReader reader, Class<T> csvClass) {
        return null;
    }
}