package ua.khpi.util;

import static org.junit.Assert.assertNotNull;

import org.apache.jena.rdf.model.Model;
import org.junit.Test;

public class RDFUtilTest {
	/**
	 * Path to a collection of ArchiMate models
	 */
	public static final String MODELS_PATH = "file:///C:/Users/lenovo/Downloads/RDFSim/";

	@Test
	public void testReadArchiMateModelAsRDFGraph() {
		final String archiMateModelName = "Archisurance";
		final String archiMateModelPath = MODELS_PATH + archiMateModelName + ".xml";
		final String collectionOfRDFStatementsPath = archiMateModelName + ".nt";

		Model graph = RDFUtil.readArchiMateModelAsRDFGraph(archiMateModelPath, collectionOfRDFStatementsPath);

		assertNotNull(graph);
	}
}
