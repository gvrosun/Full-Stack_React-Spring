package com.kaiburr.api.api;

public class Server {

	private final long id;
	private final String name;
	private final String language;
	private final String framework;

	public Server(long id, String name, String language, String framework) {
		this.id = id;
		this.name = name;
		this.language = language;
		this.framework = framework;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public String getFramework() {
		return framework;
	}
}