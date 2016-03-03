package ht.tm.dev.urlshortener.dto;

public class WhoisServer {

	private final String domain;
	private final String host;

	public WhoisServer(String domain, String host) {
		this.host = host;
		this.domain = domain;
	}

	public String getDomain() {
		return domain;
	}

	public String getHost() {
		return host;
	}

	@Override
	public String toString() {
		return "WhoisServer [domain=" + domain + ", host=" + host + "]";
	}
	
	

}
