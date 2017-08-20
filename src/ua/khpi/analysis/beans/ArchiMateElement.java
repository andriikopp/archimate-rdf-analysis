package ua.khpi.analysis.beans;

public class ArchiMateElement {

	private String elementName;
	private int elementPropertiesNumber;
	private double elementCentrality;
	
	public ArchiMateElement(String elementName, int elementPropertiesNumber) {
		super();
		this.elementName = elementName;
		this.elementPropertiesNumber = elementPropertiesNumber;
	}

	public ArchiMateElement(String elementName, int elementPropertiesNumber, double elementCentrality) {
		super();
		this.elementName = elementName;
		this.elementPropertiesNumber = elementPropertiesNumber;
		this.elementCentrality = elementCentrality;
	}

	@Override
	public String toString() {
		return "ArchiMateElement [elementName=" + elementName + ", elementPropertiesNumber=" + elementPropertiesNumber
				+ ", elementCentrality=" + elementCentrality + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(elementCentrality);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((elementName == null) ? 0 : elementName.hashCode());
		result = prime * result + elementPropertiesNumber;
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
		ArchiMateElement other = (ArchiMateElement) obj;
		if (Double.doubleToLongBits(elementCentrality) != Double.doubleToLongBits(other.elementCentrality))
			return false;
		if (elementName == null) {
			if (other.elementName != null)
				return false;
		} else if (!elementName.equals(other.elementName))
			return false;
		if (elementPropertiesNumber != other.elementPropertiesNumber)
			return false;
		return true;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public int getElementPropertiesNumber() {
		return elementPropertiesNumber;
	}

	public void setElementPropertiesNumber(int elementPropertiesNumber) {
		this.elementPropertiesNumber = elementPropertiesNumber;
	}

	public double getElementCentrality() {
		return elementCentrality;
	}

	public void setElementCentrality(double elementCentrality) {
		this.elementCentrality = elementCentrality;
	}
}
