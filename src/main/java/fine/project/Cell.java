package fine.project;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Cell {

	public Cell() {
	}

	public Cell(Boolean state) {
		this.state = state;
	}

	public Cell(Cell objToClone) {
		this.state = objToClone.state;
	}

	private Boolean state;

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
