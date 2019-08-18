package be.sleroy.huedk.utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonLocalTimeSerializer extends JsonSerializer<LocalTime> {

	@Override
	public void serialize(LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		if (value == null) {
			gen.writeNull();
		} else {
			gen.writeString(String.format("T%02d:%02d:%02d", value.getHour(), value.getMinute(), value.getSecond()));
		}
	}

}
