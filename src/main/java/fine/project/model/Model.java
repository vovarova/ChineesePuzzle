package fine.project.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class Model {
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SIMPLE_STYLE);
	};
}
