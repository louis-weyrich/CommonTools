package com.sos.tools.ui.utilities;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public abstract class MoveableObject implements Moveable, Captureable {
	
	private Point location;
	private int gravity;
	private int orientation;
	private int angle;
	private int directionX = 1;
	private int directionY = 1;
	private List <MoveableObject> captures;
	private GridMatrix matrix;
	private GridCell gridCell;


	public MoveableObject(Point location, int orientation, int angle, int directionX, int directionY) {
		captures = new ArrayList <MoveableObject> ();
		this.location = location;
		this.orientation = orientation;
		this.angle = angle;
		this.directionX = directionX;
		this.directionY = directionY;
	}

	@Override
	public int getGravity() {
		return gravity;
	}

	public void capture(MoveableObject moveable) {
		if(!captures.contains(moveable)) {
			captures.add(moveable);
		}
	}
	
	public boolean removeCapture(MoveableObject moveable) {
		if(captures.contains(moveable)){
			return captures.remove(moveable);
		}
		return false;
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public int getAngle() {
		return angle;
	}

	@Override
	public int getOrientation() {
		return orientation;
	}

	@Override
	public int getDirectionX() {
		return directionX;
	}

	@Override
	public int getDirectionY() {
		return directionY;
	}
	
	public void addGridAndCell(GridMatrix matrix, GridCell cell) {
		this.gridCell = cell;
		this.matrix = matrix;
	}

}
