package be.sleroy.huedk.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum HueColorMode {
	@JsonProperty(value = "hs")
	HS,
	@JsonProperty(value = "xy")
	XY,
	@JsonProperty(value = "ct")
	CT;
}
