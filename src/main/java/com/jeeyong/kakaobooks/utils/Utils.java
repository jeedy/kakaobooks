package com.jeeyong.kakaobooks.utils;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

public class Utils {

	public static String escapeXml(String source) {

		source = source.replaceAll("&", "&amp;");
		source = source.replaceAll("<", "&lt;");
		source = source.replaceAll(">", "&gt;");
		source = source.replaceAll("\"", "&#034;");
		source = source.replaceAll("'", "&#039;");
		source = source.replaceAll("\n", " ");
		return source;
	}

	/**
	 * 날짜를 특정한 형식으로 변환해주는 메소드.
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 */
	public static String fmtDate(Date date, String fmt) {
		if (date == null || fmt == null) {
			return "";
		}

		SimpleDateFormat sdf = new SimpleDateFormat(fmt);

		return sdf.format(date);
	}

	public static String fmtDate(Timestamp date, String fmt) {
		if (date == null || fmt == null)
			return "";

		return Utils.fmtDate(new Date(date.getTime()), fmt);
	}

	/**
	 * 숫자형을 String 으로 변환해 주는 메소드.
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	public static String fmtNumber(double value, String format) {

		DecimalFormat fd = new DecimalFormat(format);
		return fd.format(value);
	}

	/**
	 * URI에 대해 폴더만 추출해 주는 메소드.
	 * 
	 * @param req
	 * @return
	 */
	public static String getRequestURI(HttpServletRequest req) {

		//
		String returnValue = req.getRequestURI();
		returnValue = returnValue.substring(0, returnValue.lastIndexOf("/") + 1);
		return returnValue;
	}

	/**
	 * 문자열을 날짜데이타로 바꾸어 주는 메소드.
	 * 
	 * @param strDate
	 * @param fmt
	 * @return
	 * @throws Exception
	 */
	public static Date toDate(String strDate, String fmt) {

		SimpleDateFormat sdfmt = new SimpleDateFormat(fmt);
		Date date = null;
		try {
			date = new Date(sdfmt.parse(strDate).getTime());
		} catch (ParseException e) {
			// e.printStackTrace();
		}
		return date;
	}

	/**
	 * 원하는 시간을 timestamp로 찍어준다.
	 * 
	 * @param date
	 * @param hour
	 *            0~24
	 * @param min
	 *            0~60
	 * @param sec
	 *            0~60
	 * @return
	 */
	public static Timestamp dateToTimetsamp(Date date, int hour, int min, int sec) {
		//
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, sec);
		cal.set(Calendar.MILLISECOND, 0);
		return new Timestamp(cal.getTimeInMillis());
	}


	/**
	 * timemillis 값을 시간 분에 대한 string 값으로 변경해주는 메소드.
	 * 
	 * @param millis
	 * @return
	 */
	public static String changeMillisToHour(long millis) {
		return changeMillisToHour(millis, true, true, true);
	}

	/**
	 * timemillis 값을 시간 분에 대한 string 값으로 변경해주는 메소드.
	 * 
	 * @param millis
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static String changeMillisToHour(long millis, boolean hour, boolean minute, boolean second) {

		StringBuffer returnValue = new StringBuffer();

		// hour
		if (hour) {
			if ((Math.abs(millis / 1000) / 60 / 60) != 0) {
				returnValue.append(Math.abs(millis / 1000) / 60 / 60);
				returnValue.append("시간 ");
			}
		}

		// minute
		if (minute) {
			if (Math.abs((Math.abs(millis / 1000) / 60) % 60) < 10) {
				returnValue.append("0");
			}
			returnValue.append((Math.abs(millis / 1000) / 60) % 60);
			returnValue.append("분 ");
		}

		// second
		if (second) {
			if (Math.abs(millis / 1000) % 60 < 10) {
				returnValue.append("0");
			}
			returnValue.append(Math.abs(millis / 1000) % 60);
			returnValue.append("초");
		}

		//
		return returnValue.toString();
	}

	/**
	 * 인코딩 타입을 변경해주는 메소드
	 * 
	 * @param source
	 * @param encodeType
	 * @return
	 */
	public static String encodeStr(byte[] source, String encodeType) {

		//
		String returnValue = "";

		//
		try {

			returnValue = new String(source, 0, source.length, encodeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//
		return returnValue;
	}

	/**
	 * 인코딩 타입을 변경해주는 메소드
	 * 
	 * @param source
	 * @param encodeType
	 * @return
	 */
	public static String encodeStr(String source, String encodeType) {

		return encodeStr(source, "8859_1", encodeType);
	}

	/**
	 * 인코딩 타입을 변경해 주는 메소드.
	 * 
	 * @param source
	 * @return
	 */
	public static String encodeStr(String source) {

		return encodeStr(source, "UTF-8");
	}

	/**
	 * 인코딩 타입을 변경해 주는 메소드.
	 * 
	 * @param source
	 * @param sourceEncodeType
	 * @param targetEncodeType
	 * @return
	 */
	public static String encodeStr(String source, String sourceEncodeType, String targetEncodeType) {

		String returnValue = null;
		try {
			returnValue = new String(source.getBytes(sourceEncodeType), targetEncodeType);
		} catch (Exception e) {
		}

		return returnValue;
	}

	/**
	 * 배열로 들어온 경우에도 처리해 준다.
	 * 
	 * @param sources
	 * @return
	 */
	public static String[] encodeStr(String[] sources) {

		String[] returnValue = null;

		String[] source = sources;
		if (source == null) {
			return null;
		} else {

			returnValue = new String[sources.length];
			for (int i = 0; i < source.length; i++) {
				returnValue[i] = encodeStr(source[i]);
			}
		}

		return returnValue;
	}

	/**
	 * url로 접근 해서 source를 받아오는 메소드 (POST방식) created by jeeyong 타 웹사이트의 API를 Ajax에서
	 * 불러오기 위한 방법으로 만들었음.
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String getHttp2String(String url, Map<String, String> param) throws Exception {

		return getHttpPOST2String(url, null, param, false);
	}

	/**
	 * url로 접근 해서 source를 받아오는 메소드 (GET방식) created by jeeyong 타 웹사이트의 API를 Ajax에서
	 * 불러오기 위한 방법으로 만들었음.
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String getHttpGET2String(String url, Map<String, String> params) throws Exception {
		return getHttpGET2String(url, null, params);
	}

	/**
	 * url로 접근 해서 source를 받아오는 메소드 (GET방식) created by jeeyong 타 웹사이트의 API를 Ajax에서
	 * 불러오기 위한 방법으로 만들었음.
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String getHttpGET2String(String url, Map<String, String> headers, Map<String, String> params)
			throws Exception {

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (url.lastIndexOf("?") > -1) {
					url = url + "&" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
				} else {
					url = url + "?" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
				}
			}
		}

		//
		HttpGet get = new HttpGet(url);
		if (headers != null && !headers.isEmpty()) {

			for (String key : headers.keySet()) {

				get.addHeader(key, headers.get(key));
			}
		}

		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(get);

		HttpEntity entity = response.getEntity();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		entity.writeTo(out);

		return new String(out.toByteArray(), Charset.forName("UTF-8"));
	}

	/**
	 * SSL 인증서를 사용하는 요청에 대해 처리해 주는 부분
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String getHttpsGET2String(String url, Map<String, String> headers, Map<String, String> params)
			throws Exception {

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (url.lastIndexOf("?") > -1) {
					url = url + "&" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
				} else {
					url = url + "?" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
				}
			}
		}

		//
		/* Start of Fix */
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}
		} };

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		/* End of the fix */

		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		if (headers != null && !headers.isEmpty()) {

			for (String key : headers.keySet()) {

				con.addRequestProperty(key, headers.get(key));
			}
		}

		Reader reader = new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8"));

		StringBuffer returnValue = new StringBuffer();
		while (true) {
			int ch = reader.read();
			if (ch == -1)
				break;

			returnValue.append((char) ch);
		}

		//
		return returnValue.toString();

	}

	/**
	 * SSL 인증서를 사용하는 요청에 대해 처리해 주는 부분
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String getHttpsPOST2String(String url, Map<String, String> headers, Map<String, String> params)
			throws Exception {

		String post_parmas = "";
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				post_parmas = post_parmas + "&" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
			}
		}

		//
		/* Start of Fix */
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}

			@Override
			public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
					throws CertificateException {
			}
		} };

		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

		// Create all-trusting host name verifier
		HostnameVerifier allHostsValid = new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		};
		// Install the all-trusting host verifier
		HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
		/* End of the fix */

		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		if (headers != null && !headers.isEmpty()) {

			for (String key : headers.keySet()) {

				con.addRequestProperty(key, headers.get(key));
			}
		}
		OutputStream os = con.getOutputStream();
		os.write(post_parmas.getBytes());
		os.flush();
		os.close();

		Reader reader = new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8"));

		StringBuffer returnValue = new StringBuffer();
		while (true) {
			int ch = reader.read();
			if (ch == -1)
				break;

			returnValue.append((char) ch);
		}

		//
		return returnValue.toString();

	}

	/**
	 * (비인증) SSL 인증서를 통과 시켜주는 소스 {@link <a href=
	 * "http://beyondj2ee.tumblr.com/post/14507308404/httpcomponents-httpclient-4x%EC%97%90%EC%84%9C-https-%EC%84%9C%EB%B2%84-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0">http://beyondj2ee.tumblr.com/post/14507308404/httpcomponents-httpclient-4x%EC%97%90%EC%84%9C-https-%EC%84%9C%EB%B2%84-%EC%97%B0%EB%8F%99%ED%95%98%EA%B8%B0</a>
	 * }
	 * 
	 * @param httpclient
	 * @throws Exception
	 */
	private static void setTestHttpClient(HttpClient httpclient) throws Exception {
		TrustManager easyTrustManager = new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		};

		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(null, new TrustManager[] { easyTrustManager }, null);
		//
		SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext,
				org.apache.http.conn.ssl.SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);

		// 본인 인증 방식일 경우 (Self-Signed Certificate)
		// SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext,
		// SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		Scheme sch = new Scheme("https", 443, socketFactory);
		httpclient.getConnectionManager().getSchemeRegistry().register(sch);
	}

	/**
	 * url로 접근 해서 source를 받아오는 메소드 (GET방식) created by kimjy 타 웹사이트의 API를 Ajax에서 불러오기 위한 방법으로 만들었음.
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String getHttpGET2String(String url, Map<String, String> headers, Map<String, String> params,
			boolean isTest) throws Exception {

		HttpClient httpclient = new DefaultHttpClient();
		String responseBody = null;
		try {
			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					if (url.lastIndexOf("?") > -1) {
						url = url + "&" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
					} else {
						url = url + "?" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
					}
				}
			}

			//
			HttpGet get = new HttpGet(url);
			if (headers != null && !headers.isEmpty()) {

				for (String key : headers.keySet()) {

					get.addHeader(key, headers.get(key));
				}
			}

			if (isTest) {
				setTestHttpClient(httpclient);
			}
			HttpResponse response = httpclient.execute(get);
			responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");

		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return responseBody;
	}

	/**
	 * url로 접근 해서 source를 받아오는 메소드 (POST방식) created by kimjy 타 웹사이트의 API를 Ajax에서
	 * 불러오기 위한 방법으로 만들었음.
	 * 
	 * @throws Exception
	 */
	public static String getHttpPOST2String(String url, Map<String, String> headers, Map<String, String> params,
			boolean isTest) throws Exception {
		//
		HttpClient httpclient = new DefaultHttpClient();
		String responseBody = null;
		try {
			HttpPost post = new HttpPost(url);
			if (params != null && !params.isEmpty()) {

				List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
				for (String key : params.keySet()) {
					parameters.add(new BasicNameValuePair(key, params.get(key)));
				}

				UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(parameters, "UTF-8");
				post.setEntity(reqEntity);
			}

			if (headers != null && !headers.isEmpty()) {

				for (String key : headers.keySet()) {

					post.addHeader(key, headers.get(key));
				}
			}

			if (isTest) {
				setTestHttpClient(httpclient);
			}
			HttpResponse response = httpclient.execute(post);
			responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return responseBody;
	}

	/**
	 * url로 접근 해서 source를 받아오는 메소드 (DELETE방식) created by kimjy 타 웹사이트의 API를 Ajax에서
	 * 불러오기 위한 방법으로 만들었음.
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String getHttpDELETE2String(String url, Map<String, String> headers, Map<String, String> params,
			boolean isTest) throws Exception {
		//
		HttpClient httpclient = new DefaultHttpClient();
		String responseBody = null;
		try {
			HttpDelete delete = new HttpDelete(url);

			if (params != null && !params.isEmpty()) {
				for (String key : params.keySet()) {
					if (url.lastIndexOf("?") > -1) {
						url = url + "&" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
					} else {
						url = url + "?" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
					}
				}
			}

			if (headers != null && !headers.isEmpty()) {
				for (String key : headers.keySet()) {
					delete.addHeader(key, headers.get(key));
				}
			}

			if (isTest) {
				setTestHttpClient(httpclient);
			}
			HttpResponse response = httpclient.execute(delete);
			responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return responseBody;
	}

	/**
	 * url로 접근 해서 source를 받아오는 메소드 (GET방식) created by kimjy 타 웹사이트의 API를 Ajax에서
	 * 불러오기 위한 방법으로 만들었음.
	 * 
	 * @param url
	 * @param headers
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String getHttpDELETE2String(String url, Map<String, String> headers, Map<String, String> params)
			throws Exception {
		HttpDelete delete = new HttpDelete(url);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				if (url.lastIndexOf("?") > -1) {
					url = url + "&" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
				} else {
					url = url + "?" + key + "=" + URLEncoder.encode(params.get(key), "UTF-8");
				}
			}
		}

		if (headers != null && !headers.isEmpty()) {

			for (String key : headers.keySet()) {

				delete.addHeader(key, headers.get(key));
			}
		}

		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(delete);

		HttpEntity entity = response.getEntity();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		entity.writeTo(out);

		return new String(out.toByteArray(), Charset.forName("UTF-8"));
	}

}
