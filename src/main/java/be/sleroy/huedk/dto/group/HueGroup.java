package be.sleroy.huedk.dto.group;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import be.sleroy.huedk.dto.HueIdElement;

/**
 * The Class HueGroup.
 * 
 * @author sleroy
 */
public class HueGroup extends HueIdElement {

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "lights")
	private List<String> lightIdList;

	@JsonProperty(value = "sensors")
	private List<String> sensorIdList;

	@JsonProperty(value = "type")
	private String type;

	@JsonProperty(value = "state")
	private HueGroupState state;

	@JsonProperty(value = "recycle")
	private boolean recycle;

	@JsonProperty(value = "class")
	private String clas;

	@JsonProperty(value = "stream")
	private HueGroupStream stream;

	@JsonProperty(value = "locations")
	private Map<String, List<Float>> locations;

	@JsonProperty(value = "action")
	private HueGroupAction action;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getLightIdList() {
		return lightIdList;
	}

	public void setLightIdList(List<String> lightIdList) {
		this.lightIdList = lightIdList;
	}

	public List<String> getSensorIdList() {
		return sensorIdList;
	}

	public void setSensorIdList(List<String> sensorIdList) {
		this.sensorIdList = sensorIdList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HueGroupState getState() {
		return state;
	}

	public void setState(HueGroupState state) {
		this.state = state;
	}

	public boolean isRecycle() {
		return recycle;
	}

	public void setRecycle(boolean recycle) {
		this.recycle = recycle;
	}

	public String getClas() {
		return clas;
	}

	public void setClas(String clas) {
		this.clas = clas;
	}

	public HueGroupStream getStream() {
		return stream;
	}

	public void setStream(HueGroupStream stream) {
		this.stream = stream;
	}

	public Map<String, List<Float>> getLocations() {
		return locations;
	}

	public void setLocations(Map<String, List<Float>> locations) {
		this.locations = locations;
	}

	public HueGroupAction getAction() {
		return action;
	}

	public void setAction(HueGroupAction action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "HueGroup [id=" + id + ", name=" + name + ", lightIdList=" + lightIdList + ", sensorIdList=" + sensorIdList + ", type=" + type + ", state=" + state + ", recycle=" + recycle + ", clas=" + clas + ", stream=" + stream + ", locations=" + locations + ", action=" + action + "]";
	}

}
