package fine.project;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Parameters {

	private List<Parameter> values = new ArrayList<Parameter>();

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

	public void addParameter(int val) {
		values.add(new Parameter(val));
	}

	public void addParameter(int val, int firstInd, int secondInd) {
		values.add(new Parameter(val, new Link(firstInd, secondInd)));
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
