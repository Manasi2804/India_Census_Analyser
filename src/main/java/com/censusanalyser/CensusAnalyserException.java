package com.censusanalyser;

public class CensusAnalyserException extends Exception{
    public enum ExceptionType
    {
        ENTERED_WRONG_FILE_NAME;
    }

    public ExceptionType type;

    // Constructor
    public CensusAnalyserException(ExceptionType type, String message)
    {
        super(message);
        this.type = type;
    }
}

