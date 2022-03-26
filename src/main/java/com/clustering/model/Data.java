package com.clustering.model;

import java.util.List;

import com.clustering.distance.distance;

public interface Data<E> extends distance<E> {
	public Data<E> randomData();

	public Data<E> assignCentroid(List<Data<E>> datapoints);

	public E getParameters();

	public Data<E> nearestData(Data<E> data1, Data<E> data2);
}