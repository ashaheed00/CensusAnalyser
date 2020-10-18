package com.opencsv.builder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder<E> implements ICSVBuilder {
	@Override
	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			return getCsvBean(reader, csvClass).iterator();
		} catch (IllegalStateException e) {
			throw new CSVException("Wrong File type", CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}

	@Override
	public <E> List<E> getCsvFileList(Reader reader, Class<E> csvClass) throws CSVException {
		return getCsvBean(reader, csvClass).parse();
	}

	private <E> CsvToBean<E> getCsvBean(Reader reader, Class<E> csvClass) throws CSVException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true);
			return csvToBeanBuilder.build();
		} catch (IllegalStateException e) {
			throw new CSVException("Wrong File type", CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}
