package com.clustering.distance;

import com.clustering.model.Data;

public interface distance<E> {
	public Double calculateDistance(Data<E> dataPoint);
}