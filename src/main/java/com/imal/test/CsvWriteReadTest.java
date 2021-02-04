package com.imal.test;

public class CsvWriteReadTest {
	public static void main(String[] args) {
		String fileName = System.getProperty("user.home")+"/trip.csv";
		CsvReadFile.readCsvFile(fileName);
	}
}
