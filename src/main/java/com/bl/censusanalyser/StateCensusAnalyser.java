package com.bl.censusanalyser;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import com.opencsv.builder.CSVBuilderFactory;
import com.opencsv.builder.CSVException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class StateCensusAnalyser {

	public StateCensusAnalyser() {

	}

	public int loadStatesCSVData(String csvFilePath) throws CSVException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new CSVException("Wrong File type", CSVException.ExceptionType.WRONG_FILE_TYPE);
			}
			Iterator<CSVStateCensus> stateCensusIterator = CSVBuilderFactory.createCSVBuilder()
					.getCsvFileIterator(reader, CSVStateCensus.class);
			return getCount(stateCensusIterator);
		} catch (IOException e) {
			throw new CSVException("Incorrect csv file path", CSVException.ExceptionType.WRONG_CSV_FILE);

		} catch (RuntimeException e) {
			if (e.getCause().getClass().equals(CsvRequiredFieldEmptyException.class))
				throw new CSVException("Invalid CSV header", CSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
			else
				return 0;
		}
	}

	public int loadStateCodeData(String csvFilePath) throws CSVException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new CSVException("Wrong File type", CSVException.ExceptionType.WRONG_FILE_TYPE);
			}
			Iterator<CSVStateCode> stateCodeIterator = CSVBuilderFactory.createCSVBuilder().getCsvFileIterator(reader,
					CSVStateCode.class);
			return getCount(stateCodeIterator);
		} catch (IOException e) {
			throw new CSVException("Incorrect csv file path", CSVException.ExceptionType.WRONG_CSV_FILE);
		} catch (RuntimeException e) {
			if (e.getCause().getClass().equals(CsvRequiredFieldEmptyException.class))
				throw new CSVException("Invalid CSV header", CSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
			else
				return 0;
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> iterable = () -> iterator;
		return (int) StreamSupport.stream(iterable.spliterator(), false).count();
	}

}
