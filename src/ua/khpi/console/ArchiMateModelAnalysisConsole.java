package ua.khpi.console;

import org.apache.jena.rdf.model.Model;

import ua.khpi.analysis.ArchiMateModelAnalysis;
import ua.khpi.analysis.beans.ArchiMateElement;
import ua.khpi.util.ArchiMateModelTranslator;

public class ArchiMateModelAnalysisConsole {

	public static final String ARCHIMATE_MODELS_PATH = "file:///C:/Users/lenovo/Downloads/RDFSim/";
	public static final String RDF_TRIPLES_PATH = "models/";

	public void runApplication(String archiMateModelName) {
		String archiMateModelPath = ARCHIMATE_MODELS_PATH + archiMateModelName + ".xml";
		String collectionOfRDFStatementsPath = RDF_TRIPLES_PATH + archiMateModelName + ".nt";

		Model archiMateModel = ArchiMateModelTranslator.translateArchiMateModelToRDFGraph(archiMateModelPath,
				collectionOfRDFStatementsPath);

		ArchiMateModelAnalysis archiMateModelAnalysis = new ArchiMateModelAnalysis(archiMateModel);

		System.out.println(String.format("'%s' model's size: %d", archiMateModelName,
				archiMateModelAnalysis.getArchiMateModelElements().size()));

		System.out.println(String.format("'%s' model's density: %.2f", archiMateModelName,
				archiMateModelAnalysis.calculateArchiMateModelDensity()));

		System.out.println(String.format("'%s' model's centrality: %.2f", archiMateModelName,
				archiMateModelAnalysis.calculateArchiMateModelCentrality()));

		archiMateModelAnalysis.calculateArchiMateElementsCentrality();

		System.out.printf("%-35s %s %s\n", "Element's name", "Properties number", "Centrality");

		for (ArchiMateElement archiMateElement : archiMateModelAnalysis.getArchiMateModelElements()) {
			System.out.printf("%-40s\t", archiMateElement.getElementName());
			System.out.printf("%d\t", archiMateElement.getElementPropertiesNumber());
			System.out.printf("%.2f\n", archiMateElement.getElementCentrality());
		}
	}

	public static void main(String[] args) {
		new ArchiMateModelAnalysisConsole().runApplication("Archisurance");
	}
}
