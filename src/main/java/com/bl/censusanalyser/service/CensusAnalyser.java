package com.bl.censusanalyser.service;

import com.bl.censusanalyser.exception.CensusAnalyserException;
import com.bl.censusanalyser.utility.OpenCSV;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

    public class CensusAnalyser<E>
    {
        public int loadCensusCSVFileData(String csvfilepath, Object E) {
            int noOfRecords = 0;
            try (Reader reader = Files.newBufferedReader(Paths.get(csvfilepath));) {
                Iterator<E> csvRecordsIterator = (Iterator<E>) this.getCSVFileIterator(reader, E.getClass());
                while (csvRecordsIterator.hasNext()) ;
                {
                    csvRecordsIterator.next();
                    noOfRecords++;
                }
            } catch (IOException e) {
                throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_NAME,
                        "FILE NAME IS INCORRECT");
            } catch (RuntimeException e) {
                throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.INCORRECT_DELIMITER_OR_HEADER,
                        "FILE DELIMITER OR HEADER IS INCORRECT");
            }
            return noOfRecords;
        }

        private  <E> Iterator<E> getCSVFileIterator(Reader reader, Class csvClass) {
            try {
                CSVReader csvReader = new CSVReader(reader);
                CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(csvReader).withType(csvClass).build();
                return csvToBean.iterator();
            } catch (IllegalStateException e) {
                throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE, e.getMessage());
            }
        }

        public  void getFileExtension(File filePath){
            String fileName = filePath.getName();
            String extension = null;
            if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
                extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            }
            if (!(extension.equals("csv"))) {
                throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.ENTERED_WRONG_FILE_TYPE,
                        "FILE TYPE IS INCORRECT");
            }
        }
    }






