package be.sleroy.huedk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import be.sleroy.huedk.dto.HueIdElement;
import be.sleroy.huedk.dto.connection.HueAccessPoint;
import be.sleroy.huedk.dto.group.HueGroup;
import be.sleroy.huedk.dto.internal.HueResponse;
import be.sleroy.huedk.dto.light.HueLight;
import be.sleroy.huedk.exception.HueDKConnectionException;
import be.sleroy.huedk.exception.HueDKException;
import be.sleroy.huedk.exception.HueDKInitializationException;
import be.sleroy.huedk.exception.HueDKUnsupportedOperationException;

/**
 * The Class HueDKSync.
 * 
 * @author sleroy
 */
public class HueDKSync extends HueDKAbstract implements HueDK {

	private static Logger LOGGER = LoggerFactory.getLogger(HueDKSync.class);

	protected HueDKSync() {
	}

	public void registerEventListener(HueDKEventListener listener) throws HueDKUnsupportedOperationException {
		throw new HueDKUnsupportedOperationException("Registering event listener is not supported in Sync mode");
	}

	@Override
	public List<HueAccessPoint> findBridges(String discoverURL, Integer timeout) throws HueDKException {
		List<HueAccessPoint> accessPoints = null;

		JerseyClient client = null;
		try {
			client = getJerseyClient(timeout, timeout);

			JerseyWebTarget webTarget = client.target(discoverURL);

			Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON)
					.header("content-type", MediaType.APPLICATION_JSON);

			Response response = builder.get();

			if (response.getStatus() != Response.Status.OK.getStatusCode()) {
				String error = response.readEntity(String.class);
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace(String.format("Status: %d | Error: %s", response.getStatus(), error));
				}
				throw new HueDKException(String.format("Status: %d | Error: %s", response.getStatus(), error));
			}

			GenericType<List<HueAccessPoint>> generic = new GenericType<List<HueAccessPoint>>() {
			};

			accessPoints = response.readEntity(generic);

			if (LOGGER.isTraceEnabled()) {
				if (accessPoints != null) {
					for (HueAccessPoint accessPoint : accessPoints) {
						LOGGER.trace(accessPoint.toString());
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

	@Override
	public String signUp(HueAccessPoint accessPoint, String deviceType, Integer timeout) throws HueDKException, HueDKConnectionException {
		String userId = null;

		Long startDate = (new Date()).getTime();
		JerseyClient client = null;
		try {

			Long runDate = (new Date()).getTime();

			client = getJerseyClient(timeout, timeout);

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
							if (LOGGER.isDebugEnabled()) {
								LOGGER.debug(String.format("Error: %s | Description: %s", responseList.get(0).getError().getType(), responseList.get(0).getError().getDescription()));
							}
						}
					}
				} else {
					String error = response.readEntity(String.class);
					if (LOGGER.isTraceEnabled()) {
						LOGGER.trace(String.format("Status: %d | Error: %s", response.getStatus(), error));
					}
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

	@Override
	public void connect(HueAccessPoint accessPoint, String userId, Integer timeout) throws HueDKException, HueDKConnectionException {
		JerseyClient client = null;
		try {
			client = getJerseyClient(timeout, timeout);

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
							if (LOGGER.isTraceEnabled()) {
								LOGGER.trace(String.format("Error: %s | Description: %s", responseList.get(0).getError().getType(), responseList.get(0).getError().getDescription()));
							}
							throw new HueDKConnectionException(String.format("Error: %s | Description: %s", responseList.get(0).getError().getType(), responseList.get(0).getError().getDescription()));
						}
					}
				} catch (javax.ws.rs.ProcessingException ex) {
				}
			} else {
				String error = response.readEntity(String.class);
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace(String.format("Status: %d | Error: %s", response.getStatus(), error));
				}
				throw new HueDKConnectionException(String.format("Status: %d | Error: %s", response.getStatus(), error));
			}

			USERID = userId;
			ACCESSPOINT = accessPoint;

			if (LOGGER.isTraceEnabled()) {
				LOGGER.trace(String.format("%s successfully connected to %s", userId, accessPoint.getIp()));
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
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected List<? extends HueIdElement> getList(Class<? extends HueIdElement> thisClass, String path, Integer timeout) throws HueDKException, HueDKConnectionException {
		List<HueIdElement> list = null;

		if (ACCESSPOINT == null || USERID == null) {
			throw new HueDKConnectionException("Not connected to an access point or userId is null");
		}

		JerseyClient client = null;
		try {
			client = getJerseyClient(timeout, timeout);

			JerseyWebTarget webTarget = client.target(String.format("http://%s/api/%s/%s", ACCESSPOINT.getIp(), USERID, path));

			Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON)
					.header("content-type", MediaType.APPLICATION_JSON);

			Response response = builder.get();

			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				Map<String, Map> all = response.readEntity(Map.class);

				if (all != null && all.size() > 0) {
					list = new ArrayList<HueIdElement>();
					ObjectMapper mapper = new ObjectMapper();
					for (String key : all.keySet()) {
						if (LOGGER.isTraceEnabled()) {
							LOGGER.trace(String.format("%s:\n%s", key, all.get(key)));
						}
						HueIdElement element = mapper.readValue(new ObjectMapper().writeValueAsString(all.get(key)), thisClass);
						element.setId(key);
						processColor(thisClass, element);
						list.add(element);
					}
				}

			} else {
				String error = response.readEntity(String.class);
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace(String.format("Status: %d | Error: %s", response.getStatus(), error));
				}
				throw new HueDKConnectionException(String.format("Status: %d | Error: %s", response.getStatus(), error));
			}

		} catch (Exception ex) {
			throw new HueDKException(ex.getMessage(), ex);
		} finally {
			if (client != null && !client.isClosed()) {
				client.close();
			}
		}

		return list;
	}

	@Override
	protected HueIdElement getElement(Class<? extends HueIdElement> thisClass, String id, String path, Integer timeout) throws HueDKException, HueDKConnectionException {
		HueIdElement element = null;

		if (ACCESSPOINT == null || USERID == null) {
			throw new HueDKConnectionException("Not connected to an access point or userId is null");
		}

		JerseyClient client = null;
		try {
			client = getJerseyClient(timeout, timeout);

			JerseyWebTarget webTarget = client.target(String.format("http://%s/api/%s/%s/%s", ACCESSPOINT.getIp(), USERID, path, id));

			Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_JSON)
					.header("content-type", MediaType.APPLICATION_JSON);

			Response response = builder.get();

			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				element = response.readEntity(thisClass);

				element.setId(id);
				processColor(thisClass, element);

			} else {
				String error = response.readEntity(String.class);
				if (LOGGER.isTraceEnabled()) {
					LOGGER.trace(String.format("Status: %d | Error: %s", response.getStatus(), error));
				}
				throw new HueDKConnectionException(String.format("Status: %d | Error: %s", response.getStatus(), error));
			}

		} catch (Exception ex) {
			throw new HueDKException(ex.getMessage(), ex);
		} finally {
			if (client != null && !client.isClosed()) {
				client.close();
			}
		}

		return element;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<HueLight> getLightsOfGroup(String groupId, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		List<HueLight> lights = null;
		HueGroup hueGroup = (HueGroup)getElement(HueGroup.class, groupId, PATH_GROUPS, timeout);
		if (hueGroup != null && hueGroup.getLightIdList() != null && hueGroup.getLightIdList().size() > 0) {
			List<HueLight> allLights = (List<HueLight>)getList(HueLight.class, PATH_LIGHTS, timeout);
			if (allLights != null && allLights.size() > 0) {
				lights = new ArrayList<HueLight>();
				for (HueLight light : allLights) {
					if (hueGroup.getLightIdList().contains(light.getId())) {
						lights.add(light);
					}
				}
			}
		}
		if (lights != null && lights.size() == 0) {
			lights = null;
		}
		return lights;
	}
 
}
