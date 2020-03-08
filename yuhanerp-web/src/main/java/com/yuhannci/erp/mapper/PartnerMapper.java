package com.yuhannci.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yuhannci.erp.model.KeyValueEntry;
import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.model.db.JobPartner;
import com.yuhannci.erp.model.db.JobPartnerType;
import com.yuhannci.erp.model.db.JobPartnerTypeNew;

@Mapper
public interface PartnerMapper {
    public List<JobPartner> selectPartnerList(@Param("keyword") String keyword, @Param("partnerId") String partnerId, @Param("partnerName") String partnerName, @Param("typeCode") String typeCode, @Param("typeKind") String typeKind);
    public List<JobPartner> selectPartnerList2(@Param("partnerName") String partnerName, @Param("Code") String Code);
    public List<KeyValueEntry> selectPartnerComboList();
    
    public List<KeyValueEntry> selectPartnerSelectList(@Param("viewPurchase") String viewPurchase, 
    													@Param("viewProcess") String viewProcess, 
    													@Param("viewAssemble") String viewAssemble, 
    													@Param("viewFinancial") String viewFinancial,
    													@Param("typeCode") String typeCode);
    
    // [가공일정관리] - [거래명세서 관리] 에서 사용하는 리스트
    public List<JobPartner> selectSimplePartnerList(@Param("start") Integer start, @Param("length") Integer length, @Param("sortOrder") Integer sortOrder);
    public Integer selectSimplePartnerCount();

    public JobPartner selectPartner(@Param("partnerId") String partnerId, @Param("partnerName") String partnerName);
    public void insertPartner(JobPartner JobPartner);
    public void insertPartnerProcess(JobPartner JobPartner);
    public void EditPartner(JobPartner JobPartner);
    public List<JobPartner> selectOnePartnerList(String id);
    public int autoPartnerCount(String PartnerType);
    public int jobPartnerTypeCount(JobPartnerType JobPartnerType);
    public void jobPartnerTypeCount_ins(JobPartnerType JobPartnerType);
    public void jobPartnerTypeUP(JobPartnerType JobPartnerType);
    public int jobPartnerTypeNextValue(JobPartnerType JobPartnerType);
   
    public List<String> selectBillingAfter();
    public List<String> selectBillingDay();
    public List<JobPartnerType> selectJobPartnerType();
    public String selectLastPartnerId(@Param("partnerTypeId") String partnerTypeId);
    
    public void insertCustomer(Customer customer);
    public Customer selectCustomer(String id);
    public void updateCustomer(Customer customer);
    
}
