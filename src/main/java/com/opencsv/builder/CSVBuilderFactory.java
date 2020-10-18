package com.opencsv.builder;

public class CSVBuilderFactory {
	public static <E> OpenCsvBuilder<E> createCSVBuilder() {
		return new OpenCsvBuilder<E>();
	}

}
