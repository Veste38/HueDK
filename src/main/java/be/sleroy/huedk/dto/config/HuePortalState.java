package be.sleroy.huedk.dto.config;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class HuePortalState.
 * 
 * @author sleroy
 */
public class HuePortalState {

	@JsonProperty(value = "signedon")
	private boolean signedOn;

	@JsonProperty(value = "incoming")
	private boolean incoming;

	@JsonProperty(value = "outgoing")
	private boolean outgoing;

	@JsonProperty(value = "communication")
	private String communication;

	/**
	 * Checks if is signed on.
	 *
	 * @return true, if is signed on
	 */
	public boolean isSignedOn() {
		return signedOn;
	}

	/**
	 * Sets the signed on.
	 *
	 * @param signedOn
	 *            the new signed on
	 */
	public void setSignedOn(boolean signedOn) {
		this.signedOn = signedOn;
	}

	/**
	 * Checks if is incoming.
	 *
	 * @return true, if is incoming
	 */
	public boolean isIncoming() {
		return incoming;
	}

	/**
	 * Sets the incoming.
	 *
	 * @param incoming
	 *            the new incoming
	 */
	public void setIncoming(boolean incoming) {
		this.incoming = incoming;
	}

	/**
	 * Checks if is outgoing.
	 *
	 * @return true, if is outgoing
	 */
	public boolean isOutgoing() {
		return outgoing;
	}

	/**
	 * Sets the outgoing.
	 *
	 * @param outgoing
	 *            the new outgoing
	 */
	public void setOutgoing(boolean outgoing) {
		this.outgoing = outgoing;
	}

	/**
	 * Gets the communication.
	 *
	 * @return the communication
	 */
	public String getCommunication() {
		return communication;
	}

	/**
	 * Sets the communication.
	 *
	 * @param communication
	 *            the new communication
	 */
	public void setCommunication(String communication) {
		this.communication = communication;
	}

	@Override
	public String toString() {
		return "HuePortalState [signedOn=" + signedOn + ", incoming=" + incoming + ", outgoing=" + outgoing + ", communication=" + communication + "]";
	}

}
