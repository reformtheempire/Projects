//Import needed libraries

#include <OneWire.h>
#include <DallasTemperature.h>

//Declare Constants
#define ONE_WIRE_BUS 7 /*-(Connect to Pin 7 )-*/

// Declare objects
// Set up a oneWire instance to communicate with any OneWire device
OneWire ourWire(ONE_WIRE_BUS);

// Tell Dallas Temperature Library to use oneWire Library
DallasTemperature sensors(&ourWire);

// Declare Variables


void setup()
{
  // Activate Serial Port to see results.
  delay(1000);
  Serial.begin(9600);
  Serial.println("IOT Thermometer Test Application");
  Serial.println("Temperature Sensor DS18B20");
  delay(1000);

  // activate Dallas Temperature sensor library.
  sensors.begin();
}


void printCurrentTemperaturesToSerial() {
  Serial.print("Requesting temperature...");
  // Send the command to get temperatures
  sensors.requestTemperatures();
  Serial.println("DONE");

  Serial.print("Device 1 (index 0) = ");
  Serial.print(sensors.getTempCByIndex(0));
  Serial.println(" Degrees C");
  Serial.print("Device 1 (index 0) = ");
  Serial.print(sensors.getTempFByIndex(0));
  Serial.println(" Degrees F");
}

void loop()
{
  Serial.println();

  printCurrentTemperaturesToSerial();
  delay(1000);

}

// fin.

