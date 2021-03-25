package ua.khpi.apparchi.entity;

import ua.khpi.apparchi.entity.api.IGenericEntity;

public class MeasureEntity implements IGenericEntity {
	private String id;
	private String modelId;
	private double density;
	private double avgDegree;
	private double minDegree;
	private double maxDegree;
	private double connectivity;
	private double resilience;
	private double centrality;
	private double irregularity;
	private String timestamp;

	public MeasureEntity(String id, String modelId, double density, double avgDegree, double minDegree,
			double maxDegree, double connectivity, double resilience, double centrality, double irregularity,
			String timestamp) {
		super();
		this.id = id;
		this.modelId = modelId;
		this.density = density;
		this.avgDegree = avgDegree;
		this.minDegree = minDegree;
		this.maxDegree = maxDegree;
		this.connectivity = connectivity;
		this.resilience = resilience;
		this.centrality = centrality;
		this.irregularity = irregularity;
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

	public double getDensity() {
		return density;
	}

	public void setDensity(double density) {
		this.density = density;
	}

	public double getAvgDegree() {
		return avgDegree;
	}

	public void setAvgDegree(double avgDegree) {
		this.avgDegree = avgDegree;
	}

	public double getMinDegree() {
		return minDegree;
	}

	public void setMinDegree(double minDegree) {
		this.minDegree = minDegree;
	}

	public double getMaxDegree() {
		return maxDegree;
	}

	public void setMaxDegree(double maxDegree) {
		this.maxDegree = maxDegree;
	}

	public double getConnectivity() {
		return connectivity;
	}

	public void setConnectivity(double connectivity) {
		this.connectivity = connectivity;
	}

	public double getResilience() {
		return resilience;
	}

	public void setResilience(double resilience) {
		this.resilience = resilience;
	}

	public double getCentrality() {
		return centrality;
	}

	public void setCentrality(double centrality) {
		this.centrality = centrality;
	}

	public double getIrregularity() {
		return irregularity;
	}

	public void setIrregularity(double irregularity) {
		this.irregularity = irregularity;
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
		long temp;
		temp = Double.doubleToLongBits(avgDegree);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(centrality);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(connectivity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(density);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		temp = Double.doubleToLongBits(irregularity);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(maxDegree);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minDegree);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((modelId == null) ? 0 : modelId.hashCode());
		temp = Double.doubleToLongBits(resilience);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		MeasureEntity other = (MeasureEntity) obj;
		if (Double.doubleToLongBits(avgDegree) != Double.doubleToLongBits(other.avgDegree))
			return false;
		if (Double.doubleToLongBits(centrality) != Double.doubleToLongBits(other.centrality))
			return false;
		if (Double.doubleToLongBits(connectivity) != Double.doubleToLongBits(other.connectivity))
			return false;
		if (Double.doubleToLongBits(density) != Double.doubleToLongBits(other.density))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(irregularity) != Double.doubleToLongBits(other.irregularity))
			return false;
		if (Double.doubleToLongBits(maxDegree) != Double.doubleToLongBits(other.maxDegree))
			return false;
		if (Double.doubleToLongBits(minDegree) != Double.doubleToLongBits(other.minDegree))
			return false;
		if (modelId == null) {
			if (other.modelId != null)
				return false;
		} else if (!modelId.equals(other.modelId))
			return false;
		if (Double.doubleToLongBits(resilience) != Double.doubleToLongBits(other.resilience))
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
		return "MeasureEntity [id=" + id + ", modelId=" + modelId + ", density=" + density + ", avgDegree=" + avgDegree
				+ ", minDegree=" + minDegree + ", maxDegree=" + maxDegree + ", connectivity=" + connectivity
				+ ", resilience=" + resilience + ", centrality=" + centrality + ", irregularity=" + irregularity
				+ ", timestamp=" + timestamp + "]";
	}

}
