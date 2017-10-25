package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
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

import entites.ContactEntity;
import entites.UserEntity;

public class UserClient {

	// ________________________ check user login________________________

	public static Boolean ExitUser(String username, String password) {

		Boolean flage = false;
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/ProjectFinal/myapi/users/isValid");
			Map inputMap = new HashMap();
			inputMap.put("username", username);
			inputMap.put("password", password);
			String jsonText = JSONValue.toJSONString(inputMap);
			StringEntity input = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			// System.out.println(response );
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed  : " + response.getStatusLine().getStatusCode());
			} else {
				flage = true;
			}

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return flage;
	}
	// _________________________ show all users_____________________________
	public static Object[][] showUser() {
		Object[][] user = null;
		String result = "";
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet("http://localhost:8080/ProjectFinal/myapi/users/showuser");
			// String regex =
			// "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
			HttpResponse response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed  : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				result = result + output;
			}
			httpClient.getConnectionManager().shutdown();
			List<UserEntity> users = jsonToList(result);
			for (int i = 0; i < users.size(); i++) {
				user[i][0] = users.get(i).getUsername();
				user[i][1] = users.get(i).getPassword();
				user[i][2] = users.get(i).getRole();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();
		}
		return user;
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
	// __________________________enrollment new user______________________

	public static void saveUser(String username, String password) {
		final String role = "user";
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost("http://localhost:8080/ProjectFinal/myapi/users/insertUser");

			Map inputMap = new HashMap();
			inputMap.put("username", username);
			inputMap.put("password", password);
			inputMap.put("role", role);
			String jsonText = JSONValue.toJSONString(inputMap);
			StringEntity input = new StringEntity(jsonText, ContentType.APPLICATION_JSON);
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			System.out.println("Output from Server .... \n");

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
//______________________ delete a user_____________
	
	public static void deleteUser(String username) {
		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpDelete deleteRequest = new HttpDelete("http://localhost:8080/ProjectFinal/myapi/users/" +username );
			HttpResponse response = httpClient.execute(deleteRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
			}
			System.out.println("Output from Server .... \n");

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
	}
//________________ edit user%%%%% just admin____________________
	public static void editUser(String username,String password, String role) {

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut("http://localhost:8080/ProjectFinal/myapi/users/editUser/" + username);

			Map inputMap = new HashMap();
			inputMap.put("username", username);
			inputMap.put("password",password);
			inputMap.put("role", role);
			String jsonText = JSONValue.toJSONString(inputMap);
			System.out.println(jsonText);
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
//__________________edit user for all __________
	
	public static void editUserForAll(String username,String password) {

		try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPut putRequest = new HttpPut("http://localhost:8080/ProjectFinal/myapi/users/" + username);

			Map inputMap = new HashMap();
			inputMap.put("username", username);
			inputMap.put("password",password);
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

}
