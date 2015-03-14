package japan;

public class Parameter {
	
	private int val;
	private Link link;

	public Parameter(Parameter objToClone) {

		this.val = objToClone.val;
		this.link = objToClone.link;
	}

	public Parameter(int val) {
		this.val = val;
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
