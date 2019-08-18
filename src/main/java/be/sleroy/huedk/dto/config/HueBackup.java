package be.sleroy.huedk.dto.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueBackup.
 * 
 * @author sleroy
 */
public class HueBackup {

	@JsonProperty(value = "status")
	private String status;

	@JsonProperty(value = "errorcode")
	private Integer errorCode;

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public Integer getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode
	 *            the new error code
	 */
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	@Override
	public String toString() {
		return "HueBackup [status=" + status + ", errorCode=" + errorCode + "]";
	}

}
