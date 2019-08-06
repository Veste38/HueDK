package be.sleroy.huedk.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueSuccess.
 * 
 * @author sleroy
 */
public class HueSuccess {

	@JsonProperty(value = "username")
	private String username;

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HueSuccess [username=" + username + "]";
	}

}
