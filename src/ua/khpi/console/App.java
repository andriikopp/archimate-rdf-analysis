package ua.khpi.console;

import java.util.List;

import org.apache.jena.rdf.model.Model;

import ua.khpi.analysis.ModelAnalysis;
import ua.khpi.analysis.beans.Artifact;
import ua.khpi.util.ModelTranslator;

public class App {
	public static final String PATH = "file:\\D:\\GitHub\\archimate-rdf-analysis\\";
	public static final String MODELS_PATH = "models\\";
	public static final String TRIPLES_PATH = "triples\\";
	public static final String PROCESS_PATH = "process\\";

	public void run(String modelName) {
		String models = MODELS_PATH + modelName + ".xml";
		String triples = TRIPLES_PATH + modelName + ".nt";

		Model model = ModelTranslator.toRDFGraph(models, triples);
		ModelAnalysis analysis = new ModelAnalysis(model);
		List<Artifact> artifacts = analysis.extractArtifacts();

		analysis.createMap(modelName);

		System.out.println(String.format("'%s' size: %d", modelName, artifacts.size()));
		System.out.println(String.format("'%s' density: %.2f", modelName, analysis.density()));
		System.out.println(String.format("'%s' connectivity: %.2f", modelName, analysis.connectivity()));
		System.out.println(String.format("'%s' centrality: %.2f", modelName, analysis.modelCentrality()));

		analysis.centrality();
		analysis.rank();

		for (Artifact artifact : artifacts) {
			System.out.printf("%-100s\t", artifact.getType() + "::" + artifact.getName());
			System.out.printf("%d\t", artifact.getIncoming());
			System.out.printf("%d\t", artifact.getOutgoing());
			System.out.printf("%f\t", artifact.getCentrality());
			System.out.printf("%f\n", artifact.getRank());
		}
	}

	public static void main(String[] args) {
		String[] archiFiles = { "ArchiMetal", "Archisurance", "OpenDay" };
		App app = new App();

		for (String file : archiFiles) {
			app.run(file);
		}
	}
}
