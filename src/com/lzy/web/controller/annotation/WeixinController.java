package com.lzy.web.controller.annotation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lzy.web.controller.common.Constants;
import com.lzy.web.controller.model.ImageMessage;
import com.lzy.web.controller.model.InputMessage;
import com.lzy.web.controller.model.OutputMessage;
import com.lzy.web.controller.util.MsgType;
import com.lzy.web.controller.util.SerializeXmlUtil;
import com.thoughtworks.xstream.XStream;

/**
 * *@author zuidaima 2015-01-07 22:27:22
 */
@Controller
@RequestMapping("/weixin")
public class WeixinController{

	@RequestMapping("/signature")
	public void signature(
	@RequestParam(value = "signature", required = true) String signature,
	@RequestParam(value = "timestamp", required = true) String timestamp,
	@RequestParam(value = "nonce", required = true) String nonce,
	@RequestParam(value = "echostr", required = true) String echostr,
	HttpServletResponse response) throws IOException {
		String[] values = { Constants.WEIXIN_TOKEN, timestamp, nonce };
		Arrays.sort(values); // 字典序排序
		String value = values[0] + values[1] + values[2];
		System.out.println("value=="+value);  
		String sign = DigestUtils.shaHex(value);
		PrintWriter writer = response.getWriter();
		if (signature.equals(sign)) {// 验证成功返回ehcostr
			writer.print(echostr);
		} else {
			writer.print("error");
		}
		writer.flush();
		writer.close();
	}
	
	@RequestMapping("/chat")
	public void chat(HttpServletRequest request,HttpServletResponse response){
		// 进入POST聊天处理  
        System.out.println("enter post" +System.currentTimeMillis());  
        try {  
            // 接收消息并返回消息  
            acceptMessage(request, response);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	private void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        // 处理接收消息  
        ServletInputStream in = request.getInputStream();  
        // 将POST流转换为XStream对象  
        XStream xs = SerializeXmlUtil.createXstream();  
        xs.processAnnotations(InputMessage.class);  
        xs.processAnnotations(OutputMessage.class);  
        // 将指定节点下的xml节点数据映射为对象  
        xs.alias("xml", InputMessage.class);  
        // 将流转换为字符串  
        StringBuilder xmlMsg = new StringBuilder();  
        byte[] b = new byte[4096];  
        for (int n; (n = in.read(b)) != -1;) {  
            xmlMsg.append(new String(b, 0, n, "UTF-8"));  
        }  
        // 将xml内容转换为InputMessage对象  
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());  
//        String xml="<xml><URL><![CDATA[http://121.201.31.23/springMVC6/weixin/chat]]></URL><ToUserName><![CDATA[gh_41d3166e02b4]]></ToUserName><FromUserName><![CDATA[ganlen8]]></FromUserName><CreateTime>1474535037846</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[asdasdsads骈胯椋椋]]></Content><MsgId>1234567890123456</MsgId></xml>";
//        InputMessage inputMsg = (InputMessage) xs.fromXML(xml);  
       
        String servername = inputMsg.getToUserName();// 服务端  
        String custermname = inputMsg.getFromUserName();// 客户端  
        long createTime = inputMsg.getCreateTime();// 接收时间  
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// 返回时间  
  
        // 取得消息类型  
        String msgType = inputMsg.getMsgType();  
        // 根据消息类型获取对应的消息内容  
        if (msgType.equals(MsgType.Text.toString())) {  
            // 文本消息  
            System.out.println("开发者微信号：" + inputMsg.getToUserName());  
            System.out.println("发送方帐号：" + inputMsg.getFromUserName());  
            System.out.println("消息创建时间：" + inputMsg.getCreateTime() + new Date(createTime * 1000l));  
            System.out.println("消息内容：" + inputMsg.getContent());  
            System.out.println("消息Id：" + inputMsg.getMsgId());  
  
            StringBuffer str = new StringBuffer();  
            str.append("<xml>");  
            str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");  
            str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");  
            str.append("<CreateTime>" + returnTime + "</CreateTime>");  
            str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");  
            str.append("<Content><![CDATA[你说的是：" + inputMsg.getContent() + "，吗？]]></Content>");  
            str.append("</xml>");  
            System.out.println(str.toString());  
            response.getWriter().write(str.toString());  
        }  
        // 获取并返回多图片消息  
        if (msgType.equals(MsgType.Image.toString())) {  
            System.out.println("获取多媒体信息");  
            System.out.println("多媒体文件id：" + inputMsg.getMediaId());  
            System.out.println("图片链接：" + inputMsg.getPicUrl());  
            System.out.println("消息id，64位整型：" + inputMsg.getMsgId());  
  
            OutputMessage outputMsg = new OutputMessage();  
            outputMsg.setFromUserName(servername);  
            outputMsg.setToUserName(custermname);  
            outputMsg.setCreateTime(returnTime);  
            outputMsg.setMsgType(msgType);  
            ImageMessage images = new ImageMessage();  
            images.setMediaId(inputMsg.getMediaId());  
            outputMsg.setImage(images);  
            System.out.println("xml转换：/n" + xs.toXML(outputMsg));  
            response.getWriter().write(xs.toXML(outputMsg));  
  
        }  
    }  
	
	
	
}


