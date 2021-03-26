public class Cell {
	private boolean picked;
	private String content;
	private Boolean finalStatus;

	public Cell() {
		this.picked = false;
		this.content = null;
		this.finalStatus = false;
	}

	public void setFinalStatus(Boolean finalStatus) { // what is the final status of the cell: true - finally picked
		this.finalStatus = finalStatus;
	}

	public Boolean getFinalStatus() {
		return finalStatus;
	}

	public void setContent(String c) {
		if (c != null && !c.equals(""))
			this.picked = true;
		this.content = c;
	}

	public String getContent() {
		return this.content;
	}

	public void pick(String mark) {
		this.picked = true;
		this.setContent(mark);
	}

	public boolean isPicked() {
		return this.picked;
	}

	public String toString() {
		return this.content;
	}
}
