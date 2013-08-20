package com.jsoup;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsonpTest {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// File input = new File("d:/tmp/");
		// Document doc = Jsoup.parse(input, "UTF-8", "http://example.com/");

		// Document doc =
		// Jsoup.connect("http://icbu.data.alibaba-inc.com/migrate/opensql/table/detail.htm?tableName=odl_a01_im_send_msg").get();

		Document doc = Jsoup.parse(HttpHelper
				.getTableInfo("odl_a01_im_send_msg"));
		String title = doc.title();
		// System.out.println("title:"+doc.html());

		Elements links = doc.select("table.zd-detail-2 > tbody >tr"); // 带有href属性的a元素
		System.out.println(links.size());
		for (Element link : links) {
			System.out.println(link.html());
			List<Element> subEle = link.children();
			System.out.println(subEle.size());
			if(subEle.size()==3){
				String cid = subEle.get(0).attr("cid");
				String desc = subEle.get(1).text();
				String type = subEle.get(2).text();
				System.out.println(cid+"    "+type);
				System.out.println(desc);
				System.out.println("----------------------");
			}else{
				throw new  RuntimeException("表结构格式有问题....");
			}
		}

		// Elements links = doc.select("a[href]"); //带有href属性的a元素
		// Elements pngs = doc.select("img[src$=.png]");
		// //扩展名为.png的图片
		//
		// Element masthead = doc.select("div.masthead").first();
		// //class等于masthead的div标签
		//
		// Elements resultLinks = doc.select("h3.r > a"); //在h3元素之后的a元素

	}

}
