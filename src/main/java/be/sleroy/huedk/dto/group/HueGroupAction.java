package be.sleroy.huedk.dto.group;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import be.sleroy.huedk.enums.HueAlert;
import be.sleroy.huedk.enums.HueColorMode;
import be.sleroy.huedk.enums.HueEffect;

/**
 * The Class HueGroupAction.
 * 
 * @author sleroy
 */
public class HueGroupAction {

	@JsonProperty(value = "on")
	private boolean on;

	@JsonProperty(value = "bri")
	private Integer brightness;

	@JsonProperty(value = "hue")
	private Integer hue;

	@JsonProperty(value = "sat")
	private Integer saturation;

	@JsonProperty(value = "effect")
	private HueEffect effect;

	@JsonProperty(value = "xy")
	private List<Float> xy;

	@JsonProperty(value = "ct")
	private Integer ct;

	@JsonProperty(value = "alert")
	private HueAlert alert;

	@JsonProperty(value = "colormode")
	private HueColorMode colorMode;

	private Integer colorRed;
	private Integer colorGreen;
	private Integer colorBlue;

	private String colorHex;

	private Integer colorTemperature;

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public Integer getBrightness() {
		return brightness;
	}

	public void setBrightness(Integer brightness) {
		this.brightness = brightness;
	}

	public Integer getHue() {
		return hue;
	}

	public void setHue(Integer hue) {
		this.hue = hue;
	}

	public Integer getSaturation() {
		return saturation;
	}

	public void setSaturation(Integer saturation) {
		this.saturation = saturation;
	}

	public HueEffect getEffect() {
		return effect;
	}

	public void setEffect(HueEffect effect) {
		this.effect = effect;
	}

	public List<Float> getXy() {
		return xy;
	}

	public void setXy(List<Float> xy) {
		this.xy = xy;
	}

	public Integer getCt() {
		return ct;
	}

	public void setCt(Integer ct) {
		this.ct = ct;
	}

	public HueAlert getAlert() {
		return alert;
	}

	public void setAlert(HueAlert alert) {
		this.alert = alert;
	}

	public HueColorMode getColorMode() {
		return colorMode;
	}

	public void setColorMode(HueColorMode colorMode) {
		this.colorMode = colorMode;
	}

	public Integer getColorRed() {
		return colorRed;
	}

	public void setColorRed(Integer colorRed) {
		this.colorRed = colorRed;
	}

	public Integer getColorGreen() {
		return colorGreen;
	}

	public void setColorGreen(Integer colorGreen) {
		this.colorGreen = colorGreen;
	}

	public Integer getColorBlue() {
		return colorBlue;
	}

	public void setColorBlue(Integer colorBlue) {
		this.colorBlue = colorBlue;
	}

	public String getColorHex() {
		return colorHex;
	}

	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}

	public Integer getColorTemperature() {
		return colorTemperature;
	}

	public void setColorTemperature(Integer colorTemperature) {
		this.colorTemperature = colorTemperature;
	}

	@Override
	public String toString() {
		return "HueGroupAction [on=" + on + ", brightness=" + brightness + ", hue=" + hue + ", saturation=" + saturation + ", effect=" + effect + ", xy=" + xy + ", ct=" + ct + ", alert=" + alert + ", colorMode=" + colorMode + ", colorRed=" + colorRed + ", colorGreen=" + colorGreen + ", colorBlue=" + colorBlue
				+ ", colorHex=" + colorHex + ", colorTemperature=" + colorTemperature + "]";
	}

}
