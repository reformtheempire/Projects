package ht.tm.dev.iot.middleware;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ht.tm.dev.iot.sql.SQLUtil;



@SpringBootApplication
public class MiddlewareServer {

	
	public static final Properties props = new Properties();

	public static void main(String[] args) throws ClassNotFoundException {
		loadProps();
		SQLUtil.loadDBDriver();
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
