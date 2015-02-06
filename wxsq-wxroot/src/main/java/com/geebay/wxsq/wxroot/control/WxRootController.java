package com.geebay.wxsq.wxroot.control;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.XmlMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.geebay.wxsq.wxroot.service.WxRootServiceImp;


@Controller
public class WxRootController {
		
	private static Log log = LogFactory.getLog(WxRootController.class);
	
	@Autowired
	private WxRootServiceImp wxRootServiceImp;
	
	@RequestMapping(value = "/{wxAccountId}",method = RequestMethod.GET)
    @ResponseBody
    public String validate(@RequestParam("signature") String signature, @RequestParam("timestamp") String timestamp, @RequestParam("nonce") String nonce, 
    		@RequestParam("echostr") String echostr,@PathVariable("wxAppId") String wxAppId){
		log.debug("signature:" + signature + ",timestamp:" + timestamp + ",nonce:" + nonce + ",echostr:" + echostr);
		String token = "12345a";
        String[] params = new String[]{token, timestamp, nonce};
        Arrays.sort(params);
        String result = StringUtils.join(params);        
        String digRet = DigestUtils.sha1Hex(result);        	      
        if (StringUtils.equals(digRet, signature)) {       
            return echostr;
        } else {  
            return "hello";
        }
    }
	@RequestMapping(value = "/{wxAccountId}",method = RequestMethod.POST,produces = "application/xml")
    @ResponseBody
	public String root(@PathVariable("wxAccountId")String wxAccountId,@RequestBody String xmlBody) throws XmlMappingException, UnsupportedEncodingException{
		
		String resBody =  wxRootServiceImp.execute(wxAccountId,xmlBody);
		
		return  resBody;
	}
	
}
