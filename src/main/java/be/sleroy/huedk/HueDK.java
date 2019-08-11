package be.sleroy.huedk;

import java.util.List;

import be.sleroy.huedk.dto.connection.HueAccessPoint;
import be.sleroy.huedk.dto.group.HueGroup;
import be.sleroy.huedk.dto.light.HueLight;
import be.sleroy.huedk.exception.HueDKConnectionException;
import be.sleroy.huedk.exception.HueDKException;
import be.sleroy.huedk.exception.HueDKInitializationException;
import be.sleroy.huedk.exception.HueDKUnsupportedOperationException;

public interface HueDK {

	public enum Mode {
		SYNC, ASYNC;
	}

	void registerEventListener(HueDKEventListener listener) throws HueDKUnsupportedOperationException;
	
	List<HueAccessPoint> findBridges() throws HueDKException, HueDKInitializationException;

	List<HueAccessPoint> findBridges(Integer timeout) throws HueDKException, HueDKInitializationException;

	List<HueAccessPoint> findBridges(String discoverURL) throws HueDKException, HueDKInitializationException;

	List<HueAccessPoint> findBridges(String discoverURL, Integer timeout) throws HueDKException, HueDKInitializationException;

	String signUp(HueAccessPoint accessPoint) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	String signUp(HueAccessPoint accessPoint, String deviceType) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	String signUp(HueAccessPoint accessPoint, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	String signUp(HueAccessPoint accessPoint, String deviceType, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	void connect(HueAccessPoint accessPoint, String userId) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	void connect(HueAccessPoint accessPoint, String userId, Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	List<HueLight> getLights() throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	List<HueLight> getLights(Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	List<HueGroup> getGroups() throws HueDKException, HueDKInitializationException, HueDKConnectionException;

	List<HueGroup> getGroups(Integer timeout) throws HueDKException, HueDKInitializationException, HueDKConnectionException;

}
