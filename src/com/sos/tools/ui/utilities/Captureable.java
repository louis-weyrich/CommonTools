package com.sos.tools.ui.utilities;

public interface Captureable {
	public int getGravity();
	public void captured();
	public void capture(MoveableObject moveable);
	public boolean removeCapture(MoveableObject moveable);
	public boolean canCapture();
	public boolean isStable();
}
