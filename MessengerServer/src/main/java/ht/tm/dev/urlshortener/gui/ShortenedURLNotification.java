package ht.tm.dev.urlshortener.gui;

import java.io.File;

import com.vaadin.server.FileResource;
import com.vaadin.ui.Audio;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;

public class ShortenedURLNotification extends Notification {

	/**
	 * le serialVersionUID
	 */
	private static final long serialVersionUID = 3396123561856891438L;

	public ShortenedURLNotification(String caption) {
		super(caption);
		setDelayMsec(DELAY_FOREVER);
	}

	public ShortenedURLNotification(String caption, boolean success, Layout layout) {
		super(caption);
		setDelayMsec(DELAY_FOREVER);
		Audio a = new Audio();
		if (success) {
			a.setSource(new FileResource(new File("src/main/resources/sounds/Ding.wav")));
		} else {
			a.setSource(new FileResource(new File("src/main/resources/sounds/Derp.mp3")));
			a.setAutoplay(true);
			a.setAltText("DERP");
		}
		layout.addComponent(a);
		a.setShowControls(false);
		a.play();
	}

}
