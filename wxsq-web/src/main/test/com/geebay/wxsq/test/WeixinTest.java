package com.geebay.wxsq.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;









import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;




















import org.codehaus.jackson.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.geebay.wxsq.account.dao.weixin.WxAccountDaoImp;
import com.geebay.wxsq.common.api.dao.WxUserDao;
import com.geebay.wxsq.config.MarshalAndUnmarshalService;
import com.geebay.wxsq.model.account.base.WxAccount;
import com.geebay.wxsq.wxroot.control.WxRootController;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:com/geebay/wxsq/test/spring-context-test.xml"})
public class WeixinTest {
	
	@Resource
	private WxUserDao wxuserdao;
		
	@Autowired
	private WxRootController wxcontroller;
	
	@Autowired
	private WxAccountDaoImp accountDaoImp;
	

		
	@Autowired
	private MarshalAndUnmarshalService marshalAndUnmarshalService;
	
	public WxUserDao getWxuserdao() {
		return wxuserdao;
	}
	
	
	public void setWxuserdao(WxUserDao wxuserdao) {
		this.wxuserdao = wxuserdao;
	}

	
	@Test
	public void saveWxAccount(){
		WxAccount wxaccount = new WxAccount();
		wxaccount.setAppId("wx5be9450141d63b01");
		wxaccount.setCreateTime(new Date());
		try {
			accountDaoImp.save(wxaccount);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
  
    @Test  
    public void showUnMarshaller() throws XmlMappingException, IOException {  
    	
    	
    	
//    	ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfigure.class);
//        //��ȡ��jaxbʹ�õĹ���
//        	
//        MarshalAndUnmarshalService maus = ac.getBean(MarshalAndUnmarshalService.class);       
//               
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+  
            "<student id=\"12\">"+  
            "    <name>test</name>"+  
             "   <role>"+  
              "      <name>测试೤</name>"+  
               "     <desc>乱码</desc>"+  
                "</role>"+  
            "</student>";  
       String xmlBody= "<xml>\n" +
        " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
        " <FromUserName><![CDATA[o5Eagjtfawsrga]]></FromUserName> \n" +
        " <CreateTime>1348831860</CreateTime>\n" +
        " <MsgType><![CDATA[text]]></MsgType>\n" +
        " <Content><![CDATA[图文]]></Content>\n" +
        " <MsgId>1234567890123456</MsgId>\n" +
        " </xml>";		
		
        String wxAccountId = "wx1627e2d675b9678f";
   		wxcontroller.root(wxAccountId, xmlBody);
    }  
    
    
    @Test  
    public void testMessage() throws XmlMappingException, IOException {  
    	
       String xmlBody= "<xml>\n" +
        " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
        " <FromUserName><![CDATA[o5Eagjtfawsrga]]></FromUserName> \n" +
        " <CreateTime>1348831860</CreateTime>\n" +
        " <MsgType><![CDATA[text]]></MsgType>\n" +
        " <Content><![CDATA[cs]]></Content>\n" +
        " <MsgId>1234567890123456</MsgId>\n" +
        " </xml>";		
		
       String wxAccountId = "wx1627e2d675b9678f";
  		wxcontroller.root(wxAccountId, xmlBody);
    } 
    
    @Test
    public void testSubscribe() throws XmlMappingException, UnsupportedEncodingException{
    	String xmlBody = "<xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName><![CDATA[o5Eagjtfawsrga]]></FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[event]]></MsgType>\n" +
                " <Event><![CDATA[subscribe]]></Event>\n" +
                " </xml>";
    	 String wxAccountId = "wx1627e2d675b9678f";
    		wxcontroller.root(wxAccountId, xmlBody);
    }
    
    @Test
    public void testclick() throws XmlMappingException, UnsupportedEncodingException{
    	// 08jzq12lUj69  转盘
    	// KhO2B4dDsvcZ 梦想家园
    	// 36dY6a90ejpP  创意部落
    	String xmlBody = "<xml>\n" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                " <FromUserName><![CDATA[o5Eagjtfawsrga]]></FromUserName> \n" +
                " <CreateTime>1348831860</CreateTime>\n" +
                " <MsgType><![CDATA[event]]></MsgType>\n" +              
                " <Event><![CDATA[CLICK]]></Event>\n" +
                " <EventKey><![CDATA[08jzq12lUj69]]></EventKey>\n"+
                " </xml>";
    	 String wxAccountId = "wx1627e2d675b9678f";
    		wxcontroller.root(wxAccountId, xmlBody);
    }
    
   
      
}  