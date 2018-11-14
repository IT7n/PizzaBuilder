package de.it7n.pizzabuilder.factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.collinalpert.java2db.entities.BaseEntity;

import java.io.IOException;

public class JsonFactory {
	public static <T extends BaseEntity> String entityToJson(T obj) {
		try {
			var mapper = new ObjectMapper();
			return mapper.writeValueAsString(obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String generateFailure(int i, String s) {
		return String.format("{\"success\": false, \"statusCode\": %d, \"message\": %s}", i, s);
	}
}
