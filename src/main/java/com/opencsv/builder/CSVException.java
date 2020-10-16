package com.opencsv.builder;

public class CSVException extends Exception {

	public enum ExceptionType {
		WRONG_CSV_FILE, WRONG_FILE_TYPE, UNABLE_TO_PARSE, CSV_FILE_INTERNAL_ISSUES
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
