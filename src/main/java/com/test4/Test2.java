package com.test4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
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
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class Test2 {

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

	/*
	 * public static class MySecureProtocolSocketFactory implements
	 * SecureProtocolSocketFactory { private SSLContext sslcontext = null;
	 * public MySecureProtocolSocketFactory(SSLContext sslcontext){
	 * this.sslcontext =sslcontext; } public Socket createSocket(String host,
	 * int port, InetAddress localAddress, int localPort) throws IOException,
	 * UnknownHostException { // TODO Auto-generated method stub return
	 * sslcontext.getSocketFactory().createSocket(host, port, localAddress,
	 * localPort); }
	 * 
	 * public Socket createSocket(String host, int port, InetAddress
	 * localAddress, int localPort, HttpConnectionParams params) throws
	 * IOException, UnknownHostException, ConnectTimeoutException { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * public Socket createSocket(String host, int port) throws IOException,
	 * UnknownHostException { // TODO Auto-generated method stub return null; }
	 * 
	 * public Socket createSocket(Socket socket, String host, int port, boolean
	 * autoClose) throws IOException, UnknownHostException { // TODO
	 * Auto-generated method stub return null; }
	 * 
	 * }
	 */

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {

		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * @param args
	 * @throws NoSuchProviderException
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws Exception {
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

		HttpGet httpGet = new HttpGet(
				"https://login.alibaba-inc.com/ssoLogin.htm");
		httpGet.addHeader(
				HTTP.USER_AGENT,
				"User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");
		httpGet.addHeader("Referer",
				"https://login.alibaba-inc.com/ssoCaLogin.htm");

		httpGet.getParams().setParameter("http.protocol.content-charset",
				HTTP.UTF_8);
		httpGet.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
		// httpGet.getParams().setParameter("RETURN_USER","true");
		// httpGet.getParams().setParameter("SSO_TOKEN","ada");
		httpGet.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
		httpGet.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET,
				HTTP.UTF_8);
		// 创建http请求(get方式)
		/*
		 * HttpPost httpPost = new
		 * HttpPost("https://login.alibaba-inc.com/ssoLogin.htm");
		 * httpPost.getParams().setParameter("_csrf_token",
		 * "xD89cKFDowL9JWlWURMNP1");
		 * httpPost.getParams().setParameter("return", "");
		 * httpPost.getParams().setParameter("_fm.l._0.a", "wb-xuqiang");
		 * httpPost.getParams().setParameter("_fm.l._0.p", "woaizq1314");
		 * httpPost.getParams().setParameter("action", "SSOLoginAction");
		 * httpPost.getParams().setParameter("login_maintain", "true");
		 * httpPost.getParams().setParameter("event_submit_do_sso_login",
		 * "true");
		 */
		/*
		 * PostMethod postMethod = new
		 * PostMethod("https://login.alibaba-inc.com/ssoLogin.htm");
		 * 
		 * NameValuePair csrf = new NameValuePair("_csrf_token",
		 * "TB44J3ihI5u7UXmBEcV8s4"); NameValuePair re = new
		 * NameValuePair("return", ""); NameValuePair name = new
		 * NameValuePair("_fm.l._0.a", "wb-xuqiang"); NameValuePair pass = new
		 * NameValuePair("_fm.l._0.p", "woaizq1314"); NameValuePair action = new
		 * NameValuePair("action", "SSOLoginAction"); NameValuePair login = new
		 * NameValuePair("login_maintain", "true"); NameValuePair event = new
		 * NameValuePair("event_submit_do_sso_login", "true");
		 * 
		 * postMethod.setRequestBody( new
		 * NameValuePair[]{csrf,re,name,pass,action,login,event});
		 */
		// GetMethod getMethod = new
		// GetMethod("https://login.alibaba-inc.com/ssoLogin.htm");
		// getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
		// new DefaultHttpMethodRetryHandler());
		try {
			HttpContext context = new BasicHttpContext();
			CookieStore cookieStore = new BasicCookieStore();

			BasicClientCookie cookie1 = new BasicClientCookie(
					"tmp0",
					"eNotik0LgjAAQC9BRNSP8KywueXXreZi%2BZltJdhhiEoM0iQUgui%2FV9ThXd57bHm9XVQny76XXdk2082RSM7T2diqOvqmyXAfm%2F%2FVlqobPog5WSeERpLQgxALTjnfpYmMU59G4PxUtac5hWtZvg2NPPcZE3tI8iwkSGRxgULTwFvmnljADBAHGdT0avAgsrGFAHDclYP1svoJbELTQqattw8PvIo3ThozqQ%3D%3D");
			BasicClientCookie cookie2 = new BasicClientCookie(
					"umidTokenCookie", "T5e3c85124c419162a2e61c9ea713b800");
			BasicClientCookie cookie3 = new BasicClientCookie("JSESSIONID",
					"8Z966D71-WWDHHTP1CWQKC3TQMZ3K2-4FH9VHJH-0MJQ1");
			
			BasicClientCookie cookie4 = new BasicClientCookie("_umdata",
					"E01DF77D7C562F60810E1BC0741E9F547B17F2DC9EEBB7B691913F2FA2ABF62F2FEBE511EC4181F478C149980BFE3AE22FEFF2507951A3E8B467B6D724C206692FAE7129019D7B6A");

			
			cookie1.setPath("/");
			cookie2.setPath("/");
			cookie3.setPath("/");
			cookie4.setPath("/");
			cookie1.setDomain("login.alibaba-inc.com");
			cookie2.setDomain("login.alibaba-inc.com");
			cookie3.setDomain("login.alibaba-inc.com");
			cookie4.setDomain("login.alibaba-inc.com");
			cookieStore.addCookie(cookie2);
			cookieStore.addCookie(cookie1);
			cookieStore.addCookie(cookie3);
			cookieStore.addCookie(cookie4);
			// Bind custom cookie store to the local context
			context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

			HttpResponse response = httpClient.execute(httpGet, context);
			
			String responseContent = print( response,context,cookieStore);
			Pattern pattern = Pattern
					.compile("<input name='_csrf_token' type='hidden' value='(.+?)'>");
			Matcher matcher = pattern.matcher(responseContent);
			StringBuffer buffer = new StringBuffer();
			while (matcher.find()) {
				buffer.append(matcher.group(1));
				System.out.println("【_csrf_token】:"+buffer.toString());
				break;
			}
			BasicClientCookie cookie5 = new BasicClientCookie("tmp0",
					"eNrz4MvJT8%2FMi08sKIjPS8xNZXcKdY4PDvaHCucmZuaVAHEIZ2luZooPSIylpKg0lTfYNTjY098v3tffxdXHILo6M8VKySLK0szMxdxQNzzcxcMjJMDQOTzQ29k4JNA3ytjbSNfEzcMyzMPLQ9fA1yvQUEknucTK0NjcxMzYwMDC0tTCRCcxGSJgYmRoZGZqYaiTW2FlUBsFALhTMBk%3D");
			cookie5.setPath("/");
			cookie5.setDomain("login.alibaba-inc.com");
			cookieStore = (CookieStore)context.getAttribute(ClientContext.COOKIE_STORE);
			cookieStore.addCookie(cookie5);
			
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
			//登陆
			HttpPost httpPost = new HttpPost("https://login.alibaba-inc.com/ssoLogin.htm");
			 httpPost.getParams().setParameter("return", "");
			 httpPost.getParams().setParameter("_fm.l._0.a", "wb-xuqiang");
			 httpPost.getParams().setParameter("_fm.l._0.p", "woaizq1314");
			 httpPost.getParams().setParameter("action", "SSOLoginAction");
			 httpPost.getParams().setParameter("login_maintain", "true");
			 httpPost.getParams().setParameter("event_submit_do_sso_login","true");
			 httpPost.getParams().setParameter("_csrf_token",buffer.toString());
			 
			 
			 httpPost.addHeader(
						HTTP.USER_AGENT,
						"User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");
			 httpPost.addHeader("Referer",
						"https://login.alibaba-inc.com/ssoLogin.htm");

			 httpPost.getParams().setParameter("http.protocol.content-charset",
						HTTP.UTF_8);
			 httpPost.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
				// httpGet.getParams().setParameter("RETURN_USER","true");
				// httpGet.getParams().setParameter("SSO_TOKEN","ada");
			 httpPost.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
			 httpPost.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET,
						HTTP.UTF_8);
			 response = httpClient.execute(httpPost, context);
			 responseContent = print(response,context,cookieStore);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

	}
	public static String print(HttpResponse response,HttpContext context,CookieStore cookieStore) throws Exception{
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
