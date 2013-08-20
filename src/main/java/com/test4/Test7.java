package com.test4;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
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
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jsoup.HttpHelper;


public class Test7 {

	private static HttpClient httpClient;
	private static class TrustAnyHostnameVerifier implements HostnameVerifier {

		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}
	
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
	/**
	 * @param args
	 * @throws NoSuchProviderException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null,
				new TrustManager[] { new TrustAnyTrustManager() },
				new SecureRandom());

		httpClient = new DefaultHttpClient();
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

		HttpGet httpGet = new HttpGet(
				"https://login.alibaba-inc.com/ssoLogin.htm?APP_NAME=hz-datasky&BACK_URL=http%3A%2F%2Ficbu.data.alibaba-inc.com%2Fmigrate%2Fopensql%2Ftable%2Fdetail.htm%3FtableName%3Dodl_a01_im_send_msg&CONTEXT_PATH=%2F&CANCEL_CERT=true");

		httpGet.addHeader(
				HTTP.USER_AGENT,
				"User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");
		httpGet.addHeader("Referer",
				"https://login.alibaba-inc.com/ssoLogin.htm?APP_NAME=hz-datasky&BACK_URL=http%3A%2F%2Ficbu.data.alibaba-inc.com%2Fmigrate%2Fopensql%2Ftable%2Fdetail.htm%3FtableName%3Dodl_a01_im_send_msg&CONTEXT_PATH=%2F");
		
		HttpContext context = new BasicHttpContext();
		CookieStore cookieStore = new BasicCookieStore();
		
		context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

		HttpResponse response = httpClient.execute(httpGet,context);
		
		String responseContent = getContent(response,context);//print(response,context);;//getContent(response);
		
		String token = getToken(responseContent);
		System.out.println("---------------------------step1--------------------------");
		System.out.println("token:"+token);
		
		
		System.out.println("---------------------------step2--------------------------");
		//--------------------------------提交表单
		HttpPost httpPost = new HttpPost("https://login.alibaba-inc.com/ssoLogin.htm?APP_NAME=hz-datasky&BACK_URL=http%3A%2F%2Ficbu.data.alibaba-inc.com%2Fmigrate%2Fopensql%2Ftable%2Fdetail.htm%3FtableName%3Dodl_a01_im_send_msg&CONTEXT_PATH=%2F&CANCEL_CERT=true");
		//_csrf_token=oUL4hPqFVjv48oz3xoPN42
		//action=SSOLoginAction
		//return=
		//_fm.l._0.a=wb-xuqiang&_fm.l._0.p=woaizq1314&login_maintain=true&event_submit_do_sso_login=true
		
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("_csrf_token", token));
		nameValuePairs.add(new BasicNameValuePair("action", "SSOLoginAction"));
		nameValuePairs.add(new BasicNameValuePair("return", ""));
		nameValuePairs.add(new BasicNameValuePair("_fm.l._0.a", "wb-xuqiang"));
		nameValuePairs.add(new BasicNameValuePair("_fm.l._0.p", "woaizq1314"));
		nameValuePairs.add(new BasicNameValuePair("login_maintain","true"));
		nameValuePairs.add(new BasicNameValuePair("event_submit_do_sso_login","true"));
		httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		
		 /*httpPost.getParams().setParameter("_csrf_token", token);
		 httpPost.getParams().setParameter("action", "SSOLoginAction");
		 httpPost.getParams().setParameter("return", "");
		 httpPost.getParams().setParameter("_fm.l._0.a", "wb-xuqiang");
		 httpPost.getParams().setParameter("_fm.l._0.p", "woaizq1314");
		 httpPost.getParams().setParameter("login_maintain","true");
		 httpPost.getParams().setParameter("event_submit_do_sso_login","true");*/
		 
		 response = httpClient.execute(httpPost, context);
		 System.out.println(getContent(response,context));
		
	}
	
	public static String getContent(HttpResponse response,HttpContext context) throws Exception{
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Method failed: ").append(response.getStatusLine());
		}
		String responseContent = EntityUtils.toString(entity, "UTF-8");
		
		String location = null;
		//更新cookies
		CookieStore cookieStore = (CookieStore) context.getAttribute(ClientContext.COOKIE_STORE);
		for (int i = 0; i < response.getAllHeaders().length; i++) {
			String name = response.getAllHeaders()[i].getName();
			if(name.equals("Set-Cookie")){
				String value = response.getAllHeaders()[i].getValue();
				String []keys = value.split("=");
				cookieStore.addCookie(getCookie(keys[0],keys[1]));
			}else if(name.equalsIgnoreCase("Location")){
				location = response.getAllHeaders()[i].getValue();
			}
		}
		if(response.getStatusLine().toString().indexOf("302")>0){
			HttpGet httpGet = new HttpGet(location);
			HttpResponse response1 = httpClient.execute(httpGet, context);
			return getContent(response1, context);
		}
		return responseContent;
	}
	
	public static Cookie getCookie(String key,String value,String... domain){
		BasicClientCookie cookie1 = new BasicClientCookie(key,value);
		cookie1.setPath("/");
		if(domain!=null&&domain.length>0)
			cookie1.setDomain(domain[0]);
		else cookie1.setDomain("login.alibaba-inc.com");
		return cookie1;
	}
	
	public static  String getToken(String content){
		Document doc  =  Jsoup.parse(content);
		Element input = doc.select("input[name=_csrf_token]").first(); //带有href属性的a元素
		String value = input.attr("value");
		return value;
	}
	
	public static String print(HttpResponse response,HttpContext context) throws Exception{
		CookieStore cookieStore = (CookieStore) context.getAttribute(ClientContext.COOKIE_STORE);
		HttpEntity entity = response.getEntity();
		if (entity == null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Method failed: ").append(response.getStatusLine());
		}
		System.out.println("Response content length: "
				+ entity.getContentLength());
		long responseLength = entity.getContentLength();

		String responseContent = EntityUtils.toString(entity, "UTF-8");

		HttpHost target = (HttpHost) context
				.getAttribute(ExecutionContext.HTTP_TARGET_HOST);
		System.out.println("Final target: " + target);

		for (int i = 0; i < cookieStore.getCookies().size(); i++) {
			System.out.println("【name】:"
					+ cookieStore.getCookies().get(i).getName());
			System.out.println("【value】:"
					+ cookieStore.getCookies().get(i).getValue());
			System.out.println("【path】:"
					+ cookieStore.getCookies().get(i).getPath());
			System.out.println("【getDomain】:"
					+ cookieStore.getCookies().get(i).getDomain());
			System.out.println("【getVersion】:"
					+ cookieStore.getCookies().get(i).getVersion());
		}

		CookieOrigin cookieOrigin = (CookieOrigin) context
				.getAttribute(ClientContext.COOKIE_ORIGIN);
		System.out.println("Cookie origin: " + cookieOrigin);
		CookieSpec cookieSpec = (CookieSpec) context
				.getAttribute(ClientContext.COOKIE_SPEC);
		System.out.println("Cookie spec used: " + cookieSpec);

		EntityUtils.consume(entity);

		
		for (int i = 0; i < response.getAllHeaders().length; i++) {
			System.out.println("【name】: "
					+ response.getAllHeaders()[i].getName() + "【value】:"
					+ response.getAllHeaders()[i].getValue());
		}
		System.out.println("【Protocol】: "
				+ response.getProtocolVersion().getProtocol());
		System.out.println("响应状态: " + response.getStatusLine());
		System.out.println("响应长度: " + responseLength);
		System.out.println("响应内容: " + responseContent);
		return responseContent;
	}

}
