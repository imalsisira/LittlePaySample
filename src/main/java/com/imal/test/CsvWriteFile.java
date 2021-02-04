package com.imal.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriteFile {
	//Delimiter used in CSV file
		private static final String COMMA_DELIMITER = ",";
		private static final String NEW_LINE_SEPARATOR = "\n";
		//CSV file header
		private static final String FILE_HEADER = "Started,Finished,DurationSecs,FromStopId,ToStopId,ChargeAmount,CompanyId,BusID,PAN,Status";
		public static void writeCsvFile(String fileName,List<OutputData> trips ) {
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(fileName);
				//Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());
				//Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
				//Write a new trip object list to the CSV file
				for (OutputData trip : trips) {
					fileWriter.append(trip.getStarted());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(trip.getFinished());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(trip.getDurationSecs()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(trip.getFromStopId());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(trip.getToStopId());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(trip.getChargeAmount()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(trip.getCompanyId());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(trip.getBusID());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(trip.getPan());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(trip.getStatus());
					fileWriter.append(NEW_LINE_SEPARATOR);
				}
				System.out.println("CSV file was created successfully !!!");
				
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
				}
			}
		}
		public static void writeCsvFile(List<OutputData> outputList, Object trips) {
			// TODO Auto-generated method stub
			
		}
}
