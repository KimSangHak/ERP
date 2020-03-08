package com.yuhannci.erp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LoginForm {
	
	@NotNull
	@Size(min=2, max=20)
	String userId;
	
	@NotNull
	@Size(min=1, max=20)
	String password;
}
