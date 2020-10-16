package com.opencsv.builder;

public class CSVBuilderFactory {
	public static <E> ICSVBuilder createCSVBuilder() {
		return new OpenCsvBuilder<E>();
	}
}