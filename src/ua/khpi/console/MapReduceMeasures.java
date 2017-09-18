package ua.khpi.console;

import org.apache.jena.rdf.model.Model;

import ua.khpi.analysis.ArchiMateMapReduceModelAnalysis;
import ua.khpi.analysis.ArchiMateModelAnalysis;
import ua.khpi.util.ArchiMateModelTranslator;

public class MapReduceMeasures {

	public static void main(String[] args) throws InterruptedException {
		String archiMateModelName = "Archisurance";

		String archiMateModelPath = ArchiMateModelAnalysisConsole.ARCHIMATE_MODELS_PATH + archiMateModelName + ".xml";
		String collectionOfRDFStatementsPath = ArchiMateModelAnalysisConsole.RDF_TRIPLES_PATH + archiMateModelName
				+ ".nt";

		Model archiMateModel = ArchiMateModelTranslator.translateArchiMateModelToRDFGraph(archiMateModelPath,
				collectionOfRDFStatementsPath);

		final int N = 10;

		long totalClassic = 0;
		long totalMR = 0;

		for (int i = 1; i <= N; i++) {
			long start = System.nanoTime();

			new ArchiMateModelAnalysis(archiMateModel).getArchiMateModelElements();

			long timeClassic = System.nanoTime() - start;

			start = System.nanoTime();

			new ArchiMateMapReduceModelAnalysis(archiMateModel).getArchiMateModelElements();

			long timeMR = System.nanoTime() - start;

			System.out.printf("%.4f\t%.4f\t%s\n", timeClassic * 10E-9, timeMR * 10E-9,
					timeMR < timeClassic ? "T" : "F");

			totalClassic += timeClassic;
			totalMR += timeMR;
		}

		System.out.printf("Average classic: %.4f\n", totalClassic * 10E-9 / N);
		System.out.printf("Average MapReduce: %.4f\n", totalMR * 10E-9 / N);
	}
}
