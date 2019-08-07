package be.sleroy.huedk.utilities;

public class HuePoint {

	public float x;
	public float y;

	public HuePoint(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public final void set(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public final void set(HuePoint p) {
		x = p.x;
		y = p.y;
	}

	public final void negate() {
		x = -x;
		y = -y;
	}

	public final void offset(float dx, float dy) {
		x += dx;
		y += dy;
	}

	public final boolean equals(float x, float y) {
		return this.x == x && this.y == y;
	}

}
