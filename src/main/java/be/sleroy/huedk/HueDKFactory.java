package be.sleroy.huedk;

/**
 * A factory for creating HueDK objects.
 * 
 * @author sleroy
 */
public class HueDKFactory {

	private static HueDKSync instanceSync = null;
	private static HueDKAsync instanceAsync = null;

	/**
	 * Gets the hue DK instance.
	 *
	 * @param type
	 *            the type
	 * @return the hue DK instance
	 */
	public static HueDK getHueDKInstance(HueDK.Mode mode) {
		HueDK hueDK = null;
		switch (mode) {
		case SYNC:
			if (instanceSync == null) {
				instanceSync = new HueDKSync();
			}
			hueDK = instanceSync;
			break;
		case ASYNC:
			if (instanceAsync == null) {
				instanceAsync = new HueDKAsync();
			}
			hueDK = instanceAsync;
			break;
		}
		return hueDK;
	}

}
