package com.bl.censusanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class StateCode {

    @CsvBindByName(column = "SrNo")
    public int srNo;

    @CsvBindByName(column = "State Name")
    public String stateName;

    @CsvBindByName(column = "TIN")
    public int tin;

    @CsvBindByName(column = "StateCode")
    public String stateCode;

   @Override
    public String toString(){
       return "StateCode{"+
               "SrNo='" + srNo + '\'' +
               ",StateName=" + stateName +
               ",TIN=" + tin +
               ", StateCode=" + stateCode +
               '}';
   }
   }
