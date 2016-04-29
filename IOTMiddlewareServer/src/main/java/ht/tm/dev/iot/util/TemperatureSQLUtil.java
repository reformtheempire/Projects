package ht.tm.dev.iot.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ht.tm.dev.iot.classes.temperature.TemperatureDTO;
import ht.tm.dev.iot.sql.MiddlewareSQLConnectionManager;

public class TemperatureSQLUtil {

	public static boolean isIDInUse(int id) {
		Connection conn = null;
		try {
			conn = MiddlewareSQLConnectionManager.getConnectionToDB();
			PreparedStatement ps = conn.prepareStatement(TemperatureDTO.CHECK_FOR_EXISTING_TEMPERATURE);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();
			if (rs.getInt("count(*)") > 0) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MiddlewareSQLConnectionManager.closeConnection(conn);
		}
		return false;
	}

	public static boolean insertNewTemperatureToDatabase(TemperatureDTO dto) {
		Connection conn = null;
		try {
			conn = MiddlewareSQLConnectionManager.getConnectionToDB();
			PreparedStatement ps = conn.prepareStatement(TemperatureDTO.INSERT);

			
			ps.setDouble(1, dto.getTemperature());
			ps.setInt(2, dto.getDeviceID());
			ps.setInt(3, dto.getDeviceID());
			ps.setDate(4,dto.getCreatedDate());
			
			int done = ps.executeUpdate();
			if(done == 1){
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			MiddlewareSQLConnectionManager.closeConnection(conn);
		}
		return false;
	}

}
