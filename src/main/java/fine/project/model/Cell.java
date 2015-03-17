package fine.project.model;


public class Cell extends Model{

	public Cell() {
	}

	public Cell(Boolean state) {
		this.state = state;
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
