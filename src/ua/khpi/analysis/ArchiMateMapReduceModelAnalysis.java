package ua.khpi.analysis;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.StmtIterator;

import ua.khpi.analysis.beans.ArchiMateElement;

public class ArchiMateMapReduceModelAnalysis extends ArchiMateModelAnalysis {

	public ArchiMateMapReduceModelAnalysis(Model graph) {
		super(graph);
	}

	@Override
	public List<ArchiMateElement> getArchiMateModelElements() {
		if (getArchiMateElements() == null) {
			setArchiMateElements(new ArrayList<ArchiMateElement>());

			map();
			reduce();
		}

		return getArchiMateElements();
	}

	private void map() {
		for (StmtIterator i = getArchiMateModel().listStatements(); i.hasNext();) {
			getArchiMateElements().add(new ArchiMateElement(i.next().getSubject().getLocalName(), 1));
		}
	}

	private void reduce() {
		List<ArchiMateElement> result = new ArrayList<ArchiMateElement>();

		getArchiMateElements().parallelStream().forEach(element -> {
			int index = result.indexOf(element);

			if (index == -1) {
				result.add(element);
			} else {
				int value = result.get(index).getElementPropertiesNumber();

				result.get(index).setElementPropertiesNumber(value + 1);
			}
		});

		setArchiMateElements(result);
	}
}
