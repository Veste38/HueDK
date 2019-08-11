package be.sleroy.huedk;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import be.sleroy.huedk.dto.HueElement;
import be.sleroy.huedk.dto.connection.HueAccessPoint;
import be.sleroy.huedk.dto.group.HueGroup;
import be.sleroy.huedk.dto.light.HueLight;
import be.sleroy.huedk.exception.HueDKConnectionException;
import be.sleroy.huedk.exception.HueDKException;
import be.sleroy.huedk.exception.HueDKInitializationException;

public class HueDKTest {

	private static Logger LOGGER = LoggerFactory.getLogger(HueDKTest.class);

	private static int DEFAULT_WAIT_ASYNC = 500;
	
	private boolean asyncFinished = false;
	private boolean asyncBridgesFound = false;
	private boolean asyncSignedUp = false;
	private boolean asyncConnected = false;
	private boolean asyncLightsFound = false;
	private boolean asyncGroupsFound = false;
	private boolean asyncLightFound = false;
	private boolean asyncGroupFound = false;

	private List<HueAccessPoint> asyncAccessPoints = null;
	private String asyncUser = null;
	private List<HueLight> asyncLights = null;
	private List<HueGroup> asyncGroups = null;

	@Test
	public void syncFindBridgesTest() {

		try {
			List<HueAccessPoint> accessPointsList = HueDKFactory.getHueDKInstance(HueDK.Mode.SYNC).findBridges();
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
			HueDKFactory.getHueDKInstance(HueDK.Mode.SYNC).findBridges(1);
			Assert.fail("No Exception raised (timeout)!");
		} catch (HueDKException ex) {
			LOGGER.debug(ex.getMessage());
		}
	}

	@Test
	public void syncTest() {

		try {
			HueDK hueDK = HueDKFactory.getHueDKInstance(HueDK.Mode.SYNC);
			List<HueAccessPoint> accessPointsList = hueDK.findBridges();
			if (accessPointsList != null && accessPointsList.size() > 0) {
				LOGGER.debug("Trying to connect to bridge IP: " + accessPointsList.get(0).getIp());
				String userId = hueDK.signUp(accessPointsList.get(0), "junit#connectTest", 10000);
				LOGGER.debug("UserId: " + userId);
				try {
					hueDK.connect(accessPointsList.get(0), "blabla", 10000);
				} catch (HueDKConnectionException exc) {
					LOGGER.debug(exc.getMessage());
				}
				hueDK.connect(accessPointsList.get(0), userId, 10000);

				List<HueLight> lights = hueDK.getLights();
				if (lights != null && lights.size() > 0) {
					for (HueLight light : lights) {
						LOGGER.debug(light.toString());
					}
				}
				List<HueGroup> groups = hueDK.getGroups();
				if (groups != null && groups.size() > 0) {
					for (HueGroup group : groups) {
						LOGGER.debug(group.toString());
					}
				}
				HueLight light = hueDK.getLight("1");
				LOGGER.debug(light.toString());
				HueGroup group = hueDK.getGroup("1");
				LOGGER.debug(group.toString());
				List<HueLight> groupLights = hueDK.getLightsOfGroup("1");
				if (groupLights != null && groupLights.size() > 0) {
					for (HueLight groupLight : groupLights) {
						LOGGER.debug(groupLight.toString());
					}
				}
			}
		} catch (HueDKConnectionException ex) {
			LOGGER.error(ex.getMessage(), ex);
			Assert.fail(ex.getMessage());
		} catch (HueDKException ex) {
			LOGGER.error(ex.getMessage(), ex);
			Assert.fail(ex.getMessage());
		}
	}

	@Test
	public void asyncFindBridgesTest() {

		try {
			asyncFinished = false;

			HueDKFactory.getHueDKInstance(HueDK.Mode.ASYNC).findBridges();
			Assert.fail("HueDKInitializationException not thrown");

		} catch (HueDKInitializationException ex) {
			LOGGER.info(ex.getMessage());
		} catch (HueDKException ex) {
			LOGGER.error(ex.getMessage(), ex);
			Assert.fail(ex.getMessage());
		}
		try {
			asyncFinished = false;

			HueDK hueDK = HueDKFactory.getHueDKInstance(HueDK.Mode.ASYNC);
			hueDK.registerEventListener(listener);
			hueDK.findBridges();
			while (!asyncFinished && !asyncBridgesFound) {
				Thread.sleep(DEFAULT_WAIT_ASYNC);
			}
			if (asyncFinished) {
				throw new HueDKException("Error occured!");
			}
			if (asyncAccessPoints != null && asyncAccessPoints.size() > 0) {
				hueDK.signUp(asyncAccessPoints.get(0));
				while (!asyncFinished && !asyncSignedUp) {
					Thread.sleep(DEFAULT_WAIT_ASYNC);
				}
				if (asyncFinished) {
					throw new HueDKException("Error occured!");
				}
				if (asyncUser != null) {
					hueDK.connect(asyncAccessPoints.get(0), asyncUser);
					while (!asyncFinished && !asyncConnected) {
						Thread.sleep(DEFAULT_WAIT_ASYNC);
					}
					if (asyncFinished) {
						throw new HueDKException("Error occured!");
					}
					hueDK.getLights();
					while (!asyncFinished && !asyncLightsFound) {
						Thread.sleep(DEFAULT_WAIT_ASYNC);
					}
					if (asyncFinished) {
						throw new HueDKException("Error occured!");
					}
					hueDK.getGroups();
					while (!asyncFinished && !asyncGroupsFound) {
						Thread.sleep(DEFAULT_WAIT_ASYNC);
					}
					if (asyncFinished) {
						throw new HueDKException("Error occured!");
					}
					hueDK.getLight("1");
					while (!asyncFinished && !asyncLightFound) {
						Thread.sleep(DEFAULT_WAIT_ASYNC);
					}
					if (asyncFinished) {
						throw new HueDKException("Error occured!");
					}
					hueDK.getGroup("1");
					while (!asyncFinished && !asyncGroupFound) {
						Thread.sleep(DEFAULT_WAIT_ASYNC);
					}
					if (asyncFinished) {
						throw new HueDKException("Error occured!");
					}
					asyncLightsFound = false;
					hueDK.getLightsOfGroup("1");
					while (!asyncFinished && !asyncLightsFound) {
						Thread.sleep(DEFAULT_WAIT_ASYNC);
					}
					if (asyncFinished) {
						throw new HueDKException("Error occured!");
					}
				}
			}
		} catch (InterruptedException ex) {
			LOGGER.error(ex.getMessage(), ex);
			Assert.fail(ex.getMessage());
		} catch (HueDKException ex) {
			LOGGER.error(ex.getMessage(), ex);
			Assert.fail(ex.getMessage());
		}
	}

	private HueDKEventListener listener = new HueDKEventListener() {

		@Override
		public void onSuccessfulConnection(String message) {
			LOGGER.info(message);
			asyncConnected = true;
		}

		@Override
		public void onSignUp(String userId) {
			asyncUser = userId;
			asyncSignedUp = true;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onListReceived(Class<? extends HueElement> classType, List<? extends HueElement> list) {

			switch (classType.getSimpleName()) {
			case "HueAccessPoint":
				List<HueAccessPoint> accessPoints = (List<HueAccessPoint>) list;
				if (accessPoints != null) {
					for (HueAccessPoint accessPoint : accessPoints) {
						LOGGER.info(accessPoint.toString());
					}
				}
				asyncAccessPoints = accessPoints;
				asyncBridgesFound = true;
				break;
			case "HueLight":
				List<HueLight> lights = (List<HueLight>) list;
				if (lights != null) {
					for (HueLight light : lights) {
						LOGGER.info(light.toString());
					}
				}
				asyncLights = lights;
				asyncLightsFound = true;
				break;
			case "HueGroup":
				List<HueGroup> groups = (List<HueGroup>) list;
				if (groups != null) {
					for (HueGroup group : groups) {
						LOGGER.info(group.toString());
					}
				}
				asyncGroups = groups;
				asyncGroupsFound = true;
				break;
			}

		}

		@Override
		public void onError(Class<? extends HueDKException> exceptionType, HueDKException exception) {
			LOGGER.error(exception.getMessage(), exception);
			asyncFinished = true;
		}

		@Override
		public void onElementReceived(Class<? extends HueElement> classType, HueElement element) {
			// TODO Auto-generated method stub
			switch (classType.getSimpleName()) {
			case "HueLight":
				LOGGER.info(((HueLight)element).toString());
				asyncLightFound = true;
				break;
			case "HueGroup":
				LOGGER.info(((HueGroup)element).toString());
				asyncGroupFound = true;
				break;
			}

		}
	};
}
