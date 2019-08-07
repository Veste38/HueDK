package be.sleroy.huedk.dto.light;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueLightControl.
 * 
 * @author sleroy
 */
public class HueLightControl {

	@JsonProperty(value = "mindimlevel")
	private Integer minDimLevel;

	@JsonProperty(value = "maxlumen")
	private Integer maxLumen;

	@JsonProperty(value = "colorgamuttype")
	private String colorGamutType;

	@JsonProperty(value = "colorgamut")
	private List<List<Float>> colorGamut;

	@JsonProperty(value = "ct")
	private HueLightCT ct;

	public Integer getMinDimLevel() {
		return minDimLevel;
	}

	public void setMinDimLevel(Integer minDimLevel) {
		this.minDimLevel = minDimLevel;
	}

	public Integer getMaxLumen() {
		return maxLumen;
	}

	public void setMaxLumen(Integer maxLumen) {
		this.maxLumen = maxLumen;
	}

	public String getColorGamutType() {
		return colorGamutType;
	}

	public void setColorGamutType(String colorGamutType) {
		this.colorGamutType = colorGamutType;
	}

	public List<List<Float>> getColorGamut() {
		return colorGamut;
	}

	public void setColorGamut(List<List<Float>> colorGamut) {
		this.colorGamut = colorGamut;
	}

	public HueLightCT getCt() {
		return ct;
	}

	public void setCt(HueLightCT ct) {
		this.ct = ct;
	}

	@Override
	public String toString() {
		return "HueLightControl [minDimLevel=" + minDimLevel + ", maxLumen=" + maxLumen + ", colorGamutType=" + colorGamutType + ", colorGamut=" + colorGamut + ", ct=" + ct + "]";
	}

}
