package com.yuhannci.erp.model;

import com.yuhannci.erp.model.db.JobPartner;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class JobPartnerReg extends JobPartner {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String originalPartnerId;
	String partnerType;
	
	
}
