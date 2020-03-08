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
import org.springframework.web.servlet.ModelAndView;

import com.yuhannci.erp.model.MenuDataPK;
import com.yuhannci.erp.model.AllMenuDatains;
import com.yuhannci.erp.model.MappingTitle;
import com.yuhannci.erp.model.NameDept;
import com.yuhannci.erp.model.PositionId;
import com.yuhannci.erp.model.UserInsert;
import com.yuhannci.erp.model.UserUpdate;
import com.yuhannci.erp.model.db.AdminUser;
import com.yuhannci.erp.model.db.JobPurchase;
import com.yuhannci.erp.model.db.MenuData;
import com.yuhannci.erp.model.db.User;
import com.yuhannci.erp.service.LoginDongleService;
import com.yuhannci.erp.service.MenuService;
import com.yuhannci.erp.service.AdminService;

import ch.qos.logback.core.net.SyslogOutputStream;



import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/Admin")
public class AdminController {
	@Autowired LoginDongleService loginDongleService;
	@Autowired MenuService menuService;
	@Autowired AdminService AdminService;
	
	@RequestMapping("/Main")
	public ModelAndView AdminMain(){
		
		List<AdminUser> AdminUser = AdminService.selectAdminUser();
		ModelAndView mv = new ModelAndView("admin/adminPage");
		mv.addObject("user", AdminUser);
	
		return mv;
	}
	
	
	// user 수정 팝업
	@RequestMapping("/popup/userEdit/{id}")
	public ModelAndView editUser(@PathVariable("id") String id) {

		List<User> user = AdminService.AllselectAdminUser(id);
		List<NameDept> nameDept = AdminService.selectAllDeptName();
		List<PositionId> positionId = AdminService.selectAllPositionName();
		
		ModelAndView mv = new ModelAndView("admin/popup/userEdit");
		mv.addObject("user", user);
		mv.addObject("nameDept", nameDept);
		mv.addObject("positionId", positionId);
		
		return mv;
	}
	
	// user 권한 수정 팝업
	@RequestMapping("/popup/userAuthorityEdit/{id}")
	public ModelAndView editUserAuthority(@PathVariable("id") String id) {

		Integer pageNo=0; 
		List<MenuData> Menu = AdminService.selectAllMenu();
		List<MappingTitle> titleVisible = AdminService.selectUserMappingTitleVisible(id);
		List<MappingTitle> titleUpdate = AdminService.selectUserMappingTitleUpdate(id);
		
		
		ModelAndView mv = new ModelAndView("admin/popup/userAuthorityEdit");
		mv.addObject("Menu", Menu);
		mv.addObject("titleVisible", titleVisible);
		mv.addObject("titleUpdate", titleUpdate);
		mv.addObject("userId", id);
		mv.addObject("pageNo", pageNo);
		
		return mv;
	}
	
	
	public int getCharNumber(String str, char c) {
		 int count = 0;
	        for(int i=0;i<str.length();i++)
	        {
	            if(str.charAt(i) == c)
	                count++;
	        }
	        return count;
	}
	
	// user 권한 수정 동작
	@RequestMapping("/popup/userAuthorityEditAction")
	public String editUserAuthorityAction(@RequestParam("chks")String[] chks, 
										  @RequestParam("visibles")String[] visibles,
										  @RequestParam(value="updates", required = false)String[] updates,
										  @RequestParam("userId")String userId) {
		
		AdminService.updateUserMappingN(userId);
		
		
		
		for(int i=0;i<chks.length;i++) {
			
			
			String[] pks = chks[i].split("a",3);
			
			
			
			for(int b=0;b<3;b++) {
				String highCode = pks[0].replace("a","");
				String lowCode = pks[1].replace("a","");
				String listCode = pks[2].replace("a","");
				
				
				
				if(b==0) {
					
					AdminService.updateUserMappingHigh(highCode, userId);
				}
				if(b==1) {
					
					AdminService.updateUserMappingLow(highCode, lowCode, userId);
				}
				if(b==2) {
					
					AdminService.updateUserMapping(highCode, lowCode, listCode, userId);
				}
				
			}
	
			/*
			MenuDataPK MenuDataPK = AdminService.selectMenuPk(chks[i]);
			
			String highCode = MenuDataPK.getHighCode();
			String lowCode = MenuDataPK.getLowCode();
			String listCode = MenuDataPK.getListCode();
			
	
			AdminService.updateUserMapping(highCode, lowCode, listCode, userId);
			
			AdminService.updateUserMapping_Low(highCode, lowCode, userId);
		
			AdminService.updateUserMapping_High(highCode, userId);
		
			*/
		}
		
		if(updates != null) {
			
			
			for(int i=0;i<updates.length;i++) {
				
				String[] pks = updates[i].split("a",3);
				
				
				
				for(int b=0;b<3;b++) {
					String highCode = pks[0].replace("a","");
					String lowCode = pks[1].replace("a","");
					String listCode = pks[2].replace("a","");
					
					if(b==0) {
						AdminService.updateUserMappingUPHigh(highCode, userId);
					}
					if(b==1) {
						AdminService.updateUserMappingUPLow(highCode, lowCode, userId);
					}
					if(b==2) {
						AdminService.updateUserMappingUP(highCode, lowCode, listCode, userId);
					}
					
				}
				
				/*
				
				MenuDataPK MenuDataPK = AdminService.selectMenuPk(updates[i]);
				
				String highCode = MenuDataPK.getHighCode();
				String lowCode = MenuDataPK.getLowCode();
				String listCode = MenuDataPK.getListCode();
				
				
				
				
				AdminService.updateUserMappingUP(highCode, lowCode, listCode, userId);
				
				AdminService.updateUserMappingUP_Low(highCode, lowCode, userId);
				
				AdminService.updateUserMappingUP_High(highCode, userId);
				*/
				
			}
		}

			
		return "redirect:/Admin/Main";
	}
	
	// user 정보 수정 동작
	@RequestMapping("/popup/userEditAction")
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
								 @RequestParam("isAdmin")String isAdmin,
								 
								 @RequestParam("isQc")String isQc,
								 @RequestParam("isPgm")String isPgm,
								 @RequestParam("isRobot")String isRobot,
								 
								 @RequestParam("passportExpireDate")String passportExpireDateS,
								 @RequestParam("password")String password
								 )throws Exception {
		
		if(passportExpireDateS.isEmpty() || passportExpireDateS.equals("") || passportExpireDateS.equals(" ")) {
			
			String deptCode = AdminService.selectOneDeptCode(deptName);
			
			UserUpdate userUpdate = new UserUpdate();
			
			
			userUpdate.setId(id);
			userUpdate.setPassword(password);
			userUpdate.setName(name);
			userUpdate.setEngName(engName);
			userUpdate.setDeptCode(deptCode);
			userUpdate.setPosition(position);
			userUpdate.setEmail(email);
			userUpdate.setKakaoId(kakaoId);
			userUpdate.setHandPhone(handPhone);
			userUpdate.setInPhone(inPhone);
			userUpdate.setHomeAddress(homeAddress);
			userUpdate.setIsAdmin(isAdmin);
			userUpdate.setIsQc(isQc);
			userUpdate.setIsPgm(isPgm);
			userUpdate.setIsRobot(isRobot);
			userUpdate.setPassportExpireDate(null);
		
		
			AdminService.updateUser(userUpdate);
			
		}
		
		else {
		
			Date passportExpireDate = new SimpleDateFormat("yyyy-MM-dd").parse(passportExpireDateS);
			
			String deptCode = AdminService.selectOneDeptCode(deptName);
		
		
			UserUpdate userUpdate = new UserUpdate();
		
			userUpdate.setId(id);
			userUpdate.setPassword(password);
			userUpdate.setName(name);
			userUpdate.setEngName(engName);
			userUpdate.setDeptCode(deptCode);
			userUpdate.setPosition(position);
			userUpdate.setEmail(email);
			userUpdate.setKakaoId(kakaoId);
			userUpdate.setHandPhone(handPhone);
			userUpdate.setInPhone(inPhone);
			userUpdate.setHomeAddress(homeAddress);
			userUpdate.setIsAdmin(isAdmin);
			userUpdate.setIsQc(isQc);
			userUpdate.setIsPgm(isPgm);
			userUpdate.setIsRobot(isRobot);
			userUpdate.setPassportExpireDate(passportExpireDate);
		
		
			AdminService.updateUser(userUpdate);
		
		}
		
		return "redirect:/Admin/Main";
	}
	
	// user 추가 팝업
	@RequestMapping("/popup/userIns")
	public ModelAndView userIns() {

		List<NameDept> nameDept = AdminService.selectAllDeptName();
		List<PositionId> positionId = AdminService.selectAllPositionName();
		int viewCount = AdminService.selectViewIndexCount()+1;
		
		
		ModelAndView mv = new ModelAndView("admin/popup/userIns");
		mv.addObject("nameDept", nameDept);
		mv.addObject("positionId", positionId);
		mv.addObject("viewCount", viewCount);
	
			
		return mv;
	}
	
	// user 추가 동작
	@RequestMapping("/popup/userInsAction")
	public String insUserAction(@RequestParam("id")String id,
			                     @RequestParam("password")String password,
							     @RequestParam("name")String name,
							     @RequestParam("engName")String engName,
							     @RequestParam("email")String email,
								 @RequestParam("kakaoId")String kakaoId,
								 @RequestParam("deptName")String deptName,
								 @RequestParam("position")String position,
								 @RequestParam("inPhone")String inPhone,
								 @RequestParam("directPhone")String directPhone,
								 @RequestParam("handPhone")String handPhone,
								 @RequestParam("homeAddress")String homeAddress,
								 @RequestParam("viewIndex")int viewIndex,
								 @RequestParam("isAdmin")String isAdmin,
								 
								 @RequestParam("isQc")String isQc,
								 @RequestParam("isPgm")String isPgm,
								 @RequestParam("isRobot")String isRobot,
								 
								 @RequestParam("homePhone")String homePhone,
								 @RequestParam("passportExpireDate")String passportExpireDateS,
								 @RequestParam("mctPasswd")String mctPasswd
								 )throws Exception {
		
		
		String deptCode = AdminService.selectOneDeptCode(deptName);
		
		
		UserInsert userInsert = new UserInsert();
		
		userInsert.setId(id);
		userInsert.setPassword(password);
		userInsert.setName(name);
		userInsert.setEngName(engName);
		userInsert.setEmail(email);
		userInsert.setKakaoId(kakaoId);
		userInsert.setDeptCode(deptCode);
		userInsert.setPosition(position);
		userInsert.setInPhone(inPhone);
		userInsert.setDirectPhone(directPhone);
		userInsert.setHandPhone(handPhone);
		userInsert.setHomeAddress(homeAddress);
		userInsert.setViewIndex(viewIndex);
		userInsert.setIsAdmin(isAdmin);
		userInsert.setIsQc(isQc);
		userInsert.setIsPgm(isPgm);
		userInsert.setIsRobot(isRobot);
		if(homePhone.isEmpty() || homePhone.equals("") || homePhone.equals(" ")) {
			userInsert.setHomePhone(null);
		}else {
			userInsert.setHomePhone(homePhone);
		}
		if(passportExpireDateS.isEmpty() || passportExpireDateS.equals("") || passportExpireDateS.equals(" ")) {
			userInsert.setPassportExpireDate(null);
		}else {
			Date passportExpireDate = new SimpleDateFormat("yyyy-MM-dd").parse(passportExpireDateS);
			userInsert.setPassportExpireDate(passportExpireDate);
		}
		if(mctPasswd.isEmpty() || mctPasswd.equals("") || mctPasswd.equals(" ")) {
			userInsert.setMctPasswd(null);
		}else {
			userInsert.setMctPasswd(mctPasswd);
		}
		
		AdminService.insertUser(userInsert);
		
		
		
		AdminService.insUserMappingAll(id);
		
			
		return "redirect:/Admin/Main";
	}
	
	
	// user 퇴사처리
	@RequestMapping("/popup/deleteUser")
	public String deleteUser(@RequestParam("id")String id) {
			
		AdminService.userDelete(id);
		
		return "redirect:/Admin/Main";
	}
}
