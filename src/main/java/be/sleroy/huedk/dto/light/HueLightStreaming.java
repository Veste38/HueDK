package be.sleroy.huedk.dto.light;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HueLightStreaming.
 * 
 * @author sleroy
 */
public class HueLightStreaming {

	@JsonProperty(value = "renderer")
	private boolean renderer;

	@JsonProperty(value = "proxy")
	private boolean proxy;

	public boolean isRenderer() {
		return renderer;
	}

	public void setRenderer(boolean renderer) {
		this.renderer = renderer;
	}

	public boolean isProxy() {
		return proxy;
	}

	public void setProxy(boolean proxy) {
		this.proxy = proxy;
	}

	@Override
	public String toString() {
		return "HueLightStreaming [renderer=" + renderer + ", proxy=" + proxy + "]";
	}

}
