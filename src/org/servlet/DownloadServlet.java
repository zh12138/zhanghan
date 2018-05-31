package org.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.tomcat.util.codec.binary.Base64;


public class DownloadServlet extends HttpServlet {
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//获取文件名称
		String fileName=request.getParameter("fileName");
		response.setHeader("Content-Type", "application/octet-stream");
		//设置请求头的user-agent
		String agent=request.getHeader("USER-AGENT");
		if(agent!=null&&agent.toLowerCase().indexOf("firefox")>0) {
			response.addHeader("Content-Disposition","attachment;filename="+ "=?UTF-8?B?"+ (new String(Base64.encodeBase64(fileName.getBytes("UTF-8"))))  + "?=");
			
		}else {
			response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
			
		}
		InputStream is=new FileInputStream("d:\\upload\\"+fileName);
		//InputStream is=getServletContext().getResourceAsStream("upload\\"+fileName);
		OutputStream os=response.getOutputStream();
		
		byte[] buffer=new byte[1024];
		int length=-1;
		while((length=is.read(buffer))!=-1) {
			os.write(buffer,0,length);
		}
		is.close();
		os.close();
		
	}

}
