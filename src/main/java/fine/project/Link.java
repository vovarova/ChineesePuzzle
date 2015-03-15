package fine.project;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Link {
	private Integer firtsArg;
	private Integer secondArg;

	public Link(Link link) {
		this.firtsArg = link.firtsArg;
		this.secondArg = link.secondArg;
	}

	public Link(Integer firtsArg, Integer secondArg) {
		this.firtsArg = firtsArg;
		this.secondArg = secondArg;
	}

	public int getSectorSize(){
		return secondArg - firtsArg +1 ;
	}
	
	public void incrementSecond() {
		secondArg++;
	}

	public void decrementFirst() {
		firtsArg--;
	}

	public Integer getSecondArg() {
		return secondArg;
	}

	public void setSecondArg(Integer secondArg) {
		this.secondArg = secondArg;
	}

	public Integer getFirtsArg() {
		return firtsArg;
	}

	public void setFirtsArg(Integer firtsArg) {
		this.firtsArg = firtsArg;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public boolean isDone(int val) {
		return getSectorSize()==val;
	}
}
