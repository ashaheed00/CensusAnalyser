package com.bl.censusanalyser;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

import com.google.gson.Gson;
import com.opencsv.builder.CSVBuilderFactory;
import com.opencsv.builder.CSVException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class StateCensusAnalyser {
	private List<CSVStateCensus> stateCensusList;
	private List<CSVStateCode> stateCodeList;

	public StateCensusAnalyser() {

	}

	public int loadStatesCSVData(String csvFilePath) throws CSVException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new CSVException("Wrong File type", CSVException.ExceptionType.WRONG_FILE_TYPE);
			}
			stateCensusList = CSVBuilderFactory.createCSVBuilder().getCsvFileList(reader, CSVStateCensus.class);
			return stateCensusList.size();
		} catch (IOException e) {
			throw new CSVException("Incorrect csv file path", CSVException.ExceptionType.WRONG_CSV_FILE);
		} catch (RuntimeException e) {
			throw new CSVException(e.getCause().getMessage(), CSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	public int loadStateCodeData(String csvFilePath) throws CSVException {
		String[] file = csvFilePath.split("[.]");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
			if (!file[1].equals("csv")) {
				throw new CSVException("Wrong File type", CSVException.ExceptionType.WRONG_FILE_TYPE);
			}
			stateCodeList = CSVBuilderFactory.createCSVBuilder().getCsvFileList(reader, CSVStateCode.class);
			return stateCodeList.size();
		} catch (IOException e) {
			throw new CSVException("Incorrect csv file path", CSVException.ExceptionType.WRONG_CSV_FILE);
		} catch (RuntimeException e) {
			throw new CSVException(e.getCause().getMessage(), CSVException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
		}
	}

	public String sortCensusDataByState(String csvFilePath) throws CSVException {
		loadStatesCSVData(csvFilePath);
		if (stateCensusList == null || stateCensusList.size() == 0)
			throw new CSVException("No Census data found", CSVException.ExceptionType.NO_CENSUS_DATA);
		Collections.sort(stateCensusList, Comparator.comparing(census -> census.state));
		return new Gson().toJson(stateCensusList);
	}

	public String sortStateCodeDataByStateCode(String csvFilePath) throws CSVException {
		loadStateCodeData(csvFilePath);
		if (stateCodeList == null || stateCodeList.size() == 0)
			throw new CSVException("No Census data found", CSVException.ExceptionType.NO_CENSUS_DATA);
		Collections.sort(stateCodeList, Comparator.comparing(census -> census.stateCode));
		return new Gson().toJson(stateCodeList);
	}

	public String sortCensusDataByPopulation(String csvFilePath) throws CSVException {
		loadStatesCSVData(csvFilePath);
		if (stateCensusList == null || stateCensusList.size() == 0)
			throw new CSVException("No Census data found", CSVException.ExceptionType.NO_CENSUS_DATA);
		Collections.sort(stateCensusList, Comparator.comparing(census -> census.population));
		Collections.reverse(stateCensusList);
		return new Gson().toJson(stateCensusList);
	}

	public String sortCensusDataByPopulationDensity(String csvFilePath) throws CSVException {
		loadStatesCSVData(csvFilePath);
		if (stateCensusList == null || stateCensusList.size() == 0)
			throw new CSVException("No Census data found", CSVException.ExceptionType.NO_CENSUS_DATA);
		Collections.sort(stateCensusList, Comparator.comparing(census -> census.densityPerSqKm));
		Collections.reverse(stateCensusList);
		return new Gson().toJson(stateCensusList);
	}
}
