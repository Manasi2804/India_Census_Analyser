package com.bl.censusanalyser.exception;

public class CSVBuilderException extends RuntimeException {

    public enum ExceptionType {
        INCORRECT_DELIMITER_OR_HEADER, ENTERED_WRONG_FILE_TYPE, INCORRECT_DELIMITER,
        UNABLE_TO_PARSE, ENTERED_WRONG_FILE_NAME, INCORRECT_FILE, NO_CENSUS_DATA, FILE_NOT_FOUND
    }
    public ExceptionType type;
    // Constructor
    public CSVBuilderException(String message,ExceptionType type)
    {
        super(message);
        this.type = type;
    }
}