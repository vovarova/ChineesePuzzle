package fine.project.algorythms;

import java.util.List;

import fine.project.model.Cell;
import fine.project.model.ColumnObj;
import fine.project.model.Parameter.Link;
import fine.project.model.Parameter;

public class CornersAlgorythm implements Algorythm {

	public void calculate(ColumnObj columnObj) {

		List<Parameter> paramValues = columnObj.getParameters();
		if (paramValues.isEmpty()) {
			return;
		}
		List<Parameter> leftCornervalues = getLeftCornerParameters(columnObj);
		List<Parameter> rightCornervalues = getRightCornerParameters(columnObj);

		for (int i = 0; i < paramValues.size(); i++) {
			Parameter parameter = paramValues.get(i);
			if (!parameter.existLink()) {
				Parameter leftParameter = leftCornervalues.get(i);
				Parameter rightParameter = rightCornervalues.get(i);
				ParametersUtils.parametersComparison(leftParameter,
						rightParameter,parameter);
				ParametersUtils.fillColumn(parameter, columnObj);
			}
		}
	}



	private List<Parameter> getLeftCornerParameters(ColumnObj columnObj) {
		ColumnObj clone = columnObj.getClone();
		ColumnObj leftCorner = getLeftCorner(clone);
		return leftCorner.getParameters();
	}

	private List<Parameter> getRightCornerParameters(ColumnObj columnObj) {
		ColumnObj clone = columnObj.getClone();
		clone.reverseColumn();
		ColumnObj leftCorner = getLeftCorner(clone);
		leftCorner.reverseColumn();
		return leftCorner.getParameters();
	}


	private ColumnObj getLeftCorner(ColumnObj columnObj) {

		List<Parameter> paramValues = columnObj.getParameters();
		List<Cell> columVal = columnObj.getColumnValues();
		int currentIndex = 0;
		for (Parameter param : paramValues) {
			if (currentIndex >= columVal.size()) {
				break;
			}
			int paramVal = param.getVal();
			if (!param.existLink()) {
				for (int i = currentIndex; i < columVal.size(); i++) {
					boolean cond = true;
					int curenrElemIndex = 0;
					for (int k = 0; k < paramVal && i + k < columVal.size(); k++) {
						curenrElemIndex = i + k;
						Cell cell = columVal.get(curenrElemIndex);
						if (cell != null && cell.getState() != null
								&& !cell.getState()) {
							cond = false;
							break;
						}
					}
					if (cond) {
						param.setLink(i, curenrElemIndex);
						currentIndex = param.getLink().getSecondArg() + 2;
						break;
					}
				}
			} else {
				Link link = param.getLink();
				if (!link.isDone()) {
					int valuesLeft = param.getVal() - link.getSectorSize();
					Integer secondArg = link.getSecondArg();
					for (int k = 1; k <= valuesLeft
							&& secondArg + k < columVal.size(); k++) {
						Cell cell = columVal.get(secondArg + k);
						if (cell != null && cell.getState() == null) {
							link.incrementSecond();
						} else {
							break;
						}
					}
				}
				currentIndex = link.getSecondArg() + 2;
			}
		}
		return columnObj;
	}

	

	public static void main(String[] args) {
		ColumnObj columnObj = new ColumnObj();
		columnObj.addCell(null); // 0
		columnObj.addCell(null);// 1
		columnObj.addCell(null);// 2
		columnObj.addCell(null);// 3
		columnObj.addCell(null);// 4
		columnObj.addCell(null);// 5
		columnObj.addCell(null);// 6
		columnObj.addCell(null);// 7
		columnObj.addCell(null);// 8
		columnObj.addCell(null);// 9
		columnObj.addCell(null);// 10
		columnObj.addCell(null);// 11
		columnObj.addCell(null);// 12
		columnObj.addCell(null);// 13
		columnObj.addCell(null);// 14
		columnObj.addCell(null);// 15
		columnObj.addCell(null);// 16
		columnObj.addParameter(10);
		columnObj.addParameter(4);

		CornersAlgorythm cornersAlgorythm = new CornersAlgorythm();
		cornersAlgorythm.calculate(columnObj);
		System.out.println(columnObj);
	}

}
