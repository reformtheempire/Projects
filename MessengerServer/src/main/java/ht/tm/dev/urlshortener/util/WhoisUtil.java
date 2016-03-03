package ht.tm.dev.urlshortener.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.URL;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.whois.WhoisClient;
import org.json.JSONObject;

import ht.tm.dev.urlshortener.dto.JSONObjectIgnoreDuplicates;
import ht.tm.dev.urlshortener.dto.WhoisServer;

public class WhoisUtil {

	public static WhoisServer getServerByDomain(String domain) throws IOException {

		// connect to the JSON
		// https://raw.githubusercontent.com/weppos/whois/master/data/tld.json

		URL whoisURL = new URL("https://raw.githubusercontent.com/weppos/whois/master/data/tld.json");
		BufferedReader in = new BufferedReader(new InputStreamReader(whoisURL.openStream()));

		String inputLine;
		StringBuilder sb = new StringBuilder();
		while ((inputLine = in.readLine()) != null)
			sb.append(inputLine + "\n");
		in.close();

		JSONObject obj = new JSONObjectIgnoreDuplicates(sb.toString());
		try {
			return new WhoisServer(domain, obj.getJSONObject(domain).getString("host"));
		} catch (Exception e) {

			// try to return the main suffix:
			String[] splitDomain = StringUtils.split(domain, ".");
			for (int i = 0; i < splitDomain.length; i++) {
				if (i != 0) {
					domain = domain + "." + splitDomain[i];
				} else {
					domain = "";
				}

			}
			try {
				return new WhoisServer(domain, obj.getJSONObject(domain).getString("host"));
			} catch (Exception e2) {

			}

			System.err.println("Cannot get domain. return null.");
			return null;
		}

	}

	public static String getWhoisResponse(WhoisServer whois, String domain) throws SocketException, IOException {
		WhoisClient wc = new WhoisClient();
		wc.connect(whois.getHost());
		return wc.query(domain);
	}

}
