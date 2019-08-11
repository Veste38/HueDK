package be.sleroy.huedk;

import java.awt.Color;
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
import be.sleroy.huedk.utilities.ColorUtilities;

/**
 * The Class HueDKAsync.
 * 
 * @author sleroy
 */
public class HueDKAsync extends HueDKAbstract implements HueDK {

	private static Logger LOGGER = LoggerFactory.getLogger(HueDKAsync.class);

	private static HueDKEventListener LISTENER = null;

	protected HueDKAsync() {
	}

	public void registerEventListener(HueDKEventListener listener) throws HueDKUnsupportedOperationException {
		if (listener != null) {
			LISTENER = listener;
		} else {
			throw new HueDKUnsupportedOperationException("Registered listener cannot be NULL");
		}
	}

	public List<HueAccessPoint> findBridges() throws HueDKException, HueDKInitializationException {
		return findBridges(DEFAULT_DISCOVER_URL, DEFAULT_TIMEOUT);
	}

	public List<HueAccessPoint> findBridges(Integer timeout) throws HueDKException, HueDKInitializationException {
		return findBridges(DEFAULT_DISCOVER_URL, timeout);
	}

	public List<HueAccessPoint> findBridges(String discoverURL) throws HueDKException, HueDKInitializationException {
		return findBridges(discoverURL, DEFAULT_TIMEOUT);
	}

	public List<HueAccessPoint> findBridges(String discoverURL, Integer timeout) throws HueDKException, HueDKInitializationException {

		if (LISTENER == null) {
			throw new HueDKInitializationException("Registered listener cannot be NULL");
		}

		new Thread(new Runnable() {
			public void run() {
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
					LISTENER.onListReceived(HueAccessPoint.class, accessPoints);

				} catch (Exception ex) {
					LISTENER.onError(HueDKException.class, new HueDKException(ex.getMessage(), ex));
				} finally {
					if (client != null && !client.isClosed()) {
						client.close();
					}
				}
			}
		}).start();

		return null;
	}

	public String signUp(HueAccessPoint accessPoint) throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return signUp(accessPoint, DEFAULT_DEVICETYPE, DEFAULT_TIMEOUT);
	}

	public String signUp(HueAccessPoint accessPoint, String deviceType) throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return signUp(accessPoint, deviceType, DEFAULT_TIMEOUT);
	}

	public String signUp(HueAccessPoint accessPoint, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return signUp(accessPoint, DEFAULT_DEVICETYPE, timeout);
	}

	public String signUp(HueAccessPoint accessPoint, String deviceType, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException {

		if (LISTENER == null) {
			throw new HueDKInitializationException("Registered listener cannot be NULL");
		}

		new Thread(new Runnable() {
			public void run() {
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
					} else {
						LISTENER.onSignUp(userId);
					}

				} catch (HueDKConnectionException ex) {
					LISTENER.onError(HueDKConnectionException.class, ex);
				} catch (Exception ex) {
					LISTENER.onError(HueDKException.class, new HueDKException(ex.getMessage(), ex));
				} finally {
					if (client != null && !client.isClosed()) {
						client.close();
					}
				}
			}
		}).start();

		return null;
	}

	public void connect(HueAccessPoint accessPoint, String userId) throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		connect(accessPoint, userId, DEFAULT_TIMEOUT);
	}

	public void connect(HueAccessPoint accessPoint, String userId, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException {

		if (LISTENER == null) {
			throw new HueDKInitializationException("Registered listener cannot be NULL");
		}

		new Thread(new Runnable() {
			public void run() {
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
					LISTENER.onSuccessfulConnection(String.format("%s successfully connected to %s", userId, accessPoint.getIp()));

				} catch (HueDKConnectionException ex) {
					LISTENER.onError(HueDKConnectionException.class, ex);
				} catch (Exception ex) {
					LISTENER.onError(HueDKException.class, new HueDKException(ex.getMessage(), ex));
				} finally {
					if (client != null && !client.isClosed()) {
						client.close();
					}
				}
			}
		}).start();
	}

	@SuppressWarnings("unchecked")
	public List<HueLight> getLights() throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return (List<HueLight>) getList(HueLight.class, PATH_LIGHTS, DEFAULT_TIMEOUT);
	}

	@SuppressWarnings("unchecked")
	public List<HueLight> getLights(Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return (List<HueLight>) getList(HueLight.class, PATH_LIGHTS, timeout);
	}

	@SuppressWarnings("unchecked")
	public List<HueGroup> getGroups() throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return (List<HueGroup>) getList(HueGroup.class, PATH_GROUPS, DEFAULT_TIMEOUT);
	}

	@SuppressWarnings("unchecked")
	public List<HueGroup> getGroups(Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return (List<HueGroup>) getList(HueGroup.class, PATH_GROUPS, timeout);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<? extends HueIdElement> getList(Class<? extends HueIdElement> thisClass, String path, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException {

		if (LISTENER == null) {
			throw new HueDKInitializationException("Registered listener cannot be NULL");
		}

		new Thread(new Runnable() {
			public void run() {
				List<HueIdElement> list = null;

				JerseyClient client = null;
				try {
					if (ACCESSPOINT == null || USERID == null) {
						throw new HueDKConnectionException("Not connected to an access point or userId is null");
					}

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
								switch (thisClass.getSimpleName()) {
								case "HueLight":
									HueLight light = (HueLight) element;
									if (light.getState() != null) {
										if (light.getState().getXy() != null && light.getState().getXy().size() == 2) {
											Color awtColor = new Color(ColorUtilities.colorFromXY(new float[] { light.getState().getXy().get(0), light.getState().getXy().get(1) }, light.getModelId()));
											light.getState().setColorRed(awtColor.getRed());
											light.getState().setColorGreen(awtColor.getGreen());
											light.getState().setColorBlue(awtColor.getBlue());
											light.getState().setColorHex(String.format("#%02x%02x%02x", awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue()));
										}
										if (light.getState().getCt() != null) {
											light.getState().setColorTemperature(ColorUtilities.getColorTemperature(light.getState().getCt()));
										}
									}
									break;
								case "HueGroup":
									HueGroup group = (HueGroup) element;
									if (group.getAction() != null) {
										if (group.getAction().getXy() != null && group.getAction().getXy().size() == 2) {
											Color awtColor = new Color(ColorUtilities.colorFromXY(new float[] { group.getAction().getXy().get(0), group.getAction().getXy().get(1) }, "GROUP"));
											group.getAction().setColorRed(awtColor.getRed());
											group.getAction().setColorGreen(awtColor.getGreen());
											group.getAction().setColorBlue(awtColor.getBlue());
											group.getAction().setColorHex(String.format("#%02x%02x%02x", awtColor.getRed(), awtColor.getGreen(), awtColor.getBlue()));
										}
										if (group.getAction().getCt() != null) {
											group.getAction().setColorTemperature(ColorUtilities.getColorTemperature(group.getAction().getCt()));
										}
									}
									break;
								}
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
					
					LISTENER.onListReceived(thisClass, list);

				} catch (HueDKConnectionException ex) {
					LISTENER.onError(HueDKConnectionException.class, ex);
				} catch (Exception ex) {
					LISTENER.onError(HueDKException.class, new HueDKException(ex.getMessage(), ex));
				} finally {
					if (client != null && !client.isClosed()) {
						client.close();
					}
				}
			}
		}).start();

		return null;
	}
}
