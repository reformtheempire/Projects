package ht.tm.dev.iot.util.classes.temperature;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import ht.tm.dev.iot.util.classes.temperature.TemperatureSQLUtil;
import ht.tm.dev.iot.util.classes.temperature.TemperatureUtil;
import ht.tm.dev.iot.util.sql.SQLUtil;

public class TemperatureSQLUtilTest {

	@Test
	public void testIsIDInUse() {
		try {
			SQLUtil.loadDBDriver();
			boolean a = TemperatureSQLUtil.isIDInUse(0);
			boolean b = TemperatureSQLUtil.isIDInUse(1);
			assertTrue(a);
			assertFalse(b);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			fail();
		}
		
	}

	
	@Test
	public void testInsertToDatabase(){
		try {
			SQLUtil.loadDBDriver();
			boolean a = TemperatureSQLUtil.insertNewTemperatureToDatabase(TemperatureUtil.generateNewTemperatureDTO(10.2, 1337, 1337));
			assertTrue(a);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
