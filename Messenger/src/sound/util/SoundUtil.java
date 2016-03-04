package sound.util;

public class SoundUtil {

	public static void playSound(String path) {
		SoundJLayer playSound = new SoundJLayer(path);
		playSound.play();
	}
	
}
