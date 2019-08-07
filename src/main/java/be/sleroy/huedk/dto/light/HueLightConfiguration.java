package be.sleroy.huedk.dto.light;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueLightConfiguration.
 * 
 * @author sleroy
 */
public class HueLightConfiguration {

	@JsonProperty(value = "archetype")
	private String archetype;

	@JsonProperty(value = "function")
	private String function;

	@JsonProperty(value = "direction")
	private String direction;

	@JsonProperty(value = "startup")
	private HueLightStartup startup;

	public String getArchetype() {
		return archetype;
	}

	public void setArchetype(String archetype) {
		this.archetype = archetype;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public HueLightStartup getStartup() {
		return startup;
	}

	public void setStartup(HueLightStartup startup) {
		this.startup = startup;
	}

	@Override
	public String toString() {
		return "HueLightConfiguration [archetype=" + archetype + ", function=" + function + ", direction=" + direction + ", startup=" + startup + "]";
	}

}
