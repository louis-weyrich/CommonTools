package com.sos.tools.ui.utilities;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public interface Moveable {
	public Point getLocation();
	public Dimension getSize();
	public int getSpeed();
	public int getAngle();
	public int getOrientation();
	public int getDirectionX();
	public int getDirectionY();
	public void pause();
	public void resume();
	public Point move();
	public void draw(Graphics g);
}
