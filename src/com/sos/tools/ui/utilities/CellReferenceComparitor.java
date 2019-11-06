package com.sos.tools.ui.utilities;

import java.util.Comparator;

public class CellReferenceComparitor<T extends CellReference> implements Comparator<T> {

	public CellReferenceComparitor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(CellReference cf1, CellReference cf2) {
		if(cf1.getRow() == cf2.getRow() && cf1.getColumn() == cf2.getColumn()) {
			return 0;
		}
		else if(cf1.getRow() == cf2.getRow()) {
			if(cf1.getColumn() < cf2.getColumn()) {
				return -1;
			}
			else {
				return 1;
			}
		}
		else if(cf1.getRow() < cf2.getRow()) {
			return -1;
		}
		else if(cf1.getRow() > cf2.getRow()) {
			return 1;
		}
		
		return 0;
	}

}
