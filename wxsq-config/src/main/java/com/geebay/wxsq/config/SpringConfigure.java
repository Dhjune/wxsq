package com.geebay.wxsq.config;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.geebay.wxsq.model.wxroot.message.EventRequest;
import com.geebay.wxsq.model.wxroot.message.ImageRequest;
import com.geebay.wxsq.model.wxroot.message.LinkRequest;
import com.geebay.wxsq.model.wxroot.message.LocationRequest;
import com.geebay.wxsq.model.wxroot.message.NewsResponse;
import com.geebay.wxsq.model.wxroot.message.TextRequest;
import com.geebay.wxsq.model.wxroot.message.TextResponse;



@Configuration
public class SpringConfigure {
	
	
	public  @Bean Jaxb2Marshaller jaxb2Marshaller()
    {
       Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
       Map<String,Object> properties = new HashMap<String, Object>();
       properties.put(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);  
       properties.put(Marshaller.JAXB_ENCODING, "UTF-8");
       Class initclass[]={TextRequest.class, EventRequest.class, ImageRequest.class, LinkRequest.class, LocationRequest.class,
    		   TextResponse.class,NewsResponse.class};    
       marshaller.setClassesToBeBound(initclass);
       marshaller.setMarshallerProperties(properties);
       return marshaller;
    }
			
    public  @Bean MarshalAndUnmarshalService marshalAndUnmarshalService()
    {
       return new MarshalAndUnmarshalService();
    }
}
