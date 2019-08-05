package be.sleroy.huedk;

import java.util.List;

import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.sleroy.huedk.dto.HueAccessPoint;
import be.sleroy.huedk.exception.HueDKException;

/**
 * The Class HueDK.
 */
public class HueDK {

	private static Logger LOGGER = LoggerFactory.getLogger(HueDK.class);

	private static String DEFAULT_DISCOVER_URL = "https://discovery.meethue.com";
	private static int DEFAULT_FIND_BRIDGES_TIMEOUT = 30000;

	private static HueDK instance = null;

	/**
	 * Gets the single instance of PHueAPI.
	 *
	 * @return single instance of PHueAPI
	 */
	public static HueDK getInstance() {
		if (instance == null) {
			instance = new HueDK();
		}
		return instance;
	}

	/**
	 * Instantiates a new p hue API.
	 */
	private HueDK() {
	}

	/**
	 * Find bridges.
	 *
	 * @return the list
	 * @throws HueDKException
	 *             the p hue API exception
	 */
	public List<HueAccessPoint> findBridges() throws HueDKException {
		return findBridges(DEFAULT_DISCOVER_URL, DEFAULT_FIND_BRIDGES_TIMEOUT);
	}

	/**
	 * Find bridges.
	 *
	 * @param timeout
	 *            the timeout
	 * @return the list
	 * @throws HueDKException
	 *             the p hue API exception
	 */
	public List<HueAccessPoint> findBridges(Integer timeout) throws HueDKException {
		return findBridges(DEFAULT_DISCOVER_URL, timeout);
	}

	/**
	 * Find bridges.
	 *
	 * @param discoverURL
	 *            the discover URL
	 * @param timeout
	 *            the timeout
	 * @return the list
	 * @throws HueDKException
	 *             the p hue API exception
	 */
	public List<HueAccessPoint> findBridges(String discoverURL, Integer timeout) throws HueDKException {
		List<HueAccessPoint> accessPoints = null;

		JerseyClient client = null;
		try {
			client = JerseyClientBuilder.createClient();

			// default timeout value for all requests
			client.property(ClientProperties.CONNECT_TIMEOUT, timeout != null ? timeout : DEFAULT_FIND_BRIDGES_TIMEOUT);
			client.property(ClientProperties.READ_TIMEOUT, timeout != null ? timeout : DEFAULT_FIND_BRIDGES_TIMEOUT);

			JerseyWebTarget webTarget = client.target(discoverURL);

			Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON)
					.header("content-type", MediaType.APPLICATION_JSON);

			Response response = builder.get();

			// Status 200 is successful.
			if (response.getStatus() != 200) {
				String error = response.readEntity(String.class);
				LOGGER.error(String.format("Status: %d With error: %s", response.getStatus(), error));
				throw new HueDKException(String.format("Status: %d With error: %s", response.getStatus(), error));
			}

			GenericType<List<HueAccessPoint>> generic = new GenericType<List<HueAccessPoint>>() {
				// No thing
			};

			accessPoints = response.readEntity(generic);

			if (LOGGER.isDebugEnabled()) {
				if (accessPoints != null) {
					for (HueAccessPoint accessPoint : accessPoints) {
						LOGGER.debug(accessPoint.toString());
					}
				}
			}
		} catch (Exception ex) {
			throw new HueDKException(ex.getMessage(), ex);
		} finally {
			if (client != null && !client.isClosed()) {
				client.close();
			}
		}
		
		return accessPoints;
	}

}
