package ht.tm.dev.urlshortener.gui;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

import ht.tm.dev.urlshortener.dto.WhoisServer;
import ht.tm.dev.urlshortener.util.WhoisUtil;

public class HomePagePanel extends Panel {

	private TextField urlInputBox;
	private Button button;
	private Label header;
	private GridLayout mainLayout;

	public HomePagePanel() {

		setupPanel();
		setupListeners();

		mainLayout = new GridLayout(10, 15);
		mainLayout.addComponent(header, 3, 0, 6, 0);
		mainLayout.setComponentAlignment(header, Alignment.MIDDLE_CENTER);
		mainLayout.addComponent(urlInputBox, 3, 2, 6, 2);
		mainLayout.addComponent(button, 3, 3, 6, 3);
		mainLayout.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);
		urlInputBox.setSizeFull();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);
		this.setContent(mainLayout);
	}

	private void setupListeners() {

		urlInputBox.addShortcutListener(new ShortcutListener("Enter!", ShortcutAction.KeyCode.ENTER, null) {

			/**
			 * le serialVersionUID
			 */
			private static final long serialVersionUID = -6938287124919607674L;

			@Override
			public void handleAction(Object sender, Object target) {
				if (!urlInputBox.isEmpty()) {
					button.click();
				}

			}

		});

		button.addClickListener(new ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

				DomainValidator dv = DomainValidator.getInstance(false);

				String domain = urlInputBox.getValue();

				if (dv.isValid(domain)) {

					String[] domainSplit = StringUtils.split(domain, ".");

					String suffix = "";

					for (int i = 0; i < domainSplit.length; i++) {

						if (i != 0) {
							suffix = suffix + "." + domainSplit[i];
						}

					}

					System.out.println(suffix);

					// Has something gone wrong?
					mainLayout.removeComponent(urlInputBox);
					mainLayout.removeComponent(button);

					String longURL = urlInputBox.getValue();

					TextArea textArea = new TextArea();
					textArea.setSizeFull();
					mainLayout.addComponent(textArea, 2, 2, 7, 11);

					// GET WHOIS RESPONSE
					try {
						WhoisServer whoisServer = WhoisUtil.getServerByDomain(suffix);
						textArea.setValue(WhoisUtil.getWhoisResponse(whoisServer, domain));

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					Notification.show("INVALID DOMAIN");
				}
			}
		});
	}

	private void setupPanel() {
		header = new Label("No Frills WHOIS.");
		header.setStyleName("h1");

		urlInputBox = new TextField();
		urlInputBox.setDescription("Enter the domain you want to 'Chec'");
		urlInputBox.setInputPrompt("domain.foo");

		button = new Button("chec.it");

	}

}
