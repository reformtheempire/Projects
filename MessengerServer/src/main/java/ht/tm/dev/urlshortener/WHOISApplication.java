package ht.tm.dev.urlshortener;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WHOISApplication {

	public static String messages = "";
	
	public static final Properties props = new Properties();

	public static void main(String[] args) {
		loadProps();
		SpringApplication.run(WHOISApplication.class, args);
	}
	
	public static void loadProps(){
		try {
			props.load(new FileInputStream("src/main/resources/application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
