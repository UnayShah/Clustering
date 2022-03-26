package com.clustering.sample;

public class TwoDimension {
	private Double X;
	private Double Y;

	public TwoDimension(Double x, Double y) {
		super();
		X = x;
		Y = y;
	}

	public Double getX() {
		return X;
	}

	public void setX(Double x) {
		X = x;
	}

	public Double getY() {
		return Y;
	}

	public void setY(Double y) {
		Y = y;
	}

	@Override
	public String toString() {
		return "[X=" + X + ", Y=" + Y + "]";
	}
}
