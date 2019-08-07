package be.sleroy.huedk.dto.light;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueLight.
 * 
 * @author sleroy
 */
public class HueLight {

	@JsonIgnore
	private String id;

	@JsonProperty(value = "state")
	private HueLightState state;

	@JsonProperty(value = "swupdate")
	private HueLightSoftwareUpdate softwareUpdate;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "modelid")
	private String modelId;

	@JsonProperty(value = "manufacturername")
	private String manufacturerName;

	@JsonProperty(value = "productname")
	private String productName;

	@JsonProperty(value = "capabilities")
	private HueLightCapabilities capabilities;

	@JsonProperty(value = "config")
	private HueLightConfiguration configuration;

	@JsonProperty(value = "uniqueid")
	private String uniqueId;

	@JsonProperty(value = "swversion")
	private String softwareVersion;

	@JsonProperty(value = "swconfigid")
	private String softwareConfigId;

	@JsonProperty(value = "productid")
	private String productId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HueLightState getState() {
		return state;
	}

	public void setState(HueLightState state) {
		this.state = state;
	}

	public HueLightSoftwareUpdate getSoftwareUpdate() {
		return softwareUpdate;
	}

	public void setSoftwareUpdate(HueLightSoftwareUpdate softwareUpdate) {
		this.softwareUpdate = softwareUpdate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public HueLightCapabilities getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(HueLightCapabilities capabilities) {
		this.capabilities = capabilities;
	}

	public HueLightConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(HueLightConfiguration configuration) {
		this.configuration = configuration;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getSoftwareConfigId() {
		return softwareConfigId;
	}

	public void setSoftwareConfigId(String softwareConfigId) {
		this.softwareConfigId = softwareConfigId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "HueLight [id=" + id + ", state=" + state + ", softwareUpdate=" + softwareUpdate + ", type=" + type + ", name=" + name + ", modelId=" + modelId + ", manufacturerName=" + manufacturerName + ", productName=" + productName + ", capabilities=" + capabilities + ", configuration=" + configuration
				+ ", uniqueId=" + uniqueId + ", softwareVersion=" + softwareVersion + ", softwareConfigId=" + softwareConfigId + ", productId=" + productId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HueLight other = (HueLight) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
