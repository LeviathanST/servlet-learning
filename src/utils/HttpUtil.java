package utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.BufferedReader;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;


public class HttpUtil<T> {
	private final Gson gson;

	public HttpUtil() {
		this.gson = new Gson();
	}

	public T getBodyContentFromReq(HttpServletRequest req, Class<T> clazz){
		StringBuilder jsonBody = new StringBuilder();

		try (BufferedReader reader = req.getReader()) {
			String line;
			while ((line = reader.readLine()) != null) {
				jsonBody.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		// Deserialize JSON -> Object
		try {
			return gson.fromJson(jsonBody.toString(), clazz);
		} catch (JsonSyntaxException e){
			e.printStackTrace();
			return null;
		}
	}
}
