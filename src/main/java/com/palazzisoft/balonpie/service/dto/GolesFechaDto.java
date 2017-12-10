package com.palazzisoft.balonpie.service.dto;

public class GolesFechaDto {

	private int golesAchieved;
	private int golesReceived;

	public GolesFechaDto(int golesAchieved, int golesReceived) {
		this.golesAchieved = golesAchieved;
		this.golesReceived = golesReceived;
	}

	public int getGolesAchieved() {
		return golesAchieved;
	}

	public void setGolesAchieved(int golesAchieved) {
		this.golesAchieved = golesAchieved;
	}

	public int getGolesReceived() {
		return golesReceived;
	}

	public void setGolesReceived(int golesReceived) {
		this.golesReceived = golesReceived;
	}

}
