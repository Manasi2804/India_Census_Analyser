package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

public class CSVBuilder implements ICSVBuilder {
    @Override
            public <E> HashMap<E, E> getCSVFileMap(Reader reader, Class csvClass) throws CSVBuilderException {
                try {
                    CSVReader csvReader = new CSVReader(reader);
                    CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(csvReader).withType(csvClass).build();
                    List list = csvToBean.parse();
                    HashMap<Integer , Object> map = new HashMap<>();
                    Integer count = 0;
                    for (Object record:list) {
                        map.put(count,record);
                        count++;
                    }
                    return (HashMap<E, E>) map;
                } catch (IllegalStateException e) {
                    throw new CSVBuilderException(CSVBuilderException.ExceptionType.UNABLE_TO_PARSE, e.getMessage());
                }
            }
    }
