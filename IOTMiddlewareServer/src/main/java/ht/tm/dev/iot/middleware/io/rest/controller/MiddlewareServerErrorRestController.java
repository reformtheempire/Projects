package ht.tm.dev.iot.middleware.io.rest.controller;

import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ht.tm.dev.iot.classes.temperature.TemperatureDTO;

@RestController
public class MiddlewareServerErrorRestController {

	@RequestMapping("/logError")
	public boolean deviceLogin(@RequestParam(required = true, value = "userID", defaultValue = "") String userID, @RequestParam(required = true, value = "deviceID", defaultValue = "") String deviceID, @RequestParam(required = true, value = "error", defaultValue = "") String error) {

		if (deviceID == null || userID == null || error == null || deviceID.equals("") || userID.equals("") || error.equals("")) {
			return false;
		}

		// TODO: Add Database link here.
		System.out.println("User: " + userID + " on device: " + deviceID + " has error: " + error);

		return true;

	}

}
