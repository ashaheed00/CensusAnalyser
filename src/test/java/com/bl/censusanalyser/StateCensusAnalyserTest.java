package com.bl.censusanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StateCensusAnalyserTest {
	private static final String STATE_CENSUS_CSV_FILE_PATH = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/StateCensus.csv";
	private static final String WRONG_CSV_FILE_PATH = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/State.csv";
	private static final String WRONG_FILE_TYPE = "C:/Users/user/eclipse-workspace/CensusAnalyser/CSVfiles/try.txt";

	// TC1.1
	@Test
	public void givenStateCensusCSVFile_ShouldReturnNumberOfRecords() throws CensusAnalyserException {
		StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
		int noOfEntries = censusAnalyser.loadStatesCSVData(STATE_CENSUS_CSV_FILE_PATH);
		assertEquals(29, noOfEntries);
	}

	// TC1.2
	@Test
	public void givenStateCensusCSVFile_WhenPathIncorrect_ShouldThrowException() {
		try {
			StateCensusAnalyser censusAnalyzer = new StateCensusAnalyser();
			censusAnalyzer.loadStatesCSVData(WRONG_CSV_FILE_PATH);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.WRONG_CSV_FILE, e.getExceptionType());
		}
	}
	
	// TC1.3
	@Test
	public void givenStateCensusCSVFile_WhenFileTypeIncorrect_ShouldThrowException() {
		try {
			StateCensusAnalyser censusAnalyzer = new StateCensusAnalyser();
			censusAnalyzer.loadStatesCSVData(WRONG_FILE_TYPE);
		} catch (CensusAnalyserException e) {
			assertEquals(CensusAnalyserException.ExceptionType.WRONG_FILE_TYPE, e.getExceptionType());
		}
	}
}
