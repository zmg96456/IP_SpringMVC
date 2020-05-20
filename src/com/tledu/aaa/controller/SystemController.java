package com.tledu.aaa.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tledu.aaa.manger.DataProcessManager;
import com.tledu.aaa.pojo.IPAndLocationPojo;
import com.tledu.aaa.util.AjaxObj;

@Controller
public class SystemController {

	@RequestMapping("/")
	public String ipSearch() {
		
		return "index";
	}
	@RequestMapping(value="/ipsearch",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj ipSearch(HttpServletRequest request,IPAndLocationPojo iPojo){
		
	String	ip = request.getParameter("ip");
	System.out.println(ip);
	
		String location = DataProcessManager.getLocation(ip);
		System.out.println(location);
		iPojo.setLocation(location);

		
		return new AjaxObj(1, location);
				
	}
	
}
