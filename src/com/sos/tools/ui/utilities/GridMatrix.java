package com.sos.tools.ui.utilities;

import java.awt.Point;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeMap;

public class GridMatrix {

	private TreeMap <CellReference, GridCell>  gridCells;
	private int totalCount;
	private int rows;
	private int columns;
	private int totalHeight;
	private int totalWidth;
	private int gridWidth;
	private int gridHeight;

		
	public GridMatrix(int rows, int columns, int totalWidth, int totalHeight) {
		this.rows = rows;
		this.columns = columns;
		this.totalWidth = totalWidth;
		this.totalHeight = totalHeight;
		this.gridWidth = columns/totalWidth;
		this.gridHeight = rows/totalHeight;
		
		gridCells = new TreeMap <CellReference, GridCell> (new CellReferenceComparitor<CellReference>());
		
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < columns; c++) {
				CellReference cellRef = new CellReference(r,c);
				GridCell cell = new GridCell (cellRef, this);
				gridCells.put(cellRef, cell);
			}
		}
	}
	
	
	public void addToGrid(MoveableObject moveable) {
		Point point = moveable.getLocation();
		
		int row = (int)point.x/(totalWidth/columns);
		int column = (int)point.y/(totalHeight/rows);
		
		CellReference cellRef = new CellReference(row,column);
		GridCell cell = this.gridCells.get(cellRef);
		cell.getObjectContainer().add(moveable);
	}
	
	public void moveObjects() {
		NavigableSet <CellReference> cells = this.gridCells.descendingKeySet();
		Iterator <CellReference> iterator = cells.iterator();
		while(iterator.hasNext()) {
			CellReference ref = iterator.next();
			GridCell cell = this.gridCells.get(ref);
			cell.moveObjects();
		}
	}


	public TreeMap<CellReference, GridCell> getGridCells() {
		return gridCells;
	}


	public void setGridCells(TreeMap<CellReference, GridCell> gridCells) {
		this.gridCells = gridCells;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public int getColumns() {
		return columns;
	}


	public void setColumns(int columns) {
		this.columns = columns;
	}


	public int getTotalHeight() {
		return totalHeight;
	}


	public void setTotalHeight(int totalHeight) {
		this.totalHeight = totalHeight;
	}


	public int getTotalWidth() {
		return totalWidth;
	}


	public void setTotalWidth(int totalWidth) {
		this.totalWidth = totalWidth;
	}


	public int getGridWidth() {
		return gridWidth;
	}


	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}


	public int getGridHeight() {
		return gridHeight;
	}


	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}

}
