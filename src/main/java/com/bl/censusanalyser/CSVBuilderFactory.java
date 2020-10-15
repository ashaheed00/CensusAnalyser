package com.bl.censusanalyser;

public class CSVBuilderFactory {
	public static <E> OpenCsvBuilder<E> createCSVBuilder() {
		return new OpenCsvBuilder<E>();
	}

}
