package com.geebay.wxsq.wxroot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.geebay.wxsq.model.Constants;
import com.geebay.wxsq.model.wxroot.PassiveDataItem;
import com.geebay.wxsq.model.wxroot.PassiveResult;
import com.geebay.wxsq.model.wxroot.WxMsgResponse;
import com.geebay.wxsq.model.wxroot.message.ArticleItem;
import com.geebay.wxsq.model.wxroot.message.NewsResponse;
import com.geebay.wxsq.model.wxroot.message.TextResponse;




@Component
public class TransformService {
	
	public WxMsgResponse transResponse(String openId, String WxId, PassiveResult result){
		if(result!=null){
				if (PassiveResult.WEIXIN_MESSAGE_TYPE_TEXT.equals(result.getType())) {			 
					TextResponse textResponse = new TextResponse();
			        textResponse.setFuncFlag(Constants.WEIXIN_FUNCFLAG_UNSTAR);
			        textResponse.setCreateTime(String.valueOf(new Date().getTime()));
			        textResponse.setContent(result.getDataItems().get(0).getDescription());
			        textResponse.setFromUserName(WxId);
			        textResponse.setToUserName(openId);
			        return textResponse;
			        
				}else if(PassiveResult.WEIXIN_MESSAGE_TYPE_NEWS.equals(result.getType())){
					 
					NewsResponse newsresponse = new NewsResponse();
					newsresponse.setToUserName(openId);
					newsresponse.setFromUserName(WxId);
					newsresponse.setCreateTime(String.valueOf(new Date().getTime()));
					if (result.getDataItems().size() > 10) {
						newsresponse.setArticleCount("10");
			        } else {
			        	newsresponse.setArticleCount(String.valueOf(String.valueOf(result.getDataItems().size())));
			        }
			        List<ArticleItem> articleItems = new ArrayList<ArticleItem>();
			        for (PassiveDataItem dataItem : result.getDataItems()) {
			            ArticleItem articleItem = new ArticleItem();
			            articleItem.setDescription(dataItem.getDescription());
			            articleItem.setPicUrl(dataItem.getImageUrl());
			            articleItem.setTitle(dataItem.getTitle());              
			            articleItem.setUrl(dataItem.getUrl());
			            articleItems.add(articleItem);
			        }
			        newsresponse.setArticles(articleItems);
			        newsresponse.setFuncFlag(Constants.WEIXIN_FUNCFLAG_UNSTAR);
			        return newsresponse;			 
				}
			
		}else{
			return null;	
		}
		return null;		 
						
	}

}
