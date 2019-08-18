package be.sleroy.huedk.dto.config;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.sleroy.huedk.utilities.JsonDateTimeDeserializer;
import be.sleroy.huedk.utilities.JsonDateTimeSerializer;

/**
 * The Class HueSoftwareUpdate2.
 * 
 * @author sleroy
 */
public class HueSoftwareUpdate2 {

	@JsonProperty(value = "checkforupdate")
	private boolean checkForUpdate;

	@JsonProperty(value = "lastchange")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private Date dateLastChange;

	@JsonProperty(value = "bridge")
	private HueBridge bridge;

	@JsonProperty(value = "state")
	private String state;

	@JsonProperty(value = "autoinstall")
	private HueAutoInstall autoInstall;

	/**
	 * Checks if is check for update.
	 *
	 * @return true, if is check for update
	 */
	public boolean isCheckForUpdate() {
		return checkForUpdate;
	}

	/**
	 * Sets the check for update.
	 *
	 * @param checkForUpdate
	 *            the new check for update
	 */
	public void setCheckForUpdate(boolean checkForUpdate) {
		this.checkForUpdate = checkForUpdate;
	}

	/**
	 * Gets the date last change.
	 *
	 * @return the date last change
	 */
	public Date getDateLastChange() {
		return dateLastChange;
	}

	/**
	 * Sets the date last change.
	 *
	 * @param dateLastChange
	 *            the new date last change
	 */
	public void setDateLastChange(Date dateLastChange) {
		this.dateLastChange = dateLastChange;
	}

	/**
	 * Gets the bridge.
	 *
	 * @return the bridge
	 */
	public HueBridge getBridge() {
		return bridge;
	}

	/**
	 * Sets the bridge.
	 *
	 * @param bridge
	 *            the new bridge
	 */
	public void setBridge(HueBridge bridge) {
		this.bridge = bridge;
	}

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
	 * Gets the auto install.
	 *
	 * @return the auto install
	 */
	public HueAutoInstall getAutoInstall() {
		return autoInstall;
	}

	/**
	 * Sets the auto install.
	 *
	 * @param autoInstall
	 *            the new auto install
	 */
	public void setAutoInstall(HueAutoInstall autoInstall) {
		this.autoInstall = autoInstall;
	}

	@Override
	public String toString() {
		return "HueSoftwareUpdate2 [checkForUpdate=" + checkForUpdate + ", dateLastChange=" + dateLastChange + ", bridge=" + bridge + ", state=" + state + ", autoInstall=" + autoInstall + "]";
	}

}
