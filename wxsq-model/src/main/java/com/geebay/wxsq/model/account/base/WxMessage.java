package com.geebay.wxsq.model.account.base;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 定义多媒体消息，图文消息（包含图文url指向页面所需的内容，声音，图片填充），声音消息，视频消息等
 * @author dhjune
 *
 */
@Document(collection=WxMessage.COLLECTION)
public class WxMessage {
	
	public static final String COLLECTION = "wxsq_weixin_message";
	@Id
	private String  id;
	//平台注册用户,(运营管理用户-商家)
	private String userName;
	
	//后台注册公众号标识
	private String wxAccountId;
	//微信官方公公众号唯一标识
	private String wxId;
	
	
	
	private String type;  //text，image,news,voice,vedio,link?
	
	
	private String mediaId;
	
	private String mediaTitle;
	
	private String imageUrl;

    private String musicUrl;
    private String hqMusicUrl;

    private String audioUrl;
    private String videoUrl;
	
    //news
    private String title;//也可以用于音乐
    private String description;
    //private String summary;本意是用于返回给给微信后端描述，后发现description可以通用。
    private String newsContent;
    private String url;//图文链接包含的Url   
	private Date createTime;
	private Date modifyTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getMediaTitle() {
		return mediaTitle;
	}
	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getMusicUrl() {
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public String getHqMusicUrl() {
		return hqMusicUrl;
	}
	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
//	public String getSummary() {
//		return summary;
//	}
//	public void setSummary(String summary) {
//		this.summary = summary;
//	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	public static String getCollection() {
		return COLLECTION;
	}
	
}
