package com.bl.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {

	@CsvBindByName(column = "State", required = true)
	public String state;

	@CsvBindByName(column = "State Code", required = true)
	public String stateCode;

	@Override
	public String toString() {
		return "CSVStateCode [state=" + state + ", stateCode=" + stateCode + "]";
	}
}
