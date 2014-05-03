package com.generics.impl;

import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.generics.ContentHandler;

public class GenericContentHandler implements ContentHandler {

	private static final Logger log = LoggerFactory.getLogger(GenericContentHandler.class);

	@SuppressWarnings("restriction")
	public <T> T unMarshal(String content, Class<T> clasz) {
		try {
			JAXBContext jc = JAXBContext.newInstance( clasz );
			Unmarshaller u = jc.createUnmarshaller();
			return u.unmarshal(new StreamSource( new StringReader( content )), clasz).getValue();
		} catch (JAXBException e) {
			log.error(String.format("Exception while unmarshalling: %s", e.getMessage()));
		}		
		return null;
	}

	public <T> String marshal(T object) {
		try {
			StringWriter stringWriter = new StringWriter();
			JAXBContext jc = JAXBContext.newInstance( object.getClass());
			Marshaller m = jc.createMarshaller();
			m.marshal(object, stringWriter);
			return stringWriter.toString();
		} catch (JAXBException e) {
			log.error(String.format("Exception while marshalling: %s", e.getMessage()));
		}
		return null;
	}

}
