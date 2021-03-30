package ua.khpi.apparchi.util;

import ua.khpi.apparchi.entity.MeasureEntity;

public final class SimilarityUtil {

	private SimilarityUtil() {
	}
	
	public static double euclideanDistance(MeasureEntity a, MeasureEntity b) {
		return Math.sqrt(Math.pow(a.getDensity() - b.getDensity(), 2)
				+ Math.pow(a.getAvgDegree() - b.getAvgDegree(), 2)
				+ Math.pow(a.getMinDegree() - b.getMinDegree(), 2)
				+ Math.pow(a.getMaxDegree() - b.getMaxDegree(), 2)
				+ Math.pow(a.getConnectivity() - b.getConnectivity(), 2)
				+ Math.pow(a.getResilience() - b.getResilience(), 2)
				+ Math.pow(a.getCentrality() - b.getCentrality(), 2)
				+ Math.pow(a.getIrregularity() - b.getIrregularity(), 2));
	}
}
