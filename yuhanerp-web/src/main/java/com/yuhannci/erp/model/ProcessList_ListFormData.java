package com.yuhannci.erp.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProcessList_ListFormData {
	@NotNull
	Long designDrawingId;
	@NotNull
	String requestedAction;
	
	String workPosition;
	String outsourcingPartnerId;
	String finishDate;
	String coatingDate;
	String inspectDate;
	String cancelReason;
}
