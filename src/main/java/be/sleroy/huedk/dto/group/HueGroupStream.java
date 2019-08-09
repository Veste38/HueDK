package be.sleroy.huedk.dto.group;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueGroupStream.
 * 
 * @author sleroy
 */
public class HueGroupStream {

	@JsonProperty(value = "proxymode")
	private String proxyMode;

	@JsonProperty(value = "proxynode")
	private String proxyNode;

	@JsonProperty(value = "active")
	private boolean active;

	@JsonProperty(value = "owner")
	private String owner;

	public String getProxyMode() {
		return proxyMode;
	}

	public void setProxyMode(String proxyMode) {
		this.proxyMode = proxyMode;
	}

	public String getProxyNode() {
		return proxyNode;
	}

	public void setProxyNode(String proxyNode) {
		this.proxyNode = proxyNode;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "HueGroupStream [proxyMode=" + proxyMode + ", proxyNode=" + proxyNode + ", active=" + active + ", owner=" + owner + "]";
	}

}
