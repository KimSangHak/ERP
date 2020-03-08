package com.yuhannci.erp.service;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yuhannci.erp.mapper.CustomerMapper;
import com.yuhannci.erp.mapper.PartnerMapper;
import com.yuhannci.erp.model.IdNameEntry;
import com.yuhannci.erp.model.JobPartnerReg;
import com.yuhannci.erp.model.BillingPartner;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.KeyValueEntry;
import com.yuhannci.erp.model.OrderTypeEnum;
import com.yuhannci.erp.model.PartnerListData;
import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.model.db.JobPartner;
import com.yuhannci.erp.model.db.PartnerBilling;

@Service
public class PartnerService {
	@Autowired
	CustomerMapper customerMapper;
	@Autowired
	PartnerMapper partnerMapper;

	final int pageSize = 20;

	public List<IdNameEntry> enumSimpleCustomerList() {
		return customerMapper.selectSimpleCustomerList();
	}

	public List<SimpleEntry> getSimpleCustomerList() {

		LinkedList<SimpleEntry> result = new LinkedList<SimpleEntry>();

		customerMapper.selectSimpleCustomerList().stream()
				.forEach(a -> result.add(new SimpleEntry<String, String>(a.getId(), a.getName())));
		return result;
	}

	/**
	 * @param isConcept
	 * @param orderType
	 * @param partnerId
	 * @return
	 */
	public String getNextSequenceNo(boolean isConcept, OrderTypeEnum orderType, String customerId) {
		return customerMapper.getNextCustomerSequenceNo(isConcept ? "Y" : "N", customerId);
	}

	// 파트너 목록 내려주는 기능
	public PartnerListData getPartnerListPage(Integer page, String orderBy, String sortOrder) {

		if (page == null || page == 0)
			page = 1;

		int totalLists = customerMapper.selectPartnerListCount();
		int totalPages = Math.floorDiv(totalLists, pageSize);
		if ((totalPages * pageSize) < totalLists)
			totalPages = totalPages + 1;
		if (page > totalPages)
			page = 1;

		int pageOffset = (page - 1) * pageSize;
		List<BillingPartner> org = customerMapper.selectPartnerList(pageOffset, pageSize, orderBy, sortOrder);

		PartnerListData data = new PartnerListData();
		data.setCurrentPageNo(page);
		data.setTotalPageCount(totalPages);
		data.setPageSize(pageSize);
		data.setData(org);
		data.setPageOffset(pageOffset);

		return data;
	}

	@Cacheable(value = "발주업체리스트")
	public List<JobPartner> getBuyPartnerList() {

		return customerMapper.selectBuyPartner(null, null);
	}
	
	public List<JobPartner> getBuyPartnerList(String category, String keyword) {

		category = StringUtils.isEmpty(category) ? null : category;
		keyword = StringUtils.isEmpty(keyword) ? null : keyword;

		return customerMapper.selectBuyPartner(category, keyword);
	}

	@Cacheable(value="업체명", key="#id")
	public String getPartnerName(String id) {
		// return customerMapper.selectBuyPartnerName(id);
		if (StringUtils.isEmpty(id))
			return null;

		JobPartner partner = partnerMapper.selectPartner(id, null);
		return partner != null ? partner.getPartnerName() : null;
	}

	public JobPartner getPartner(String id) {
		// return customerMapper.selectPartner(id);
		if (StringUtils.isEmpty(id))
			return null;

		JobPartner partner = partnerMapper.selectPartner(id, null);
		return partner;
	}
	
	public List<KeyValueEntry> getSelectPartnerList(String viewPurchase, String viewProcess, String viewAssemble, String viewFinancial, String typeCode) {

		return partnerMapper.selectPartnerSelectList(viewPurchase, viewProcess, viewAssemble, viewFinancial, typeCode);
	}
	
	
	// 후처리 업체 리스트
	@Cacheable(value="후처리업체")
	public List<JobPartner> getPostprocessParnterList(){
		
		return partnerMapper.selectPartnerList(null, null, null, "C", null);
		
	}
	@Cacheable(value="후처리업체콤보")
	public List<KeyValueEntry> getPostprocessParnterComboList(){
				
		return partnerMapper.selectPartnerList(null, null, null, "C", null).stream().map(a -> new KeyValueEntry(a.getId(), a.getPartnerName())).collect(Collectors.toList());		
	}
	
	@Cacheable(value="billingAfter")
	public List<KeyValueEntry> getBillingAfterList(){
		
		return partnerMapper.selectBillingAfter().stream().map(a -> new KeyValueEntry(a,a)).collect(Collectors.toList());
	}
	
	@Cacheable(value="billingDay")
	public List<KeyValueEntry> getBillingDayList(){
		
		return partnerMapper.selectBillingDay().stream().map(a -> new KeyValueEntry(a,a)).collect(Collectors.toList());
	}
	
	@Cacheable(value="partnerTypes")
	public List<KeyValueEntry> getPartnerTypes(){
		
		return partnerMapper.selectJobPartnerType().stream().map(a -> new KeyValueEntry(a.getTypeCode() + a.getTypeKind(), a.getTypeName())).collect(Collectors.toList());
	}

	public List<KeyValueEntry> getProcessParnterComboList(){
				
		return partnerMapper.selectPartnerComboList();
		
	}
	
	public String getPartnerId(String partnerName) {
		if (StringUtils.isEmpty(partnerName))
			return null;

		JobPartner partner = partnerMapper.selectPartner(null, partnerName);
		return partner != null ? partner.getId() : null;
	}
	
	public DataTableResponse getSimplePartnerList(Integer draw, Integer sortOrder, Integer start, Integer length) {
		
		DataTableResponse res = new DataTableResponse();
		res.setData( partnerMapper.selectSimplePartnerList(start, length, sortOrder) );
		res.setTotalRecords(partnerMapper.selectSimplePartnerCount());
		res.setDraw(draw);
		
		return res;
	}
	
	public String getNextPartnerId(String partnerTypeId) {
		if(partnerTypeId == null || partnerTypeId.length() != 2)
			throw new RuntimeException("업체 종류 ID 가 잘못되었습니다");
		
		String currentLastPartner = partnerMapper.selectLastPartnerId(partnerTypeId);
		if(currentLastPartner == null) 
			return partnerTypeId + "000";
			
		Integer val = 0;
		val = Integer.parseInt(		currentLastPartner.substring(2) ) + 1;
		
		return partnerTypeId + String.format("%03d", val);
	}
	
	void checkValidPartnerInformation(JobPartnerReg partner) {
		if(StringUtils.isEmpty(partner.getId()))
			throw new RuntimeException("ID 를 입력해 주세요");
		if(StringUtils.isEmpty(partner.getPartnerName()))
			throw new RuntimeException("거래처 이름을 입력해 주세요");
		if(StringUtils.isEmpty(partner.getPersonName()))
			throw new RuntimeException("담당자 이름을 입력해 주세요");
		if(StringUtils.isEmpty(partner.getPersonMail()))
			throw new RuntimeException("거래처 이메일 주소를 입력해 주세요");
		if(StringUtils.isEmpty(partner.getCorporateMail()))
			throw new RuntimeException("거래처 이메일 주소를 입력해 주세요");		
		if(StringUtils.isEmpty(partner.getCorporateNum()))
			throw new RuntimeException("거래처 사업자 번호를 입력해 주세요");
	}
	public void updatePartner(JobPartnerReg partner) {
		
		checkValidPartnerInformation(partner);
		
		if(partner.getOriginalPartnerId() == null) {
			if( partnerMapper.selectPartner(partner.getId(), null) != null)
				throw new RuntimeException("이미 등록된 업체코드 입니다");
			if(StringUtils.isEmpty(partner.getPartnerType()))
				throw new RuntimeException("업체 종류 정보가 없습니다");
			
			partner.setTypeCode(partner.getPartnerType().substring(0,1));
			partner.setTypeKind(partner.getPartnerType().substring(1,2));
			
			partnerMapper.insertPartnerProcess(partner);
		}
		else {
			if( StringUtils.isEmpty(partner.getOriginalPartnerId()) || !partner.getOriginalPartnerId().equals(partner.getId()) )
				throw new RuntimeException("내부 오류");
			System.out.println("#####################"+partner.getProcessEnjoy());
			partnerMapper.EditPartner(partner);		
		}		
	}
	
	public void insertCustomer(Customer customer) {
		partnerMapper.insertCustomer(customer);
	}
	
	public Customer selectCustomer(String id) {
		return partnerMapper.selectCustomer(id);
	}
	
	public void updateCustomer(Customer customer) {
		partnerMapper.updateCustomer(customer);
	}
	
}
