package com.bl.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;
import com.opencsv.builder.CSVException;

public class StateCodeAnalyserTest {
	private static final String STATE_CENSUS_CSV_FILE_PATH = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/StateCode.csv";
	private static final String WRONG_CSV_FILE_PATH = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/State.csv";
	private static final String WRONG_FILE_TYPE = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/try.txt";
	private static final String WRONG_CSV_HEADER = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/StateCodeWrongHeader.csv";
	private static final String WRONG_CSV_DELIMITER = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/StateCodeWrongDelimiter.csv";
	StateCensusAnalyser stateCensusAnalyser;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void initializer() {
		stateCensusAnalyser = new StateCensusAnalyser();
	}

	@Test
	public void givenStateCensusCSVFile_ShouldReturnNumberOfRecords() throws CSVException {
		int noOfEntries = stateCensusAnalyser.loadStateCodeData(STATE_CENSUS_CSV_FILE_PATH);
		assertEquals(37, noOfEntries);
	}

	@Test
	public void givenStateCensusCSVFile_WhenPathIncorrect_ShouldThrowException() {
		try {
			stateCensusAnalyser.loadStateCodeData(WRONG_CSV_FILE_PATH);
			thrown.expect(CSVException.class);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.WRONG_CSV_FILE, e.getExceptionType());
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenFileTypeIncorrect_ShouldThrowException() {
		try {
			stateCensusAnalyser.loadStateCodeData(WRONG_FILE_TYPE);
			thrown.expect(CSVException.class);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.WRONG_FILE_TYPE, e.getExceptionType());
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenIncorrectDelimiter_ShouldThrowException() {
		try {
			stateCensusAnalyser.loadStateCodeData(WRONG_CSV_DELIMITER);
			thrown.expect(CSVException.class);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.getExceptionType());
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenIncorrectHeader_ShouldThrowException() {
		try {
			stateCensusAnalyser.loadStateCodeData(WRONG_CSV_HEADER);
			thrown.expect(CSVException.class);
		} catch (CSVException e) {
			assertEquals(CSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.getExceptionType());
		}
	}

	@Test
	public void givenStateCensusCSVFile_WhenSortedOnState_ShouldReturnSortedResult() throws CSVException {
		String sortedCensusDataByStateCode = stateCensusAnalyser
				.sortStateCodeDataByStateCode(STATE_CENSUS_CSV_FILE_PATH);
		CSVStateCode[] csvStateCode = new Gson().fromJson(sortedCensusDataByStateCode, CSVStateCode[].class);
		assertEquals("AD", csvStateCode[0].stateCode);
		assertEquals("WB", csvStateCode[csvStateCode.length - 1].stateCode);
	}
}
