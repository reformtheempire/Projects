package ht.tm.dev.iot.sql;

public class SQLUtil {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "DB_URL";
	
	public static final String USER = "USER";
	public static final String PASS = "PASS";
	
	public static void loadDBDriver() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	public static int generateRandomNumber(){
		return (int) ((int) Math.random() * Math.random());
	}
	
}
