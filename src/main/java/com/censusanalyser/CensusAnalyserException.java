package com.censusanalyser;

public class CensusAnalyserException extends Exception
{
    public enum ExceptionType
    {
        ENTERED_WRONG_FILE_NAME, ENTERED_WRONG_FILE_TYPE,
        INCORRECT_DELIMITER, INCORRECT_DELIMITER_OR_HEADER;
    }
    public ExceptionType type;

    // Constructor
    public CensusAnalyserException(ExceptionType type, String message)
    {
        super(message);
        this.type = type;
    }
}

