package rpc;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RpcHelper {

	// Writes a JSONArray to http response.
	public static void writeJsonArray(HttpServletResponse response, JSONArray array) throws IOException{
		PrintWriter out = response.getWriter();	
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		out.print(array);
		out.close();
	}

    // Writes a JSONObject to http response.
	public static void writeJsonObject(HttpServletResponse response, JSONObject obj) throws IOException {		
		PrintWriter out = response.getWriter();	
		response.setContentType("application/json");
		response.addHeader("Access-Control-Allow-Origin", "*");
		out.print(obj);
		out.close();
	}
	
	public static JSONObject readJSONObject(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = request.getReader()){
			String line = null;
			while ((line = reader.readLine())!= null) {
				sb.append(line);
			}
			return new JSONObject(sb.toString());
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JSONArray array = new JSONArray();
		try {
			array.put(new JSONObject().put("username", "abcd"));
			array.put(new JSONObject().put("username", "1234"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, array);
	}


}
