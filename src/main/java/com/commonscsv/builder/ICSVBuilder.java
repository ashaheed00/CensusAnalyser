package com.commonscsv.builder;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvCLass) throws CSVException;

	public <E> boolean checkDelimiter(Reader reader, Class<E> class1) throws IOException, CSVException;
}
