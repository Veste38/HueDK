package be.sleroy.huedk;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.sleroy.huedk.dto.HueAccessPoint;
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
			LOGGER.info(ex.getMessage(), ex);
		}
	}

}
