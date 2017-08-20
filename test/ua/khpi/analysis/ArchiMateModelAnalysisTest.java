package ua.khpi.analysis;

import static org.junit.Assert.*;

import org.apache.jena.rdf.model.Model;
import org.junit.Test;

import ua.khpi.util.ArchiMateModelTranslator;
import ua.khpi.util.ArchiMateModelTranslatorTest;

public class ArchiMateModelAnalysisTest {

	@Test
	public void testGetArchiMateModelElements() {
		String archiMateModelName = "Archisurance";
		String archiMateModelPath = ArchiMateModelTranslatorTest.MODELS_PATH + archiMateModelName + ".xml";
		String collectionOfRDFStatementsPath = archiMateModelName + ".nt";

		Model archiMateModel = ArchiMateModelTranslator.translateArchiMateModelToRDFGraph(archiMateModelPath,
				collectionOfRDFStatementsPath);
		
		ArchiMateModelAnalysis archiMateModelAnalysis = new ArchiMateModelAnalysis(archiMateModel);
		
		int archiMateModelElementsNumber = archiMateModelAnalysis.getArchiMateModelElements().size();
		int expectedNumber = 86;
		
		assertEquals("", expectedNumber, archiMateModelElementsNumber);
	}
}
