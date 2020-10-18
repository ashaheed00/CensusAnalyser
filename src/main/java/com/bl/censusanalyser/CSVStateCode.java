package com.bl.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {

	@CsvBindByName(column = "State", required = true)
	String state;

	@CsvBindByName(column = "State Code", required = true)
	String stateCode;

	@Override
	public String toString() {
		return "CSVStateCode [state=" + state + ", stateCode=" + stateCode + "]";
	}
}
