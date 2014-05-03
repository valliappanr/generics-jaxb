package com.generics;

public interface ContentHandler {
	
	public <T> T unMarshal(String content, Class<T> clasz);

	public <T> String marshal(T object);
	

}
