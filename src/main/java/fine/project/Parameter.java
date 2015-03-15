package fine.project;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Parameter {
	
	private int val;
	private Link link;

	public Parameter(Parameter objToClone) {

		this.val = objToClone.val;
		if(objToClone.getLink()!=null){			
			this.link = new Link(objToClone.getLink());
		}
	}

	public Parameter(int val) {
		this.val = val;
	}

	public Parameter(int val, Link link) {
		this.val = val;
		this.link = link;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

}
