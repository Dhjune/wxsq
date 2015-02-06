package com.geebay.wxsq.model.account.base;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection=WxMessagePackage.COLLECTION)
public class WxMessagePackage {
	
	public static final String COLLECTION = "wxsq_weixin_message_package";
	
	@Id
	private String  id;
	
	private String title;
    private String name;
    private String wxAccountId;
    private String wxId;
    private Date createTime;
    private int status = 1;
    
    private List<MessageItem> msgItems;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MessageItem> getMsgItems() {
		return msgItems;
	}

	public void setMsgItems(List<MessageItem> msgItems) {
		this.msgItems = msgItems;
	}

	public String getWxAccountId() {
		return wxAccountId;
	}

	public void setWxAccountId(String wxAccountId) {
		this.wxAccountId = wxAccountId;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public class MessageItem{
		
		
		private String msgId;
	    private String title;
	    private String imageUrl;
	    private String description;
	    private String url;
	    

	    public String getMsgId() {
	        return msgId;
	    }

	    public void setMsgId(String msgId) {
	        this.msgId = msgId;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }

	    public String getImageUrl() {
	        return imageUrl;
	    }

	    public void setImageUrl(String imageUrl) {
	        this.imageUrl = imageUrl;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
	
	public static String getCollection() {
		return COLLECTION;
	}

}
