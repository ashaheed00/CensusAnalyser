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
			List<CSVStateCensus> stateCensusList = CSVBuilderFactory.createCSVBuilder().getCsvFileList(reader,
					CSVStateCensus.class);
			return stateCensusList.size();
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
			List<CSVStateCode> stateCodeList = CSVBuilderFactory.createCSVBuilder().getCsvFileList(reader,
					CSVStateCode.class);
			return stateCodeList.size();
		} catch (IOException e) {
			throw new CSVException("Incorrect csv file path", CSVException.ExceptionType.WRONG_CSV_FILE);
		} catch (RuntimeException e) {
			if (e.getCause().getClass().equals(CsvRequiredFieldEmptyException.class))
				throw new CSVException("Invalid CSV header", CSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
			else
				return 0;
		}
	}
}
