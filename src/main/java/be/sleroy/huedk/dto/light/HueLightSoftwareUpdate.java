package be.sleroy.huedk.dto.light;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.sleroy.huedk.utilities.JsonDateTimeDeserializer;
import be.sleroy.huedk.utilities.JsonDateTimeSerializer;

/**
 * The Class HueLightSoftwareUpdate.
 * 
 * @author sleroy
 */
public class HueLightSoftwareUpdate {

	@JsonProperty(value = "state")
	private String state;

	@JsonProperty(value = "lastinstall")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private Date lastInstall;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getLastInstall() {
		return lastInstall;
	}

	public void setLastInstall(Date lastInstall) {
		this.lastInstall = lastInstall;
	}

	@Override
	public String toString() {
		return "HueLightSoftwareUpdate [state=" + state + ", lastInstall=" + lastInstall + "]";
	}

}
