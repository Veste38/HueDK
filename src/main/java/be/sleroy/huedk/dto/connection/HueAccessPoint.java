package be.sleroy.huedk.dto.connection;

import com.fasterxml.jackson.annotation.JsonProperty;

import be.sleroy.huedk.dto.HueIdElement;

/**
 * The Class HueAccessPoint.
 * 
 * @author sleroy
 */
public class HueAccessPoint extends HueIdElement {

	@JsonProperty(value = "internalipaddress")
	private String ip;

	/**
	 * Gets the ip.
	 *
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * Sets the ip.
	 *
	 * @param ip
	 *            the new ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HueAccessPoint [id=" + id + ", ip=" + ip + "]";
	}

}
