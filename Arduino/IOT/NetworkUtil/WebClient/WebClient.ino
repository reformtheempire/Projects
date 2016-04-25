/*
  Web client

  This sketch connects to a website (http://www.google.com)
  using an Arduino Wiznet Ethernet shield.

  Circuit:
   Ethernet shield attached to pins 10, 11, 12, 13

  created 18 Dec 2009
  by David A. Mellis
  modified 9 Apr 2012
  by Tom Igoe, based on work by Adrian McEwen

*/


//Import needed libraries

#include <SPI.h>
#include <Ethernet.h>
#include <OneWire.h>
#include <DallasTemperature.h>

//Declare Constants
#define ONE_WIRE_BUS 7 /*-(Connect to Pin 7 )-*/

// Declare objects
// Set up a oneWire instance to communicate with any OneWire device
OneWire ourWire(ONE_WIRE_BUS);

// Tell Dallas Temperature Library to use oneWire Library
DallasTemperature sensors(&ourWire);

// Enter a MAC address for your controller below.
// Newer Ethernet shields have a MAC address printed on a sticker on the shield
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
// if you don't want to use DNS (and reduce your sketch size)
// use the numeric IP instead of the name for the server:
IPAddress server(10,0,0,2);  // numeric IP for Google (no DNS)
//char server[] = "www.google.com";    // name address for Google (using DNS)

// Set the static IP address to use if the DHCP fails to assign
IPAddress ip(10, 0, 0, 23);

// Initialize the Ethernet client library
// with the IP address and port of the server
// that you want to connect to (port 80 is default for HTTP):
EthernetClient client;

String CurrentTemperature;

void OverWriteCurrentTemperaturesToSerial() {
  Serial.print("Requesting temperature...");
  // Send the command to get temperatures
  sensors.requestTemperatures();
  Serial.println("DONE");

  Serial.print("Device 1 (index 0) = ");
  CurrentTemperature = String(sensors.getTempCByIndex(0));
  Serial.println(" Degrees C");
}

void setup() {
  
  // Activate Serial Port to see results.
  delay(1000);
  Serial.begin(9600);
  Serial.println("IOT Thermometer Test Application");
  Serial.println("Temperature Sensor DS18B20");
  delay(1000);

  // activate Dallas Temperature sensor library.
  sensors.begin();
  
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
    // try to congifure using IP address instead of DHCP:
    Ethernet.begin(mac, ip);
  }
  // give the Ethernet shield a second to initialize:
  delay(1000);
  Serial.println("connecting...");

  //Check Current Temperature
  OverWriteCurrentTemperaturesToSerial();
  
  // if you get a connection, report back via serial:
  if (client.connect(server, 25569)) {
    Serial.println("connected");
    // Make a HTTP request:
    client.println("GET /sendTemperature?temp=" + CurrentTemperature + "&deviceID=222");
    client.println("Host: 10.0.0.2:25569");
    client.println("Connection: close");
    client.println();
  } else {
    // if you didn't get a connection to the server:
    Serial.println("connection failed");
  }
}

void loop() {

  // if there are incoming bytes available
  // from the server, read them and print them:
  String response = "";
  if (client.available()) {
    char c = client.read();
   // Serial.print(c);
   response = response + c;
   response.remove(response.length());
  }

  Serial.println(response);
  
  // if the server's disconnected, stop the client:
  if (!client.connected()) {
    Serial.println();
    Serial.println("disconnecting.");
    client.stop();

    // do nothing forevermore:
    while (true);
  }
}

