package com.yuhannci.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.PartnerListData;
import com.yuhannci.erp.model.db.Customer;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.PartnerService;

@Controller
public class PartnerController {

	@Autowired PartnerService partnerService;
	@Autowired MenuService menuService;
	@Autowired LoginDongleService loginDongleService;
	
	@RequestMapping("/partner")
	public ModelAndView PartnerManage(Integer pageNo, String orderBy, String sortOrder){
		
		String isupdate = menuService.updateYN(loginDongleService.getLoginId(), "10", "03", "01");
		
		ModelAndView mv = new ModelAndView("partner/list");
		mv.addObject("data", partnerService.getPartnerListPage(pageNo, orderBy, sortOrder));		
		mv.addObject("orderBy", orderBy);
		mv.addObject("sortOrder", sortOrder);
		mv.addObject("isupdate", isupdate);	
		return mv;
	}
	
	@RequestMapping("/partner/popup/new")
	public ModelAndView PopupNewPartner(){
		ModelAndView mv = new ModelAndView("partner/popup_new");
		
		return mv;
	}
	
	@RequestMapping("/partner/popup/register")
	public String PopupNewPartnerRegister(@RequestParam("id")String id,
										  @RequestParam("name")String name,
										  @RequestParam("region")String region){
		
		Customer customer = new Customer();
		
		customer.setId(id);
		customer.setName(name);
		customer.setRegion(region);
		
		partnerService.insertCustomer(customer);
		
		return "redirect:/partner";
	}
	
	
	@RequestMapping("/partner/popup/edit/{partnerId}")
	public ModelAndView PopupEditPartner(@PathVariable("partnerId") String partnerId){
		
		Customer data = partnerService.selectCustomer(partnerId);
		
		ModelAndView mv = new ModelAndView("partner/popup_edit");
		mv.addObject("data", data);	
		
		return mv;
	}
	
	@RequestMapping("/partner/popup/editDo")
	public String PopupNewPartnereditDo(@RequestParam("id")String id,
										  @RequestParam("name")String name,
										  @RequestParam("region")String region){
		
		Customer customer = new Customer();
		
		customer.setId(id);
		customer.setName(name);
		customer.setRegion(region);
		
		partnerService.updateCustomer(customer);
		
		return "redirect:/partner";
	}
	
}
