package fine.project.algorythms;

import java.util.List;

import fine.project.model.Cell;
import fine.project.model.ColumnObj;
import fine.project.model.Parameter;
import fine.project.model.Parameter.Link;

public class LinkCornerAnalyzer implements Algorythm {

	public void calculate(ColumnObj columnObj) {
		List<Parameter> parameters = columnObj.getParameters();
			List<Cell> columnValues = columnObj.getColumnValues();
		for (Parameter parameter : parameters) {						
			Link link = parameter.getLink();
			if (parameter.existLink() && !link.isDone()) {
				Parameter parameterLeftCorner = getParameterLeftCorner(columnObj, parameter);
				Parameter parameterRightCorner = getParameterRightCorner(columnObj, parameter);
				boolean parametersComparison = ParametersUtils.parametersComparison(parameterLeftCorner, parameterRightCorner, parameter);
				if(parametersComparison){
					ParametersUtils.fillColumn(parameter, columnObj);
				}
			}
		}
	}

	private Parameter getParameterLeftCorner(ColumnObj columnObj, Parameter parameter) {
		return getLeftCorner(columnObj, parameter.getClone());
	}
	private Parameter getParameterRightCorner(ColumnObj columnObj, Parameter parameter) {
		columnObj.reverseColumn();
		Parameter cornerParam = getLeftCorner(columnObj, parameter.getClone().reverseParameter());
		columnObj.reverseColumn();
		return cornerParam.reverseParameter();
		
	}
	
	
	private Parameter getLeftCorner(ColumnObj columnObj, Parameter parameter) {
		
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
		return parameter;
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
