package com.bl.censusanalyser;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	public StateCensusAnalyser() {

	}

	public int loadStatesCSVData(String csvFilePath) throws CensusAnalyserException {
		int noOfStates = 0;

		String[] file = csvFilePath.split("[.]");

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new CensusAnalyserException(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
			}
			String header = reader.readLine();
			String[] columnsForGivenDelimeter = header.split(",");
			if (columnsForGivenDelimeter.length < 4) {
				throw new CensusAnalyserException("Invalid delimiter",
						CensusAnalyserException.ExceptionType.WRONG_DELIMITER);
			}
			boolean isRightHeaders = columnsForGivenDelimeter[0].equals("State")
					&& columnsForGivenDelimeter[1].equals("Population")
					&& columnsForGivenDelimeter[2].equals("AreaInSqKm")
					&& columnsForGivenDelimeter[3].equals("DensityPerSqKm");
			if (!isRightHeaders) {
				throw new CensusAnalyserException("Invalid CSV header",
						CensusAnalyserException.ExceptionType.WRONG_HEADER);
			}

			CsvToBeanBuilder<CSVStateCensus> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
			csvToBeanBuilder.withType(CSVStateCensus.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<CSVStateCensus> csvToBean = csvToBeanBuilder.build();
			Iterator<CSVStateCensus> censusCSVIterator = csvToBean.iterator();
			Iterable<CSVStateCensus> iterable = () -> censusCSVIterator;
			noOfStates = (int) StreamSupport.stream(iterable.spliterator(), false).count();
			return noOfStates;

		} catch (IOException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_CSV_FILE);
		} catch (IllegalStateException e) {
			throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE);
		}
	}
}
