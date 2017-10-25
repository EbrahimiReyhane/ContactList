package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONValue;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.*;
import java.net.MalformedURLException;

import entites.ContactEntity;

public class ContactClient {
	// ________________________________ Show all contacts_______________________
	public static Object[][] showContacts() {
		Object[][] contact = null;
		String result = "";
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/ProjectFinal/myapi/contacts/show");

			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				result = result + output;
				System.out.println(output);
			}
			httpClient.getConnectionManager().shutdown();
			List<ContactEntity> contacts = jsonToList(result);

			for (int i = 0; i < contacts.size(); i++) {
				contact[i][0] = contacts.get(i).getId();
				contact[i][1] = contacts.get(i).getName();
				contact[i][2] = contacts.get(i).getFamily();
				contact[i][3] = contacts.get(i).getEmail();
				contact[i][4] = contacts.get(i).getMobile();
				contact[i][5] = contacts.get(i).getTel();

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return contact;
	}

	// --------------------------- convert json to list
	public static List jsonToList(String input) {
		Gson gsonReceiver = new Gson();
		Type type = new TypeToken<List<ContactEntity>>() {
		}.getType();
		List<ContactEntity> obj = gsonReceiver.fromJson(input, type);

		Iterator it = obj.iterator();
		while (it.hasNext()) {
			System.out.println(it.next().toString());
		}
		return obj;
	}
	// _________________________________ add new contact___________________

	public static void saveContact(String name, String family, int tel, int mobile, String email) {

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/ProjectFinal/myapi/contacts/insert");

			Map inputMap = new HashMap();
			inputMap.put("name", name);
			inputMap.put("family", family);
			inputMap.put("tel", tel);
			inputMap.put("mobile", mobile);
			inputMap.put("email", email);
			String jsonText = JSONValue.toJSONString(inputMap);
			;
			StringEntity input = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}else{
				System.out.println("succsecful :" + response.getStatusLine().getStatusCode()); 
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
//____________________ edit  a contact____________________
	public static void editContact(int id,String name, String family, int tel, int mobile, String email) {

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut("http://localhost:8080/ProjectFinal/myapi/contacts/" + id);

			Map inputMap = new HashMap();
			inputMap.put("name", name);
			inputMap.put("family", family);
			inputMap.put("tel", tel);
			inputMap.put("mobile", mobile);
			inputMap.put("email", email);
			String jsonText = JSONValue.toJSONString(inputMap);
			StringEntity input = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
			putRequest.setEntity(input);

			HttpResponse response = httpClient.execute(putRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}else{
				System.out.println("succsecful :" + response.getStatusLine().toString()); 
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
//__________________ delete a contact____________
	
	public static void deleteContact(int id) {

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/ProjectFinal/myapi/contacts/" + id);

//			Map inputMap = new HashMap();
//			inputMap.put("name", name);
//			inputMap.put("family", family);
//			inputMap.put("tel", tel);
//			inputMap.put("mobile", mobile);
//			inputMap.put("email", email);
//			String jsonText = JSONValue.toJSONString(inputMap);
//			StringEntity input = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
//			putRequest.setEntity(input);

			HttpResponse response = httpClient.execute(deleteRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}else{
				System.out.println("succsecful :" + response.getStatusLine().toString()); 
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
