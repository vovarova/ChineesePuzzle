package fine.project.model;


public class Parameter extends Model {

	private int val;
	private Link link;
	private int columnSize;

	public Parameter(Parameter objToClone) {
		this.columnSize = objToClone.columnSize;
		this.val = objToClone.val;
		if (objToClone.getLink() != null) {
			this.link = new Link(objToClone.getLink());
		}
	}

	public Parameter(int val,int columnSize) {
		this.val = val;
		this.columnSize = columnSize;
	}

	public Parameter(int val,int columnSize,int firstLinkArg,int secondLinkArg) {
		this.val = val;
		this.columnSize = columnSize;
		link = new Link(firstLinkArg, secondLinkArg);
	}
	public Parameter reverseParameter(){
		if(link!=null){
			link.reverseLink();
		}
		return this;
	}
	public boolean existLink(){
		return link!=null;
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

	public void setLink(int firtsArg,int secondArg) {
		if(link==null){			
			link = new Link(firtsArg, secondArg);
		}else{
			link.setFirtsArg(firtsArg);
			link.setSecondArg(firtsArg);
		}
	}
	public void setLink(Link link) {
		setLink(link.firtsArg, link.secondArg);
	}
	
	
	public class Link extends Model {
		private int firtsArg;
		private int secondArg;

		public Link(Link link) {
			this.firtsArg = link.firtsArg;
			this.secondArg = link.secondArg;
		}

		public Link(int firtsArg, int secondArg) {
			this.firtsArg = firtsArg;
			this.secondArg = secondArg;
		}

		public int getSectorSize() {
			return secondArg - firtsArg + 1;
		}

		public void incrementSecond() {
			secondArg++;
		}

		public void decrementFirst() {
			firtsArg--;
		}

		public int getSecondArg() {
			return secondArg;
		}

		public void setSecondArg(int secondArg) {
			this.secondArg = secondArg;
		}

		public int getFirtsArg() {
			return firtsArg;
		}

		public void setFirtsArg(int firtsArg) {
			this.firtsArg = firtsArg;
		}

		public boolean isDone() {
			return getSectorSize() == val;
		}
		private void reverseLink(){
			int firtsArgSaved = firtsArg;
			int secondArgSaved = secondArg;
			firtsArg = columnSize - secondArgSaved - 1;
			secondArg = columnSize - firtsArgSaved - 1;
		}
		public int getValuesLeft(){
			return val-getSectorSize();
		}
	}
	
	public Parameter getClone(){
		return new Parameter(this);
	}
}
