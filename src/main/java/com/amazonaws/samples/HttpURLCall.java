package com.amazonaws.samples;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLCall {

	private final String USER_AGENT = "Chrome/63.0";

	public static void main(String[] args) throws Exception {

		HttpURLCall http = new HttpURLCall();

		http.sendingGetRequest();
		// http.sendingPostRequest();
	}

	private void sendingGetRequest() throws Exception {

		String urlString = "https://api.ghostinspector.com/v1/tests/5a4cb4153be6b23ca8db9468/execute/?apiKey=e0d29ac1d75fcb8e7beb5e8e6db718d7cf45507d&startUrl=http://ec2-184-73-144-250.compute-1.amazonaws.com:9092/DevOpsWebAppTest/index.jsp";

		URL url = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

		// Add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("Sending get request : " + url);
		System.out.println("Response code : " + responseCode);

		// Reading response from input Stream
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();

		while ((output = in.readLine()) != null) {
			response.append(output);
		}
		in.close();

		// printing result from response
		System.out.println(response.toString());

	}

}
