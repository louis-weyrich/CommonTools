package com.sos.tools.ui.utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GridCell {
	
	private List <MoveableObject> objectContainer;
	private CellReference cellReference;
	private GridMatrix matrix;

	public GridCell(CellReference cellReference, GridMatrix matrix) {
		this.objectContainer = new ArrayList <MoveableObject> ();
		this.cellReference = cellReference;
		this.matrix = matrix;
	}
	
	public List <MoveableObject> getObjectContainer(){
		return this.objectContainer;
	}
	
	public void moveObjects() {
		Iterator <MoveableObject> iterator = this.objectContainer.iterator();
		while(iterator.hasNext()) {
			MoveableObject mo = iterator.next();
			
			// calculate how to move
			
			
		}
		
	}

}
