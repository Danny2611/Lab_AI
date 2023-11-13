package lab6;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void move() {
		if (this.row < Node.N - 1) {
	        this.row++; // di chuyen len 1 hang
	    } else {
	        this.row = 0; 
	    }
	}

	public boolean isConflict(Queen q) {
		// check cung hang or cot
		if (this.row == q.row || this.column == q.column) {
	        return true;
	    }
	
		 if (Math.abs(this.row - q.row) == Math.abs(this.column - q.column)) {
		        return true;
		    }
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}
