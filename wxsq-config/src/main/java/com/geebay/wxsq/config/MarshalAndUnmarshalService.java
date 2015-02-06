package com.geebay.wxsq.config;

import java.io.StringReader;
import java.io.StringWriter;




import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.stereotype.Component;

@Component
public class MarshalAndUnmarshalService {
	
	
	    private Marshaller marshaller;
	    
	    private Unmarshaller unmarshaller;
	   
	    @Autowired(required=true)
	    public void setMarshaller(Marshaller marshaller) {
	       this.marshaller = marshaller;
	    }
	    public Marshaller getMarshaller() {
	       return marshaller;
	    }
	    @Autowired(required=true)
	    public void setUnmarshaller(Unmarshaller unmarshaller) {
	       this.unmarshaller = unmarshaller;
	    }
	    public Unmarshaller getUnmarshaller() {
	       return unmarshaller;
	    }

	
}
