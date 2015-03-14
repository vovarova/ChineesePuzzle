package japan.algorythms;

import java.util.List;

import japan.Cell;
import japan.ColumObj;
import japan.Link;
import japan.Parameter;
import japan.Parameters;

public class CornersAlgorythm implements Algorythm {

	@Override
	public void calculate(ColumObj columObj) {

		Parameters parameters = columObj.getParameters();
		if (parameters.getValues().isEmpty()) {
			return;
		}
		Parameters left = new Parameters(parameters);
		List<Parameter> leftValues = left.getValues();
		int currentIndex=0;
		for (Parameter leftparameter : leftValues) {
			int leftParamVal = leftparameter.getVal();
			
			List<Cell> columVal = columObj.getColumVal();
			for (int i=0;i<columVal.size();i++) {
				boolean cond = true;
				for (int k=0;k<=leftParamVal;k++) {
					Cell cell = columVal.get(i+k);
					if(cell!=null && cell.getState()!=null){
						cond = false;
						break;
					}
				}
				if (cond){
					leftparameter.setLink(new Link(i,i+leftparameter.getVal()-1));
				
					break;
				}
				
				
				
			}
		}
		
		
		
		
		Parameters right = new Parameters(parameters);
		
		
		
	}
	
	


}
