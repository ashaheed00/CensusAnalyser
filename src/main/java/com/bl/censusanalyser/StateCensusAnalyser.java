package com.bl.censusanalyser;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class StateCensusAnalyser {

	public StateCensusAnalyser() {

	}

	public int loadStatesCSVData(String csvFilePath) throws CensusAnalyserException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new CensusAnalyserException("Wrong File type",
						CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
			}
			checkHeaderStateCensus(csvFilePath);
			checkDelimiter(csvFilePath, 4);
			Iterator<CSVStateCensus> stateCensusIterator = CSVBuilderFactory.createCSVBuilder()
					.getCsvFileIterator(reader, CSVStateCensus.class);
			return getCount(stateCensusIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("Incorrect csv file path",
					CensusAnalyserException.ExceptionType.WRONG_CSV_FILE);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
		}
	}

	public int loadStateCodeData(String csvFilePath) throws CensusAnalyserException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new CensusAnalyserException("Wrong File type",
						CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
			}
			checkHeaderStateCode(csvFilePath);
			checkDelimiter(csvFilePath, 2);
			Iterator<CSVStateCode> stateCodeIterator = CSVBuilderFactory.createCSVBuilder().getCsvFileIterator(reader,
					CSVStateCode.class);
			return getCount(stateCodeIterator);
		} catch (IOException e) {
			throw new CensusAnalyserException("Incorrect csv file path",
					CensusAnalyserException.ExceptionType.WRONG_CSV_FILE);
		}
	}

	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> iterable = () -> iterator;
		return (int) StreamSupport.stream(iterable.spliterator(), false).count();
	}

	private void checkHeaderStateCensus(String csvFilePath) throws CensusAnalyserException {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			String[] columnsForGivenHeader = reader.readLine().split(",");
			boolean isRightHeaders = columnsForGivenHeader[0].equals("State")
					&& columnsForGivenHeader[1].equals("Population") && columnsForGivenHeader[2].equals("AreaInSqKm")
					&& columnsForGivenHeader[3].equals("DensityPerSqKm");
			if (!isRightHeaders) {
				throw new CensusAnalyserException("Invalid CSV header",
						CensusAnalyserException.ExceptionType.WRONG_HEADER);
			}
		} catch (IOException | NullPointerException e) {
		}
	}

	private void checkHeaderStateCode(String csvFilePath) throws CensusAnalyserException {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			String[] columnsForGivenHeader = reader.readLine().trim().split(",");
			boolean isRightHeaders = columnsForGivenHeader[0].trim().equals("State")
					&& columnsForGivenHeader[1].trim().equals("State Code");
			if (!isRightHeaders) {
				throw new CensusAnalyserException("Invalid CSV header",
						CensusAnalyserException.ExceptionType.WRONG_HEADER);
			}
		} catch (IOException | NullPointerException e) {
		}
	}

	private void checkDelimiter(String csvFilePath, int noOfColoumns) throws CensusAnalyserException {
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			while (true) {
				String line = reader.readLine();
				String[] columnsForGivenDelimeter = line.split(",");
				if (columnsForGivenDelimeter.length < noOfColoumns) {
					throw new CensusAnalyserException("Invalid delimiter",
							CensusAnalyserException.ExceptionType.WRONG_DELIMITER);
				}
			}
		} catch (IOException | NullPointerException e) {
		}
	}
}
