package ht.tm.dev.iot.middleware.io.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ht.tm.dev.iot.classes.temperature.TemperatureDTO;

@RestController
public class MiddlewareServerRestController {

	@RequestMapping("/sendTemperature")
	public boolean sendTemperature(@RequestParam(required = true, value = "temp", defaultValue = "") String temp, @RequestParam(required = true, value = "deviceID", defaultValue = "") String deviceID) {

		if (deviceID == null || temp == null || deviceID.equals("") || temp.equals("")) {
			return false;
		}

		// TODO: Add Database link here.
		System.out.println("Device: " + deviceID + " is at: " + new TemperatureDTO(Double.parseDouble(temp)).toString());

		return true;

	}
	
	

}
