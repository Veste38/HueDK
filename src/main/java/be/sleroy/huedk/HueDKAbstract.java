package be.sleroy.huedk;

import java.awt.Color;
import java.util.List;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import be.sleroy.huedk.dto.HueIdElement;
import be.sleroy.huedk.dto.config.HueConfig;
import be.sleroy.huedk.dto.connection.HueAccessPoint;
import be.sleroy.huedk.dto.group.HueGroup;
import be.sleroy.huedk.dto.light.HueLight;
import be.sleroy.huedk.exception.HueDKConnectionException;
import be.sleroy.huedk.exception.HueDKException;
import be.sleroy.huedk.exception.HueDKInitializationException;
import be.sleroy.huedk.utilities.ColorUtilities;

public abstract class HueDKAbstract {

	protected static String DEFAULT_DISCOVER_URL = "https://discovery.meethue.com";
	protected static int DEFAULT_TIMEOUT = 30000;
	protected static String DEFAULT_DEVICETYPE = "HueDK#Default";

	protected static String PATH_LIGHTS = "lights";
	protected static String PATH_GROUPS = "groups";
	protected static String PATH_CONFIG = "config";
	protected static String PATH_SCHEDULES = "schedules";
	protected static String PATH_SCENES = "scenes";
	protected static String PATH_SENSORS = "sensors";
	protected static String PATH_RULES = "rules";

	protected static HueDKSync instance = null;

	protected static HueAccessPoint ACCESSPOINT = null;
	protected static String USERID = null;

	protected JerseyClient getJerseyClient(Integer connectionTimeout, Integer readTimeout) {
		JerseyClient client = JerseyClientBuilder.createClient();

		// default timeout value for all requests
		client.property(ClientProperties.CONNECT_TIMEOUT, connectionTimeout != null ? connectionTimeout : DEFAULT_TIMEOUT);
		client.property(ClientProperties.READ_TIMEOUT, readTimeout != null ? readTimeout : DEFAULT_TIMEOUT);

		return client;
	}

	protected void processColor(Class<? extends HueIdElement> thisClass, HueIdElement element) {
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

	public String signUp(HueAccessPoint accessPoint) throws HueDKException, HueDKConnectionException {
		return signUp(accessPoint, DEFAULT_DEVICETYPE, DEFAULT_TIMEOUT);
	}

	public String signUp(HueAccessPoint accessPoint, String deviceType) throws HueDKException, HueDKConnectionException {
		return signUp(accessPoint, deviceType, DEFAULT_TIMEOUT);
	}

	public String signUp(HueAccessPoint accessPoint, Integer timeout) throws HueDKException, HueDKConnectionException {
		return signUp(accessPoint, DEFAULT_DEVICETYPE, timeout);
	}

	public void connect(HueAccessPoint accessPoint, String userId) throws HueDKException, HueDKConnectionException {
		connect(accessPoint, userId, DEFAULT_TIMEOUT);
	}

	@SuppressWarnings("unchecked")
	public List<HueLight> getLights() throws HueDKException, HueDKConnectionException {
		return (List<HueLight>) getList(HueLight.class, PATH_LIGHTS, DEFAULT_TIMEOUT);
	}

	@SuppressWarnings("unchecked")
	public List<HueLight> getLights(Integer timeout) throws HueDKException, HueDKConnectionException {
		return (List<HueLight>) getList(HueLight.class, PATH_LIGHTS, timeout);
	}

	public HueLight getLight(String id) throws HueDKException, HueDKConnectionException {
		return (HueLight) getElement(HueLight.class, id, PATH_LIGHTS, DEFAULT_TIMEOUT);
	}

	public HueLight getLight(String id, Integer timeout) throws HueDKException, HueDKConnectionException {
		return (HueLight) getElement(HueLight.class, id, PATH_LIGHTS, timeout);
	}

	public List<HueLight> getLightsOfGroup(String groupId) throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return getLightsOfGroup(groupId, DEFAULT_TIMEOUT);
	}

	@SuppressWarnings("unchecked")
	public List<HueGroup> getGroups() throws HueDKException, HueDKConnectionException {
		return (List<HueGroup>) getList(HueGroup.class, PATH_GROUPS, DEFAULT_TIMEOUT);
	}

	@SuppressWarnings("unchecked")
	public List<HueGroup> getGroups(Integer timeout) throws HueDKException, HueDKConnectionException {
		return (List<HueGroup>) getList(HueGroup.class, PATH_GROUPS, timeout);
	}

	public List<HueGroup> getGroupsOfLight(String lightId) throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return getGroupsOfLight(lightId, DEFAULT_TIMEOUT);
	}

	public HueGroup getGroup(String id) throws HueDKException, HueDKConnectionException {
		return (HueGroup) getElement(HueGroup.class, id, PATH_GROUPS, DEFAULT_TIMEOUT);
	}

	public HueGroup getGroup(String id, Integer timeout) throws HueDKException, HueDKConnectionException {
		return (HueGroup) getElement(HueGroup.class, id, PATH_GROUPS, timeout);
	}

	public HueConfig getConfig() throws HueDKException, HueDKInitializationException, HueDKConnectionException {
		return getConfig(DEFAULT_TIMEOUT);
	}

	public abstract List<HueAccessPoint> findBridges(String discoverURL, Integer timeout) throws HueDKException;

	public abstract String signUp(HueAccessPoint accessPoint, String deviceType, Integer timeout) throws HueDKException, HueDKConnectionException;

	public abstract void connect(HueAccessPoint accessPoint, String userId, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	protected abstract List<? extends HueIdElement> getList(Class<? extends HueIdElement> thisClass, String path, Integer timeout) throws HueDKException, HueDKConnectionException;

	protected abstract HueIdElement getElement(Class<? extends HueIdElement> thisClass, String id, String path, Integer timeout) throws HueDKException, HueDKConnectionException;

	public abstract List<HueLight> getLightsOfGroup(String groupId, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	public abstract List<HueGroup> getGroupsOfLight(String lightId, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	public abstract HueConfig getConfig(Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

}
