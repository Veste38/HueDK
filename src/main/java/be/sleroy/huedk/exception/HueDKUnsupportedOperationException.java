package be.sleroy.huedk.exception;

/**
 * The Class HueDKUnsupportedOperationException.
 * 
 * @author sleroy
 */
public class HueDKUnsupportedOperationException extends HueDKException {

	private static final long serialVersionUID = 6833807132842145496L;

	/**
	 * Instantiates a new hue DK unsupported operation exception.
	 *
	 * @param message
	 *            the message
	 */
	public HueDKUnsupportedOperationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new hue DK unsupported operation exception.
	 *
	 * @param message
	 *            the message
	 * @param throwable
	 *            the throwable
	 */
	public HueDKUnsupportedOperationException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
