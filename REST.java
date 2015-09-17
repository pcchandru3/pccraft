package functionallibraries;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;
import org.junit.Test;

public class Rest{
	public String strHost = null;
	public String storeId = null;
	public String zipCode = null;
	public String catagoryId = null;
	public String locale = null;

	public Rest(String strHost){
		this.strHost = strHost.toLowerCase().replace("http://", "");
		if(strHost.toLowerCase().contains("staples.com")){
			this.storeId = "10001";
			this.zipCode = "01702";
			this.catagoryId = "10051";
			this.locale = "en_US";
		} else{
			this.storeId = "20001";
			this.zipCode = "M5G2C2";
			this.catagoryId = "20051";
			this.locale = "en_CA";
		}
	}

	static{
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier(){
			@Override
			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession){
				if(hostname.contains("staples.com") || hostname.contains("staples.ca"))
					return true;
				return false;
			}
		});
	}

	public static void main(String[] args) throws Exception{
		Rest test = new Rest("qa16.staples.com");
		// test.registerNewUser("vaibhavsankaye", "password","Yes");
		try{
			test.deleteCartItems("vaibhavsankaye", "password");
		} catch(Exception e){
			if(e.toString().contains("400")){
				test.registerNewUser("vaibhavsankaye", "password", "No");
			}
		}
		test.deleteAddress("vaibhavsankaye", "password");
		test.deleteCoupon("vaibhavsankaye", "password");
	}

	@Test
	public void deleteCartItems(String strUsername, String strPassword) throws Exception{
		String[] param = {"logonId", "logonPassword"};
		String[] paramValues = {strUsername, strPassword};

		String WCToken, WCTrustedToken, orderItemId;
		WCToken = null;
		WCTrustedToken = null;
		orderItemId = null;

		String[] response = httpPost(
				"https://" + strHost + "/wcs/resources/store/" + storeId + "/loginidentity?responseFormat=json", param,
				paramValues).split("\n");
		for(int nLoop = 0; nLoop < response.length - 1; nLoop++){
			if(response[nLoop].contains("WCToken")){
				WCToken = response[nLoop].replace("<WCToken>", "");
				WCToken = WCToken.replace("</WCToken>", "");
				WCToken = WCToken.replace("WCToken", "");
				WCToken = WCToken.replace(":", "");
				WCToken = WCToken.replace("\"", "");
				WCToken = WCToken.replace(" ", "");
				WCToken = WCToken.replace("\t", "");
				WCToken = WCToken.replace(",", "");
			}

			if(response[nLoop].contains("WCTrustedToken")){
				WCTrustedToken = response[nLoop].replace("<WCTrustedToken>", "");
				WCTrustedToken = WCTrustedToken.replace("</WCTrustedToken>", "");
				WCTrustedToken = WCTrustedToken.replace("WCTrustedToken", "");
				WCTrustedToken = WCTrustedToken.replace(":", "");
				WCTrustedToken = WCTrustedToken.replace("\"", "");
				WCTrustedToken = WCTrustedToken.replace(" ", "");
				WCTrustedToken = WCTrustedToken.replace("\t", "");
				WCTrustedToken = WCTrustedToken.replace(",", "");
			}
		}

		if(WCToken != null){
			String[] paramGET = {"WCToken"};
			String[] paramValuesGET = {WCToken};
			response = httpGet(
					"http://" + strHost + "/wcs/resources/store/" + storeId + "/cart?zipCode=" + zipCode
							+ "&responseFormat=xml&locale=" + locale + "&catalogId=" + catagoryId, paramGET,
					paramValuesGET).split("\n");
			for(int nLoop = 0; nLoop < response.length - 1; nLoop++){
				if(response[nLoop].contains("<orderItemId>")){
					orderItemId = response[nLoop].trim().replace("<orderItemId>", "");
					orderItemId = orderItemId.replace("</orderItemId>", "");
					System.out.println(httpDelete("https://" + strHost + "/wcs/resources/store/" + storeId
							+ "/cart/id/" + orderItemId + "?responseFormat=xml&locale=" + locale, WCToken,
							WCTrustedToken));
				}
			}
		}
	}

	public boolean registerNewUser(String strUsername, String strPassword, String strSubscribe) throws Exception{
		String[] param = {"email1", "newReEnterEmailAddr", "emailPreference", "logonId", "logonPassword",
				"logonPasswordVerify", "autoLoginPreference", "challengeQuestion", "challengeAnswer",
				"preferredLanguage"};
		String[] paramValues = {"abc@xyz.com", "abc@xyz.com", "emailNo", strUsername, strPassword, strPassword, "0",
				"Your first pet's name?", "xyz", "English"};
		if(strSubscribe.toLowerCase().equals("yes")){
			paramValues[2] = "emailYes";
		}

		String WCToken, WCTrustedToken;
		WCToken = null;
		WCTrustedToken = null;

		String[] response = httpPost(
				"https://" + strHost + "/wcs/resources/store/" + storeId
						+ "/member/registeruser?responseFormat=json&locale=" + locale, param, paramValues).split("\n");
		for(int nLoop = 0; nLoop < response.length - 1; nLoop++){
			if(response[nLoop].contains("WCToken")){
				WCToken = response[nLoop].replace("<WCToken>", "");
				WCToken = WCToken.replace("</WCToken>", "");
				WCToken = WCToken.replace("WCToken", "");
				WCToken = WCToken.replace(":", "");
				WCToken = WCToken.replace("\"", "");
				WCToken = WCToken.replace(" ", "");
				WCToken = WCToken.replace("\t", "");
				WCToken = WCToken.replace(",", "");
			}

			if(response[nLoop].contains("WCTrustedToken")){
				WCTrustedToken = response[nLoop].replace("<WCTrustedToken>", "");
				WCTrustedToken = WCTrustedToken.replace("</WCTrustedToken>", "");
				WCTrustedToken = WCTrustedToken.replace("WCTrustedToken", "");
				WCTrustedToken = WCTrustedToken.replace(":", "");
				WCTrustedToken = WCTrustedToken.replace("\"", "");
				WCTrustedToken = WCTrustedToken.replace(" ", "");
				WCTrustedToken = WCTrustedToken.replace("\t", "");
				WCTrustedToken = WCTrustedToken.replace(",", "");
			}
		}

		if(WCToken != null)
			return true;
		else
			return false;

	}

	public void deleteAddress(String strUsername, String strPassword) throws Exception{
		String[] param = {"logonId", "logonPassword"};
		String[] paramValues = {strUsername, strPassword};

		String WCToken, WCTrustedToken, addressId;
		WCToken = null;
		WCTrustedToken = null;
		addressId = null;

		String[] response = httpPost(
				"https://" + strHost + "/wcs/resources/store/" + storeId + "/loginidentity?responseFormat=json", param,
				paramValues).split("\n");
		for(int nLoop = 0; nLoop < response.length - 1; nLoop++){
			if(response[nLoop].contains("WCToken")){
				WCToken = response[nLoop].replace("<WCToken>", "");
				WCToken = WCToken.replace("</WCToken>", "");
				WCToken = WCToken.replace("WCToken", "");
				WCToken = WCToken.replace(":", "");
				WCToken = WCToken.replace("\"", "");
				WCToken = WCToken.replace(" ", "");
				WCToken = WCToken.replace("\t", "");
				WCToken = WCToken.replace(",", "");
			}

			if(response[nLoop].contains("WCTrustedToken")){
				WCTrustedToken = response[nLoop].replace("<WCTrustedToken>", "");
				WCTrustedToken = WCTrustedToken.replace("</WCTrustedToken>", "");
				WCTrustedToken = WCTrustedToken.replace("WCTrustedToken", "");
				WCTrustedToken = WCTrustedToken.replace(":", "");
				WCTrustedToken = WCTrustedToken.replace("\"", "");
				WCTrustedToken = WCTrustedToken.replace(" ", "");
				WCTrustedToken = WCTrustedToken.replace("\t", "");
				WCTrustedToken = WCTrustedToken.replace(",", "");
			}
		}

		if(WCToken != null){
			String[] paramGET = {"WCToken", "WCTrustedToken"};
			String[] paramValuesGET = {WCToken, WCTrustedToken};
			response = httpsGet(
					"https://" + strHost + "/wcs/resources/store/" + storeId
							+ "/member/profile/address?responseFormat=xml&locale=" + locale, paramGET, paramValuesGET)
					.split("\n");
			for(int nLoop = 0; nLoop < response.length - 1; nLoop++){
				if(response[nLoop].contains("<addressId>")){
					addressId = response[nLoop].trim().replace("<addressId>", "");
					addressId = addressId.replace("</addressId>", "");
					System.out.println(httpDelete("https://" + strHost + "/wcs/resources/store/" + storeId
							+ "/member/profile/address/id/" + addressId + "?responseFormat=xml&locale=" + locale,
							WCToken, WCTrustedToken));
				}
			}
		}
	}

	public void deleteCoupon(String strUsername, String strPassword) throws Exception{
		String[] param = {"logonId", "logonPassword"};
		String[] paramValues = {strUsername, strPassword};

		String WCToken, WCTrustedToken, couponId;
		WCToken = null;
		WCTrustedToken = null;
		couponId = null;

		String[] response = httpPost(
				"https://" + strHost + "/wcs/resources/store/" + storeId + "/loginidentity?responseFormat=json", param,
				paramValues).split("\n");
		for(int nLoop = 0; nLoop < response.length - 1; nLoop++){
			if(response[nLoop].contains("WCToken")){
				WCToken = response[nLoop].replace("<WCToken>", "");
				WCToken = WCToken.replace("</WCToken>", "");
				WCToken = WCToken.replace("WCToken", "");
				WCToken = WCToken.replace(":", "");
				WCToken = WCToken.replace("\"", "");
				WCToken = WCToken.replace(" ", "");
				WCToken = WCToken.replace("\t", "");
				WCToken = WCToken.replace(",", "");
			}

			if(response[nLoop].contains("WCTrustedToken")){
				WCTrustedToken = response[nLoop].replace("<WCTrustedToken>", "");
				WCTrustedToken = WCTrustedToken.replace("</WCTrustedToken>", "");
				WCTrustedToken = WCTrustedToken.replace("WCTrustedToken", "");
				WCTrustedToken = WCTrustedToken.replace(":", "");
				WCTrustedToken = WCTrustedToken.replace("\"", "");
				WCTrustedToken = WCTrustedToken.replace(" ", "");
				WCTrustedToken = WCTrustedToken.replace("\t", "");
				WCTrustedToken = WCTrustedToken.replace(",", "");
			}
		}

		if(WCToken != null){
			String[] paramGET = {"WCToken"};
			String[] paramValuesGET = {WCToken};
			response = httpGet(
					"http://" + strHost + "/wcs/resources/store/" + storeId + "/coupon?responseFormat=xml&locale="
							+ locale, paramGET, paramValuesGET).split("\n");
			for(int nLoop = 0; nLoop < response.length - 1; nLoop++){
				if(response[nLoop].contains("<code>")){
					couponId = response[nLoop].trim().replace("<code>", "");
					couponId = couponId.replace("</code>", "");
					System.out.println(httpDelete("https://" + strHost + "/wcs/resources/store/" + storeId
							+ "/coupon/id/" + couponId + "?responseFormat=xml&locale=" + locale, WCToken,
							WCTrustedToken));
				}
			}
		}
	}

	public String httpGet(String urlStr, String[] paramGET, String[] paramValuesGET) throws IOException{
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// Buffer the result into a string
		conn.setRequestMethod("GET");
		for(int nLoop = 0; nLoop <= paramGET.length - 1; nLoop++){
			conn.setRequestProperty(paramGET[nLoop], paramValuesGET[nLoop]);
		}
		conn.setRequestProperty("SPLS_AUTH", "r1ckgr1mes7275");
		conn.connect();

		try{
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = rd.readLine()) != null){
				sb.append(line + "\n");
			}
			rd.close();

			conn.disconnect();
			return sb.toString();
		} catch(Exception e){
			return "no coupon";
		}
	}

	public String httpsGet(String urlStr, String[] paramGET, String[] paramValuesGET) throws IOException{
		URL url = new URL(urlStr);
		SSLContext ssl = null;
		try{
			ssl = SSLContext.getInstance("TLSv1");
		} catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		try{
			ssl.init(null, new TrustManager[]{new SimpleX509TrustManager()}, null);
		} catch(KeyManagementException e){
			e.printStackTrace();
		}
		SSLSocketFactory factory = ssl.getSocketFactory();
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setSSLSocketFactory(factory);

		// Buffer the result into a string
		conn.setRequestMethod("GET");
		for(int nLoop = 0; nLoop <= paramGET.length - 1; nLoop++){
			conn.setRequestProperty(paramGET[nLoop], paramValuesGET[nLoop]);
		}
		conn.setRequestProperty("SPLS_AUTH", "r1ckgr1mes7275");
		conn.connect();

		try{
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while((line = rd.readLine()) != null){
				sb.append(line + "\n");
			}
			rd.close();

			conn.disconnect();
			return sb.toString();
		} catch(Exception e){
			return "Invalid";
		}
	}

	public String httpDelete(String urlStr, String WCToken, String WCTrustedToken) throws IOException{
		URL url = new URL(urlStr);
		SSLContext ssl = null;
		try{
			ssl = SSLContext.getInstance("TLSv1");
		} catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		try{
			ssl.init(null, new TrustManager[]{new SimpleX509TrustManager()}, null);
		} catch(KeyManagementException e){
			e.printStackTrace();
		}
		SSLSocketFactory factory = ssl.getSocketFactory();
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setSSLSocketFactory(factory);
		// Buffer the result into a string
		conn.setRequestMethod("DELETE");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("WCToken", WCToken);
		conn.setRequestProperty("WCTrustedToken", WCTrustedToken);
		conn.setRequestProperty("SPLS_AUTH", "r1ckgr1mes7275");
		conn.connect();

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line = rd.readLine()) != null){
			sb.append(line + "\n");
		}
		rd.close();

		conn.disconnect();
		return sb.toString();
	}

	public String httpPost(String urlStr, String[] paramName, String[] paramVal) throws Exception{

		URL url = new URL(urlStr);
		SSLContext ssl = null;
		try{
			ssl = SSLContext.getInstance("TLSv1");
		} catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		try{
			ssl.init(null, new TrustManager[]{new SimpleX509TrustManager()}, null);
		} catch(KeyManagementException e){
			e.printStackTrace();
		}
		SSLSocketFactory factory = ssl.getSocketFactory();

		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		conn.setSSLSocketFactory(factory);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("SPLS_AUTH", "r1ckgr1mes7275");
		conn.connect();

		// Create the json content
		JSONObject jsonParam = new JSONObject();
		for(int nLoop = 0; nLoop < paramName.length; nLoop++){
			jsonParam.put(paramName[nLoop], paramVal[nLoop]);
		}

		OutputStream out = conn.getOutputStream();
		DataOutputStream writer = new DataOutputStream(out);
		writer.writeBytes(jsonParam.toString());
		writer.close();
		out.flush();
		out.close();

		// Buffer the result into a string
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = rd.readLine()) != null){
			sb.append(line + "\n");
		}
		rd.close();
		conn.disconnect();
		return sb.toString();

	}

}

class SimpleX509TrustManager implements X509TrustManager{
	@Override
	public void checkClientTrusted(X509Certificate[] cert, String s) throws CertificateException{
	}

	@Override
	public void checkServerTrusted(X509Certificate[] cert, String s) throws CertificateException{
	}

	@Override
	public X509Certificate[] getAcceptedIssuers(){
		return null;
	}
}
