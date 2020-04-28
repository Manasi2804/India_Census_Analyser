package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CSVBuilderException;
import com.bl.censusanalyser.model.CSVStateCensus;

import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;

public interface ICSVBuilder {
    public <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) throws CSVBuilderException;
}
