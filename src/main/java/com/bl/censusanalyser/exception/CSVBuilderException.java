package com.bl.censusanalyser.exception;

public class CSVBuilderException extends RuntimeException{
    public enum ExceptionType
{
    INCORRECT_DELIMITER_OR_HEADER, ENTERED_WRONG_FILE_TYPE, INCORRECT_DELIMITER,
    NO_CENSUS_DATA, UNABLE_TO_PARSE, ENTERED_WRONG_FILE_NAME

}
    public ExceptionType type;
    // Constructor
    public CSVBuilderException(ExceptionType type, String message)
    {
        super(message);
        this.type = type;
    }
}
