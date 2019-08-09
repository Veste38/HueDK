package be.sleroy.huedk.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum HueAlert {
	@JsonProperty(value = "none")
	NONE,
	@JsonProperty(value = "select")
	SELECT,
	@JsonProperty(value = "lselect")
	LSELECT;
}
