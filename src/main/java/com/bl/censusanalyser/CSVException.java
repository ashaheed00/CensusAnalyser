package com.bl.censusanalyser;

public class CSVException extends Exception {

	enum ExceptionType {
		WRONG_CSV_FILE, WRONG_FILE_TYPE, WRONG_DELIMITER, CSV_FILE_INTERNAL_ISSUES
	}

	private ExceptionType exceptionType;

	public ExceptionType getExceptionType() {
		return exceptionType;
	}

	public CSVException(ExceptionType exception) {
		this.exceptionType = exception;
	}

	public CSVException(String message, ExceptionType exception) {
		this.exceptionType = exception;
	}

}
