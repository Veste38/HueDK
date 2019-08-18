package be.sleroy.huedk.dto.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueInternetServices.
 * 
 * @author sleroy
 */
public class HueInternetServices {

	@JsonProperty(value = "internet")
	private String internet;

	@JsonProperty(value = "remoteaccess")
	private String remoteAccess;

	@JsonProperty(value = "time")
	private String time;

	@JsonProperty(value = "swupdate")
	private String softwareUpdate;

	/**
	 * Gets the internet.
	 *
	 * @return the internet
	 */
	public String getInternet() {
		return internet;
	}

	/**
	 * Sets the internet.
	 *
	 * @param internet
	 *            the new internet
	 */
	public void setInternet(String internet) {
		this.internet = internet;
	}

	/**
	 * Gets the remote access.
	 *
	 * @return the remote access
	 */
	public String getRemoteAccess() {
		return remoteAccess;
	}

	/**
	 * Sets the remote access.
	 *
	 * @param remoteAccess
	 *            the new remote access
	 */
	public void setRemoteAccess(String remoteAccess) {
		this.remoteAccess = remoteAccess;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time
	 *            the new time
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Gets the software update.
	 *
	 * @return the software update
	 */
	public String getSoftwareUpdate() {
		return softwareUpdate;
	}

	/**
	 * Sets the software update.
	 *
	 * @param softwareUpdate
	 *            the new software update
	 */
	public void setSoftwareUpdate(String softwareUpdate) {
		this.softwareUpdate = softwareUpdate;
	}

	@Override
	public String toString() {
		return "HueInternetServices [internet=" + internet + ", remoteAccess=" + remoteAccess + ", time=" + time + ", softwareUpdate=" + softwareUpdate + "]";
	}

}
