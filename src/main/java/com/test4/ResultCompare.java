package com.test4;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class ResultCompare {
	
	public static String getUrlResponse(String url) throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Method failed: ").append(response.getStatusLine());
		}
		//System.out.println(entity.getContentLength());
		String responseContent = EntityUtils.toString(entity, "UTF-8");
		return responseContent;
	}
	public static List<ResultDto> getResultDtoBy2037(String id) throws Exception{
		String url =  "http://10.125.193.110/eventdriver/recommendEntry.do?sceneId=2037&companyId="+id+
				"&callback=f&pageId=79001dc1ac1054354f506723135d2156e9b546d172";
		String result = getUrlResponse(url);
		result = result.substring(2, result.length()-2);
		@SuppressWarnings("unchecked")
		HashMap<String, Object> map = JSON.parseObject(result, HashMap.class);
		List<ResultDto> r = JSON.parseArray(map.get("items").toString(),ResultDto.class);
		return r;
	}
	
	public static List<ResultDto> getResultDtoBy2035(String id) throws Exception{
		String url =  "http://10.125.193.110/eventdriver/recommendEntry.do?sceneId=2035&productId="+id+
				"&callback=f&pageId=79001dc1ac1054354f506723135d2156e9b546d172";
		String result = getUrlResponse(url);
		result = result.substring(2, result.length()-2);
		List<ResultDto> r = JSON.parseArray(result,ResultDto.class);
		return r;
	}
	
	public static List<ResultDto> getResultDtoBy2056(String id) throws Exception{
		String url =  "http://10.125.193.110/eventdriver/recommendEntry.do?sceneId=2056&companyId="+id+
				"&callback=f&pageId=79001dc1ac1054354f506723135d2156e9b546d172";
		String result = getUrlResponse(url);
		result = result.substring(2, result.length()-2);
		List<ResultDto> r = JSON.parseArray(result,ResultDto.class);
		return r;
	}
	
	public static void throwsException(ResultDto dto){
		throw new RuntimeException("error:"+dto.getId());
	}
	public static void compare(List<ResultDto> r) throws Exception{
		for(int i=0;i<r.size();i++){
			
			ResultDto dto = r.get(i);
			System.out.println("productId:"+dto.getId());
			String content = getUrlResponse(dto.getProductUrl());
			//�鿴id
			if(!content.contains(dto.getId().toString())){
				throwsException(dto);
			}
			if(!content.contains(dto.getLowerPrice())){
				throwsException(dto);
			}
			if(!content.contains(dto.getHigherPrice())){
				throwsException(dto);
			}
			if(!content.contains(dto.getSubject())){
				throwsException(dto);
			}
			if(!content.contains(dto.getCurrencySymbol())){
				throwsException(dto);
			}
			if(!content.contains(dto.getLowerPrice())){
				throwsException(dto);
			}
		}
		
	}
	
	public static List<String> readInput(String url) throws Exception{
		File file = new File(url);
		List<String> list =new ArrayList<String>();
		Workbook book = Workbook.getWorkbook(file);
		Sheet sheet = book.getSheet(0);
		for (int i = 1; i < sheet.getRows(); i++){
			Cell[] row = sheet.getRow(i);
			if (row[0].getContents().trim().length() == 0) {
				break;
			}
			list.add(row[0].getContents().trim());
		}
		return list;
	}
	
	public static void test2037() throws Exception{
		List<String> id = readInput("d:\\tmp\\2037.xls");
		for(int i=0;i<1;i++){
			System.out.println("companyId:"+id.get(i));
			List<ResultDto> dtos = getResultDtoBy2037(id.get(i));
				compare(dtos);
		}
	}
	
	public static void test2035() throws Exception{
		List<String> id = readInput("d:\\tmp\\2035.xls");
		for(int i=0;i<id.size();i++){
			
			List<ResultDto> dtos = getResultDtoBy2035(id.get(i));
				compare(dtos);
		}
	}
	
	public static void test2056() throws Exception{
		List<String> id = readInput("d:\\tmp\\2056.xls");
		for(int i=0;i<id.size();i++){
			List<ResultDto> dtos = getResultDtoBy2056(id.get(i));
				compare(dtos);
		}
	}
	
	public static void main(String args[]) throws Exception{
		System.out.println("start...");
		test2056();
		System.out.println("end...");
	}

}
