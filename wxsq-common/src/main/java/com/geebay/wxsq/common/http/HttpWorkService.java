package com.geebay.wxsq.common.http;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class HttpWorkService {
	
	public  String boundary = "mjds23s11g023n546ljogu"; 
	
	public HttpURLConnection createPostConnection(String url){
		HttpURLConnection conn = null;
		URL urlObj = null;
		try {
			urlObj = new URL(url);
			conn = (HttpURLConnection) urlObj.openConnection();	
			conn.setDoOutput(true);  
	        conn.setUseCaches(false);  
	        conn.setConnectTimeout(2000); //连接超时为2秒  
	        conn.setRequestMethod("POST");  
	        conn.setRequestProperty("Content-Type",  
	                "multipart/form-data; boundary=" + boundary);  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return  conn;		
	}

	public HttpURLConnection createGetConnection(String url){
		HttpURLConnection conn = null;
		URL urlObj = null;
		try {
			urlObj = new URL(url);
			conn = (HttpURLConnection) urlObj.openConnection();	
			conn.setDoOutput(true);  
	        conn.setUseCaches(false);  
	        conn.setConnectTimeout(2000); //连接超时为2秒  
	        conn.setRequestMethod("GET");  
	        conn.setRequestProperty("Content-Type",  
	                "multipart/form-data; boundary=" + boundary);  
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return  conn;		
	}
	
	
	public Map<String,Object> getMapService(Map<String,Object> params,Map<String,Object> files,String url,String returnType){
		Map<String,Object> map = null;
		HttpURLConnection conn = createPostConnection(url);
		InputStream in = null;
		BufferedOutputStream bfOut = null;
		try {
			conn.connect();
			bfOut = new BufferedOutputStream(conn.getOutputStream(),1024);
			writeStringParams(params,bfOut);
			writeFileParams(files,bfOut);
			byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");
			conn.getOutputStream().write(foot);
			conn.getOutputStream().flush();
			conn.getOutputStream().close();
			in = conn.getInputStream(); 
			BufferedReader br = new BufferedReader(new InputStreamReader(in));   
	        StringBuilder sb = new StringBuilder();   
	        while (br.read() != -1) {   
	              sb.append(br.readLine());   
	        }
	        br.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  map;
	}
	
	public JsonObject getJsonService(Map<String,Object> params,Map<String,Object> files,String url){
		JsonObject jsonObject = null;
		HttpURLConnection conn = createPostConnection(url);
		InputStream in = null;
		BufferedOutputStream bfOut = null;
		try {
			conn.connect();		
			bfOut = new BufferedOutputStream(conn.getOutputStream(),1024);
			writeStringParams(params,bfOut);
			writeFileParams(files,bfOut);
			byte[] foot = ("\r\n--" + boundary + "--\r\n").getBytes("utf-8");
			conn.getOutputStream().write(foot);
			conn.getOutputStream().flush();
			in = conn.getInputStream(); 
			BufferedReader br = new BufferedReader(new InputStreamReader(in));   
	        StringBuilder sb = new StringBuilder();   
	        while (br.read() != -1) {   
	              sb.append(br.readLine());   
	        }
	        br.close();
	        jsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  jsonObject;
	}
	
	public void writeFileParams(Map<String,Object> params,BufferedOutputStream bfOut){
		
		BufferedInputStream bfIn = null;
		try {			
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			Set<String> keySet = params.keySet();
			for(Iterator<String> it = keySet.iterator(); it.hasNext();){
				String key =  it.next();
				String value = (String)params.get(key);
				StringBuilder sb = new StringBuilder();
				File file = new File(value);
				sb.append("----");
				sb.append(boundary);
				sb.append("rn");
				sb.append("Content-Disposition: form-data;name=\""+key+"\";filename=\""
						+ file.getName() + "\"\r\n");
				sb.append(getContentType());
				bfOut.write(sb.toString().getBytes("utf-8"));				
				bfIn = new BufferedInputStream( new FileInputStream(file), 2048);
				
				while ((bytes = bfIn.read(bufferOut)) != -1) {
					bfOut.write(bufferOut, 0, bytes);
				}
				bfOut.write("rn".getBytes("utf-8"));
				bfOut.flush();
				//bfOut.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bfOut.flush();
				//bfIn.close();
				//bfOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void writeStringParams(Map<String,Object> params,BufferedOutputStream bfOut){
		
		
		try {
		
			Set<String> keySet = params.keySet();
			for(Iterator<String> it = keySet.iterator(); it.hasNext();){
				String key =  it.next();
				String value = (String)params.get(key);
				StringBuilder sb = new StringBuilder();
				sb.append("----");
				sb.append(boundary);
				sb.append("rn");
				sb.append("Content-Disposition: form-data;name=\""+key+ "\"\r\n");
				sb.append(value);
				sb.append("rn");
				bfOut.write(sb.toString().getBytes("utf-8"));
			}
			bfOut.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				bfOut.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getContentType(){
		return  "application/octet-stream";
	}
	
	@SuppressWarnings("unused")
	private String encode(String value) {  
	 	String nStr = null;
		try {
			nStr = URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} 
        return nStr ;
    }  
	
}
