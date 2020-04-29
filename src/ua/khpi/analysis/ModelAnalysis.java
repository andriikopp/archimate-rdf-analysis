package ua.khpi.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BpmnModelElementInstance;
import org.camunda.bpm.model.bpmn.instance.Definitions;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.Task;

import ua.khpi.analysis.beans.Artifact;
import ua.khpi.app.DataExtractionUtil;

public class ModelAnalysis {
	private Model model;
	private List<Artifact> artifacts;
	private BpmnModelInstance modelInstance = Bpmn.createEmptyModel();

	public ModelAnalysis(Model graph) {
		super();
		this.model = graph;
	}

	public List<Artifact> extractArtifacts() {
		if (artifacts == null) {
			artifacts = new ArrayList<Artifact>();

			Set<RDFNode> nodes = new HashSet<>();

			for (StmtIterator iterator = model.listStatements(); iterator.hasNext();) {
				Statement stmt = iterator.next();

				if (getType(stmt.getSubject().asResource()).equals("BusinessProcess")) {
					nodes.add(stmt.getSubject());
				}

				if (getType(stmt.getObject().asResource()).equals("BusinessProcess")) {
					nodes.add(stmt.getObject());
				}
			}

			for (RDFNode node : nodes) {
				Set<String> incomingNodes = new HashSet<>();
				Set<String> outgoingNodes = new HashSet<>();

				for (StmtIterator iterator = model.listStatements(); iterator.hasNext();) {
					Statement stmt = iterator.next();

					if (stmt.getObject().equals(node)) {
						if (getType(stmt.getSubject().asResource()).equals("BusinessProcess")) {
							incomingNodes.add(stmt.getSubject().getLocalName());
						}
					}

					if (stmt.getSubject().equals(node)) {
						if (getType(stmt.getObject().asResource()).equals("BusinessProcess")) {
							outgoingNodes.add(stmt.getObject().asResource().getLocalName());
						}
					}
				}

				Artifact artifact = new Artifact(node.asResource().getLocalName(), incomingNodes.size(),
						outgoingNodes.size());
				artifact.setIncomingNodes(incomingNodes);
				artifact.setOutgoingNodes(outgoingNodes);
				artifact.setType(getType(node.asResource()));

				artifacts.add(artifact);
			}
		}

		return artifacts;
	}

	public void printMatrix() {
		int[][] matrix = new int[artifacts.size()][artifacts.size()];
		List<String> vertices = new ArrayList<>();

		for (Artifact artifact : artifacts) {
			vertices.add(artifact.getName());
		}

		for (Artifact artifact : artifacts) {
			int row = vertices.indexOf(artifact.getName());

			for (String out : artifact.getOutgoingNodes()) {
				int col = vertices.indexOf(out);
				matrix[row][col]++;
			}
		}

		for (int i = 0; i < artifacts.size(); i++) {
			System.out.printf("%-100s\t", vertices.get(i));

			for (int j = 0; j < artifacts.size(); j++) {
				System.out.printf("%d\t", matrix[i][j]);
			}

			System.out.println();
		}
	}

	public void createMap(String fileName) {
		Definitions definitions = modelInstance.newInstance(Definitions.class);
		definitions.setTargetNamespace("http://camunda.org/examples");
		modelInstance.setDefinitions(definitions);
		Process process = createElement(definitions, fileName, Process.class);

		Map<String, FlowNode> flowNodes = new HashMap<>();

		for (Artifact artifact : artifacts) {
			FlowNode flowNode = createElement(process, artifact.getName(), Task.class);
			flowNode.setAttributeValue("name", artifact.getName(), true);
			flowNodes.put(artifact.getName(), flowNode);
		}

		for (StmtIterator iterator = model.listStatements(); iterator.hasNext();) {
			Statement stmt = iterator.next();

			if (flowNodes.containsKey(stmt.getSubject().getLocalName())
					&& flowNodes.containsKey(stmt.getObject().asResource().getLocalName())) {
				createSequenceFlow(process, flowNodes.get(stmt.getSubject().getLocalName()),
						flowNodes.get(stmt.getObject().asResource().getLocalName()));
			}
		}

		Bpmn.validateModel(modelInstance);
		File file = new File(DataExtractionUtil.PROCESS_PATH + fileName + ".bpmn");
		Bpmn.writeModelToFile(file, modelInstance);
	}

	public double density() {
		if (artifacts == null) {
			artifacts = extractArtifacts();
		}

		double size = artifacts.size();
		return links() / (size * (size - 1));
	}

	public double connectivity() {
		if (artifacts == null) {
			artifacts = extractArtifacts();
		}

		return links() / artifacts.size();
	}

	public void centrality() {
		if (artifacts == null) {
			artifacts = extractArtifacts();
		}

		double size = artifacts.size();

		for (Artifact artifact : artifacts) {
			double centrality = (artifact.getIncoming() + artifact.getOutgoing()) / (size - 1);
			artifact.setCentrality(centrality);
		}
	}

	public double modelCentrality() {
		if (artifacts == null) {
			artifacts = extractArtifacts();
		}

		double size = artifacts.size();
		double difference = 0;
		double maxDegree = maxDegree();

		for (Artifact artifact : artifacts) {
			difference += (maxDegree - (artifact.getIncoming() + artifact.getOutgoing()));
		}

		return difference / ((size - 1) * (size - 2));
	}

	public void rank() {
		if (artifacts == null) {
			artifacts = extractArtifacts();
		}

		double size = artifacts.size();
		List<Double> centrality = new ArrayList<>();
		double total = 0;

		for (Artifact artifact : artifacts) {
			double rank = (artifact.getIncoming() + artifact.getOutgoing()) / (size - 1);
			centrality.add(rank);
			total += rank;
		}

		for (int i = 0; i < artifacts.size(); i++) {
			double rank = 0;

			for (int j = 0; j < artifacts.size(); j++) {
				if (i != j && artifacts.get(i).getIncomingNodes().contains(artifacts.get(j).getName())) {
					rank += centrality.get(j);
				}

				if (i != j && artifacts.get(i).getOutgoingNodes().contains(artifacts.get(j).getName())) {
					rank += centrality.get(j);
				}
			}

			rank /= (total - centrality.get(i));
			artifacts.get(i).setRank(rank);
		}
	}

	protected <T extends BpmnModelElementInstance> T createElement(BpmnModelElementInstance parentElement, String id,
			Class<T> elementClass) {
		T element = modelInstance.newInstance(elementClass);
		element.setAttributeValue("id", id, true);
		parentElement.addChildElement(element);
		return element;
	}

	protected SequenceFlow createSequenceFlow(Process process, FlowNode from, FlowNode to) {
		String identifier = from.getId() + "-" + to.getId();
		SequenceFlow sequenceFlow = createElement(process, identifier, SequenceFlow.class);
		process.addChildElement(sequenceFlow);
		sequenceFlow.setSource(from);
		from.getOutgoing().add(sequenceFlow);
		sequenceFlow.setTarget(to);
		to.getIncoming().add(sequenceFlow);
		return sequenceFlow;
	}

	protected String getType(Resource resource) {
		return resource.getURI().split("#")[1].split("::")[0];
	}

	private double links() {
		double properties = 0;

		for (StmtIterator iterator = model.listStatements(); iterator.hasNext();) {
			iterator.next();
			properties++;
		}
		return properties;
	}

	private double maxDegree() {
		if (artifacts == null) {
			artifacts = extractArtifacts();
		}

		double maxDegree = 0;

		for (Artifact artifact : artifacts) {
			int degree = artifact.getIncoming() + artifact.getOutgoing();

			if (degree > maxDegree) {
				maxDegree = degree;
			}
		}

		return maxDegree;
	}

	public Model getArchiMateModel() {
		return model;
	}

	public void setArchiMateModel(Model graph) {
		this.model = graph;
	}

	public List<Artifact> getArchiMateElements() {
		return artifacts;
	}

	public void setArchiMateElements(List<Artifact> archiMateElements) {
		this.artifacts = archiMateElements;
	}
}
