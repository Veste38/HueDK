package be.sleroy.huedk.dto.internal;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueResponse.
 * 
 * @author sleroy
 */
public class HueResponse {

	@JsonProperty(value = "error")
	private HueError error;

	@JsonProperty(value = "success")
	private HueSuccess success;

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public HueError getError() {
		return error;
	}

	/**
	 * Sets the error.
	 *
	 * @param error
	 *            the new error
	 */
	public void setError(HueError error) {
		this.error = error;
	}

	/**
	 * Gets the success.
	 *
	 * @return the success
	 */
	public HueSuccess getSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 *
	 * @param success
	 *            the new success
	 */
	public void setSuccess(HueSuccess success) {
		this.success = success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HueResponse [error=" + error + ", success=" + success + "]";
	}

}
