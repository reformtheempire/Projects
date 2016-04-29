package ht.tm.dev.iot.classes.temperature;

import java.sql.Date;
import java.sql.Timestamp;

public class TemperatureDTO {
	
	public static final String INSERT = "insert into TemperatureData(temperature, deviceID, userID, createdDate) values (?,?,?,?);";
	public static final String CHECK_FOR_EXISTING_TEMPERATURE = "select count(*) from TemperatureData where temperatureID = ?;";
	
	private final double temperature;
	private final int deviceID;
	private final int userID;
	private final Date createdDate;
	private final Timestamp createdTime;

	/**
	 * Create a fully custom temperature object.
	 * 
	 * @param temperature
	 * @param temperatureID
	 * @param deviceID
	 * @param userID
	 * @param createdDate
	 * @param createdTime
	 */
	public TemperatureDTO(double temperature, int deviceID, int userID, Date createdDate, Timestamp createdTime) {
		this.temperature = temperature;
		this.deviceID = deviceID;
		this.userID = userID;
		this.createdDate = createdDate;
		this.createdTime = createdTime;
	}

	public double getTemperature() {
		return temperature;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public int getUserID() {
		return userID;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}
	@Override
	public String toString() {
		return "TemperatureDTO [temp=" + temperature + ", deviceID=" + deviceID + ", userID=" + userID + ", createdDate=" + createdDate + ", createdTime=" + createdTime + "]";
	}

}
