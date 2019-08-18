package be.sleroy.huedk.dto.config;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import be.sleroy.huedk.dto.HueElement;
import be.sleroy.huedk.utilities.JsonDateTimeDeserializer;
import be.sleroy.huedk.utilities.JsonDateTimeSerializer;

/**
 * The Class HueConfig.
 * 
 * @author sleroy
 */
public class HueConfig extends HueElement {

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "zigbeechannel")
	private Integer zigbeeChannel;

	@JsonProperty(value = "bridgeid")
	private String bridgeId;

	@JsonProperty(value = "mac")
	private String mac;

	@JsonProperty(value = "dhcp")
	private boolean dhcp;

	@JsonProperty(value = "ipaddress")
	private String ipAddress;

	@JsonProperty(value = "netmask")
	private String netMask;

	@JsonProperty(value = "gateway")
	private String gateway;

	@JsonProperty(value = "proxyaddress")
	private String proxyAddress;

	@JsonProperty(value = "proxyport")
	private Integer proxyPort;

	@JsonProperty(value = "UTC")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private Date dateUTC;

	@JsonProperty(value = "localtime")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private Date dateLocalTime;

	@JsonProperty(value = "timezone")
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	private Date dateTimeZone;

	@JsonProperty(value = "modelid")
	private String modelId;

	@JsonProperty(value = "datastoreversion")
	private String dataStoreVersion;

	@JsonProperty(value = "swversion")
	private String softwareVersion;

	@JsonProperty(value = "apiversion")
	private String apiVersion;

	@JsonProperty(value = "swupdate")
	private Map<String, ?> swupdate;

	@JsonProperty(value = "swupdate2")
	private HueSoftwareUpdate2 softwareUpdate2;

	@JsonProperty(value = "linkbutton")
	private boolean linkButton;

	@JsonProperty(value = "portalservices")
	private boolean portalServices;

	@JsonProperty(value = "portalconnection")
	private String portalConnection;

	@JsonProperty(value = "portalstate")
	private HuePortalState portalstate;

	@JsonProperty(value = "internetservices")
	private HueInternetServices internetservices;

	@JsonProperty(value = "factorynew")
	private boolean factoryNew;

	@JsonProperty(value = "replacesbridgeid")
	private String replacesBridgeId;

	@JsonProperty(value = "backup")
	private HueBackup backup;

	@JsonProperty(value = "starterkitid")
	private String starterKitId;

	@JsonProperty(value = "whitelist")
	private Map<String, ?> internalWhiteList;

	@JsonIgnore
	private List<HueWhiteUser> whitelist;

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the zigbee channel.
	 *
	 * @return the zigbee channel
	 */
	public Integer getZigbeeChannel() {
		return zigbeeChannel;
	}

	/**
	 * Sets the zigbee channel.
	 *
	 * @param zigbeeChannel
	 *            the new zigbee channel
	 */
	public void setZigbeeChannel(Integer zigbeeChannel) {
		this.zigbeeChannel = zigbeeChannel;
	}

	/**
	 * Gets the bridge id.
	 *
	 * @return the bridge id
	 */
	public String getBridgeId() {
		return bridgeId;
	}

	/**
	 * Sets the bridge id.
	 *
	 * @param bridgeId
	 *            the new bridge id
	 */
	public void setBridgeId(String bridgeId) {
		this.bridgeId = bridgeId;
	}

	/**
	 * Gets the mac.
	 *
	 * @return the mac
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Sets the mac.
	 *
	 * @param mac
	 *            the new mac
	 */
	public void setMac(String mac) {
		this.mac = mac;
	}

	/**
	 * Checks if is dhcp.
	 *
	 * @return true, if is dhcp
	 */
	public boolean isDhcp() {
		return dhcp;
	}

	/**
	 * Sets the dhcp.
	 *
	 * @param dhcp
	 *            the new dhcp
	 */
	public void setDhcp(boolean dhcp) {
		this.dhcp = dhcp;
	}

	/**
	 * Gets the ip address.
	 *
	 * @return the ip address
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * Sets the ip address.
	 *
	 * @param ipAddress
	 *            the new ip address
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * Gets the net mask.
	 *
	 * @return the net mask
	 */
	public String getNetMask() {
		return netMask;
	}

	/**
	 * Sets the net mask.
	 *
	 * @param netMask
	 *            the new net mask
	 */
	public void setNetMask(String netMask) {
		this.netMask = netMask;
	}

	/**
	 * Gets the gateway.
	 *
	 * @return the gateway
	 */
	public String getGateway() {
		return gateway;
	}

	/**
	 * Sets the gateway.
	 *
	 * @param gateway
	 *            the new gateway
	 */
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	/**
	 * Gets the proxy address.
	 *
	 * @return the proxy address
	 */
	public String getProxyAddress() {
		return proxyAddress;
	}

	/**
	 * Sets the proxy address.
	 *
	 * @param proxyAddress
	 *            the new proxy address
	 */
	public void setProxyAddress(String proxyAddress) {
		this.proxyAddress = proxyAddress;
	}

	/**
	 * Gets the proxy port.
	 *
	 * @return the proxy port
	 */
	public Integer getProxyPort() {
		return proxyPort;
	}

	/**
	 * Sets the proxy port.
	 *
	 * @param proxyPort
	 *            the new proxy port
	 */
	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}

	/**
	 * Gets the date UTC.
	 *
	 * @return the date UTC
	 */
	public Date getDateUTC() {
		return dateUTC;
	}

	/**
	 * Sets the date UTC.
	 *
	 * @param dateUTC
	 *            the new date UTC
	 */
	public void setDateUTC(Date dateUTC) {
		this.dateUTC = dateUTC;
	}

	/**
	 * Gets the date local time.
	 *
	 * @return the date local time
	 */
	public Date getDateLocalTime() {
		return dateLocalTime;
	}

	/**
	 * Sets the date local time.
	 *
	 * @param dateLocalTime
	 *            the new date local time
	 */
	public void setDateLocalTime(Date dateLocalTime) {
		this.dateLocalTime = dateLocalTime;
	}

	/**
	 * Gets the date time zone.
	 *
	 * @return the date time zone
	 */
	public Date getDateTimeZone() {
		return dateTimeZone;
	}

	/**
	 * Sets the date time zone.
	 *
	 * @param dateTimeZone
	 *            the new date time zone
	 */
	public void setDateTimeZone(Date dateTimeZone) {
		this.dateTimeZone = dateTimeZone;
	}

	/**
	 * Gets the model id.
	 *
	 * @return the model id
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * Sets the model id.
	 *
	 * @param modelId
	 *            the new model id
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	/**
	 * Gets the data store version.
	 *
	 * @return the data store version
	 */
	public String getDataStoreVersion() {
		return dataStoreVersion;
	}

	/**
	 * Sets the data store version.
	 *
	 * @param dataStoreVersion
	 *            the new data store version
	 */
	public void setDataStoreVersion(String dataStoreVersion) {
		this.dataStoreVersion = dataStoreVersion;
	}

	/**
	 * Gets the software version.
	 *
	 * @return the software version
	 */
	public String getSoftwareVersion() {
		return softwareVersion;
	}

	/**
	 * Sets the software version.
	 *
	 * @param softwareVersion
	 *            the new software version
	 */
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	/**
	 * Gets the api version.
	 *
	 * @return the api version
	 */
	public String getApiVersion() {
		return apiVersion;
	}

	/**
	 * Sets the api version.
	 *
	 * @param apiVersion
	 *            the new api version
	 */
	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	/**
	 * Gets the software update 2.
	 *
	 * @return the software update 2
	 */
	public HueSoftwareUpdate2 getSoftwareUpdate2() {
		return softwareUpdate2;
	}

	/**
	 * Sets the software update 2.
	 *
	 * @param softwareUpdate2
	 *            the new software update 2
	 */
	public void setSoftwareUpdate2(HueSoftwareUpdate2 softwareUpdate2) {
		this.softwareUpdate2 = softwareUpdate2;
	}

	/**
	 * Checks if is link button.
	 *
	 * @return true, if is link button
	 */
	public boolean isLinkButton() {
		return linkButton;
	}

	/**
	 * Sets the link button.
	 *
	 * @param linkButton
	 *            the new link button
	 */
	public void setLinkButton(boolean linkButton) {
		this.linkButton = linkButton;
	}

	/**
	 * Checks if is portal services.
	 *
	 * @return true, if is portal services
	 */
	public boolean isPortalServices() {
		return portalServices;
	}

	/**
	 * Sets the portal services.
	 *
	 * @param portalServices
	 *            the new portal services
	 */
	public void setPortalServices(boolean portalServices) {
		this.portalServices = portalServices;
	}

	/**
	 * Gets the portal connection.
	 *
	 * @return the portal connection
	 */
	public String getPortalConnection() {
		return portalConnection;
	}

	/**
	 * Sets the portal connection.
	 *
	 * @param portalConnection
	 *            the new portal connection
	 */
	public void setPortalConnection(String portalConnection) {
		this.portalConnection = portalConnection;
	}

	/**
	 * Gets the portalstate.
	 *
	 * @return the portalstate
	 */
	public HuePortalState getPortalstate() {
		return portalstate;
	}

	/**
	 * Sets the portalstate.
	 *
	 * @param portalstate
	 *            the new portalstate
	 */
	public void setPortalstate(HuePortalState portalstate) {
		this.portalstate = portalstate;
	}

	/**
	 * Gets the internetservices.
	 *
	 * @return the internetservices
	 */
	public HueInternetServices getInternetservices() {
		return internetservices;
	}

	/**
	 * Sets the internetservices.
	 *
	 * @param internetservices
	 *            the new internetservices
	 */
	public void setInternetservices(HueInternetServices internetservices) {
		this.internetservices = internetservices;
	}

	/**
	 * Checks if is factory new.
	 *
	 * @return true, if is factory new
	 */
	public boolean isFactoryNew() {
		return factoryNew;
	}

	/**
	 * Sets the factory new.
	 *
	 * @param factoryNew
	 *            the new factory new
	 */
	public void setFactoryNew(boolean factoryNew) {
		this.factoryNew = factoryNew;
	}

	/**
	 * Gets the replaces bridge id.
	 *
	 * @return the replaces bridge id
	 */
	public String getReplacesBridgeId() {
		return replacesBridgeId;
	}

	/**
	 * Sets the replaces bridge id.
	 *
	 * @param replacesBridgeId
	 *            the new replaces bridge id
	 */
	public void setReplacesBridgeId(String replacesBridgeId) {
		this.replacesBridgeId = replacesBridgeId;
	}

	/**
	 * Gets the backup.
	 *
	 * @return the backup
	 */
	public HueBackup getBackup() {
		return backup;
	}

	/**
	 * Sets the backup.
	 *
	 * @param backup
	 *            the new backup
	 */
	public void setBackup(HueBackup backup) {
		this.backup = backup;
	}

	/**
	 * Gets the starter kit id.
	 *
	 * @return the starter kit id
	 */
	public String getStarterKitId() {
		return starterKitId;
	}

	/**
	 * Sets the starter kit id.
	 *
	 * @param starterKitId
	 *            the new starter kit id
	 */
	public void setStarterKitId(String starterKitId) {
		this.starterKitId = starterKitId;
	}

	/**
	 * Gets the white list internal.
	 *
	 * @return the white list internal
	 */
	public Map<String, ?> getInternalWhiteList() {
		return internalWhiteList;
	}

	/**
	 * Sets the white list internal.
	 *
	 * @param whiteListInternal
	 *            the new white list internal
	 */
	public void setInternalWhiteList(Map<String, ?> whiteListInternal) {
		this.internalWhiteList = whiteListInternal;
	}

	/**
	 * Gets the whitelist.
	 *
	 * @return the whitelist
	 */
	public List<HueWhiteUser> getWhitelist() {
		return whitelist;
	}

	/**
	 * Sets the whitelist.
	 *
	 * @param whitelist
	 *            the new whitelist
	 */
	public void setWhitelist(List<HueWhiteUser> whitelist) {
		this.whitelist = whitelist;
	}

	@Override
	public String toString() {
		return "HueConfig [name=" + name + ", zigbeeChannel=" + zigbeeChannel + ", bridgeId=" + bridgeId + ", mac=" + mac + ", dhcp=" + dhcp + ", ipAddress=" + ipAddress + ", netMask=" + netMask + ", gateway=" + gateway + ", proxyAddress=" + proxyAddress + ", proxyPort=" + proxyPort + ", dateUTC=" + dateUTC
				+ ", dateLocalTime=" + dateLocalTime + ", dateTimeZone=" + dateTimeZone + ", modelId=" + modelId + ", dataStoreVersion=" + dataStoreVersion + ", softwareVersion=" + softwareVersion + ", apiVersion=" + apiVersion + ", softwareUpdate2=" + softwareUpdate2 + ", linkButton="
				+ linkButton + ", portalServices=" + portalServices + ", portalConnection=" + portalConnection + ", portalstate=" + portalstate + ", internetservices=" + internetservices + ", factoryNew=" + factoryNew + ", replacesBridgeId=" + replacesBridgeId + ", backup=" + backup + ", starterKitId=" + starterKitId
				+ ", whitelist=" + whitelist + "]";
	}

}
