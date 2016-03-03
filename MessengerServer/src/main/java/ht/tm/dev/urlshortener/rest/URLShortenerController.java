package ht.tm.dev.urlshortener.rest;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.DomainValidator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.vaadin.ui.TextArea;

import ht.tm.dev.urlshortener.WHOISApplication;
import ht.tm.dev.urlshortener.dto.WhoisServer;
import ht.tm.dev.urlshortener.util.WhoisUtil;

@RestController
public class URLShortenerController {

	private final AtomicLong createCount = new AtomicLong();
	private final AtomicLong getCount = new AtomicLong();

	@RequestMapping("/send")
	public boolean get(@RequestParam(required = true, value = "message", defaultValue = "") String message,
			@RequestParam(required = true, value = "userauth", defaultValue = "") String userauth) {
		
		
		
		System.out.println("USER AUTH: " + userauth + ": Message: " + message);
		
		WHOISApplication.messages = WHOISApplication.messages + "\n " + userauth + ": " + message;
		
		return true;
	}

	@RequestMapping("/getAll")
	public String getAll(@RequestParam(required = true, value = "userauth", defaultValue = "") String userauth) {
		
		System.out.println(userauth + " IS GETTING FULL CHAT");
		
		return WHOISApplication.messages;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/*")
	public ModelAndView redirect(HttpServletRequest request) {
		if (request.getRequestURI() == null || request.getRequestURI().equals("")) {
			return new ModelAndView("redirect:/#no-exist");
		} else {
			return new ModelAndView("redirect:/#" + request.getRequestURI());
		}
	}

}
