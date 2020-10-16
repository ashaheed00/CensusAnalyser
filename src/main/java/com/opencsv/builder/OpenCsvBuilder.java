package com.opencsv.builder;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCsvBuilder<E> implements ICSVBuilder {
	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvCLass) throws CSVException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(csvCLass).withIgnoreLeadingWhiteSpace(true);
			CsvToBean<E> csvToBean = csvToBeanBuilder.build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new CSVException("Wrong File type", CSVException.ExceptionType.UNABLE_TO_PARSE);
		}
	}
}
