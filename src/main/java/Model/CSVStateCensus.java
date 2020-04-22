package Model;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus
{
    @CsvBindByName(column = "State")
    public String State;

    @CsvBindByName(column = "Population")
    public long Population;

    @CsvBindByName(column = "AreaInSqKm")
    public long AreaInSqKm;

    @CsvBindByName(column = "DensityPerSqKm")
    public int DensityPerSqkm;

    public CSVStateCensus()
    {
    }

    public CSVStateCensus(String state, long population, long areaInSqKm, int densityPerSqkm)
    {
        State = state;
        Population = population;
        AreaInSqKm = areaInSqKm;
        DensityPerSqkm = densityPerSqkm;
    }

    public String getState()
    {
        return State;
    }
    public void setState(String state)
    {
        State = state;
    }

    public long getPopulation()
    {
        return Population;
    }

    public void setPopulation(long population)
    {
        Population = population;
    }

    public long getAreaInSqKm() {
        return AreaInSqKm;
    }

    public void setAreaInSqKm(long areaInSqKm) {
        AreaInSqKm = areaInSqKm;
    }

    public int getDensityPerSqkm() {
        return DensityPerSqkm;
    }

    public void setDensityPerSqkm(int densityPerSqkm) {
        DensityPerSqkm = densityPerSqkm;
    }

    @Override
    public String toString()
    {
        return "CSVStateCensus{" +
                "State='" + State + '\'' +
                ", Population=" + Population +
                ", AreaInSqKm=" + AreaInSqKm +
                ", DensityPerSqkm=" + DensityPerSqkm +
                '}';
    }

}
