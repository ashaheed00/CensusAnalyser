package com.bl.censusanalyser;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCode {
	@CsvBindByName(column = "State")
	private String state;
	@CsvBindByName(column = "State Code")
	private String stateCode;

	@Override
	public String toString() {
		return "CSVStateCode [state=" + state + ", stateCode=" + stateCode + "]";
	}
}
