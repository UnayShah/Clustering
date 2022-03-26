package com.clustering.sample;

import java.util.List;
import java.util.Random;

import com.clustering.model.Data;

public class DoubleCoordinateClustering implements Data<TwoDimension> {
	TwoDimension twoDimension;

	public DoubleCoordinateClustering(Double X, Double Y) {
		twoDimension = new TwoDimension(X, Y);
	}

	public DoubleCoordinateClustering(TwoDimension twoDimension) {
		this.twoDimension = twoDimension;
	}

	@Override
	public Double calculateDistance(Data<TwoDimension> dataPoint) {
		return Math.sqrt(Math.pow(this.twoDimension.getX() - dataPoint.getParameters().getX(), 2)
				+ Math.pow(this.twoDimension.getY() - dataPoint.getParameters().getY(), 2));
	}

	@Override
	public Data<TwoDimension> randomData() {
		Random random = new Random();
		return new DoubleCoordinateClustering(random.nextDouble(), random.nextDouble());
	}

	@Override
	public Data<TwoDimension> assignCentroid(List<Data<TwoDimension>> datapoints) {
		TwoDimension newCentroid = new TwoDimension(0.0, 0.0);
		for (Data<TwoDimension> datapoint : datapoints) {
			newCentroid.setX((datapoint.getParameters().getX() / datapoints.size()) + newCentroid.getX());
			newCentroid.setY((datapoint.getParameters().getY() / datapoints.size()) + newCentroid.getY());
		}
		return new DoubleCoordinateClustering(newCentroid);
	}

	@Override
	public TwoDimension getParameters() {
		return this.twoDimension;
	}

	@Override
	public Data<TwoDimension> nearestData(Data<TwoDimension> datapoint1, Data<TwoDimension> datapoint2) {
		return this.calculateDistance(datapoint1) < this.calculateDistance(datapoint2) ? datapoint1 : datapoint2;
	}

	@Override
	public String toString() {
		return "[2D=" + twoDimension + "]";
	}

}
