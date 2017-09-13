package joymeter.aliduanxin.common.util;


import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

public class HttpUtil {
    /**
     * 描述:  发起https请求并获取结果
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     */
	  public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
	    	JSONObject json = null;
	        try {
				URL getUrl=new URL(requestUrl);
				HttpURLConnection http=(HttpURLConnection)getUrl.openConnection();
				http.setRequestMethod(requestMethod); 
				http.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);
				http.connect();
              OutputStream outputStream = http.getOutputStream();
              // 注意编码格式，防止中文乱码
              outputStream.write(outputStr.getBytes("UTF-8"));
              outputStream.flush();
              outputStream.close();
				InputStream is = http.getInputStream(); 
				int size = is.available(); 
				byte[] b = new byte[size];
				is.read(b);
				String message = new String(b, "UTF-8");
				 json = JSONObject.fromObject(message);
                is.close();
				return json;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	return json;
	    }
	  public static JSONObject httpRequest(String requestUrl, String requestMethod){
			JSONObject json = null;
		  try {
				URL getUrl=new URL(requestUrl);
				HttpURLConnection http=(HttpURLConnection)getUrl.openConnection();
				http.setRequestMethod(requestMethod); 
				http.setRequestProperty("Content-Type",
						"application/x-www-form-urlencoded");
				http.setDoOutput(true);
				http.setDoInput(true);
				http.connect();
				InputStream is = http.getInputStream(); 
				int size = is.available(); 
				byte[] b = new byte[size];
				is.read(b);
				String message = new String(b, "UTF-8");
				 json = JSONObject.fromObject(message);
			return json;
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return json;
		}
		  
	  public static JSONObject httpRequest(String requestUrl, String requestMethod,File file){
			JSONObject json = null;
			 URL url;
			 HttpURLConnection http=null;
			 String BOUNDARY = "----------" + System.currentTimeMillis();  
			try {
				url = new URL(requestUrl);
				http= (HttpURLConnection) url.openConnection(); 
				http.setDoInput(true);  
				http.setDoOutput(true);  
				http.setUseCaches(false); // post方式不能使用缓存  
			     // 设置请求头信息     
				http.setRequestProperty("Connection", "Keep-Alive");       
				http.setRequestProperty("Charset", "UTF-8");  
			     // 设置边界     
				http.setRequestProperty("Content-Type",  
			             "multipart/form-data; boundary="         
			             + BOUNDARY);  	       
 

		     // 请求正文信息  		       
		     // 第一部分：  		       
		     StringBuilder sb = new StringBuilder();  	       
		     sb.append("--"); // 必须多两道线  		       
		     sb.append(BOUNDARY);  		       
		     sb.append("\r\n");  		    
		     sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""  
		             + file.getName() + "\"\r\n");  
		     sb.append("Content-Type:application/octet-stream\r\n\r\n");  
		     byte[] head = sb.toString().getBytes("utf-8");  
		     // 获得输出流  
		     OutputStream out = new DataOutputStream(http.getOutputStream());  
		     // 输出表头  
		     out.write(head);  
		     // 文件正文部分     
		     // 把文件已流文件的方式 推入到url中  
		     DataInputStream in = new DataInputStream(new FileInputStream(file));        
		     int bytes = 0;         
		     byte[] bufferOut = new byte[1024];        
		     while ((bytes = in.read(bufferOut)) != -1) {        
		         out.write(bufferOut, 0, bytes);     
		     }  
		     in.close();  
             byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线        
		     out.write(foot);     
		     out.flush();  
		     out.close();  	


		        InputStream is = http.getInputStream(); 
				int size = is.available(); 
				byte[] b = new byte[size];
				is.read(b);
				String message = new String(b, "UTF-8");
				//此操作暂时记录得到的返回值到txt文件中，方便查看，以后更改
				 FileWriter outSTr = new  FileWriter(new File("C:\\Users\\Administrator\\Desktop\\微信公共号素材管理\\image\\return.txt"),true); 
				 BufferedWriter Buff=new BufferedWriter(outSTr);   
				 Buff.write((message+"\n"));   
		            Buff.flush();   
		            Buff.close();
		         //---------------------------------------
				 json = JSONObject.fromObject(message);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return json;
	  }
	  
	  public static JSONObject httpRequest(String requestUrl, String requestMethod,File file,String outStr){
			JSONObject json = null;
			 URL url;
			 HttpURLConnection http=null;
			 String BOUNDARY = "----------" + System.currentTimeMillis();  
			try {
				url = new URL(requestUrl);
				http= (HttpURLConnection) url.openConnection(); 
				http.setDoInput(true);  
				http.setDoOutput(true);  
				http.setUseCaches(false); // post方式不能使用缓存  
			     // 设置请求头信息     
				http.setRequestProperty("Connection", "Keep-Alive");       
				http.setRequestProperty("Charset", "UTF-8");  
			     // 设置边界     
				http.setRequestProperty("Content-Type",  
			             "multipart/form-data; boundary="         
			             + BOUNDARY);  	       



		     // 请求正文信息  		       
		     // 第一部分：  		       
		     StringBuilder sb = new StringBuilder();  	       
		     sb.append("--"); // 必须多两道线  		       
		     sb.append(BOUNDARY);  		       
		     sb.append("\r\n");  		    
		     sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""  
		             + file.getName() + "\"\r\n");  
		     sb.append("Content-Type:application/octet-stream\r\n\r\n");  
		     byte[] head = sb.toString().getBytes("utf-8");  
		     // 获得输出流  
		     OutputStream out = new DataOutputStream(http.getOutputStream());  
		     // 输出表头  
		     out.write(head);  
		     // 文件正文部分     
		     // 把文件已流文件的方式 推入到url中  
		     DataInputStream in = new DataInputStream(new FileInputStream(file));        
		     int bytes = 0;         
		     byte[] bufferOut = new byte[1024];        
		     while ((bytes = in.read(bufferOut)) != -1) {        
		         out.write(bufferOut, 0, bytes);
		         out.write("\r\n".getBytes());
		     }  
		     in.close();  
             byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线        
		     out.write(foot);     
		     out.flush();  
		     out.close();  	

				OutputStream outputStream = http.getOutputStream();
		      // 注意编码格式，防止中文乱码
		    outputStream.write(outStr.getBytes("UTF-8"));
		    outputStream.flush();
		    outputStream.close();
		        InputStream is = http.getInputStream(); 
				int size = is.available(); 
				byte[] b = new byte[size];
				is.read(b);
				String message = new String(b, "UTF-8");
				//此操作暂时记录得到的返回值到txt文件中，方便查看，以后更改
				 FileWriter outSTr = new  FileWriter(new File("C:\\Users\\Administrator\\Desktop\\微信公共号素材管理\\image\\return.txt"),true); 
				 BufferedWriter Buff=new BufferedWriter(outSTr);   
				 Buff.write((message+"\n"));   
		            Buff.flush();   
		            Buff.close();
		         //---------------------------------------
				 json = JSONObject.fromObject(message);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			return json;
	  }
	 
	  
//		OutputStream outputStream = http.getOutputStream();
//      // 注意编码格式，防止中文乱码
//    outputStream.write(outputStr.getBytes("UTF-8"));
//    outputStream.flush();
//    outputStream.close();
	  
	  }
	  

