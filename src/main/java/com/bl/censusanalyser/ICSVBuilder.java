package com.bl.censusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder {
	public <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvCLass) throws CensusAnalyserException;
}
