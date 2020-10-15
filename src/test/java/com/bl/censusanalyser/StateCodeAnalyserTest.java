package com.bl.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class StateCodeAnalyserTest {
	private static final String STATE_CENSUS_CSV_FILE_PATH = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/StateCode.csv";
	private static final String WRONG_CSV_FILE_PATH = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/State.csv";
	private static final String WRONG_FILE_TYPE = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/try.txt";
	private static final String WRONG_CSV_HEADER = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/StateCodeWrongHeader.csv";
	private static final String WRONG_CSV_DELIMITER = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/StateCodeWrongDelimiter.csv";
	StateCensusAnalyser stateCensusAnalyser;

	@Before
	public void initializer() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenStateCensusCSVFile_ShouldReturnNumberOfRecords() throws CensusAnalyserException {
		int noOfEntries = stateCensusAnalyser.loadStateCodeData(STATE_CENSUS_CSV_FILE_PATH);
		assertEquals(37, noOfEntries);
	}

	@Test
	public void givenStateCensusCSVFile_WhenPathIncorrect_ShouldThrowException() {
		try {
			stateCensusAnalyser.loadStateCodeData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.WRONG_CSV_FILE, e.getExceptionType());
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenFileTypeIncorrect_ShouldThrowException() {
		try {
			stateCensusAnalyser.loadStateCodeData(WRONG_FILE_TYPE);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, e.getExceptionType());
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenIncorrectDelimiter_ShouldThrowException() {
		try {
			stateCensusAnalyser.loadStateCodeData(WRONG_CSV_DELIMITER);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.WRONG_DELIMITER, e.getExceptionType());
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenIncorrectHeader_ShouldThrowException() {
		try {
			stateCensusAnalyser.loadStateCodeData(WRONG_CSV_HEADER);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER, e.getExceptionType());
		}
	}
}
