package ua.khpi.apparchi.entity;

import ua.khpi.apparchi.entity.api.IGenericEntity;

public class StructureEntity implements IGenericEntity {
	private String id;
	private String modelId;
	private int nodes;
	private int edges;
	private String timestamp;

	public StructureEntity(String id, String modelId, int nodes, int edges, String timestamp) {
		super();
		this.id = id;
		this.modelId = modelId;
		this.nodes = nodes;
		this.edges = edges;
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public int getNodes() {
		return nodes;
	}

	public void setNodes(int nodes) {
		this.nodes = nodes;
	}

	public int getEdges() {
		return edges;
	}

	public void setEdges(int edges) {
		this.edges = edges;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + edges;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modelId == null) ? 0 : modelId.hashCode());
		result = prime * result + nodes;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StructureEntity other = (StructureEntity) obj;
		if (edges != other.edges)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modelId == null) {
			if (other.modelId != null)
				return false;
		} else if (!modelId.equals(other.modelId))
			return false;
		if (nodes != other.nodes)
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StructureEntity [id=" + id + ", modelId=" + modelId + ", nodes=" + nodes + ", edges=" + edges
				+ ", timestamp=" + timestamp + "]";
	}

}
