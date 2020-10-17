package com.commonscsv.builder;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CommonsCsvBuilder<E> implements ICSVBuilder {
	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			List<Field> fields = Arrays.asList(csvClass.getFields());
			List<String> headers = fields.stream().map(f -> f.getName().toUpperCase()).collect(Collectors.toList());
			CSVParser csvparser = getCsvParser(reader, csvClass);
			boolean isRightHeader = csvparser.getHeaderNames().stream().map(h -> h.toUpperCase())
					.collect(Collectors.toList()).equals(headers);
			if (!isRightHeader)
				throw new CSVException(CSVException.ExceptionType.WRONG_CSV_HEADER);
			return (Iterator<E>) csvparser.iterator();
		} catch (IllegalStateException e) {
			throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	public <E> boolean checkDelimiter(Reader reader, Class<E> csvClass) throws IOException, CSVException {
		CSVParser csvParser = getCsvParser(reader, csvClass);
		return csvParser.getRecords().stream().anyMatch(i -> i.size() < csvClass.getFields().length);
	}

	private <E> CSVParser getCsvParser(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			CSVFormat csvFormat = CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase()
					.withAllowMissingColumnNames(false);
			CSVParser csvparser = new CSVParser(reader, csvFormat);
			return csvparser;
		} catch (IllegalStateException | IOException e) {
			throw new CSVException(e.getMessage(), CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

}
