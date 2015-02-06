package com.geebay.wxsq.model.wxroot;

import java.util.List;


public class PassiveResult {	
	    public static final String WEIXIN_MESSAGE_TYPE_TEXT = "text";
	    public static final String WEIXIN_MESSAGE_TYPE_MUSIC = "music";
	    public static final String WEIXIN_MESSAGE_TYPE_NEWS = "news";
	    public static final String WEIXIN_MESSAGE_TYPE_IMAGE = "image";
	    
	    private String type;
	    private String imageUrl;
	    public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}



		private List<PassiveDataItem> dataItems;//for news

	    public String getType() {
	        return type;
	    }

	    public void setType(String type) {
	        this.type = type;
	    }

	    public List<PassiveDataItem> getDataItems() {
	        return dataItems;
	    }

	    public void setDataItems(List<PassiveDataItem> dataItems) {
	        this.dataItems = dataItems;
	    }
	    
	    

	    @Override
	    public String toString() {
	        return "Result{" +
	                "type='" + type + '\'' +
	                ", dataItems=" + dataItems +
	                '}';
	    }
	
}
