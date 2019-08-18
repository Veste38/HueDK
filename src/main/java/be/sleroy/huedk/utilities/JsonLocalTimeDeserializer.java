package be.sleroy.huedk.utilities;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonLocalTimeDeserializer extends JsonDeserializer<LocalTime> {

	private static String TIME_PATTERN = "'T'HH:mm:ss";

	@Override
	public LocalTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		LocalTime time = null;

		String timeStr = p.getText();
		if (timeStr != null) {
			try  {
			time = LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(TIME_PATTERN));
			} catch(DateTimeParseException ex) {
			}
		}

		return time;
	}

}
