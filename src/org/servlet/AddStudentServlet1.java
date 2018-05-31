package org.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.lanqiao.entity.Student;


public class AddStudentServlet1 extends HttpServlet {
	
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                 request.setCharacterEncoding("UTF-8");
                 response.setContentType("text/html;charset=utf-8");
		
		//文件上传
		
				if(ServletFileUpload.isMultipartContent(request)) {
					DiskFileItemFactory factory=new DiskFileItemFactory();
					//FileItemFactory factory= new DiskFileItemFactory();
					//设置缓冲区大小
					factory.setSizeThreshold(10240);
					//设置临时路径
					File file=new File("d:\\upload");
					factory.setRepository(file);
					
					ServletFileUpload upload=new ServletFileUpload(factory);
					
					try {
						Student stu1=new Student();
						List<FileItem> items=upload.parseRequest(request);
						Iterator<FileItem> iter=items.iterator();
						while(iter.hasNext()) {
							FileItem item=iter.next();
							if(item.isFormField()) {
								String fieldName=item.getFieldName();
								if(fieldName.equals("sno")) {
									String studentNoStr=item.getString("utf-8");
									int studentNo=Integer.parseInt(studentNoStr);
									stu1.setStudentNo(studentNo);
								}else if(fieldName.equals("sname")) {
									String studentName=item.getString("utf-8");	
									stu1.setStudentName(studentName);
								}else if(fieldName.equals("sage")) {
									int studentAge=Integer.parseInt(item.getString("utf-8"));
									stu1.setStudentAge(studentAge);
								}else if(fieldName.equals("gname")) {
									String gradeName=item.getString("utf-8");
									stu1.setGradeName(gradeName);
									
								}
							}else {
								//String uploadFilePath="C:\\Users\\Administrator\\eclipse-workspace\\Student\\WebContent\\upload";
								upload.setSizeMax(20480);
								String fileName=item.getName();
								PrintWriter out=response.getWriter();
								if(fileName !=null &&!fileName.equals("")) {
									String ext=fileName.substring(fileName.lastIndexOf(".")+1);
									if(!(ext.equals("png")||ext.equals("jpg")||ext.equals("bmp")) ){
										out.println("<h1>图片上传失败！</h1>");
										return;
									}
									File file1 =new File(file,fileName);
									try {
										item.write(file1);
										out.print("<h1>图片上传成功！</h1>");
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
							
						}
					} catch (FileUploadException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
	}

}
