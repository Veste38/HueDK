package be.sleroy.huedk;

import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;

import be.sleroy.huedk.dto.connection.HueAccessPoint;

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


}
