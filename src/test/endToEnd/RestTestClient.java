package endToEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestTestClient {

	public final String USER_AGENT = "Mozilla/5.0";
	public final String CONTENT_TYPE = "application/x-www-form-urlencoded";
	public final String URL = "http://localhost:8080/queue/";
	public final String QUEUE_URL = URL + "users";
	public final String DEQUEUE_URL = URL + "dequeue";
	public final String WAIT_TIME_URL = URL + "waitTime?timestamp=";
	public int tempResponseCode;
	
	public void sendGetUsers() throws Exception {
		URL obj = new URL(QUEUE_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// add request header
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", CONTENT_TYPE);
		
		// response
		int responseCode = con.getResponseCode();
		System.out.println("\nListing all Users");
		System.out.println("\nSending 'GET' request to URL : " + QUEUE_URL);
		System.out.println("Response Code : " + responseCode);
		
		tempResponseCode = responseCode;
		
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
	
	void sendPostUser (long id, long timeStamp) throws Exception {
		URL obj = new URL(QUEUE_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", CONTENT_TYPE);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		String urlParameters = "id=" + id + "&timestamp=" + timeStamp;
		
		//Send post request		
		con.setDoOutput(true);
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		wr.append(urlParameters);
		wr.close();
		
		// response
		int responseCode = con.getResponseCode();
		System.out.println("\nAdding a user");
		System.out.println("\nSending 'POST' request to URL : " + QUEUE_URL);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		
		tempResponseCode = responseCode;
		
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

	void sendDeleteUser(long id) throws Exception {
		URL obj = new URL(QUEUE_URL + "/" + id);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// add request header
		con.setDoOutput(true);
		con.setRequestMethod("DELETE");
		con.setRequestProperty("Content-Type", CONTENT_TYPE);
		con.connect();
		
		// response
		int responseCode = con.getResponseCode();
		System.out.println("\nDeleting a user");
		System.out.println("\nSending 'DELETE' request to URL : " + QUEUE_URL + "/" + id);
		System.out.println("Delete id parameter : " + id);
		System.out.println("Response Code : " + responseCode);
		
		tempResponseCode = responseCode;
		
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
	
	void sendPostDequeueUser(long id) throws Exception {
		URL obj = new URL(DEQUEUE_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		//add request header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", CONTENT_TYPE);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		String urlParameters = "id=" + id;
		
		//Send post request
		con.setDoOutput(true);
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
		wr.append(urlParameters);
		wr.close();
		
		// response
		int responseCode = con.getResponseCode();
		System.out.println("Dequeue a user");
		System.out.println("\nSending 'POST' request to URL : " + DEQUEUE_URL);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
		
		tempResponseCode = responseCode;
		
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
	
	public void sendGetSpecificUser(long id) throws Exception {
		URL obj = new URL(QUEUE_URL + "/" + id);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// add request header
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", CONTENT_TYPE);
		
		// response
		int responseCode = con.getResponseCode();
		System.out.println("\nGetting Specific User");
		System.out.println("\nSending 'GET' request to URL : " + QUEUE_URL + "/" + id);
		System.out.println("Response Code : " + responseCode);
		
		tempResponseCode = responseCode;
		
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
	
	public void sendGetUserPosition(long id) throws Exception {
		URL obj = new URL(QUEUE_URL + "/" + id + "/position");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// add request header
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", CONTENT_TYPE);
		
		// response
		int responseCode = con.getResponseCode();
		System.out.println("\nGetting a user position");
		System.out.println("\nSending 'GET' request to URL : " + QUEUE_URL + "/" + id + "/position");
		System.out.println("Response Code : " + responseCode);
		
		tempResponseCode = responseCode;
		
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
	
	public void sendGetWaitTime(long timeStamp) throws Exception {
		URL obj = new URL(WAIT_TIME_URL + timeStamp);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		// add request header
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", CONTENT_TYPE);
		
		// response
		int responseCode = con.getResponseCode();
		System.out.println("\nGetting wait time");
		System.out.println("\nSending 'GET' request to URL : " + WAIT_TIME_URL + timeStamp);
		System.out.println("Response Code : " + responseCode);
		
		tempResponseCode = responseCode;
		
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

