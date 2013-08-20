package com.test4;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
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


public class Test6 {

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
				"https://login.alibaba-inc.com/ssoLogin.htm");

		httpGet.getParams().setParameter("http.protocol.content-charset",
				HTTP.UTF_8);
		httpGet.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
		// httpGet.getParams().setParameter("RETURN_USER","true");
		// httpGet.getParams().setParameter("SSO_TOKEN","ada");
		httpGet.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
		httpGet.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET,
				HTTP.UTF_8);
			HttpContext context = new BasicHttpContext();
			CookieStore cookieStore = new BasicCookieStore();

			
			/*JSESSIONID=8Z966D71-WWDHHTP1CWQKC3TQMZ3K2-4FH9VHJH-0MJQ1; 
			tmp0=eNpFjctKw0AYhUEUq6C4dtl1MiRNzG0lTVtib7EkNVAXcTId60Bmpk5maEB8Bxc%2BjYhv0%2FcwwYKLA4dzzv%2F90RVSQmAm80KhXFVYhIaLOAWwJAUsINiWUD5zQUHTg6riYIcLoAhoNpQzAJV8AX2Fls3lZ0dgWM4hxZ0W1JpTiBBXTJ4IXuLq6Wj%2F87H%2F%2FjrfFXqtXglkm%2Bt%2Fe3t4qROGWnx9WfINYTncbnPWsvrLME%2BS%2BExRsp621bEUCh9WFBImG6UXyTBJ7uJ5PosHw6nx%2BEbWQddb%2BY4zcE09ywZRlN6bYbaYhFa6mK2sSU%2B3R5H%2FEI0j3ZiNF2ZXQzIwLdd2LMPw%2FBvP1iD6C%2Bxez%2FdNx9NoHRjvq1%2BBEWu%2F; _
			_umdata=E01DF77D7C562F60810E1BC0741E9F547B17F2DC9EEBB7B691913F2FA2ABF62F2FEBE511EC4181F478C149980BFE3AE22FEFF2507951A3E8B467B6D724C206692FAE7129019D7B6A; 
			umidTokenCookie=T5e3c85124c419162a2e61c9ea713b800; 
			BUC_SSO_COOKIE=32D620931AEFB31597E3C8A34B30788E; 
			auth_type=F713C2B682978C36D9A7BC8EA61BDDE1		*/
			BasicClientCookie cookie1 = new BasicClientCookie(
					"tmp0",
					"eNpFjctKw0AYhUEUq6C4dtl1MiRNzG0lTVtib7EkNVAXcTId60Bmpk5maEB8Bxc%2BjYhv0%2FcwwYKLA4dzzv%2F90RVSQmAm80KhXFVYhIaLOAWwJAUsINiWUD5zQUHTg6riYIcLoAhoNpQzAJV8AX2Fls3lZ0dgWM4hxZ0W1JpTiBBXTJ4IXuLq6Wj%2F87H%2F%2FjrfFXqtXglkm%2Bt%2Fe3t4qROGWnx9WfINYTncbnPWsvrLME%2BS%2BExRsp621bEUCh9WFBImG6UXyTBJ7uJ5PosHw6nx%2BEbWQddb%2BY4zcE09ywZRlN6bYbaYhFa6mK2sSU%2B3R5H%2FEI0j3ZiNF2ZXQzIwLdd2LMPw%2FBvP1iD6C%2Bxez%2FdNx9NoHRjvq1%2BBEWu%2F");
			BasicClientCookie cookie2 = new BasicClientCookie(
					"umidTokenCookie", "T5e3c85124c419162a2e61c9ea713b800");
			BasicClientCookie cookie3 = new BasicClientCookie("JSESSIONID",
					"8Z966D71-WWDHHTP1CWQKC3TQMZ3K2-4FH9VHJH-0MJQ1");
			
			BasicClientCookie cookie4 = new BasicClientCookie("_umdata",
					"E01DF77D7C562F60810E1BC0741E9F547B17F2DC9EEBB7B691913F2FA2ABF62F2FEBE511EC4181F478C149980BFE3AE22FEFF2507951A3E8B467B6D724C206692FAE7129019D7B6A");
			
			BasicClientCookie cookie5 = new BasicClientCookie("BUC_SSO_COOKIE",
					"32D620931AEFB31597E3C8A34B30788E");

			BasicClientCookie cookie6 = new BasicClientCookie("auth_type",
					"F713C2B682978C36D9A7BC8EA61BDDE1");
			
			

			
			cookie1.setPath("/");
			cookie2.setPath("/");
			cookie3.setPath("/");
			cookie4.setPath("/");
			cookie5.setPath("/");
			cookie6.setPath("/");
			cookie1.setDomain("login.alibaba-inc.com");
			cookie2.setDomain("login.alibaba-inc.com");
			cookie3.setDomain("login.alibaba-inc.com");
			cookie4.setDomain("login.alibaba-inc.com");
			cookie5.setDomain("login.alibaba-inc.com");
			cookie6.setDomain("login.alibaba-inc.com");
			cookieStore.addCookie(cookie2);
			cookieStore.addCookie(cookie1);
			cookieStore.addCookie(cookie3);
			cookieStore.addCookie(cookie4);
			cookieStore.addCookie(cookie5);
			cookieStore.addCookie(cookie6);
			// Bind custom cookie store to the local context
			context.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

			HttpResponse response = httpClient.execute(httpGet, context);
			
			String responseContent = print( response,context,cookieStore);
			
			System.out.println("-----------------------------------------------");
			HttpContext context1 = new BasicHttpContext();
			CookieStore cookieStore1 = new BasicCookieStore();
			for(int i=0;i<cookieStore.getCookies().size();i++){
				BasicClientCookie co = new BasicClientCookie(cookieStore.getCookies().get(i).getName(),cookieStore.getCookies().get(i).getValue());
				co.setPath("/");
				co.setDomain("icbu.data.alibaba-inc.com");
				cookieStore1.addCookie(co);
			}
			
			BasicClientCookie co1 = new BasicClientCookie("user_name_out","wb-xuqiang");
			co1.setPath("/");
			co1.setDomain("icbu.data.alibaba-inc.com");
			context1.setAttribute(ClientContext.COOKIE_STORE, cookieStore1);
			cookieStore1.addCookie(co1);
			
			BasicClientCookie co2 = new BasicClientCookie("USER_COOKIE",
					"1CC6C7ABB2CB19012FCA1C8D702FBED332218FAC6E5E9CDB7906A398BBD81923445E6D73153920D923180158FDC71C86E8ED6FA9345ED9B1D268EFAF34F61B13DC42959F0A47E0491A0F2FD543AD655E662B5A402D10D30EDAB3C717089367B882FDAEDEDE0935F3074E960216B2EE8EAE7D27828C49A46D2DED6713250D3A94E161E8F5107496217DD05E812026A1499B071BA56F26AF45027E3C807BE18E99D18E62C28766CA7BCB8D4F5990747E6A489541A47C9ADFD2952AA8B95BED815AD637E71845C8FD2F7BF1CE4C22D256C78B456AC035F9900559795B49BD987B9F7634505D6FDB5538544686027216A7DA70A2E963FE98EAB9E367BA448E3D45D57897563BF7DABC57520A83E2874EF47FA2CC87F96903C9B398320C0546ECF853177A2C461C773DB84D9F911310120D9F848797114DED6B8D5ACF68CD294A0C80E8B24F894DFE2EDA2EC79B8439F9196B4CA863BB2069C02F4D150BA369D56ED7403E94C61FB1F5C385E0CC28D03437D7A1C588CE3A88E81735E899BA558C70A34069FC9CB05FCEF60603BA0D374A19C3C65170647DA2177962113727AEF67667D9936DB19BC1747285CAC6854F0835A1E7BA7301471D8D7D035081335F04955620925A253E4D368D9048EF2A14B8FF3EE56BB7E19F40316963DB5EE80DEDFD9CECE440A0B438911902FDB76A54663ABA0D1E1442D5348FF05675B053F5173601B8C8FC70504972827D546FB756A02F1CAEBB02E6BBDAC42AF63F1B4928DB3D16B0B6DB13F1F88A05B813AE138112A0B10A31BE43F9BE1649B06A41158D48646E7DDC75659901CD4EBB55FD11F81B01AB679DA8F8E7DBB415DEE2053481322CC74313051BCB083647C805D3833475B2419980E5BA485C1B83076FF4BE1EA73EEE4A905D882B4018C0ECDF2ECD56C8431544BF543AF97447A81140CE5BFAABF05134DB3150ABA4001D276D9FD31AE867B7E8E6F66B91D700ADF8337AE4132F0DB887B8AE7CD59DDF1EC15D51A22389E8C59398E82A3CBA82D45308682D8CE0FD3BE42B4EBB71E98CED446532D5B3C3E6861E4872F4FCC7D4FAD39232A436AE139F7CDE16CB1290DCD42E1569488B55C613E37B535375D3C9FCFE1EF9A936DD05744334F3F0890F9049AD94718CEBB196C215E409FBD50470DFA860201AF0B1190B83725F131E9FE2D30E7DA60C45667DE8FB6013C7093C5E5FF247E6D6CF6A35C4FDFCEA41A8A19C098ADBC6E1512DF26B0D829543B180F165AFD6666D29028929D508255AA285FC667CE54BA968C7ECF0ADFD13E9D52D108BF22B73381291971A3ABD37109AC4827E66FEACE076AE4B8D87F72FA74472D83080F4427AF907E1275B0C35C483F437760CC7DC5F37A7AB3F5B921303ED2FC71BFF96AD0506B0BF6F409289011C323B9928DC35B189648C5B176210A53C05CC91C8F4D98CBDCF4D87340F8166A5B622C37DA91E36665DD42D7B6666CEDE7A07E9A86D6CA8B00A5A9DB819F5D26E8FA871CEE3170098C33899444B50675EC1EB2ECC73CB62CB35D6CA9E975804A9D7F363A04B930B464CD8B8");
			co2.setPath("/");
			co2.setDomain("icbu.data.alibaba-inc.com");
			context1.setAttribute(ClientContext.COOKIE_STORE, cookieStore1);
			cookieStore1.addCookie(co2);
			
			
			HttpPost httpPost = new HttpPost("http://icbu.data.alibaba-inc.com/migrate/opensql/table/detail.htm?tableName=odl_a01_im_send_msg");
			//=&=D&site=en&pageNo=1&pageSize=15&keyword=a 
			 httpPost.getParams().setParameter("timestamp", "1374643708220");
			 httpPost.getParams().setParameter("type", "D");
			 httpPost.getParams().setParameter("site", "en");
			 httpPost.getParams().setParameter("pageNo", "1");
			 httpPost.getParams().setParameter("pageSize", "15");
			 httpPost.getParams().setParameter("keyword","a");
			 
			 
			 httpPost.addHeader(
						HTTP.USER_AGENT,
						"User-Agent:Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/28.0.1500.72 Safari/537.36");

			 httpPost.getParams().setParameter("http.protocol.content-charset",
						HTTP.UTF_8);
			 httpPost.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
				// httpGet.getParams().setParameter("RETURN_USER","true");
				// httpGet.getParams().setParameter("SSO_TOKEN","ada");
			 httpPost.getParams().setParameter(HTTP.CHARSET_PARAM, HTTP.UTF_8);
			 httpPost.getParams().setParameter(HTTP.DEFAULT_PROTOCOL_CHARSET,
						HTTP.UTF_8);
			 response = httpClient.execute(httpPost, context1);
			 responseContent = print(response,context,cookieStore);

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
