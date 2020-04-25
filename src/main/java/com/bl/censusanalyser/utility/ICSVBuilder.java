package com.bl.censusanalyser.utility;

import com.bl.censusanalyser.exception.CSVBuilderException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder
{
    public <E> List<E> getCSVFileList(Reader reader, Class csvClass) throws CSVBuilderException;
}


