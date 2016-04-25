package ht.tm.dev.iot.middleware;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MiddlewareServer {

	public static String messages = "";
	
	public static final Properties props = new Properties();

	public static void main(String[] args) {
		loadProps();
		SpringApplication.run(MiddlewareServer.class, args);
	}
	
	public static void loadProps(){
		try {
			props.load(new FileInputStream("resources/application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
