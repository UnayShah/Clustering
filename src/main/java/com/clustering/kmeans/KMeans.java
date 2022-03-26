package com.clustering.kmeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.clustering.model.Data;

public class KMeans<E> {
	List<Data<E>> datapoints;
	Data<E> data;
	Map<Data<E>, List<Data<E>>> clusters;

	public KMeans(Data<E> data) {
		this.data = data;
		this.clusters = new HashMap<>();
	}

	public List<Data<E>> getDatapoint() {
		return datapoints;
	}

	public void setDatapoint(List<Data<E>> datapoint) {
		this.datapoints = datapoint;
	}

	public Boolean addDatapoint(Data<E> datapoint) {
		if (this.datapoints != null) {
			return this.datapoints.add(datapoint);
		}
		return false;
	}

	public Map<Data<E>, List<Data<E>>> getClusters() {
		return clusters;
	}

	public void calculateClusters(int clusterCount, int maxEpochs) {
		List<Data<E>> randomDatapoints = new ArrayList<>();
		for (int i = 0; i < clusterCount; randomDatapoints.add(data.randomData()), i++)
			;
		this.calculateClusters(clusterCount, maxEpochs, randomDatapoints);
	}

	public void calculateClusters(int clusterCount, int maxEpochs, List<Data<E>> randomDatapoints) {
		Map<Data<E>, List<Data<E>>> clusters = new HashMap<>();
		this.clusters.clear();
		for (int i = 0; i < clusterCount; clusters.put(randomDatapoints.get(i), new ArrayList<>()), i++)
			;
		for (int epoch = 0; epoch < maxEpochs; epoch++) {
			System.err.println("Epoch " + (epoch + 1));
			for (Data<E> datapoint : datapoints) {
				Data<E> nearestKey = null;
				for (Data<E> key : clusters.keySet()) {
					nearestKey = nearestKey == null ? key : datapoint.nearestData(key, nearestKey);
				}
				clusters.get(nearestKey).add(datapoint);
			}
			this.clusters.clear();
			for (Data<E> key : clusters.keySet()) {
				List<Data<E>> copyDatapoints = clusters.get(key);
				data = data.assignCentroid(copyDatapoints);
				this.clusters.put(data, copyDatapoints);
			}
			clusters.clear();
			this.clusters.keySet().forEach(key -> clusters.put(key, new ArrayList<>()));
		}
	}
}
