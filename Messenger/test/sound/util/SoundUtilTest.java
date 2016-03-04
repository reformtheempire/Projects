package sound.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class SoundUtilTest {

	@Test
	public void testPlaySound() {
		SoundUtil.playSound("trumpets1.mp3");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
