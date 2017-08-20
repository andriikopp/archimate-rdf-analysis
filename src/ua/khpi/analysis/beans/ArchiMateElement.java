package ua.khpi.analysis.beans;

public class ArchiMateElement {

	private String elementName;
	private int elementPropertiesNumber;

	public ArchiMateElement(String elementName, int elementPropertiesNumber) {
		super();
		this.elementName = elementName;
		this.elementPropertiesNumber = elementPropertiesNumber;
	}

	@Override
	public String toString() {
		return "ArchiMateNode [elementName=" + elementName + ", elementPropertiesNumber=" + elementPropertiesNumber
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
}
