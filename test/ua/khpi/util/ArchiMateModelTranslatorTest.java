package ua.khpi.util;

import static org.junit.Assert.assertNotNull;

import org.apache.jena.rdf.model.Model;
import org.junit.Test;

public class ArchiMateModelTranslatorTest {

	public static final String ARCHIMATE_MODELS_PATH = "file:///C:/Users/lenovo/Downloads/RDFSim/";
	public static final String RDF_TRIPLES_PATH = "models/";

	@Test
	public void testTranslateArchiMateModelToRDFGraph() {
		String archiMateModelName = "Archisurance";
		String archiMateModelPath = ARCHIMATE_MODELS_PATH + archiMateModelName + ".xml";
		String collectionOfRDFStatementsPath = RDF_TRIPLES_PATH + archiMateModelName + ".nt";

		Model archiMateModel = ArchiMateModelTranslator.translateArchiMateModelToRDFGraph(archiMateModelPath,
				collectionOfRDFStatementsPath);

		assertNotNull(archiMateModel);
	}
}
