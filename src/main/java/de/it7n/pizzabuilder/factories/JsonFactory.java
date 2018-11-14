package de.it7n.pizzabuilder.factories;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.collinalpert.java2db.entities.BaseEntity;

import java.io.IOException;

public class JsonFactory {
    public static <T extends BaseEntity> String entityToJson(T obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            String jsonString = mapper.writeValueAsString(obj);
            System.out.println(jsonString);

            return jsonString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
