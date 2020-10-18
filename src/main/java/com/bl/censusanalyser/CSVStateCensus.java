package com.bl.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {

	@CsvBindByName(column = "State", required = true)
	String state;

	@CsvBindByName(column = "Population", required = true)
	long population;

	@CsvBindByName(column = "AreaInSqKm", required = true)
	long areaInSqKm;

	@CsvBindByName(column = "DensityPerSqKm", required = true)
	int densityPerSqKm;

	@Override
	public String toString() {
		return "IndiaCensusCSV{" + "State='" + state + '\'' + ", Population='" + population + '\'' + ", AreaInSqKm='"
				+ areaInSqKm + '\'' + ", DensityPerSqKm='" + densityPerSqKm + '\'' + '}';
	}
}
