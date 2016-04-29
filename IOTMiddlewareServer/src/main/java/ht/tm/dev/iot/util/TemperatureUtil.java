package ht.tm.dev.iot.util;

import java.sql.Date;
import java.sql.Timestamp;

import ht.tm.dev.iot.classes.temperature.TemperatureDTO;

public class TemperatureUtil {

	public static TemperatureDTO generateNewTemperatureDTO(double temperature, int deviceID, int userID){
		// temperature has been acquired.
		// deviceID has been acquired.
		// userID has been acquired.
		Date createdDate = new Date(new java.util.Date().getTime());
		Timestamp createdTime = new Timestamp(new java.util.Date().getTime());
		return new TemperatureDTO(temperature, deviceID, userID, createdDate, createdTime);
	}
	
	
}
