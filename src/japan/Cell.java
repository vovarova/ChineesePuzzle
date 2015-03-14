package japan;

public class Cell {

	public Cell() {
	}

	public Cell(Cell objToClone) {
		this.state = objToClone.state;
	}

	private Boolean state;

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

}
