package com.jsoup;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * http登陆，取得数据
 * 
 * @author xq 2013-07-25
 * 
 */
public class HttpHelper {
	private static final String LOGIN_URL = "https://login.alibaba-inc.com/ssoLogin.htm?APP_NAME=hz-datasky&BACK_URL=http%3A%2F%2Ficbu.data.alibaba-inc.com%2Findex.htm&CONTEXT_PATH=%2F&CANCEL_CERT=true";
	private static final String LOGIN_DOMAIN = "login.alibaba-inc.com";

	private static final String DATA_URL = "http://icbu.data.alibaba-inc.com/migrate/.dox?action=opensql/SearchAction&event_submit_do_search=true";
	private static final String DATA_DOMAIN = "icbu.data.alibaba-inc.com";

	private static final String TABLEINFO_URL = "http://icbu.data.alibaba-inc.com/migrate/opensql/table/detail.htm?tableName=";

	private static final String USERNAME = "wb-xuqiang";
	private static final String PASSWORD = "woaizq1314";

	private static boolean isSuccess = false;
	private static HttpContext httpContext = null;
	

	private static class TrustAnyTrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static HttpClient getHttpClient() throws Exception {
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null,
				new TrustManager[] { new TrustAnyTrustManager() },
				new SecureRandom());

		HttpClient httpClient = new DefaultHttpClient();
		// KeyStore trustStore =
		// KeyStore.getInstance(KeyStore.getDefaultType());
		// 穿件Socket工厂,将trustStore注入 
		SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext,
				SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		// 创建Scheme
		Scheme sch = new Scheme("https", 443, socketFactory);
		// 注册Scheme 
		httpClient.getConnectionManager().getSchemeRegistry().register(sch);
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.BROWSER_COMPATIBILITY);
		httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS,
				false);
		return httpClient;

	}

	private static void login() throws Exception {

		HttpGet httpGet = new HttpGet(LOGIN_URL);

		HttpClient httpClient = getHttpClient();
		httpContext = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		// Bind custom cookie store to the local context
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		try {
			// 第一步取得token
			HttpResponse response = httpClient.execute(httpGet, httpContext);
			String content = getContent(response, httpContext, httpClient);
			String token = getToken(content);

			// 模拟登陆
			HttpPost httpPost = new HttpPost(
					"https://login.alibaba-inc.com/ssoLogin.htm?APP_NAME=hz-datasky&BACK_URL=http%3A%2F%2Ficbu.data.alibaba-inc.com%2Findex.htm&CONTEXT_PATH=%2F&CANCEL_CERT=true");
			// _csrf_token=oUL4hPqFVjv48oz3xoPN42
			// action=SSOLoginAction
			// return=
			// _fm.l._0.a=wb-xuqiang&_fm.l._0.p=woaizq1314&login_maintain=true&event_submit_do_sso_login=true
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("_csrf_token", token));
			nameValuePairs.add(new BasicNameValuePair("action",
					"SSOLoginAction"));
			nameValuePairs.add(new BasicNameValuePair("return", ""));
			nameValuePairs.add(new BasicNameValuePair("_fm.l._0.a", USERNAME));
			nameValuePairs.add(new BasicNameValuePair("_fm.l._0.p", PASSWORD));
			nameValuePairs
					.add(new BasicNameValuePair("login_maintain", "true"));
			nameValuePairs.add(new BasicNameValuePair(
					"event_submit_do_sso_login", "true"));
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpClient.execute(httpPost, httpContext);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				StringBuilder sb = new StringBuilder();
				sb.append("Method failed: ").append(response.getStatusLine());
				throw new RuntimeException(sb.toString());
			}
			if (response.getStatusLine().toString().indexOf("200") == 0) {
				throw new RuntimeException("login error....");
			} else {
				isSuccess = true;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	/**
	 * 登陆时取得token
	 * 
	 * @param content
	 * @return
	 */
	private static String getToken(String content) {
		Document doc = Jsoup.parse(content);
		Element input = doc.select("input[name=_csrf_token]").first(); // 带有href属性的a元素
		String value = input.attr("value");
		return value;
	}

	public static String getContent(HttpResponse response, HttpContext context,
			HttpClient httpClient) throws Exception {
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Method failed: ").append(response.getStatusLine());
		}
		String responseContent = EntityUtils.toString(entity, "UTF-8");

		String location = null;
		// 更新cookies
		CookieStore cookieStore = (CookieStore) context
				.getAttribute(ClientContext.COOKIE_STORE);
		for (int i = 0; i < response.getAllHeaders().length; i++) {
			String name = response.getAllHeaders()[i].getName();
			if (name.equals("Set-Cookie")) {
				String value = response.getAllHeaders()[i].getValue();
				String[] keys = value.split("=");
				cookieStore.addCookie(getCookie(keys[0], keys[1]));
			} else if (name.equalsIgnoreCase("Location")) {
				location = response.getAllHeaders()[i].getValue();
			}
		}
		if (response.getStatusLine().toString().indexOf("302") > 0) {
			HttpGet httpGet = new HttpGet(location);
			HttpResponse response1 = httpClient.execute(httpGet, context);
			return getContent(response1, context, httpClient);
		}
		return responseContent;
	}

	public static Cookie getCookie(String key, String value, String... domain) {
		BasicClientCookie cookie1 = new BasicClientCookie(key, value);
		cookie1.setPath("/");
		if (domain != null && domain.length > 0)
			cookie1.setDomain(domain[0]);
		else
			cookie1.setDomain(LOGIN_DOMAIN);
		return cookie1;
	}

	public static String getData(String pageNo, String pageSize, String keyword)
			throws Exception {
		synchronized (HttpHelper.class) {
			if (!isSuccess || httpContext == null) {
				login();
			}
		}
		String responseContent = null;
		HttpClient httpClient = null;
		try {
			HttpPost httpPost = new HttpPost(DATA_URL);
			// =&=D&site=en&pageNo=1&pageSize=15&keyword=a
			httpPost.getParams().setParameter("timestamp", "1374643708220");
			httpPost.getParams().setParameter("type", "D");
			httpPost.getParams().setParameter("site", "en");
			httpPost.getParams().setParameter("pageNo", pageNo);
			httpPost.getParams().setParameter("pageSize", pageSize);
			if (!StringUtils.isEmpty(keyword))
				httpPost.getParams().setParameter("keyword", keyword);

			httpPost.getParams()
					.setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
			httpPost.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
			httpPost.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET,
					HTTP.UTF_8);
			httpClient = getHttpClient();
			HttpResponse response = httpClient.execute(httpPost, httpContext);
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				StringBuilder sb = new StringBuilder();
				sb.append("Method failed: ").append(response.getStatusLine());
				throw new RuntimeException(sb.toString());
			}
			responseContent = EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			throw e;
		} finally {
			if (httpClient != null)
				httpClient.getConnectionManager().shutdown();
		}
		return responseContent;
	}

	public static String getTableInfo(String tableName) throws Exception {

		synchronized (HttpHelper.class) {
			if (!isSuccess || httpContext == null) {
				login();
			}
		}
		String responseContent = null;
		HttpClient httpClient = null;
		try {
			httpClient = getHttpClient();
			HttpGet httpGet = new HttpGet(TABLEINFO_URL + tableName);
			HttpResponse response = httpClient.execute(httpGet, (httpContext));
			 responseContent =getContent(response,(httpContext),httpClient);
		} catch (Exception e) {
			throw e;
		} finally {
			if(httpClient!=null)
				httpClient.getConnectionManager().shutdown();
		}

		return responseContent;
	}

	private String analyseTableInfo(String responseContent) {
		Document doc = Jsoup.parse(responseContent);

		Elements links = doc.select("table.zd-detail-2 > tbody >tr"); // 
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
		return null;
	}

	public static void main(String[] args) throws Exception {
		/*String data = getData("1", "15", null);
		System.out.println(data);
		data = getData("15", "30", null);
		System.out.println(data);*/
		System.out.println(getTableInfo("odl_a01_im_send_msg"));
	}

}