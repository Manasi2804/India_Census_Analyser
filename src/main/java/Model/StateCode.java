package Model;

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

    public StateCode(){

    }

    public StateCode(int srNo, String stateName, int tin, String stateCode) {
        this.srNo = srNo;
        this.stateName = stateName;
        this.tin = tin;
        this.stateCode = stateCode;
    }

    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getTin() {
        return tin;
    }

    public void setTin(int tin) {
        this.tin = tin;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
}