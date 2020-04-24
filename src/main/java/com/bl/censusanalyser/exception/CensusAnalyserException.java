package com.bl.censusanalyser.exception;

public class CensusAnalyserException extends RuntimeException
{
    public enum ExceptionType
    {
        INCORRECT_DELIMITER_OR_HEADER, ENTERED_WRONG_FILE_NAME,
        ENTERED_WRONG_FILE_TYPE, UNABLE_TO_PARSE, INCORRECT_DELIMITER;
    }
    public ExceptionType type;
    //constructor
    public CensusAnalyserException(ExceptionType type,String message)
    {
        super(message);
        this.type = type;
    }
}