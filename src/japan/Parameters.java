package japan;

import java.util.ArrayList;
import java.util.List;

public class Parameters {
	
	private	List<Parameter> values  = new ArrayList<Parameter>();
	
	public Parameters() {
	}
	
	
	public Parameters(Parameters parametersToClone) {
		List<Parameter> valuesToClone = parametersToClone.getValues();
		for (Parameter parameter : valuesToClone) {
			this.values.add(new Parameter(parameter));
		}
	}


	public List<Parameter> getValues() {
		return values;
	}

	public void setValues(List<Parameter> values) {
		this.values = values;
	}


}

