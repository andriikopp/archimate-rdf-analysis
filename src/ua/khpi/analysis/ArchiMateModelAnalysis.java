package ua.khpi.analysis;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;

import ua.khpi.analysis.beans.ArchiMateElement;

public class ArchiMateModelAnalysis {

	private Model archiMateModel;

	public ArchiMateModelAnalysis(Model graph) {
		super();
		this.archiMateModel = graph;
	}

	public List<ArchiMateElement> getArchiMateModelElements() {
		List<ArchiMateElement> archiMateElements = new ArrayList<ArchiMateElement>();

		for (ResIterator iterator = archiMateModel.listSubjects(); iterator.hasNext();) {
			Resource resource = iterator.nextResource();
			int propertiesNumber = 0;

			for (StmtIterator j = resource.listProperties(); j.hasNext();) {
				j.nextStatement();
				propertiesNumber++;
			}

			archiMateElements.add(new ArchiMateElement(resource.getLocalName(), propertiesNumber));
		}

		return archiMateElements;
	}

	public double calculateArchiMateModelDensity() {
		List<ArchiMateElement> archiMateElements = getArchiMateModelElements();

		int archiMateModelSize = archiMateElements.size();
		int archiMateElementsPropertiesSum = 0;

		for (ArchiMateElement archiMateElement : archiMateElements) {
			archiMateElementsPropertiesSum += archiMateElement.getElementPropertiesNumber();
		}

		int archiMateModelDensity = archiMateElementsPropertiesSum / (archiMateModelSize * (archiMateModelSize - 1));

		return archiMateModelDensity;
	}

	public Model getArchiMateModel() {
		return archiMateModel;
	}

	public void setArchiMateModel(Model graph) {
		this.archiMateModel = graph;
	}
}
