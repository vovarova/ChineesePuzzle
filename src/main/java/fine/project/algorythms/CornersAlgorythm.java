package fine.project.algorythms;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fine.project.Cell;
import fine.project.ColumObj;
import fine.project.Link;
import fine.project.Parameter;
import fine.project.Parameters;

public class CornersAlgorythm implements Algorythm {

	public void calculate(ColumObj columObj) {

		Parameters parameters = columObj.getParameters();
		List<Parameter> paramValues = parameters.getValues();
		if (paramValues.isEmpty()) {
			return;
		}
		Parameters leftCorner = getLeftCorner(columObj);
		Parameters rightCorner = getRightCorner(columObj);
		List<Parameter> leftCornervalues = leftCorner.getValues();
		List<Parameter> rightCornervalues = rightCorner.getValues();
		
		for (int i = 0; i < paramValues.size(); i++) {
			Parameter parameter = paramValues.get(i);
			Link link = parameter.getLink();
			Parameter leftParameter = leftCornervalues.get(i);
			Parameter rightParameter = rightCornervalues.get(i);
			if(link==null || !link.isDone(parameter.getVal())){
				Link compareLinks = compareLinks(leftParameter.getLink(),rightParameter.getLink());
				if(compareLinks!=null){
					parameter.setLink(compareLinks);
					Integer firtsArg = compareLinks.getFirtsArg();
					Integer secondArg = compareLinks.getSecondArg();
					List<Cell> columVal = columObj.getColumVal();
					for (int p = firtsArg; p <= secondArg; p++) {
						columVal.get(p).setState(true);
					}
				}
			}
		}
		
		
	}

	
	
	
	private Link compareLinks(Link leftLink, Link rightLink) {
		Set<Integer> result = createSet(leftLink);
		Link linkResult = null;
		result.retainAll(createSet(rightLink));
		if(!result.isEmpty()){			
			Integer max = Collections.max(result);
			Integer min = Collections.min(result);			
			linkResult = new Link(min, max);
		}
		return linkResult;
	}
	
	private Set<Integer> createSet(Link link){
		Set<Integer> linkSet = new HashSet<Integer>(link.getSectorSize());
		for (int i = link.getFirtsArg(); i <= link.getSecondArg(); i++) {
			linkSet.add(i);
		}
		return linkSet;
	}

	private Parameters getLeftCorner(ColumObj columObj) {
		Parameters parameters = columObj.getParameters();
		Parameters paramsClone = new Parameters(parameters);
		List<Parameter> cloneParamValues = paramsClone.getValues();
		List<Cell> columVal = columObj.getColumVal();
		int currentIndex = 0;
		for (Parameter param : cloneParamValues) {
			if (currentIndex >= columVal.size()) {
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
				if (!link.isDone(paramVal)) {
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
			if (currentIndex < 0) {
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
						param.setLink(new Link(curenrElemIndex, i));
						currentIndex = param.getLink().getFirtsArg() - 2;
						break;
					}
				}
			} else {
				if(!link.isDone(paramVal)){
					int valuesLeft = param.getVal() - link.getSectorSize();
					Integer firstArg = link.getFirtsArg();
					for (int k = 1; k <= valuesLeft && firstArg - k >= 0; k++) {
						Cell cell = columVal.get(firstArg - k);
						if (cell != null && cell.getState() == null) {
							link.decrementFirst();
						} else {
							break;
						}
					}
				}
				currentIndex = link.getFirtsArg() - 2;
			}
		}
		Collections.reverse(cloneParamValues);
		return paramsClone;
	}

	public static void main(String[] args) {
		ColumObj columObj = new ColumObj();
		columObj.addCell(null); // 0
		columObj.addCell(null);// 1
		columObj.addCell(null);// 2
		columObj.addCell(null);// 3
		columObj.addCell(null);// 4
		columObj.addCell(null);// 5
		columObj.addCell(false);// 6
		columObj.addCell(null);// 7
		columObj.addCell(null);// 8
		columObj.addCell(null);// 9
		columObj.addCell(null);// 10
		columObj.addCell(false);// 11
		columObj.addCell(null);// 12
		columObj.addCell(null);// 13
		columObj.addCell(false);// 14
		columObj.addCell(null);// 15
		columObj.addCell(false);// 16
		Parameters parameters = columObj.getParameters();
		parameters.addParameter(5);
		parameters.addParameter(3);

		CornersAlgorythm cornersAlgorythm = new CornersAlgorythm();
		cornersAlgorythm.calculate(columObj);
		System.out.println(Arrays.toString(columObj.getColumVal().toArray()));
	}

}
