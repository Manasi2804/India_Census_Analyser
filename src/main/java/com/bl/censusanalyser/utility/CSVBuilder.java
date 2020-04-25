package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.List;

public class CSVBuilder implements ICSVBuilder
{
    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException
    {
        try
        {
            CSVReader csvReader = new CSVReader(reader);
            CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(csvReader).withType(csvClass).build();
            return csvToBean.parse();
        }
        catch (IllegalStateException e)
        {
            throw new CSVBuilderException(CSVBuilderException.ExceptionType.UNABLE_TO_PARSE, e.getMessage());
        }
    }

}