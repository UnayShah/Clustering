package com.clustering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.clustering.kmeans.KMeans;
import com.clustering.model.Data;
import com.clustering.sample.DoubleCoordinateClustering;
import com.clustering.sample.SingleCoordinateClustering;
import com.clustering.sample.TwoDimension;

public class DriverCode {

	public static void main(String[] args) {
//		List<Double> yList = sampleDataFromFile("C:\\Users\\unays\\Downloads\\output.hocr");
		List<Double> yList = Arrays.asList(new Double[] { 1.0, 2.0, 3.0, 4.0, 50.0, 51.0, 52.0, 53.0, 54.0, 100.0,
				101.0, 102.0, 103.0, 200.0, 201.0, 202.0, 203.0 });
		KMeans<Double> kMeans = new KMeans<>(new SingleCoordinateClustering(1.0));
		List<Data<Double>> datapoints = new ArrayList<>();
		yList.forEach(y -> datapoints.add(new SingleCoordinateClustering(y)));
		kMeans.setDatapoint(datapoints);
		List<Data<Double>> randomDatapoints = Arrays.asList(new SingleCoordinateClustering[] {
				new SingleCoordinateClustering(1.0), new SingleCoordinateClustering(50.0),
				new SingleCoordinateClustering(100.0), new SingleCoordinateClustering(200.0) });
		kMeans.calculateClusters(4, 500, randomDatapoints);
		System.out.println(kMeans.getClusters().keySet().toString());

		List<TwoDimension> twoDimensionList = Arrays.asList(new TwoDimension[] { new TwoDimension(1.0, 1.0),
				new TwoDimension(1.0, 2.0), new TwoDimension(1.0, 1.5), new TwoDimension(10.0, 10.0),
				new TwoDimension(12.0, 10.0), new TwoDimension(15.0, 10.0), new TwoDimension(10.0, 13.0),
				new TwoDimension(50.0, 56.0), new TwoDimension(54.0, 51.0), new TwoDimension(45.0, 50.0) });
		KMeans<TwoDimension> kMeansTwoDimensions = new KMeans<TwoDimension>(new DoubleCoordinateClustering(10.0, 20.0));
		List<Data<TwoDimension>> datapointsTwoDimension = new ArrayList<>();
		twoDimensionList
				.forEach(twoDimension -> datapointsTwoDimension.add(new DoubleCoordinateClustering(twoDimension)));
		kMeansTwoDimensions.setDatapoint(datapointsTwoDimension);
		kMeansTwoDimensions.calculateClusters(3, 100);
		System.out.println(kMeansTwoDimensions.getClusters().keySet().toString());

	}

	public static List<Double> sampleDataFromFile(String filepath) {
		List<Double> yList = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(filepath));
			String s = "";
			while (sc.hasNext()) {
				s += sc.nextLine() + "\n";
			}
			sc.close();
			Matcher m = Pattern.compile("([0-9]+ ){3}[0-9]+;").matcher(s);
			while (m.find()) {
				yList.add(Double.parseDouble(m.group().split(" ")[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return yList;
	}
}
