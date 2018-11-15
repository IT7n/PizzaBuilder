package de.it7n.pizzabuilder.factories;

public class JsonFactory {

	public static String generateFailure(int statusCode, String failureMessage) {
		return String.format("{\"success\": false, \"statusCode\": %d, \"message\": \"%s\"}", statusCode, failureMessage);
	}

	public static String generateSuccess(String successMessage) {
		return generateSuccess(200, successMessage);
	}

	public static String generateSuccess(int statusCode, String successMessage) {
		return String.format("{\"success\": true, \"statusCode\": %d, \"message\": \"%s\"}", statusCode, successMessage);
	}
}
