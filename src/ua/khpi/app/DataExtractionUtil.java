package ua.khpi.app;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Model;

import ua.khpi.analysis.ModelAnalysis;
import ua.khpi.analysis.beans.Artifact;
import ua.khpi.analysis.dao.ModelAnalysisDAO;
import ua.khpi.util.ModelTranslator;

public final class DataExtractionUtil {
	public static final String PATH = "file:\\D:\\GitHub\\archimate-rdf-analysis\\";
	public static final String MODELS_PATH = "models\\";
	public static final String TRIPLES_PATH = "triples\\";
	public static final String PROCESS_PATH = "process\\";

	private static final ModelAnalysisDAO dao = new ModelAnalysisDAO();
	private static final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

	private DataExtractionUtil() {
	}

	public static void processModel(String modelName) {
		String models = MODELS_PATH + modelName + ".xml";
		String triples = TRIPLES_PATH + modelName + ".nt";

		Model model = ModelTranslator.toRDFGraph(models, triples);
		ModelAnalysis analysis = new ModelAnalysis(model);
		List<Artifact> artifacts = analysis.extractArtifacts();

		System.out.println(String.format("'%s' size: %d", modelName, artifacts.size()));
		System.out.println(String.format("'%s' density: %.2f", modelName, analysis.density()));
		System.out.println(String.format("'%s' connectivity: %.2f", modelName, analysis.connectivity()));
		System.out.println(String.format("'%s' centrality: %.2f", modelName, analysis.modelCentrality()));

		analysis.centrality();
		analysis.rank();
		analysis.propagationCost();

		final double percentage = 0.2;
		analysis.optimization(percentage);

		for (Artifact artifact : artifacts) {
			System.out.printf("%-100s\t", artifact.getType() + "::" + artifact.getName());
			System.out.printf("%d\t", artifact.getIncoming());
			System.out.printf("%d\t", artifact.getOutgoing());
			System.out.printf("%f\t", artifact.getCentrality());
			System.out.printf("%f\t", artifact.getRank());
			System.out.printf("%f\t", artifact.getCost());
			System.out.printf("%d\n", artifact.getOpt());

			dao.insertAnalysisResults(modelName, artifact, timestamp);
		}

		analysis.createMap(modelName);
	}

	public static void extractData() {
		List<File> files = new ArrayList<>();

		listFiles(MODELS_PATH, files);

		for (File file : files) {
			processModel(file.getName().replace(".xml", ""));
		}
	}

	public static void main(String[] args) {
		extractData();
	}

	private static void listFiles(String directoryName, List<File> files) {
		File directory = new File(MODELS_PATH);
		File[] fList = directory.listFiles();

		if (fList != null) {
			for (File file : fList) {
				if (file.isFile()) {
					files.add(file);
				} else if (file.isDirectory()) {
					listFiles(file.getAbsolutePath(), files);
				}
			}
		}
	}
}
