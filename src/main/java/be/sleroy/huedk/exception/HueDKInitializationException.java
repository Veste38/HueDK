package be.sleroy.huedk.exception;

/**
 * The Class HueDKInitializationException.
 * 
 * @author sleroy
 */
public class HueDKInitializationException extends HueDKException {

	private static final long serialVersionUID = -102719638485935533L;

	public HueDKInitializationException(String message) {
		super(message);
	}

	public HueDKInitializationException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
