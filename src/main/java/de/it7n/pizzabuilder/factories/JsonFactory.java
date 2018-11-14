package de.it7n.pizzabuilder.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.collinalpert.java2db.entities.BaseEntity;

import java.io.IOException;

public class JsonFactory {
	public static String entityToJson(Object obj) {
		try {
			var mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String generateFailure(int statusCode, String failureMessage) {
		return String.format("{\"success\": false, \"statusCode\": %d, \"message\": \"%s\"}", statusCode, failureMessage);
	}

	public static String generateSuccess(String successMessage) {
		return String.format("{\"success\": true, \"statusCode\": 200, \"message\": \"%s\"}", successMessage);
	}
}
