package ua.khpi.apparchi.entity;

import ua.khpi.apparchi.entity.api.IGenericEntity;

public class ElementEntity implements IGenericEntity {
	private String id;
	private String structureId;
	private String label;
	private String type;
	private int inDegree;
	private int outDegree;

	public ElementEntity(String id, String structureId, String label, String type, int inDegree, int outDegree) {
		super();
		this.id = id;
		this.structureId = structureId;
		this.label = label;
		this.type = type;
		this.inDegree = inDegree;
		this.outDegree = outDegree;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStructureId() {
		return structureId;
	}

	public void setStructureId(String structureId) {
		this.structureId = structureId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getInDegree() {
		return inDegree;
	}

	public void setInDegree(int inDegree) {
		this.inDegree = inDegree;
	}

	public int getOutDegree() {
		return outDegree;
	}

	public void setOutDegree(int outDegree) {
		this.outDegree = outDegree;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + inDegree;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + outDegree;
		result = prime * result + ((structureId == null) ? 0 : structureId.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ElementEntity other = (ElementEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (inDegree != other.inDegree)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (outDegree != other.outDegree)
			return false;
		if (structureId == null) {
			if (other.structureId != null)
				return false;
		} else if (!structureId.equals(other.structureId))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ElementEntity [id=" + id + ", structureId=" + structureId + ", label=" + label + ", type=" + type
				+ ", inDegree=" + inDegree + ", outDegree=" + outDegree + "]";
	}

}
