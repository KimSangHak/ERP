package com.yuhannci.erp.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.MenuDataPK;
import com.yuhannci.erp.model.AllMenuDatains;
import com.yuhannci.erp.model.DataTableResponse;
import com.yuhannci.erp.model.GeneralSearchForm;
import com.yuhannci.erp.model.GeneralWorkSearch;
import com.yuhannci.erp.model.GeneralVacationSearch;
import com.yuhannci.erp.model.MappingTitle;
import com.yuhannci.erp.model.NameDept;
import com.yuhannci.erp.model.PidbackSearchForm;
import com.yuhannci.erp.model.PositionId;
import com.yuhannci.erp.model.UserInsert;
import com.yuhannci.erp.model.UserUpdate;
import com.yuhannci.erp.model.db.AdminUser;
import com.yuhannci.erp.model.db.DeptData;
import com.yuhannci.erp.model.db.JobPurchase;
import com.yuhannci.erp.model.db.MenuData;
import com.yuhannci.erp.model.db.User;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.MyMenuService;
import com.yuhannci.erp.service.UserService;
import com.yuhannci.erp.service.AdminService;
import com.yuhannci.erp.service.GeneralService;

import ch.qos.logback.core.net.SyslogOutputStream;



import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/general")
public class GeneralController {
	
	
	@Autowired UserService userService;
	@Autowired LoginDongleService loginDongleService;
	@Autowired MyMenuService myMenuService;
	@Autowired GeneralService generalService;
	@Autowired AdminService AdminService;
	
	
	@RequestMapping("/main")
	public ModelAndView generalMain(String usrName, String deptCode, Date DateBegin, String isOut) {
		
		
		List<DeptData> deptData = generalService.getGeneralDeptData();
		ModelAndView mv = new ModelAndView("general/main");
		
		mv.addObject("deptData", deptData);
		
		return mv;
	}
	
	@RequestMapping("/data/main")
	@ResponseBody
	public DataTableResponse generalMainData(GeneralSearchForm form) {
		
		
		
		DataTableResponse res = new DataTableResponse();
		
		try {
			
			System.out.println("여기 찍힘!?!?!?!??!?!?!?!?!??!??!");
			
			
			res = generalService.getGeneralEntry(form);
		
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		
		
		return res;
		
	}
	
	//usr 수정 팝업
	@RequestMapping("/popup/generalUsrEdit")
	public ModelAndView generalEditUser(@RequestParam("id") String id) {

		List<User> user = AdminService.AllselectAdminUser(id);
		List<NameDept> nameDept = AdminService.selectAllDeptName();
		List<PositionId> positionId = AdminService.selectAllPositionName();
			
		ModelAndView mv = new ModelAndView("general/popup/userGeneral");
		mv.addObject("user", user);
		mv.addObject("nameDept", nameDept);
		mv.addObject("positionId", positionId);
			
		return mv;
	}
	
	//usr 수정 동작
	@RequestMapping("/popup/generalUsrEditAction")
	public String editUserAction(@RequestParam("id")String id, 
								 @RequestParam("name")String name,
								 @RequestParam("engName")String engName,
								 @RequestParam("deptName")String deptName,
								 @RequestParam("position")String position,
								 @RequestParam("email")String email,
								 @RequestParam("kakaoId")String kakaoId,
								 @RequestParam("handPhone")String handPhone,
								 @RequestParam("inPhone")String inPhone,
								 @RequestParam("homeAddress")String homeAddress,
								 @RequestParam("passportExpireDate")String passportExpireDateS
								 )throws Exception {
		
		if(passportExpireDateS.isEmpty() || passportExpireDateS.equals("") || passportExpireDateS.equals(" ")) {
			
			String deptCode = AdminService.selectOneDeptCode(deptName);
			
			UserUpdate userUpdate = new UserUpdate();
			
			
			userUpdate.setId(id);
			userUpdate.setName(name);
			userUpdate.setEngName(engName);
			userUpdate.setDeptCode(deptCode);
			userUpdate.setPosition(position);
			userUpdate.setEmail(email);
			userUpdate.setKakaoId(kakaoId);
			userUpdate.setHandPhone(handPhone);
			userUpdate.setInPhone(inPhone);
			userUpdate.setHomeAddress(homeAddress);
			userUpdate.setPassportExpireDate(null);
		
		
			AdminService.updateUserForGeneral(userUpdate);
			
		}
		
		else {
		
			Date passportExpireDate = new SimpleDateFormat("yyyy-MM-dd").parse(passportExpireDateS);
			
			String deptCode = AdminService.selectOneDeptCode(deptName);
		
		
			UserUpdate userUpdate = new UserUpdate();
		
			userUpdate.setId(id);
			userUpdate.setName(name);
			userUpdate.setEngName(engName);
			userUpdate.setDeptCode(deptCode);
			userUpdate.setPosition(position);
			userUpdate.setEmail(email);
			userUpdate.setKakaoId(kakaoId);
			userUpdate.setHandPhone(handPhone);
			userUpdate.setInPhone(inPhone);
			userUpdate.setHomeAddress(homeAddress);
			userUpdate.setPassportExpireDate(passportExpireDate);
	
			AdminService.updateUserForGeneral(userUpdate);
		
		}
		
		return "redirect:/general/main";
	}
	
	//usr 퇴사처리
	@RequestMapping("/popup/generalDeleteUser")
	public String deleteUser(@RequestParam("id")String id) {
		
		AdminService.userDelete(id);
		
		return "redirect:/general/main";
	}
	

	@RequestMapping("/vacation")
	public ModelAndView generalVacation(String usrName, String deptCode) {
		
		
		List<DeptData> deptData = generalService.getGeneralDeptData();
		ModelAndView mv = new ModelAndView("general/vacationMain");
		
		mv.addObject("deptData", deptData);
		
		return mv;
	}
	
	@RequestMapping("/data/vacation")
	@ResponseBody
	public DataTableResponse generalVacation(GeneralVacationSearch form) {
		
		
		
		DataTableResponse res = new DataTableResponse();
		
		try {
			
			System.out.println("여기 찍힘!?!?!?!??!?!?!?!?!??!??!");
			
			
			res = generalService.getVacationEntry(form);
		
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		
		
		return res;
		
	}
	
	@RequestMapping("/work")
	public ModelAndView generalWork(String usrName, String deptCode, Date DateBegin) {
		
		
		List<DeptData> deptData = generalService.getGeneralDeptData();
		ModelAndView mv = new ModelAndView("general/workMain");
		
		mv.addObject("deptData", deptData);
		
		return mv;
	}
	
	@RequestMapping("/data/work")
	@ResponseBody
	public DataTableResponse generalWorkData(GeneralWorkSearch form) {
		
		
		
		DataTableResponse res = new DataTableResponse();
		
		try {
			
			System.out.println("여기 찍힘!?!?!?!??!?!?!?!?!??!??!");
			
			if(myMenuService.isManagerRoundrobin(loginDongleService.getLoginId()).equals("Y")) {
				res = generalService.getGeneralWorkEntryY(form);
				
			}else {
				
				res = generalService.getGeneralWorkEntry(form);
			}

		
			
		}catch(RuntimeException e) {
			res.setErrorResponse(e);
			e.printStackTrace();
		}catch(Exception e) {
			res.setErrorResponse();
			e.printStackTrace();
		}
		
		
		return res;
		
	}
	
	

}
