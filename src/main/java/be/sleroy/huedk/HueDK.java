package be.sleroy.huedk;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Entity;
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

import be.sleroy.huedk.dto.connection.HueAccessPoint;
import be.sleroy.huedk.dto.internal.HueResponse;
import be.sleroy.huedk.exception.HueDKConnectionException;
import be.sleroy.huedk.exception.HueDKException;

/**
 * The Class HueDK.
 * 
 * @author sleroy
 */
public class HueDK {

	private static Logger LOGGER = LoggerFactory.getLogger(HueDK.class);

	private static String DEFAULT_DISCOVER_URL = "https://discovery.meethue.com";
	private static int DEFAULT_TIMEOUT = 30000;
	private static String DEFAULT_DEVICETYPE = "HueDK#Default";

	private static HueDK instance = null;

	private static HueAccessPoint ACCESSPOINT = null;
	private static String USERID = null;

	public static HueDK getInstance() {
		if (instance == null) {
			instance = new HueDK();
		}
		return instance;
	}

	private HueDK() {
	}

	public List<HueAccessPoint> findBridges() throws HueDKException {
		return findBridges(DEFAULT_DISCOVER_URL, DEFAULT_TIMEOUT);
	}

	public List<HueAccessPoint> findBridges(Integer timeout) throws HueDKException {
		return findBridges(DEFAULT_DISCOVER_URL, timeout);
	}

	public List<HueAccessPoint> findBridges(String discoverURL) throws HueDKException {
		return findBridges(discoverURL, DEFAULT_TIMEOUT);
	}

	public List<HueAccessPoint> findBridges(String discoverURL, Integer timeout) throws HueDKException {
		List<HueAccessPoint> accessPoints = null;

		JerseyClient client = null;
		try {
			client = JerseyClientBuilder.createClient();

			// default timeout value for all requests
			client.property(ClientProperties.CONNECT_TIMEOUT, timeout != null ? timeout : DEFAULT_TIMEOUT);
			client.property(ClientProperties.READ_TIMEOUT, timeout != null ? timeout : DEFAULT_TIMEOUT);

			JerseyWebTarget webTarget = client.target(discoverURL);

			Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON)
					.header("content-type", MediaType.APPLICATION_JSON);

			Response response = builder.get();

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				String error = response.readEntity(String.class);
				LOGGER.error(String.format("Status: %d | Error: %s", response.getStatus(), error));
				throw new HueDKException(String.format("Status: %d | Error: %s", response.getStatus(), error));
			}

			GenericType<List<HueAccessPoint>> generic = new GenericType<List<HueAccessPoint>>() {
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

	public String signUp(HueAccessPoint accessPoint) throws HueDKException, HueDKConnectionException {
		return signUp(accessPoint, DEFAULT_DEVICETYPE, DEFAULT_TIMEOUT);
	}

	public String signUp(HueAccessPoint accessPoint, String deviceType) throws HueDKException, HueDKConnectionException {
		return signUp(accessPoint, deviceType, DEFAULT_TIMEOUT);
	}

	public String signUp(HueAccessPoint accessPoint, Integer timeout) throws HueDKException, HueDKConnectionException {
		return signUp(accessPoint, DEFAULT_DEVICETYPE, timeout);
	}

	public String signUp(HueAccessPoint accessPoint, String deviceType, Integer timeout) throws HueDKException, HueDKConnectionException {
		String userId = null;

		Long startDate = (new Date()).getTime();
		JerseyClient client = null;
		try {

			Long runDate = (new Date()).getTime();

			client = JerseyClientBuilder.createClient();

			// default timeout value for all requests
			client.property(ClientProperties.CONNECT_TIMEOUT, timeout != null ? timeout : DEFAULT_TIMEOUT);
			client.property(ClientProperties.READ_TIMEOUT, timeout != null ? timeout : DEFAULT_TIMEOUT);

			JerseyWebTarget webTarget = client.target(String.format("http://%s/api", accessPoint.getIp()));

			Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON)
					.header("content-type", MediaType.APPLICATION_JSON);

			GenericType<List<HueResponse>> generic = new GenericType<List<HueResponse>>() {
			};

			while (userId == null && (runDate - startDate) < timeout) {

				Response response = builder.post(Entity.entity(String.format("{\"devicetype\":\"%s\"}", deviceType), MediaType.APPLICATION_JSON));

				if (response.getStatus() == Response.Status.OK.getStatusCode()) {
					List<HueResponse> responseList = response.readEntity(generic);
					if (responseList != null && responseList.size() > 0) {
						if (responseList.get(0).getSuccess() != null) {
							userId = responseList.get(0).getSuccess().getUsername();
						} else if (responseList.get(0).getError() != null) {
							LOGGER.debug(String.format("Error: %s | Description: %s", responseList.get(0).getError().getType(), responseList.get(0).getError().getDescription()));
						}
					}
				} else {
					String error = response.readEntity(String.class);
					LOGGER.error(String.format("Status: %d | Error: %s", response.getStatus(), error));
					throw new HueDKConnectionException(String.format("Status: %d | Error: %s", response.getStatus(), error));
				}

				if (userId == null) {
					Thread.sleep(1000);
					runDate = (new Date()).getTime();
				}
			}
			if (userId == null) {
				throw new HueDKConnectionException("Bridge button not pressed!");
			}

		} catch (HueDKConnectionException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HueDKException(ex.getMessage(), ex);
		} finally {
			if (client != null && !client.isClosed()) {
				client.close();
			}
		}

		return userId;
	}

	public void connect(HueAccessPoint accessPoint, String userId) throws HueDKException, HueDKConnectionException {
		connect(accessPoint, userId, DEFAULT_TIMEOUT);
	}	
	
	public void connect(HueAccessPoint accessPoint, String userId, Integer timeout) throws HueDKException, HueDKConnectionException {
		JerseyClient client = null;
		try {
			client = JerseyClientBuilder.createClient();

			// default timeout value for all requests
			client.property(ClientProperties.CONNECT_TIMEOUT, timeout != null ? timeout : DEFAULT_TIMEOUT);
			client.property(ClientProperties.READ_TIMEOUT, timeout != null ? timeout : DEFAULT_TIMEOUT);

			JerseyWebTarget webTarget = client.target(String.format("http://%s/api/%s", accessPoint.getIp(), userId));

			Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON)
					.header("content-type", MediaType.APPLICATION_JSON);

			Response response = builder.get();

			GenericType<List<HueResponse>> generic = new GenericType<List<HueResponse>>() {
			};

			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				try {
					List<HueResponse> responseList = response.readEntity(generic);
					if (responseList != null && responseList.size() > 0) {
						if (responseList.get(0).getError() != null) {
							LOGGER.debug(String.format("Error: %s | Description: %s", responseList.get(0).getError().getType(), responseList.get(0).getError().getDescription()));
							throw new HueDKConnectionException(String.format("Error: %s | Description: %s", responseList.get(0).getError().getType(), responseList.get(0).getError().getDescription()));
						}
					}
				} catch (javax.ws.rs.ProcessingException ex) {
				}
			} else {
				String error = response.readEntity(String.class);
				LOGGER.error(String.format("Status: %d | Error: %s", response.getStatus(), error));
				throw new HueDKConnectionException(String.format("Status: %d | Error: %s", response.getStatus(), error));
			}

			USERID = userId;
			ACCESSPOINT = accessPoint;
			
			LOGGER.debug(String.format("%s successfully connected to %s", userId, accessPoint.getIp()));

		} catch (HueDKConnectionException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new HueDKException(ex.getMessage(), ex);
		} finally {
			if (client != null && !client.isClosed()) {
				client.close();
			}
		}
	}

}
