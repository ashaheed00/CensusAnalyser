package com.commonscsv.builder;

public class CSVException extends Exception {

	public enum ExceptionType {
		WRONG_CSV_FILE, WRONG_FILE_TYPE, UNABLE_TO_PARSE, CSV_FILE_INTERNAL_ISSUES, WRONG_CSV_HEADER,
		WRONG_CSV_DELIMITER
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
