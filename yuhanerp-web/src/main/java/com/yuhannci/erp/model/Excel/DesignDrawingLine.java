package com.yuhannci.erp.model.Excel;

import lombok.Data;

@Data
public class DesignDrawingLine {

	String drawingNo;			// NO
	String description;			// DESCRIPTION
	String dimensions;			// DIMENSIONS
	String material;			// MAT'L
	String heatTreat;			// HEAT-TREAT (HRC)
	String quantity;			// Q'TY 1SET
	String spare;				// spare
	String appliedFinish;		// applied finish
	String qc;					// Q.C
	String note;				// 비고
}
