package com.clustering.sample;

import java.util.List;
import java.util.Random;

import com.clustering.model.Data;

public class SingleCoordinateClustering implements Data<Double> {
	Double Y;

	public SingleCoordinateClustering(Double Y) {
		this.Y = Y;
	}

	@Override
	public Double calculateDistance(Data<Double> dataPoint) {
		return Math.abs(this.Y - dataPoint.getParameters());
	}

	@Override
	public Data<Double> randomData() {
		return new SingleCoordinateClustering(new Random().nextDouble());
	}

	@Override
	public Data<Double> assignCentroid(List<Data<Double>> datapoints) {
		Double newCentroid = 0.0;
		for (Data<Double> datapoint : datapoints)
			newCentroid += (datapoint.getParameters() / datapoints.size());
		return new SingleCoordinateClustering(newCentroid);
	}

	@Override
	public Double getParameters() {
		return this.Y;
	}

	@Override
	public Data<Double> nearestData(Data<Double> datapoint1, Data<Double> datapoint2) {
		return this.calculateDistance(datapoint1) < this.calculateDistance(datapoint2) ? datapoint1 : datapoint2;
	}

	@Override
	public String toString() {
		return "[Y=" + Y + "]";
	}
}
