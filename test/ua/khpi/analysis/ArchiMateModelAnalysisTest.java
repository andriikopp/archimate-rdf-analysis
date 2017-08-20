package ua.khpi.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.jena.rdf.model.Model;
import org.junit.Before;
import org.junit.Test;

import ua.khpi.analysis.beans.ArchiMateElement;
import ua.khpi.util.ArchiMateModelTranslator;
import ua.khpi.util.ArchiMateModelTranslatorTest;

public class ArchiMateModelAnalysisTest {

	private ArchiMateModelAnalysis archiMateModelAnalysis;

	@Before
	public void setUpArchiMateModel() {
		String archiMateModelName = "Archisurance";
		String archiMateModelPath = ArchiMateModelTranslatorTest.MODELS_PATH + archiMateModelName + ".xml";
		String collectionOfRDFStatementsPath = archiMateModelName + ".nt";

		Model archiMateModel = ArchiMateModelTranslator.translateArchiMateModelToRDFGraph(archiMateModelPath,
				collectionOfRDFStatementsPath);

		archiMateModelAnalysis = new ArchiMateModelAnalysis(archiMateModel);
	}

	@Test
	public void testGetArchiMateModelElements() {
		int archiMateModelElementsNumber = archiMateModelAnalysis.getArchiMateModelElements().size();
		int expectedNumber = 86;

		assertEquals("Elements numbers doesn't match", expectedNumber, archiMateModelElementsNumber);
	}

	@Test
	public void testCalculateArchiMateModelDensity() {
		double archiMateModelDensity = archiMateModelAnalysis.calculateArchiMateModelDensity();
		double expectedDensity = 0.02;

		assertEquals("Density values doesn't match", expectedDensity, archiMateModelDensity, 10E-2);
	}

	@Test
	public void testCalculateArchiMateElementsCentrality() {
		archiMateModelAnalysis.calculateArchiMateElementsCentrality();

		for (ArchiMateElement archiMateElement : archiMateModelAnalysis.getArchiMateModelElements()) {
			assertTrue(archiMateElement.getElementCentrality() >= 0);
			assertTrue(archiMateElement.getElementCentrality() <= 1);
		}
	}
}
