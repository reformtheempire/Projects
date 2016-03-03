package ht.tm.dev.urlshortener.gui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

@SpringUI
@Theme("valo")
@Title("No Frills URL Shortening")
public class VaadinUI extends UI {
	@Override
	protected void init(VaadinRequest vaadinRequest) {
		if (Page.getCurrent().getUriFragment() == null || Page.getCurrent().getUriFragment().equals("")) {
			HomePagePanel hp = new HomePagePanel();
			hp.setSizeFull();
			setContent(hp);
		} else if (Page.getCurrent().getUriFragment().equals("no-exist")) {
			ErrorPagePanel ep = new ErrorPagePanel();
			ep.setSizeFull();
			setContent(ep);
		} else {
			
		}
	}
}