package com.ajun.linux;/*
package com.test.download;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

public class TestDownload extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//*******�����ļ��������ĵ��ļ�*******
		
		
		
				String str = "����.txt";
				String encode = URLEncoder.encode(str,"UTF-8");
				String encode2 = URLDecoder.decode(encode,"ISO8859-1");
				
				String str1 = "����.txt";
				String str12 = URLEncoder.encode(str1,"ISO8859-1");
				String str13 = URLDecoder.decode(str12,"UTF-8");
				
				String str4 = "����.txt";
				String str15 = URLEncoder.encode(str4,"UTF-8");
				String str16 = URLDecoder.decode(str15,"GBK");
				
				String str7 = "����.txt";
				String str18 = URLEncoder.encode(str7,"GBK");
				String str19 = URLDecoder.decode(str18,"ISO8859-1");
				String str10 = URLEncoder.encode(str19,"UTF-8");
				String param = request.getParameter("filename"); // ???.jpg (��������)
				*/
/*String filename = URLDecoder.decode(param,"ISO8859-1");
				String filename1 = URLDecoder.decode(param,"UTF-8");
				String filename2 = URLDecoder.decode(param,"GBK");
				String filename3 = URLDecoder.decode(param,"GB2312");*//*

				String test1 = URLEncoder.encode(param,"ISO8859-1");
				
				String test2 = URLDecoder.decode(test1,"UTF-8");
				String test3 = URLEncoder.encode(test2,"ISO8859-1");
				String filename = URLEncoder.encode(test3,"UTF-8");
				//String filename = URLDecoder.decode(filename1,"UTF-8");
				//String filename = URLDecoder.decode(encode,"UTF-8");
				*/
/*//*
/���������Ĳ���������
				/*filename = new String(filename.getBytes("ISO8859-1"),"UTF-8"); //����.jpg*//*

				
				//�������ͷ�е�User-Agent �ж����������
				String agent = request.getHeader("User-Agent");
				//���ݲ�ͬ��������в�ͬ�ı���
				String filenameEncoder = "";
				if (agent.contains("MSIE")) {  // �����IE�����
					filenameEncoder = URLEncoder.encode(filename, "utf-8");
					filenameEncoder = filenameEncoder.replace("+", " ");
				} else if (agent.contains("Firefox")) {  // ����ǻ�������
					BASE64Encoder base64Encoder = new BASE64Encoder();
					filenameEncoder = "=?utf-8?B?" + base64Encoder.encode(filename.getBytes("utf-8")) + "?=";
				} else {  // ���������
					filenameEncoder = URLEncoder.encode(filename, "utf-8");				
				}
		 
				//���߿ͻ���,Ҫ���ص��ļ���MIME����
				response.setContentType(this.getServletContext().getMimeType(filename));
				//���߿ͻ��˸��ļ�����ֱ�ӽ���,�����Ը�����ʽ��(����)�� �ļ����Ǳ������ļ���,Ϊ�˷�ֹ��������
				response.setHeader("Content-Disposition", "attachment;filename="+filenameEncoder);
		 
				//��ȡ�ļ��ľ���·��
				String path = this.getServletContext().getRealPath("/"+filename);
				InputStream in = new FileInputStream(path);
				ServletOutputStream out = response.getOutputStream(); // �ֽ������,д���ͻ���
				
				int len = 0;
				byte[] buffer = new byte[1024];
				while((len=in.read(buffer))>0){
					out.write(buffer, 0, len);
				}
		 
				in.close();
				//out.close();  // Tomcat������Զ��رա�

	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
*/
