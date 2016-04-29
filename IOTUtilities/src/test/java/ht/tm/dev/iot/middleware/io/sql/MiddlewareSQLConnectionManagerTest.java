package ht.tm.dev.iot.middleware.io.sql;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import ht.tm.dev.iot.util.sql.MiddlewareSQLConnectionManager;
import ht.tm.dev.iot.util.sql.SQLUtil;

public class MiddlewareSQLConnectionManagerTest {

	@Test
	public void testGetConnectionToDB() {
		try {
			SQLUtil.loadDBDriver();
		} catch (ClassNotFoundException e) {
			fail();
		}
		Connection conn = null;
		try {
			conn = MiddlewareSQLConnectionManager.getConnectionToDB();
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		
		if(conn == null){
			fail();
		} else {
			MiddlewareSQLConnectionManager.closeConnection(conn);
		}
		
	}

}
