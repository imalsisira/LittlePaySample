package com.imal.test;

public class Trips {
	private long id;
	private String dateTime;
	private String tapType;
	private String busStopId;
	private String companyId;
	private String busId;
	private int pan;
	public Trips(long id, String dateTime, String tapType, String busStopId, String companyId, String busId,
			int pan) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.dateTime = dateTime;
		this.tapType = tapType;
		this.busStopId = busStopId;
		this.companyId = companyId;
		this.busId = busId;
		this.pan = pan;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getTapType() {
		return tapType;
	}
	public void setTapType(String tapType) {
		this.tapType = tapType;
	}
	public String getBusStopId() {
		return busStopId;
	}
	public void setBusStopId(String busStopId) {
		this.busStopId = busStopId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public int getPan() {
		return pan;
	}
	public void setPan(int pan) {
		this.pan = pan;
	}
	
	@Override
	public String toString() {
		return "Trips [id=" + id + 
				", dateTime=" + dateTime + 
				", tapType=" + tapType + 
				", busStopId=" + busStopId +
				", companyId=" + companyId +
				", busId=" + busId +
				", pan="+ pan + "]";
	}
	
	
}
