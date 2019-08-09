package be.sleroy.huedk.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueGroupState.
 * 
 * @author sleroy
 */
public class HueGroupState {

	@JsonProperty(value = "all_on")
	private boolean allOn;

	@JsonProperty(value = "any_on")
	private boolean anyOn;

	public boolean isAllOn() {
		return allOn;
	}

	public void setAllOn(boolean allOn) {
		this.allOn = allOn;
	}

	public boolean isAnyOn() {
		return anyOn;
	}

	public void setAnyOn(boolean anyOn) {
		this.anyOn = anyOn;
	}

	@Override
	public String toString() {
		return "HueGroupState [allOn=" + allOn + ", anyOn=" + anyOn + "]";
	}

}
