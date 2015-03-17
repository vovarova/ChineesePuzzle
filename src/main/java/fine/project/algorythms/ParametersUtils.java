package fine.project.algorythms;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fine.project.model.Cell;
import fine.project.model.ColumnObj;
import fine.project.model.Parameter;
import fine.project.model.Parameter.Link;

public class ParametersUtils {
	private ParametersUtils() {}
	
	private static Set<Integer> createSet(Link link) {
		Set<Integer> linkSet = new HashSet<Integer>(link.getSectorSize());
		for (int i = link.getFirtsArg(); i <= link.getSecondArg(); i++) {
			linkSet.add(i);
		}
		return linkSet;
	}
	
	public static void fillColumn(Parameter parameter,ColumnObj columnObj){
		if(parameter.existLink()){
			Integer firtsArg = parameter.getLink().getFirtsArg();
			Integer secondArg = parameter.getLink().getSecondArg();
			List<Cell> columVal = columnObj.getColumnValues();
			for (int p = firtsArg; p <= secondArg; p++) {
				columVal.get(p).setState(true);
			}			
		}
	}
	

	public static boolean parametersComparison(Parameter leftparam, Parameter rightParam,Parameter resultParam) {
		boolean existChanges = false;
		Integer initialSectorSize = null;
		if(resultParam.existLink()){
			 initialSectorSize = resultParam.getLink().getSectorSize();
		}
		Set<Integer> result = createSet(leftparam.getLink());
		result.retainAll(createSet(rightParam.getLink()));
		if (!result.isEmpty()) {
			Integer max = Collections.max(result);
			Integer min = Collections.min(result);
			resultParam.setLink(min,max);
			if(initialSectorSize ==null || initialSectorSize< resultParam.getLink().getSectorSize()){
				existChanges = true;
			}
		}
		return existChanges;
	}
}
