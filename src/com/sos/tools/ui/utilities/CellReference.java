package com.sos.tools.ui.utilities;

public class CellReference {
	
	private int row, column;

	public CellReference(int row, int column) {
		this.row = row;
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}
