package com.test4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Test5 {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws BiffException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Workbook book = Workbook.getWorkbook(new File("d:/tmp/456.xls"));
		// 获得第一个工作表对象
		Sheet sheet = book.getSheet(0);
		Cell[] head = sheet.getRow(0);
		List list = new ArrayList();
		 HttpClient httpClient = new DefaultHttpClient();
		boolean flag = true;
		for (int i = 1; i < sheet.getRows() && flag; i++) {
			Cell[] row = sheet.getRow(i);
			if (row[0].getContents().trim().length() == 0) {
						flag = false;
						break;
					}
				String url = "http://tianshu.alibaba-inc.com/regenactionbytaskid.do?taskid=" +row[7].getContents().trim()+
						"&owner="+"liang.jial"+"&parameter="+row[0].getContents().trim();
				System.out.println("url:"+url);
				HttpGet httpGet = new HttpGet(url);
				HttpResponse response = httpClient.execute(httpGet);
	        	HttpEntity entity = response.getEntity();
	            if (entity==null) {
	                StringBuilder sb = new StringBuilder();
	                sb.append("Method failed: ").append(response.getStatusLine());
	                break;
	            }
	            System.out.println("Response content length: "+entity.getContentLength());
	            long responseLength = entity.getContentLength(); 
	            String responseContent = EntityUtils.toString(entity, "UTF-8");
	            System.out.println("响应状态: " + response.getStatusLine()); 
	            System.out.println("响应长度: " + responseLength); 
	            System.out.println("响应内容: " + responseContent);
	            System.out.println();
	            System.out.println("-------------------------------");
	            System.out.println();
	            EntityUtils.consume(entity);

		}
		
		httpClient.getConnectionManager().shutdown();

	}

}
