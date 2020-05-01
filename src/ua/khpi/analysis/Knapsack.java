package ua.khpi.analysis;

import java.util.Arrays;

public class Knapsack {
	public static class KnapsackSolution {
		public double[] items;
		public double max;
		public double total;

		public KnapsackSolution(double[] items, double max, double total) {
			this.items = items;
			this.max = max;
			this.total = total;
		}

		@Override
		public String toString() {
			return "opt = " + Arrays.toString(items) + ", max = " + max + ", total = " + total;
		}
	}

	public static KnapsackSolution knapSack(double maxWeight, double weight[], double value[], int size) {
		double[] items = new double[size];

		for (int i = 0; i < size; i++) {
			items[i] = 1;

			if (sumProduct(items, weight, size) > maxWeight) {
				items[i] = 0;
			}
		}

		return new KnapsackSolution(items, sumProduct(items, value, size), sumProduct(items, weight, size));
	}

	private static double sumProduct(double[] a, double[] b, int size) {
		double result = 0;

		for (int i = 0; i < size; i++) {
			result += a[i] * b[i];
		}

		return result;
	}
}
