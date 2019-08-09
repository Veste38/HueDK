package be.sleroy.huedk.utilities;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Code comes from PHUtilities class from Philips Hue SDK (not supported anymore).
 * 
 * @author Philips Hue SDK
 * @author sleroy
 */
public class ColorUtilities {

	private static List<HuePoint> colorPointsGamut_A;
	private static List<HuePoint> colorPointsGamut_B;
	private static List<HuePoint> colorPointsGamut_C;
	private static List<HuePoint> colorPointsDefault;
	private static final List<String> GAMUT_A_BULBS_LIST;
	private static final List<String> GAMUT_B_BULBS_LIST;
	private static final List<String> GAMUT_C_BULBS_LIST;
	private static final List<String> MULTI_SOURCE_LUMINAIRES;

	public static int colorFromXY(float points[], String model) {
		if (points == null || model == null)
			throw new IllegalArgumentException("Input parameter can't be null");
		HuePoint xy = new HuePoint(points[0], points[1]);
		List<HuePoint> colorPoints = colorPointsForModel(model);
		boolean inReachOfLamps = checkPointInLampsReach(xy, colorPoints);
		if (!inReachOfLamps) {
			HuePoint pAB = getClosestPointToPoints((HuePoint) colorPoints.get(0), (HuePoint) colorPoints.get(1), xy);
			HuePoint pAC = getClosestPointToPoints((HuePoint) colorPoints.get(2), (HuePoint) colorPoints.get(0), xy);
			HuePoint pBC = getClosestPointToPoints((HuePoint) colorPoints.get(1), (HuePoint) colorPoints.get(2), xy);
			float dAB = getDistanceBetweenTwoPoints(xy, pAB);
			float dAC = getDistanceBetweenTwoPoints(xy, pAC);
			float dBC = getDistanceBetweenTwoPoints(xy, pBC);
			float lowest = dAB;
			HuePoint closestPoint = pAB;
			if (dAC < lowest) {
				lowest = dAC;
				closestPoint = pAC;
			}
			if (dBC < lowest) {
				lowest = dBC;
				closestPoint = pBC;
			}
			xy.x = closestPoint.x;
			xy.y = closestPoint.y;
		}
		float x = xy.x;
		float y = xy.y;
		float z = 1.0F - x - y;
		float y2 = 1.0F;
		float x2 = (y2 / y) * x;
		float z2 = (y2 / y) * z;
		float r = x2 * 1.656492F - y2 * 0.354851F - z2 * 0.255038F;
		float g = -x2 * 0.707196F + y2 * 1.655397F + z2 * 0.036152F;
		float b = (x2 * 0.051713F - y2 * 0.121364F) + z2 * 1.01153F;
		if (r > b && r > g && r > 1.0F) {
			g /= r;
			b /= r;
			r = 1.0F;
		} else if (g > b && g > r && g > 1.0F) {
			r /= g;
			b /= g;
			g = 1.0F;
		} else if (b > r && b > g && b > 1.0F) {
			r /= b;
			g /= b;
			b = 1.0F;
		}
		r = r > 0.0031308F ? 1.055F * (float) Math.pow(r, 0.4166666567325592D) - 0.055F : 12.92F * r;
		g = g > 0.0031308F ? 1.055F * (float) Math.pow(g, 0.4166666567325592D) - 0.055F : 12.92F * g;
		b = b > 0.0031308F ? 1.055F * (float) Math.pow(b, 0.4166666567325592D) - 0.055F : 12.92F * b;
		if (r > b && r > g) {
			if (r > 1.0F) {
				g /= r;
				b /= r;
				r = 1.0F;
			}
		} else if (g > b && g > r) {
			if (g > 1.0F) {
				r /= g;
				b /= g;
				g = 1.0F;
			}
		} else if (b > r && b > g && b > 1.0F) {
			r /= b;
			g /= b;
			b = 1.0F;
		}
		if (r < 0.0F)
			r = 0.0F;
		if (g < 0.0F)
			g = 0.0F;
		if (b < 0.0F)
			b = 0.0F;
		int r1 = (int) (r * 255F);
		int g1 = (int) (g * 255F);
		int b1 = (int) (b * 255F);
		return HueColor.rgb(r1, g1, b1);
	}

	public static float[] calculateXY(int color, String model) {
		float red = 1.0F;
		float green = 1.0F;
		float blue = 1.0F;
		red = (float) HueColor.red(color) / 255F;
		green = (float) HueColor.green(color) / 255F;
		blue = (float) HueColor.blue(color) / 255F;
		float r = red <= 0.04045F ? red / 12.92F : (float) Math.pow((red + 0.055F) / 1.055F, 2.4000000953674316D);
		float g = green <= 0.04045F ? green / 12.92F : (float) Math.pow((green + 0.055F) / 1.055F, 2.4000000953674316D);
		float b = blue <= 0.04045F ? blue / 12.92F : (float) Math.pow((blue + 0.055F) / 1.055F, 2.4000000953674316D);
		float x = r * 0.664511F + g * 0.154324F + b * 0.162028F;
		float y = r * 0.283881F + g * 0.668433F + b * 0.047685F;
		float z = r * 8.8E-005F + g * 0.07231F + b * 0.986039F;
		float xy[] = new float[2];
		xy[0] = x / (x + y + z);
		xy[1] = y / (x + y + z);
		if (Float.isNaN(xy[0]))
			xy[0] = 0.0F;
		if (Float.isNaN(xy[1]))
			xy[1] = 0.0F;
		HuePoint xyPoint = new HuePoint(xy[0], xy[1]);
		List<HuePoint> colorPoints = colorPointsForModel(model);
		boolean inReachOfLamps = checkPointInLampsReach(xyPoint, colorPoints);
		if (!inReachOfLamps) {
			HuePoint pAB = getClosestPointToPoints((HuePoint) colorPoints.get(0), (HuePoint) colorPoints.get(1), xyPoint);
			HuePoint pAC = getClosestPointToPoints((HuePoint) colorPoints.get(2), (HuePoint) colorPoints.get(0), xyPoint);
			HuePoint pBC = getClosestPointToPoints((HuePoint) colorPoints.get(1), (HuePoint) colorPoints.get(2), xyPoint);
			float dAB = getDistanceBetweenTwoPoints(xyPoint, pAB);
			float dAC = getDistanceBetweenTwoPoints(xyPoint, pAC);
			float dBC = getDistanceBetweenTwoPoints(xyPoint, pBC);
			float lowest = dAB;
			HuePoint closestPoint = pAB;
			if (dAC < lowest) {
				lowest = dAC;
				closestPoint = pAC;
			}
			if (dBC < lowest) {
				lowest = dBC;
				closestPoint = pBC;
			}
			xy[0] = closestPoint.x;
			xy[1] = closestPoint.y;
		}
		xy[0] = precision(xy[0]);
		xy[1] = precision(xy[1]);
		return xy;
	}

	public static float[] calculateXYFromRGB(int red, int green, int blue, String model) {
		int rgb = HueColor.rgb(red, green, blue);
		return calculateXY(rgb, model);
	}

	public static Color hex2Rgb(String colorStr) {
		return new Color(
				Integer.valueOf(colorStr.substring(1, 3), 16),
				Integer.valueOf(colorStr.substring(3, 5), 16),
				Integer.valueOf(colorStr.substring(5, 7), 16));
	}

	public static Integer getColorTemperature(Integer ct) {
		Integer colorTemp = null;
		
		if (ct != null) {
			colorTemp = 6500 - ((ct - 153) * (4500/347));
		}
		
		return colorTemp;
	}
	
	private static List<HuePoint> colorPointsForModel(String model) {
		if (model == null)
			model = " ";
		List<HuePoint> colorPoints;
		if (GAMUT_B_BULBS_LIST.contains(model) || MULTI_SOURCE_LUMINAIRES.contains(model))
			colorPoints = colorPointsGamut_B;
		else if (GAMUT_A_BULBS_LIST.contains(model))
			colorPoints = colorPointsGamut_A;
		else if (GAMUT_C_BULBS_LIST.contains(model))
			colorPoints = colorPointsGamut_C;
		else
			colorPoints = colorPointsDefault;
		return colorPoints;
	}

	private static float getDistanceBetweenTwoPoints(HuePoint one, HuePoint two) {
		float dx = one.x - two.x;
		float dy = one.y - two.y;
		float dist = (float) Math.sqrt(dx * dx + dy * dy);
		return dist;
	}

	private static boolean checkPointInLampsReach(HuePoint point, List<HuePoint> colorPoints) {
		if (point == null || colorPoints == null)
			return false;
		HuePoint red = (HuePoint) colorPoints.get(0);
		HuePoint green = (HuePoint) colorPoints.get(1);
		HuePoint blue = (HuePoint) colorPoints.get(2);
		HuePoint v1 = new HuePoint(green.x - red.x, green.y - red.y);
		HuePoint v2 = new HuePoint(blue.x - red.x, blue.y - red.y);
		HuePoint q = new HuePoint(point.x - red.x, point.y - red.y);
		float s = crossProduct(q, v2) / crossProduct(v1, v2);
		float t = crossProduct(v1, q) / crossProduct(v1, v2);
		return s >= 0.0F && t >= 0.0F && s + t <= 1.0F;
	}

	private static HuePoint getClosestPointToPoints(HuePoint pointA, HuePoint pointB, HuePoint pointP) {
		if (pointA == null || pointB == null || pointP == null)
			return null;
		HuePoint pointAP = new HuePoint(pointP.x - pointA.x, pointP.y - pointA.y);
		HuePoint pointAB = new HuePoint(pointB.x - pointA.x, pointB.y - pointA.y);
		float ab2 = pointAB.x * pointAB.x + pointAB.y * pointAB.y;
		float apAb = pointAP.x * pointAB.x + pointAP.y * pointAB.y;
		float t = apAb / ab2;
		if (t < 0.0F)
			t = 0.0F;
		else if (t > 1.0F)
			t = 1.0F;
		HuePoint newPoint = new HuePoint(pointA.x + pointAB.x * t, pointA.y + pointAB.y * t);
		return newPoint;
	}

	private static float crossProduct(HuePoint point1, HuePoint point2) {
		return point1.x * point2.y - point1.y * point2.x;
	}

	private static float precision(float d) {
		return (float) Math.round(10000F * d) / 10000F;
	}

	static {
		colorPointsGamut_A = new ArrayList<HuePoint>();
		colorPointsGamut_B = new ArrayList<HuePoint>();
		colorPointsGamut_C = new ArrayList<HuePoint>();
		colorPointsDefault = new ArrayList<HuePoint>();
		GAMUT_A_BULBS_LIST = new ArrayList<String>();
		GAMUT_B_BULBS_LIST = new ArrayList<String>();
		GAMUT_C_BULBS_LIST = new ArrayList<String>();
		MULTI_SOURCE_LUMINAIRES = new ArrayList<String>();
		GAMUT_A_BULBS_LIST.add("LLC001");
		GAMUT_A_BULBS_LIST.add("LLC005");
		GAMUT_A_BULBS_LIST.add("LLC006");
		GAMUT_A_BULBS_LIST.add("LLC007");
		GAMUT_A_BULBS_LIST.add("LLC010");
		GAMUT_A_BULBS_LIST.add("LLC011");
		GAMUT_A_BULBS_LIST.add("LLC012");
		GAMUT_A_BULBS_LIST.add("LLC014");
		GAMUT_A_BULBS_LIST.add("LLC013");
		GAMUT_A_BULBS_LIST.add("LST001");
		GAMUT_B_BULBS_LIST.add("LCT001");
		GAMUT_B_BULBS_LIST.add("LCT002");
		GAMUT_B_BULBS_LIST.add("LCT003");
		GAMUT_B_BULBS_LIST.add("LCT004");
		GAMUT_B_BULBS_LIST.add("LLM001");
		GAMUT_B_BULBS_LIST.add("LCT005");
		GAMUT_B_BULBS_LIST.add("LCT006");
		GAMUT_B_BULBS_LIST.add("LCT007");
		GAMUT_C_BULBS_LIST.add("LLC020");
		GAMUT_C_BULBS_LIST.add("LST002");
		MULTI_SOURCE_LUMINAIRES.add("HBL001");
		MULTI_SOURCE_LUMINAIRES.add("HBL002");
		MULTI_SOURCE_LUMINAIRES.add("HBL003");
		MULTI_SOURCE_LUMINAIRES.add("HIL001");
		MULTI_SOURCE_LUMINAIRES.add("HIL002");
		MULTI_SOURCE_LUMINAIRES.add("HEL001");
		MULTI_SOURCE_LUMINAIRES.add("HEL002");
		colorPointsGamut_A.add(new HuePoint(0.703F, 0.296F));
		colorPointsGamut_A.add(new HuePoint(0.214F, 0.709F));
		colorPointsGamut_A.add(new HuePoint(0.139F, 0.081F));
		colorPointsGamut_B.add(new HuePoint(0.674F, 0.322F));
		colorPointsGamut_B.add(new HuePoint(0.408F, 0.517F));
		colorPointsGamut_B.add(new HuePoint(0.168F, 0.041F));
		colorPointsGamut_C.add(new HuePoint(0.692F, 0.308F));
		colorPointsGamut_C.add(new HuePoint(0.17F, 0.7F));
		colorPointsGamut_C.add(new HuePoint(0.153F, 0.048F));
		colorPointsDefault.add(new HuePoint(1.0F, 0.0F));
		colorPointsDefault.add(new HuePoint(0.0F, 1.0F));
		colorPointsDefault.add(new HuePoint(0.0F, 0.0F));
	}

	
}
