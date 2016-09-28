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
		Arrays.sort(values); // �ֵ�������
		String value = values[0] + values[1] + values[2];
		System.out.println("value=="+value);  
		String sign = DigestUtils.shaHex(value);
		PrintWriter writer = response.getWriter();
		if (signature.equals(sign)) {// ��֤�ɹ�����ehcostr
			writer.print(echostr);
		} else {
			writer.print("error");
		}
		writer.flush();
		writer.close();
	}
	
	@RequestMapping("/chat")
	public void chat(HttpServletRequest request,HttpServletResponse response){
		// ����POST���촦��  
        System.out.println("enter post" +System.currentTimeMillis());  
        try {  
            // ������Ϣ��������Ϣ  
            acceptMessage(request, response);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	
	private void acceptMessage(HttpServletRequest request, HttpServletResponse response) throws IOException {  
        // ���������Ϣ  
        ServletInputStream in = request.getInputStream();  
        // ��POST��ת��ΪXStream����  
        XStream xs = SerializeXmlUtil.createXstream();  
        xs.processAnnotations(InputMessage.class);  
        xs.processAnnotations(OutputMessage.class);  
        // ��ָ���ڵ��µ�xml�ڵ�����ӳ��Ϊ����  
        xs.alias("xml", InputMessage.class);  
        // ����ת��Ϊ�ַ���  
        StringBuilder xmlMsg = new StringBuilder();  
        byte[] b = new byte[4096];  
        for (int n; (n = in.read(b)) != -1;) {  
            xmlMsg.append(new String(b, 0, n, "UTF-8"));  
        }  
        // ��xml����ת��ΪInputMessage����  
        InputMessage inputMsg = (InputMessage) xs.fromXML(xmlMsg.toString());  
//        String xml="<xml><URL><![CDATA[http://121.201.31.23/springMVC6/weixin/chat]]></URL><ToUserName><![CDATA[gh_41d3166e02b4]]></ToUserName><FromUserName><![CDATA[ganlen8]]></FromUserName><CreateTime>1474535037846</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[asdasdsads�����]]></Content><MsgId>1234567890123456</MsgId></xml>";
//        InputMessage inputMsg = (InputMessage) xs.fromXML(xml);  
       
        String servername = inputMsg.getToUserName();// �����  
        String custermname = inputMsg.getFromUserName();// �ͻ���  
        long createTime = inputMsg.getCreateTime();// ����ʱ��  
        Long returnTime = Calendar.getInstance().getTimeInMillis() / 1000;// ����ʱ��  
  
        // ȡ����Ϣ����  
        String msgType = inputMsg.getMsgType();  
        // ������Ϣ���ͻ�ȡ��Ӧ����Ϣ����  
        if (msgType.equals(MsgType.Text.toString())) {  
            // �ı���Ϣ  
            System.out.println("������΢�źţ�" + inputMsg.getToUserName());  
            System.out.println("���ͷ��ʺţ�" + inputMsg.getFromUserName());  
            System.out.println("��Ϣ����ʱ�䣺" + inputMsg.getCreateTime() + new Date(createTime * 1000l));  
            System.out.println("��Ϣ���ݣ�" + inputMsg.getContent());  
            System.out.println("��ϢId��" + inputMsg.getMsgId());  
  
            StringBuffer str = new StringBuffer();  
            str.append("<xml>");  
            str.append("<ToUserName><![CDATA[" + custermname + "]]></ToUserName>");  
            str.append("<FromUserName><![CDATA[" + servername + "]]></FromUserName>");  
            str.append("<CreateTime>" + returnTime + "</CreateTime>");  
            str.append("<MsgType><![CDATA[" + msgType + "]]></MsgType>");  
            str.append("<Content><![CDATA[��˵���ǣ�" + inputMsg.getContent() + "����]]></Content>");  
            str.append("</xml>");  
            System.out.println(str.toString());  
            response.getWriter().write(str.toString());  
        }  
        // ��ȡ�����ض�ͼƬ��Ϣ  
        if (msgType.equals(MsgType.Image.toString())) {  
            System.out.println("��ȡ��ý����Ϣ");  
            System.out.println("��ý���ļ�id��" + inputMsg.getMediaId());  
            System.out.println("ͼƬ���ӣ�" + inputMsg.getPicUrl());  
            System.out.println("��Ϣid��64λ���ͣ�" + inputMsg.getMsgId());  
  
            OutputMessage outputMsg = new OutputMessage();  
            outputMsg.setFromUserName(servername);  
            outputMsg.setToUserName(custermname);  
            outputMsg.setCreateTime(returnTime);  
            outputMsg.setMsgType(msgType);  
            ImageMessage images = new ImageMessage();  
            images.setMediaId(inputMsg.getMediaId());  
            outputMsg.setImage(images);  
            System.out.println("xmlת����/n" + xs.toXML(outputMsg));  
            response.getWriter().write(xs.toXML(outputMsg));  
  
        }  
    }  
	
	
	
}


