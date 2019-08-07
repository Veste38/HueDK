package be.sleroy.huedk.dto.light;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueLightCT.
 * 
 * @author sleroy
 */
public class HueLightCT {

	@JsonProperty(value = "min")
	private Integer min;

	@JsonProperty(value = "max")
	private Integer max;

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	@Override
	public String toString() {
		return "HueLightCT [min=" + min + ", max=" + max + "]";
	}

}
