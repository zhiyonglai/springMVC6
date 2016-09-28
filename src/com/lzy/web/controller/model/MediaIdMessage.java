package com.lzy.web.controller.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

//多媒体id 实体类
public class MediaIdMessage {  
    @XStreamAlias("MediaId")  
    @XStreamCDATA  
    private String MediaId;  
  
    public String getMediaId() {  
        return MediaId;  
    }  
  
    public void setMediaId(String mediaId) {  
        MediaId = mediaId;  
    }  
  
}  
