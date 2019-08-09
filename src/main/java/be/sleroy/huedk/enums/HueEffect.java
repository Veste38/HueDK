package be.sleroy.huedk.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum HueEffect {
	@JsonProperty(value = "none")
	NONE,
	@JsonProperty(value = "colorloop")
	COLORLOOP;
}
