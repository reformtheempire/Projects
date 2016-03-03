package ht.tm.dev.urlshortener.gui;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class ErrorPagePanel extends Panel {

	private Label header;
	private GridLayout mainLayout;

	public ErrorPagePanel() {

		mainLayout = new GridLayout(10, 15);
		setupPanel();
		mainLayout.addComponent(header, 3, 0, 6, 0);
		mainLayout.setComponentAlignment(header, Alignment.MIDDLE_CENTER);
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);
		this.setContent(mainLayout);
	}

	private void setupPanel() {
		header = new Label();
		header.setStyleName("h1");
		// Has something gone wrong?
		new ShortenedURLNotification("ERROR", false, mainLayout);
		header.setValue("Ahw, Snap!");

		Label urlOut = new Label("There's a problem with the domain you entered...");

		urlOut.setCaptionAsHtml(true);
		urlOut.setStyleName("h2");
		mainLayout.addComponent(urlOut, 3, 2, 6, 2);
		mainLayout.setComponentAlignment(urlOut, Alignment.MIDDLE_CENTER);
	}

}
