package ht.tm.dev.iot.middleware.io.rest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ht.tm.dev.iot.classes.temperature.TemperatureDTO;

@RestController
public class MiddlewareServerLoginRestController {
	
	@RequestMapping("/deviceLogin")
	public boolean deviceLogin(@RequestParam(required = true, value = "userID", defaultValue = "") String userID, @RequestParam(required = true, value = "PIN", defaultValue = "") String pin, @RequestParam(required = true, value = "deviceID", defaultValue = "") String deviceID) {

		if (deviceID == null || userID == null || pin == null || deviceID.equals("") || userID.equals("") || pin.equals("")) {
			return false;
		}

		// TODO: Add Database link here.
		System.out.println("User: " + userID + " is logging in on device: " + deviceID + " with PIN: " + pin);
		
		return true;

	}

}
