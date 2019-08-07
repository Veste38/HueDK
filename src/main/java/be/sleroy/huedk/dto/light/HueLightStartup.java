package be.sleroy.huedk.dto.light;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueLightStartup.
 * 
 * @author sleroy
 */
public class HueLightStartup {

	@JsonProperty(value = "mode")
	private String mode;

	@JsonProperty(value = "configured")
	private boolean configured;

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isConfigured() {
		return configured;
	}

	public void setConfigured(boolean configured) {
		this.configured = configured;
	}

	@Override
	public String toString() {
		return "HueLightStartup [mode=" + mode + ", configured=" + configured + "]";
	}

}
