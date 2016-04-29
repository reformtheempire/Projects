//Import needed libraries

#include <SPI.h>
#include <Ethernet.h>
#include <OneWire.h>
#include <DallasTemperature.h>



#define USER_ID 1
#define DEVICE_ID 1
#define PIN 1337
#define MIDDLEWARE_PORT 25569


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
IPAddress server(31,187,70,22); // numeric IP for server (no DNS)
//char server[] = "dash.iot.dev.tm.ht";    // name address for server (using DNS)

// Initialize the Ethernet client library
// with the IP address and port of the server
// that you want to connect to (port 80 is default for HTTP):
EthernetClient client;

// boolean to see if we need to reconnect to the network
boolean ethernetActive = false;

boolean isValidTemperature(double temperatureInC) {

  if (temperatureInC >= 124.00) {
    return false;
  } else if (temperatureInC <= -50.00) {
    return false;
  } else {
    return true;
  }

}

/**
   Used to get the current temperature in celcius.
*/
String getCurrentTemperatureInC() {
  // Send the command to get temperatures
  Serial.println("Requesting temperature...");
  sensors.requestTemperatures();

  Serial.println("DONE");

  Serial.print("Device 1 (index 0) = ");
  float temp = sensors.getTempCByIndex(0);

  if (!isValidTemperature(temp)) {
    delay(10000);
    return getCurrentTemperatureInC();
  }

  Serial.println(String(temp) + " Degrees C");

  return String(temp);
}



boolean sendDataToServer(String temperatureInC) {

  Serial.println("connecting to middleware...");

  // if you get a connection, report back via serial:
  if (client.connect(server, MIDDLEWARE_PORT)) {

    Serial.println("connected.");

    // Make a HTTP request:
    client.println("GET /sendTemperature?temp=" + temperatureInC + "&deviceID=" + DEVICE_ID + "&user=" + USER_ID);
    client.println("Host: 37.187.70.22:25569");
    client.println("Connection: close");
    client.println();

    delay(1000);

    if (client.available()) {

      char c = client.read();
      String res = String(c);

      if (res.equals("t")) {
        client.stop();
        return true;
      } else {
        client.stop();
        return false;
      }
    }

    client.stop();

  } else {
    // if no connection to the server:
    Serial.println("connection failed.");
    reconnectToEthernet();
    sendDataToServer(temperatureInC);
  }
  return false;
}

boolean setupEthernet() {
  // start the Ethernet connection:
  if (Ethernet.begin(mac) == 0) {
    return false;
  }
  // give the Ethernet shield a second to initialize:
  delay(1000);
  return true;
}

void reconnectToEthernet() {
  client.stop();
  ethernetActive = false;
  while (!ethernetActive) {
    ethernetActive = setupEthernet();
    Serial.println("COULD NOT CONNECT TO DHCP SERVER");
    Serial.println("RETRY IN 10 SECONDS.");
    delay(10000);
  }
}

String displayIPAddress(IPAddress address)
{
 return String(address[0]) + "." + 
        String(address[1]) + "." + 
        String(address[2]) + "." + 
        String(address[3]);
}

void setup() {
  // open comms
  Serial.begin(9600);
  Serial.println("*****************************");
  Serial.println("   IOT TEMPERATURE SENSOR");
  Serial.println("*****************************");
  Serial.println("INITIALISING,  PLEASE WAIT...");
  Serial.println("\n\n");
    
  // activate temperature sensor.
  sensors.begin();

  delay(250);

  // connect ethernet. if cannot connect. wait 10 seconds and try again.
  if (!setupEthernet()) {
    reconnectToEthernet();
  }
  delay(250);

  String ip = displayIPAddress(Ethernet.localIP());
  
  Serial.println("LAN IP ADDRESS: " + ip +"\n");

  Serial.println("READY.\n\n");
  // all good. begin.
}

void loop() {

  if (sendDataToServer(getCurrentTemperatureInC())) {
    Serial.println("Sent correctly.");
  } else {
    Serial.println("Invalid response");
  }
  delay(120000);
}

