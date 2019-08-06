package be.sleroy.huedk.exception;

/**
 * The Class HueDKConnectionException.
 * 
 * @author sleroy
 */
public class HueDKConnectionException extends Exception {

	private static final long serialVersionUID = 8329592001094529265L;

	/**
	 * Instantiates a new hue DK connection exception.
	 *
	 * @param message
	 *            the message
	 */
	public HueDKConnectionException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new hue DK connection exception.
	 *
	 * @param message
	 *            the message
	 * @param throwable
	 *            the throwable
	 */
	public HueDKConnectionException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
