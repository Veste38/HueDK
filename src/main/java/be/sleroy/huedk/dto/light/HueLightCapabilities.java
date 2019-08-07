package be.sleroy.huedk.dto.light;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueLightCapabilities.
 * 
 * @author sleroy
 */
public class HueLightCapabilities {

	@JsonProperty(value = "certified")
	private boolean certified;

	@JsonProperty(value = "control")
	private HueLightControl control;

	@JsonProperty(value = "streaming")
	private HueLightStreaming streaming;

	public boolean isCertified() {
		return certified;
	}

	public void setCertified(boolean certified) {
		this.certified = certified;
	}

	public HueLightControl getControl() {
		return control;
	}

	public void setControl(HueLightControl control) {
		this.control = control;
	}

	public HueLightStreaming getStreaming() {
		return streaming;
	}

	public void setStreaming(HueLightStreaming streaming) {
		this.streaming = streaming;
	}

	@Override
	public String toString() {
		return "HueLightCapabilities [certified=" + certified + ", control=" + control + ", streaming=" + streaming + "]";
	}

}
