generics-jaxb
=============

Overview
--------

Sample project to Marshall / Unmarshall POJO using JAXB and generics. Using generics simplifies the marshalling / 
unmarshaling of POJO quiet easily. 

Sample Code to unmarshal using generics,

	public <T> T unMarshal(String content, Class<T> clasz) throws JAXBException {
		try {
			JAXBContext jc = JAXBContext.newInstance( clasz );
			Unmarshaller u = jc.createUnmarshaller();
			return u.unmarshal(new StreamSource( new StringReader( content )), clasz).getValue();
		} catch (JAXBException e) {
			log.error(String.format("Exception while unmarshalling: %s", e.getMessage()));
            throw e;
		}		
	}

To Marshal using Generics

	public <T> String marshal(T object) throws JAXBException {
		try {
			StringWriter stringWriter = new StringWriter();
			JAXBContext jc = JAXBContext.newInstance( object.getClass());
			Marshaller m = jc.createMarshaller();
			m.marshal(object, stringWriter);
			return stringWriter.toString();
		} catch (JAXBException e) {
			log.error(String.format("Exception while marshalling: %s", e.getMessage()));
            throw e;
		}
	}



Usage:
------

    There is a test class GenericContentHandlerTest tests both Marshaling / Unmarshaling using Generics.


For further details,

http://valliappanr.blogspot.co.uk/2012/08/jaxb-marshalling-unmarshalling-using.html
  
