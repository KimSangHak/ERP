package com.yuhannci.erp.model;

public enum CoatingAbortTypeEnum {
	AbortCoatingOrderOnly,	// 후처리 발주만 취소
	CoatingSkip,			// 후처리 스킵
	AsFinishedProduct		// 완품으로 처리
}
