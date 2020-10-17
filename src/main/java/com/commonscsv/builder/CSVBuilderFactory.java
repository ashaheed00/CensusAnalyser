package com.commonscsv.builder;

public class CSVBuilderFactory {
	public static <E> ICSVBuilder createCSVBuilder() {
		return new CommonsCsvBuilder<E>();
	}
}
