package be.sleroy.huedk.utilities;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date> {

	private static String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		Date date = null;

		String dateStr = p.getText();
		if (dateStr != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
				sdf.parse(dateStr);
			} catch (ParseException ex) {
			}
		}

		return date;
	}

}
