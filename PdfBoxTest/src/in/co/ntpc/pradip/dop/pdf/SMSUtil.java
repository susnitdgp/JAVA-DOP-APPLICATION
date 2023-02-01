package in.co.ntpc.pradip.dop.pdf;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;

public class SMSUtil {
	
	private final static String USER_AGENT = "Mozilla/5.0";
	
	public static void sendSms(String recipient,String message) throws Exception{
		
		String message_final=URLEncoder.encode(message, "UTF-8");
		
		//String url = "http://10.1.254.1:8080/ntpcd/URL2_Sender?locid=002040&msg="+message_final + "&num="+recipient;

		
		String url ="http://api.instaalerts.zone/SendSMS/sendmsg.php?uname=ROLNTPCLIVE&pass=f5e7J!G9&send=PRADIP&dest=" + recipient +"&msg="+message_final;
		
		
		//Proxy instance, proxy ip = 10.0.9.54 with port 8080
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.9.54", 8080));
		
				
		URL obj = new URL(url);
		
		HttpURLConnection con = (HttpURLConnection) obj.openConnection(proxy);
		
		
		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		
	}

}
