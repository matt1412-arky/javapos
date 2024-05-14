package com.xsis.javapos.models;

public class Salam {
	public Salam(long _id, String _content) {
		id = _id;
		content = _content;
	}
	
	private final long id;
	private final String content;
	
	public long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
}
