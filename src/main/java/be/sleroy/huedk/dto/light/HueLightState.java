package be.sleroy.huedk.dto.light;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueLightState.
 * 
 * @author sleroy
 */
public class HueLightState {

	@JsonProperty(value = "on")
	private boolean on;

	@JsonProperty(value = "bri")
	private Integer brightness;

	@JsonProperty(value = "hue")
	private Integer hue;

	@JsonProperty(value = "sat")
	private Integer saturation;

	@JsonProperty(value = "effect")
	private String effect;

	@JsonProperty(value = "xy")
	private List<Float> xy;

	@JsonProperty(value = "ct")
	private Integer ct;

	@JsonProperty(value = "alert")
	private String alert;

	@JsonProperty(value = "colormode")
	private String colorMode;

	@JsonProperty(value = "mode")
	private String mode;

	@JsonProperty(value = "reachable")
	private boolean reachaable;

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

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
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

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}

	public String getColorMode() {
		return colorMode;
	}

	public void setColorMode(String colorMode) {
		this.colorMode = colorMode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public boolean isReachaable() {
		return reachaable;
	}

	public void setReachaable(boolean reachaable) {
		this.reachaable = reachaable;
	}

	@Override
	public String toString() {
		return "HueLightState [on=" + on + ", brightness=" + brightness + ", hue=" + hue + ", saturation=" + saturation + ", effect=" + effect + ", xy=" + xy + ", ct=" + ct + ", alert=" + alert + ", colorMode=" + colorMode + ", mode=" + mode + ", reachaable=" + reachaable + "]";
	}

}
