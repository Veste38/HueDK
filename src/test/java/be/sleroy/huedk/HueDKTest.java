package be.sleroy.huedk;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.sleroy.huedk.dto.connection.HueAccessPoint;
import be.sleroy.huedk.exception.HueDKConnectionException;
import be.sleroy.huedk.exception.HueDKException;

public class HueDKTest {

	private static Logger LOGGER = LoggerFactory.getLogger(HueDKTest.class);

	@Test
	public void findBridgesTest() {

		try {
			List<HueAccessPoint> accessPointsList = HueDK.getInstance().findBridges();
			if (accessPointsList != null && accessPointsList.size() > 0) {
				for (HueAccessPoint accessPoint : accessPointsList) {
					LOGGER.debug("accessPoint.getId(): " + accessPoint.getId());
					LOGGER.debug("accessPoint.getIp(): " + accessPoint.getIp());
				}
			}
		} catch (HueDKException ex) {
			LOGGER.error(ex.getMessage(), ex);
			Assert.fail(ex.getMessage());
		}
		try {
			HueDK.getInstance().findBridges(1);
			Assert.fail("No Exception raised (timeout)!");
		} catch (HueDKException ex) {
			LOGGER.debug(ex.getMessage());
		}
	}

	@Test
	public void connectTest() {

		try {
			List<HueAccessPoint> accessPointsList = HueDK.getInstance().findBridges();
			if (accessPointsList != null && accessPointsList.size() > 0) {
				LOGGER.debug("Trying to connect to bridge IP: " + accessPointsList.get(0).getIp());
				String userId = HueDK.getInstance().signUp(accessPointsList.get(0), "junit#connectTest", 10000);
				LOGGER.debug("UserId: " + userId);
				try {
					HueDK.getInstance().connect(accessPointsList.get(0), "blabla", 10000);
				} catch(HueDKConnectionException exc) {
					LOGGER.debug(exc.getMessage());
				}
				HueDK.getInstance().connect(accessPointsList.get(0), userId, 10000);
			}
		} catch (HueDKConnectionException ex) {
			LOGGER.error(ex.getMessage(), ex);
			Assert.fail(ex.getMessage());
		} catch (HueDKException ex) {
			LOGGER.error(ex.getMessage(), ex);
			Assert.fail(ex.getMessage());
		}
	}

}
