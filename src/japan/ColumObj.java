package japan;

import java.util.ArrayList;
import java.util.List;

public class ColumObj {
	private Parameters parameters;
	private List<Cell> columVal = new ArrayList<Cell>();

	public ColumObj() {

	}

	public ColumObj(ColumObj columObjToClone) {
		this.parameters = new Parameters(columObjToClone.getParameters());
		for (Cell cell : columObjToClone.getColumVal()) {
			this.columVal.add(new Cell(cell));
		}
	}

	public ColumObj(Parameters element, List<Cell> columVal) {
		this.setParameters(element);
		this.setColumVal(columVal);
	}

	public Parameters getParameters() {
		return parameters;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public List<Cell> getColumVal() {
		return columVal;
	}

	public void setColumVal(List<Cell> columVal) {
		this.columVal = columVal;
	}

}
