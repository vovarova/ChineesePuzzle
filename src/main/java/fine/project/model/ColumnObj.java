package fine.project.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ColumnObj extends Model {
	private List<Parameter> parameters = new ArrayList<Parameter>();
	private List<Cell> columnValues = new ArrayList<Cell>();

	public ColumnObj() {
	}

	public ColumnObj(ColumnObj columObjToClone) {
		for (Parameter parameter : columObjToClone.getParameters()) {
			this.parameters.add(new Parameter(parameter));
		}
		for (Cell cell : columObjToClone.getColumnValues()) {
			this.columnValues.add(new Cell(cell));
		}
	}

	public void addParameter(int val) {
		parameters.add(new Parameter(val, columnValues.size()));
	}

	public void addParameter(int val, int firstInd, int secondInd) {
		parameters.add(new Parameter(val, columnValues.size(), firstInd,
				secondInd));
	}

	public void addCell(Boolean state) {
		columnValues.add(new Cell(state));
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public List<Cell> getColumnValues() {
		return columnValues;
	}

	public void setColumnValues(List<Cell> columnValues) {
		this.columnValues = columnValues;
	}

	public ColumnObj reverseColumn() {
		Collections.reverse(columnValues);
		Collections.reverse(parameters);
		for (Parameter parameter : parameters) {
			parameter.reverseParameter();
		}
		return this;
	}

	public ColumnObj getClone() {
		return new ColumnObj(this);
	}

}
