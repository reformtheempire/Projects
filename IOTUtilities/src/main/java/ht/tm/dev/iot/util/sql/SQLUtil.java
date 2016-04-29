package ht.tm.dev.iot.util.sql;

public class SQLUtil {
	public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://31.187.70.22/admin_iot?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	
	public static final String USER = "admin_iot";
	public static final String PASS = "SpringOnions2016";
	
	public static void loadDBDriver() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}
	
	public static int generateRandomNumber(){
		return (int) ((int) Math.random() * Math.random());
	}
	
}
