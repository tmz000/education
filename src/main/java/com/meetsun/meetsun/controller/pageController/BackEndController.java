package com.meetsun.meetsun.controller.pageController;
 
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
 
 
@Controller
public class BackEndController{
	
	//后端登录
	@RequestMapping({"/"})
	public String login() { 
		return "back-end/login"; 
	}
	@RequestMapping({"/reset"})
	public String resetPass() { 
		return "back-end/reset"; 
	}
	//退出系统
	@RequestMapping({"/outLogin"})
	public String outLogin(HttpSession session, SessionStatus sessionStatus) {
		session.invalidate();
	    sessionStatus.setComplete();
	    return "redirect:/";
	}
	//index
	@RequestMapping({"/index"})
	public String index() {
		return "back-end/index"; 
	}
	//主页
	@RequestMapping({"/home"})
	public String home() { 
		return "back-end/home"; 
	}
	//用户管理页面
	@RequestMapping({"/msUser"})
	public String msUser() { 
		return "back-end/system/msUser"; 
	}
	//角色管理页面
	@RequestMapping({"/role"})
	public String role() { 
		return "back-end/system/role"; 
	}
	//角色权限页面
	@RequestMapping({"/roleMenu"})
	public String roleMenu() { 
		return "back-end/system/role-menu"; 
	}
	//权限方法页面
	@RequestMapping({"/menuQx"})
	public String aMenuQx() { 
		return "back-end/system/menu-qx"; 
	}
	//菜单（权限）页面
	@RequestMapping({"/menu"})
	public String menu() { 
		return "back-end/system/menu"; 
	}
	//日志页面
	@RequestMapping({"/logInfo"})
	public String logInfo() { 
		return "back-end/system/log"; 
	}
	//用户详情页面
	@RequestMapping({"/oneDetail"})
	public String msUserOne() { 
		return "back-end/system/oneDetail"; 
	}
	//收支概览
	@RequestMapping({"/incomePay"})
	public String incomePay() { 
		return "back-end/incomePay/income-pay"; 
	}
	//待缴费用页面
	@RequestMapping({"/bePaidBill"})
	public String bePaidBill() { 
		return "back-end/commonTools/be_paid_bill"; 
	}
	//日录账单页面
	@RequestMapping({"/dateBill"})
	public String dateBill() { 
		return "back-end/commonTools/date_bill"; 
	}
	//日录账单类型页面
	@RequestMapping({"/dateBillType"})
	public String dateBillType() { 
		return "back-end/commonTools/date-bill-type"; 
	}
	//积分录入页面
	@RequestMapping({"/pointsWrite"})
	public String pointsWrite() { 
		return "back-end/points/points-write"; 
	}
	//周期积分页面
	@RequestMapping({"/pointsCycle"})
	public String pointsCycle() { 
		return "back-end/points/points-cycle"; 
	}
	//积分总览页面
	@RequestMapping({"/pointsCount"})
	public String pointsCount() { 
		return "back-end/points/points-count"; 
	}
	//积分总览页面
	@RequestMapping({"/pointsType"})
	public String pointsType() { 
		return "back-end/points/points-type"; 
	}
	//班级页面
	@RequestMapping({"/classes"})
	public String classes() { 
		return "back-end/basedata/classes"; 
	}
	//学生页面
	@RequestMapping({"/student"})
	public String student() { 
		return "back-end/basedata/student"; 
	}
	//员工页面
	@RequestMapping({"/staff"})
	public String staff() { 
		return "back-end/basedata/staff"; 
	}
	
 }
