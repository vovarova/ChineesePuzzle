package fine.project.algorythms;

import java.util.List;

import fine.project.model.Cell;
import fine.project.model.ColumnObj;
import fine.project.model.Parameter;
import fine.project.model.Parameter.Link;

public class LinkCornerAnalyzer implements Algorythm {

	public void calculate(ColumnObj columObj) {
		List<Parameter> parameters = columObj.getParameters();
			List<Cell> columnValues = columObj.getColumnValues();
		for (Parameter parameter : parameters) {						
			Link link = parameter.getLink();
			if (parameter.existLink() && link.isDone()) {
				getParameterCorner(columObj, parameter);
			}
		}
	}

	private void getParameterCorner(ColumnObj columnObj, Parameter parameter) {
		
		Link link = parameter.getLink();
		int valuesLeft = link.getValuesLeft();
		Integer secondArg = link.getSecondArg();
		Integer firsIArg = link.getFirtsArg();
		
		List<Cell> columnValues = columnObj.getColumnValues();
		for (int k = 1; k <= valuesLeft
				&& secondArg + k < columnValues.size(); k++) {
			Cell cell = columnValues.get(secondArg + k);
			if (cell != null && cell.getState() == null) {
				link.incrementSecond();
			} else {
				for (int i = firsIArg; i >= 0; i--) {
					Cell cellToFind = columnValues.get(firsIArg - k);
					if (cellToFind.getState() == null) {
						link.decrementFirst();
					}
				}
			}
		}
	}

}
