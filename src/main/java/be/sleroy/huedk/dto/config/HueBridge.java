package be.sleroy.huedk.dto.config;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.sleroy.huedk.utilities.JsonDateTimeDeserializer;
import be.sleroy.huedk.utilities.JsonDateTimeSerializer;

/**
 * The Class HueBridge.
 * 
 * @author sleroy
 */
public class HueBridge {

	@JsonProperty(value = "state")
	private String state;

	@JsonProperty(value = "lastinstall")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private Date dateLastInstall;

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state
	 *            the new state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Gets the date last install.
	 *
	 * @return the date last install
	 */
	public Date getDateLastInstall() {
		return dateLastInstall;
	}

	/**
	 * Sets the date last install.
	 *
	 * @param dateLastInstall
	 *            the new date last install
	 */
	public void setDateLastInstall(Date dateLastInstall) {
		this.dateLastInstall = dateLastInstall;
	}

	@Override
	public String toString() {
		return "HueBridge [state=" + state + ", dateLastInstall=" + dateLastInstall + "]";
	}

}
