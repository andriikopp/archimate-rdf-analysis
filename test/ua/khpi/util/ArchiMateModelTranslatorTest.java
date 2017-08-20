package ua.khpi.util;

import static org.junit.Assert.assertNotNull;

import org.apache.jena.rdf.model.Model;
import org.junit.Test;

public class ArchiMateModelTranslatorTest {

	public static final String MODELS_PATH = "file:///C:/Users/lenovo/Downloads/RDFSim/";

	@Test
	public void testTranslateArchiMateModelToRDFGraph() {
		String archiMateModelName = "Archisurance";
		String archiMateModelPath = MODELS_PATH + archiMateModelName + ".xml";
		String collectionOfRDFStatementsPath = archiMateModelName + ".nt";

		Model archiMateModel = ArchiMateModelTranslator.translateArchiMateModelToRDFGraph(archiMateModelPath,
				collectionOfRDFStatementsPath);

		assertNotNull(archiMateModel);
	}
}
