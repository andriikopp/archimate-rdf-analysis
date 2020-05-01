package ua.khpi.analysis.beans;

import java.util.Set;

public class Artifact {
	private String name;

	private int incoming;
	private int outgoing;

	private double centrality;
	private double rank;

	private Set<String> incomingNodes;
	private Set<String> outgoingNodes;

	private String type;

	private double cost;

	private int opt;

	public Artifact(String name, int incoming, int outgoing) {
		super();
		this.name = name;
		this.incoming = incoming;
		this.outgoing = outgoing;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIncoming() {
		return incoming;
	}

	public void setIncoming(int incoming) {
		this.incoming = incoming;
	}

	public int getOutgoing() {
		return outgoing;
	}

	public void setOutgoing(int outgoing) {
		this.outgoing = outgoing;
	}

	public double getCentrality() {
		return centrality;
	}

	public void setCentrality(double centrality) {
		this.centrality = centrality;
	}

	public double getRank() {
		return rank;
	}

	public void setRank(double rank) {
		this.rank = rank;
	}

	public Set<String> getIncomingNodes() {
		return incomingNodes;
	}

	public void setIncomingNodes(Set<String> incomingNodes) {
		this.incomingNodes = incomingNodes;
	}

	public Set<String> getOutgoingNodes() {
		return outgoingNodes;
	}

	public void setOutgoingNodes(Set<String> outgoingNodes) {
		this.outgoingNodes = outgoingNodes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getOpt() {
		return opt;
	}

	public void setOpt(int opt) {
		this.opt = opt;
	}
}
