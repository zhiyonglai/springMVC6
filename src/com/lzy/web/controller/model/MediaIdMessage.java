package com.lzy.web.controller.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

//��ý��id ʵ����
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
