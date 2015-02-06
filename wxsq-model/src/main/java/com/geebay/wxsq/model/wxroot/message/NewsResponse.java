package com.geebay.wxsq.model.wxroot.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.geebay.wxsq.model.Constants;
import com.geebay.wxsq.model.wxroot.WxMsgResponse;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name=NewsResponse.ResponseHead)
public class NewsResponse implements WxMsgResponse{
	public static final String ResponseHead = "newsResponse";
    
    @XmlElement
    private String ToUserName;
    
    @XmlElement
    private String FromUserName;
    
    @XmlElement
    private String CreateTime;
    
    @XmlElement
    private String MsgType = Constants.WEIXIN_MESSAGE_TYPE_NEWS;
    
    @XmlElement
    private String ArticleCount;

    @XmlElementWrapper(name = "Articles")    
    @XmlElement(name = "item")
    private List<ArticleItem> Articles;

    public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public List<ArticleItem> getArticles() {
		return Articles;
	}

	public void setArticles(List<ArticleItem> articles) {
		Articles = articles;
	}

	public String getFuncFlag() {
		return FuncFlag;
	}

	public void setFuncFlag(String funcFlag) {
		FuncFlag = funcFlag;
	}

	@XmlElement
    private String FuncFlag;

    
	public String getToUserName() {
		return ToUserName;
	}
	
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	
	public String getFromUserName() {
		return FromUserName;
	}
	
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	
	public String getCreateTime() {
		return CreateTime;
	}
	
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	
	public String getMsgType() {
		return MsgType;
	}
	
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	
	public static String getResponsehead() {
		return ResponseHead;
	}

	public String getResponseHead() {
		return ResponseHead;
	}

	 
	
}
