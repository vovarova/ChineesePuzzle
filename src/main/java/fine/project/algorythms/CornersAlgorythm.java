package fine.project.algorythms;

import java.util.Collections;
import java.util.List;

import fine.project.Cell;
import fine.project.ColumObj;
import fine.project.Link;
import fine.project.Parameter;
import fine.project.Parameters;

public class CornersAlgorythm implements Algorythm {

	public void calculate(ColumObj columObj) {

		Parameters parameters = columObj.getParameters();
		if (parameters.getValues().isEmpty()) {
			return;
		}
		Parameters left = getLeftCorner(columObj);
		System.out.println(left);
		Parameters rightCorner = getRightCorner(columObj);
		System.out.println(rightCorner);
	}

	private Parameters getLeftCorner(ColumObj columObj) {
		Parameters parameters = columObj.getParameters();
		Parameters paramsClone = new Parameters(parameters);
		List<Parameter> cloneParamValues = paramsClone.getValues();
		List<Cell> columVal = columObj.getColumVal();
		int currentIndex = 0;
		for (Parameter param : cloneParamValues) {
			if(currentIndex>=columVal.size()){
				break;
			}
			int paramVal = param.getVal();
			Link link = param.getLink();
			if (link == null) {
				for (int i = currentIndex; i < columVal.size(); i++) {
					boolean cond = true;
					int curenrElemIndex = 0;
					for (int k = 0; k < paramVal && i + k < columVal.size(); k++) {
						curenrElemIndex = i + k;
						Cell cell = columVal.get(curenrElemIndex);
						if (cell != null && cell.getState() != null) {
							cond = false;
							break;
						}
					}
					if (cond) {
						param.setLink(new Link(i, curenrElemIndex));
						currentIndex = param.getLink().getSecondArg() + 2;
						break;
					}
				}
			} else {
				int valuesLeft = param.getVal() - link.getSectorSize();
				Integer secondArg = link.getSecondArg();
				for (int k = 1; k <= valuesLeft
						&& secondArg + k < columVal.size(); k++) {
					Cell cell = columVal.get(secondArg + k);
					if (cell != null && cell.getState() == null) {
						link.incrementSecond();
						currentIndex = link.getSecondArg() + 2;
					} else {
						break;
					}
				}
			}
		}
		return paramsClone;
	}

	private Parameters getRightCorner(ColumObj columObj) {
		Parameters parameters = columObj.getParameters();
		Parameters paramsClone = new Parameters(parameters);
		List<Parameter> cloneParamValues = paramsClone.getValues();
		List<Cell> columVal = columObj.getColumVal();
		int currentIndex = columObj.getColumVal().size() - 1;
		Collections.reverse(cloneParamValues);
		for (Parameter param : cloneParamValues) {
			if(currentIndex<0){
				break;
			}
			int paramVal = param.getVal();
			Link link = param.getLink();
			if (link == null) {
				for (int i = currentIndex; i >= 0; i--) {
					boolean cond = true;
					int curenrElemIndex = 0;
					for (int k = 0; k < paramVal && i - k >= 0; k++) {
						curenrElemIndex = i - k;
						Cell cell = columVal.get(curenrElemIndex);
						if (cell != null && cell.getState() != null) {
							cond = false;
							break;
						}
					}
					if (cond) {
						param.setLink(new Link(curenrElemIndex,i));
						currentIndex = param.getLink().getFirtsArg() - 2;
						break;
					}
				}
			} else {
				int valuesLeft = param.getVal() - link.getSectorSize();
				Integer firstArg = link.getFirtsArg();
				for (int k = 1; k <= valuesLeft && firstArg - k >= 0; k++) {
					Cell cell = columVal.get(firstArg - k);
					if (cell != null && cell.getState() == null) {
						link.decrementFirst();
						currentIndex = link.getFirtsArg() - 2;
					} else {
						break;
					}
				}
			}
		}
		Collections.reverse(cloneParamValues);
		return paramsClone;
	}

	public static void main(String[] args) {
		ColumObj columObj = new ColumObj();
		columObj.addCell(null); //0
		columObj.addCell(null);//1
		columObj.addCell(null);//2
		columObj.addCell(null);//3
		columObj.addCell(null);//4
		columObj.addCell(null);//5
		columObj.addCell(false);//6
		columObj.addCell(null);//7
		columObj.addCell(null);//8
		columObj.addCell(null);//9
		columObj.addCell(null);//10
		columObj.addCell(null);//11
		columObj.addCell(null);//12
		columObj.addCell(null);//13
		columObj.addCell(false);//14
		columObj.addCell(null);//15
		columObj.addCell(false);//16
		Parameters parameters = columObj.getParameters();
		parameters.addParameter(5,3,3);
		parameters.addParameter(2,11,11);

		CornersAlgorythm cornersAlgorythm = new CornersAlgorythm();
		cornersAlgorythm.calculate(columObj);

	}

}
