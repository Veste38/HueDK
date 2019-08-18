package be.sleroy.huedk.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonDateTimeSerializer extends JsonSerializer<Date> {

	private static String DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
	
	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value == null) {
			gen.writeNull();
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
			gen.writeString(sdf.format(value));
		}
	}

}
