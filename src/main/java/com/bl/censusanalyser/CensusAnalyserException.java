package com.bl.censusanalyser;

public class CensusAnalyserException extends Exception {

	enum ExceptionType {
		WRONG_CSV_FILE, WRONG_FILE_TYPE, WRONG_DELIMITER, WRONG_HEADER
	}

	private ExceptionType exception;

	public CensusAnalyserException(ExceptionType exception) {
		this.exception = exception;
	}

	public CensusAnalyserException(String message, ExceptionType exception) {
		this.exception = exception;
	}

}
