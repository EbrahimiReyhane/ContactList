
package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.simple.*;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.bcel.internal.generic.Type;

import entites.ContactEntity;

import org.json.*;
import org.json.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;

public class Client {

	public static String showContacts() {

		String result = "";
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/ProjectFinal/myapi/system/show");
			String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				result = result + output;
				System.out.println(output);
			}
			httpClient.getConnectionManager().shutdown();
			jsonToList(result);
		} catch (ClientProtocolException e) {
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return result;
	}
	// ---------------------------
	public static List jsonToList(String input){
		 Gson gsonReceiver = new Gson();
		 java.lang.reflect.Type type = new TypeToken<List<ContactEntity>>(){}.getType();
		 List<ContactEntity> obj = gsonReceiver.fromJson(input,type);
		   
		    Iterator it=obj.iterator();
		    while(it.hasNext())
		    {
		        System.out.println(it.next().toString());//java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.String
		    }
	    return obj;
	  }

	public static void main(String[] args) throws ClientProtocolException, IOException, ParseException, UnsupportedOperationException, JSONException 
	{
		
		
		//saveUser("zahra0","ff23");
		System.out.println(ExitUser("zahra0","ff23"));
		
//		    HttpClient client = new DefaultHttpClient();
//		  
//		    HttpPost post = new HttpPost("http://localhost:8080/ProjectFinal/myapi/system/insert");
//		    ContactEntity contact_new = new ContactEntity();
//		    ObjectMapper mapperObj = new ObjectMapper();
//		    String jsonStr="";
//		        Map inputMap = new HashMap();
//		        inputMap.put("name", "reyhane");
//		        inputMap.put("family", "rahimi");
//		        inputMap.put("tel", 34621);
//		        inputMap.put("mobile", 235346);
//		        inputMap.put("email", "rahimi@fg");
//		        // convert map to JSON String
//		        String jsonResp ="";
//		        try {
//		             jsonResp = mapperObj.writeValueAsString(inputMap);
//		            System.out.println(jsonResp);
//		        } catch (IOException e) {
//		         
//		            e.printStackTrace();
//		        }
//		    
//		   
//		    StringEntity input = new StringEntity(jsonResp);
//		    //input.setContentType("application/json");
//		    post.setEntity(input);
//		    HttpResponse response = client.execute(post);
//		    System.out.println(response.getStatusLine());
//		   
//		 
//		   
//		    
//		    String content2 = EntityUtils.toString(response.getEntity());
//		    System.out.println(content2);
//	  
//		     
	}		   

	
	 public static void saveContact(String name,String family,int tel,int mobile,String email){
	
	 try {
	
	 DefaultHttpClient httpClient = new DefaultHttpClient();
	 HttpPost postRequest = new HttpPost(
	 "http://localhost:8080/ProjectFinal/myapi/system/insert");
	
	 Map inputMap=new HashMap();
	 inputMap.put("name",name);
	 inputMap.put("family",family);
	 inputMap.put("tel",tel);
	 inputMap.put("mobile",mobile);
	 inputMap.put("email",email);
	 String jsonText =JSONValue.toJSONString(inputMap); ;
	 StringEntity input = new StringEntity(jsonText,
	 ContentType.APPLICATION_JSON);
	 postRequest.setEntity(input);
	
	 HttpResponse response = httpClient.execute(postRequest);
	
	 if (response.getStatusLine().getStatusCode() != 200) {
	 throw new RuntimeException("Failed : HTTP error code : "
	 + response.getStatusLine().getStatusCode());
	 }
	
	 BufferedReader br = new BufferedReader(
	 new InputStreamReader((response.getEntity().getContent())));
	
	 String output;
	 System.out.println("Output from Server .... \n");
	 while ((output = br.readLine()) != null) {
	 System.out.println(output);
	 }
	
	 httpClient.getConnectionManager().shutdown();
	
	 } catch (MalformedURLException e) {
	
	 e.printStackTrace();
	
	 } catch (IOException e) {
	
	 e.printStackTrace();
	
	 }
	
	 }
	
	
	// check user
	 public static Boolean ExitUser(String username,String password){
	
	Boolean flage = false;
	 try {
	
	 DefaultHttpClient httpClient = new DefaultHttpClient();
	 HttpPost postRequest = new HttpPost(
	 "http://localhost:8080/ProjectFinal/myapi/system/isValid");
	
	
	Map inputMap=new HashMap();
	 inputMap.put("username",username);
	 inputMap.put("password",password);
	 String jsonText = JSONValue.toJSONString(inputMap);
	 StringEntity input = new StringEntity(jsonText,
	 ContentType.APPLICATION_JSON);
	 postRequest.setEntity(input);
	
	 HttpResponse response = httpClient.execute(postRequest);
	// System.out.println(response );
	 if (response.getStatusLine().getStatusCode() != 200) {
	 throw new RuntimeException("Failed : HTTP error code : "
	 + response.getStatusLine().getStatusCode());
	 }else{
		 flage = true;
	 }
	//
	// BufferedReader br = new BufferedReader(
	// new InputStreamReader((response.getEntity().getContent())));
	//
	// String output;
	// System.out.println("Output from Server .... \n");
	// while ((output = br.readLine()) != null) {
	// result=result+output;
	// System.out.println(output);
	// }
	//
	// httpClient.getConnectionManager().shutdown();
	//
	//
	 } catch (MalformedURLException e) {
	 e.printStackTrace();
	
 } catch (IOException e) {
	 e.printStackTrace();
	 }
	 return flage;
	 }
	 public static String showUser() {

			String result = "";
			try {

				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet getRequest = new HttpGet("http://localhost:8080/ProjectFinal/myapi/system/showuser");
				String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";

				HttpResponse response = httpClient.execute(getRequest);
				if (response.getStatusLine().getStatusCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

				String output;
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					result = result + output;
					System.out.println(output);
				}
				httpClient.getConnectionManager().shutdown();
				jsonToList(result);
			} catch (ClientProtocolException e) {
				e.printStackTrace();

			} catch (IOException e) {

				e.printStackTrace();
			}
			return result;
		}
//add new user
	 public static void saveUser(String username,String password){
			final String role="user";
		 try {
		
		 DefaultHttpClient httpClient = new DefaultHttpClient();
		 HttpPost postRequest = new HttpPost(
		 "http://localhost:8080/ProjectFinal/myapi/system/insertUser");
		
		 Map inputMap=new HashMap();
		 inputMap.put("username",username);
		 inputMap.put("password",password);
		 inputMap.put("role",role);
		 String jsonText =JSONValue.toJSONString(inputMap); ;
		 StringEntity input = new StringEntity(jsonText,
		 ContentType.APPLICATION_JSON);
		 postRequest.setEntity(input);
		
		 HttpResponse response = httpClient.execute(postRequest);
		
		 if (response.getStatusLine().getStatusCode() != 200) {
		 throw new RuntimeException("Failed : HTTP error code : "
		 + response.getStatusLine().getStatusCode());
		 }
		
		 BufferedReader br = new BufferedReader(
		 new InputStreamReader((response.getEntity().getContent())));
		
		 String output;
		 System.out.println("Output from Server .... \n");
		 while ((output = br.readLine()) != null) {
		 System.out.println(output);
		 }
		
		 httpClient.getConnectionManager().shutdown();
		
		 } catch (MalformedURLException e) {
		
		 e.printStackTrace();
		
		 } catch (IOException e) {
		
		 e.printStackTrace();
		
		 }
		
		 }
		
		    
	}		  

