package ht.tm.dev.iot.middleware.io.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ht.tm.dev.iot.classes.temperature.TemperatureDTO;
import ht.tm.dev.iot.util.TemperatureSQLUtil;
import ht.tm.dev.iot.util.TemperatureUtil;

@RestController
public class MiddlewareServerTemperatureRestController {

	@RequestMapping("/sendTemperature")
	public boolean sendTemperature(@RequestParam(required = true, value = "temp", defaultValue = "") String temp, @RequestParam(required = true, value = "deviceID", defaultValue = "") String deviceID, @RequestParam(required = true, value = "user", defaultValue = "") String user) {
		TemperatureDTO dto = null;
		 if (deviceID == null || temp == null || user == null || deviceID.equals("") || temp.equals("") || user.equals("")) {
		 return false;
		 }

		try {
			dto = TemperatureUtil.generateNewTemperatureDTO(Double.parseDouble(temp), Integer.parseInt(deviceID), Integer.parseInt(deviceID));
			TemperatureSQLUtil.insertNewTemperatureToDatabase(dto);
			return true;
		} finally {
			if(dto != null){
				System.out.println(dto.toString());				
			}
		}

	}

}
