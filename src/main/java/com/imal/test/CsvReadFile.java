package com.imal.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CsvReadFile {
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
		
	//Trip attributes index
	private static final int TRIP_ID_IDX = 0;
	private static final int TRIP_DATE = 1;
	private static final int TAP_TYPE = 2;
	private static final int BUS_STOP_ID = 3; 
	private static final int COMPANY_ID = 4;
	private static final int BUS_ID = 5;
	private static final int PAN_ID = 6;
	
	public String calcAmount(String stop1,String stop2) {
		if(stop1.equalsIgnoreCase(" Stop1") && stop2.equalsIgnoreCase(" Stop2")) {
			return "$3.25";
		}
		else if(stop1.equalsIgnoreCase(" Stop2") && stop2.equalsIgnoreCase(" Stop3")) {
			return "$5.50";
		}
		else if(stop1.equalsIgnoreCase(" Stop1") && stop2.equalsIgnoreCase(" Stop3")) {
			return "$7.30";
		}
		return null;
	}
	
	public static void readCsvFile(String fileName) {
		BufferedReader fileReader = null;
		CsvReadFile csv = new CsvReadFile();
        try {
        	//Create a new list of trip to be filled by CSV file data 
        	List<Trips> trips = new ArrayList<Trips>();
        	
            String line = "";
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));
            //Read the CSV file header to skip it
            fileReader.readLine();
            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                	//Create a new trip object and fill trip data
					Trips trip = new Trips(Long.parseLong(tokens[TRIP_ID_IDX]), tokens[TRIP_DATE], tokens[TAP_TYPE], tokens[BUS_STOP_ID], tokens[COMPANY_ID], tokens[BUS_ID], Integer.parseInt(tokens[PAN_ID]));
					trips.add(trip);
				}
            }
            Trips tempObject = null;
            
            List<OutputData> outputList = new ArrayList<OutputData>();
            int tempId=0;
            for (Trips trip : trips) {
            	OutputData outputData = new OutputData();
            	if (tempObject==null) {
					tempObject=trip;
				}
				else {
					if (trip.getTapType().equalsIgnoreCase(" OFF") && (trip.getPan()==tempObject.getPan()) && !trip.getBusStopId().equalsIgnoreCase(tempObject.getBusStopId())) {
						System.out.println("completed trip");
						System.out.println(trip.toString());
						outputData.setChargeAmount(csv.calcAmount(tempObject.getBusStopId(), trip.getBusStopId()));
						outputData.setBusID(trip.getBusId());
						outputData.setCompanyId(trip.getCompanyId());
						
						outputData.setStarted(tempObject.getDateTime());
						outputData.setFinished(trip.getDateTime());
						outputData.setPan(String.valueOf(trip.getPan()));
						outputData.setToStopId(trip.getBusStopId());
						try {
							Duration duration = Duration.between(LocalDateTime.parse(tempObject.getDateTime()), LocalDateTime.parse(trip.getDateTime()));
					        outputData.setDurationSecs(duration.getSeconds());
						} catch (Exception e) {
							// TODO: handle exception
						}
						outputData.setStatus("COMPLETED");
					}else if(trip.getTapType().equalsIgnoreCase(" OFF") && (trip.getPan()==tempObject.getPan()) && trip.getBusStopId().equalsIgnoreCase(tempObject.getBusStopId())){
						System.out.println("cancelled trip");
						System.out.println(trip.toString());
						outputData.setChargeAmount("$0.00");
						outputData.setBusID(trip.getBusId());
						outputData.setCompanyId(trip.getCompanyId());
						outputData.setStarted(tempObject.getDateTime());
						outputData.setFinished(trip.getDateTime());
						outputData.setPan(String.valueOf(trip.getPan()));
						outputData.setToStopId(trip.getBusStopId());
						outputData.setStatus("CANCELLED");
					}else if(trip.getTapType().equalsIgnoreCase(" ON") && (trip.getPan()!=tempObject.getPan()) && tempId<trip.getId()) {
						System.out.println("incomplete trip");
						System.out.println(trip.toString());
						outputData.setChargeAmount("$5.50");
						outputData.setBusID(trip.getBusId());
						outputData.setCompanyId(trip.getCompanyId());
						outputData.setStarted(tempObject.getDateTime());
						outputData.setFinished(trip.getDateTime());
						outputData.setPan(String.valueOf(trip.getPan()));
						outputData.setToStopId(trip.getBusStopId());
						outputData.setStatus("INCOMPLETED");
					}
					
				}
				tempObject=trip;
				tempId++;
				outputList.add(outputData);
			}
            String fileName1 = System.getProperty("user.home")+"/output.csv";
            CsvWriteFile.writeCsvFile(fileName1,outputList);
        } 
        catch (Exception e) {
        	System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
            	System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
	}
}
