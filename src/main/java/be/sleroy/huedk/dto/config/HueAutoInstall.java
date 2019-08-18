package be.sleroy.huedk.dto.config;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.sleroy.huedk.utilities.JsonLocalTimeDeserializer;
import be.sleroy.huedk.utilities.JsonLocalTimeSerializer;

/**
 * The Class HueAutoInstall.
 */
public class HueAutoInstall {

	@JsonProperty(value = "updatetime")
	@JsonSerialize(using = JsonLocalTimeSerializer.class)
	@JsonDeserialize(using = JsonLocalTimeDeserializer.class)
	private LocalTime updateTime;

	@JsonProperty(value = "on")
	private boolean on;

	/**
	 * Gets the update time.
	 *
	 * @return the update time
	 */
	public LocalTime getUpdateTime() {
		return updateTime;
	}

	/**
	 * Sets the update time.
	 *
	 * @param updateTime
	 *            the new update time
	 */
	public void setUpdateTime(LocalTime updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * Checks if is on.
	 *
	 * @return true, if is on
	 */
	public boolean isOn() {
		return on;
	}

	/**
	 * Sets the on.
	 *
	 * @param on
	 *            the new on
	 */
	public void setOn(boolean on) {
		this.on = on;
	}

	@Override
	public String toString() {
		return "HueAutoInstall [updateTime=" + updateTime + ", on=" + on + "]";
	}

}
