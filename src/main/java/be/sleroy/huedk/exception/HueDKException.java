package be.sleroy.huedk.exception;

/**
 * The Class HueDKException.
 * 
 * @author sleroy
 */
public class HueDKException extends Exception {

	private static final long serialVersionUID = 224265585642285196L;

	/**
	 * Instantiates a new hue DK exception.
	 *
	 * @param message
	 *            the message
	 */
	public HueDKException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new hue DK exception.
	 *
	 * @param message
	 *            the message
	 * @param throwable
	 *            the throwable
	 */
	public HueDKException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
