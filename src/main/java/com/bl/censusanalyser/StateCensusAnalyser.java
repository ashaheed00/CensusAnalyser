package com.bl.censusanalyser;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import com.commonscsv.builder.CSVBuilderFactory;
import com.commonscsv.builder.CSVException;
import com.commonscsv.builder.ICSVBuilder;
//import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
//import com.opencsv.builder.CSVBuilderFactory;
//import com.opencsv.builder.CSVException;
//import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class StateCensusAnalyser {

	public StateCensusAnalyser() {

	}

	public int loadStatesCSVData(String csvFilePath) throws CSVException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new CSVException("Wrong File type", CSVException.ExceptionType.WRONG_FILE_TYPE);
			}
			checkDelimiter(csvFilePath, CSVStateCensus.class);
			Iterator<CSVStateCensus> stateCensusIterator = CSVBuilderFactory.createCSVBuilder()
					.getCsvFileIterator(reader, CSVStateCensus.class);
			return getCount(stateCensusIterator);
		} catch (IOException e) {
			throw new CSVException("Incorrect csv file path", CSVException.ExceptionType.WRONG_CSV_FILE);

		} catch (RuntimeException e) {
			throw new CSVException("Invalid CSV header", CSVException.ExceptionType.WRONG_CSV_HEADER);
		}
	}

	public int loadStateCodeData(String csvFilePath) throws CSVException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new CSVException("Wrong File type", CSVException.ExceptionType.WRONG_FILE_TYPE);
			}
			checkDelimiter(csvFilePath, CSVStateCode.class);
			Iterator<CSVStateCode> stateCodeIterator = CSVBuilderFactory.createCSVBuilder().getCsvFileIterator(reader,
					CSVStateCode.class);
			return getCount(stateCodeIterator);
		} catch (IOException e) {
			throw new CSVException("Incorrect csv file path", CSVException.ExceptionType.WRONG_CSV_FILE);
		} catch (RuntimeException e) {
			throw new CSVException("Invalid CSV header", CSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> iterable = () -> iterator;
		return (int) StreamSupport.stream(iterable.spliterator(), false).count();
	}

	private <E> void checkDelimiter(String csvFilePath, Class<E> csvClass) throws CSVException {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			if (csvBuilder.checkDelimiter(reader, csvClass))
				throw new CSVException("Invalid CSV Delimiter", CSVException.ExceptionType.WRONG_CSV_DELIMITER);
		} catch (IOException e) {
		}
	}

}
