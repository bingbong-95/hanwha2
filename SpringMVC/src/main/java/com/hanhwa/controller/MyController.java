package com.hanhwa.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hanhwa.model.DeptDAO_Mybatis;
import com.hanhwa.model.DeptDTO;
import com.hanhwa.model.EmpService;
import com.hanhwa.model.EmpVO;
//<bean id="myController" class"">
@Controller
public class MyController {
	//DeptDAO dao = new DeptDAO();
	//<bean id="dao" class="com.hanhwa.model.deptDAO"/>
	//<bean id="myController" class"">
	//<Property name="dao" ref="dao">
	//<bean/>
	
	@Autowired
	DeptDAO_Mybatis dao;
	
	//@Autowired
	//DeptDAO dao;
	
	@Autowired
	EmpService service;
	
	@RequestMapping("/404")
	public String error404(Model model) {
		model.addAttribute("company","한화ICT");
		model.addAttribute("manager","바보뿌리");
		return "error404";
	}
	
	@ExceptionHandler(Exception.class)
	public String error500(Exception ex, Model model) {
		model.addAttribute("company","한화ICT"); 
		model.addAttribute("manager","바보뿌리");
		model.addAttribute("phone","010-1234-5678");
		model.addAttribute("errormessage",ex.getMessage());		
		return "error500";
	}
	
	@RequestMapping("/emp/emplist")
	public ModelAndView selectAll() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emplist", service.selectAll());
		mv.setViewName("emp/emplist");
		return mv;	
	}
	
	@RequestMapping(value = "/emp/empdetail",method=RequestMethod.GET)
	public ModelAndView empUpdate(int sid) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("emp", service.selectById(sid));
		mv.addObject("managerlist",service.selectAllManager());
		mv.addObject("joblist",service.selectAllJob());
		mv.addObject("deptlist",dao.selectAll());
		mv.setViewName("emp/empdetail");
		return mv;
	}
	@RequestMapping(value = "/emp/empdetail",method=RequestMethod.POST)
	public String empUpdate(EmpVO emp) {
		service.updateEmp(emp);
		return "redirect:emplist";
	}
	@RequestMapping(value = "/emp/empinsert",method=RequestMethod.GET)
	public ModelAndView empInsert() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("deptlist",dao.selectAll());
		mv.addObject("joblist",service.selectAllJob());
		mv.addObject("managerlist",service.selectAllManager());
		mv.addObject("emplist",service.selectAll());
		mv.setViewName("emp/empinsert");
		return mv;
	}
	@RequestMapping(value = "/emp/empinsert",method=RequestMethod.POST)
	public String empInsert(EmpVO emp) {
		service.insertEmp(emp);
		return "redirect:emplist";
	}
	
	@RequestMapping("/emp/empdelete")
	public String empDelete(int sid) {
		service.deleteEmp(sid);
		return "redirect:emplist";	
	}

	
	
	@RequestMapping("/dept/deptlist2")
	public String deptlist3(Model model) {
		
		model.addAttribute("deptlist",dao.selectAll());
		return "dept/deptlist";
	}
	
	
	@RequestMapping(value = "/dept/deptdetail", method=RequestMethod.GET)
	public String deptdetailGet(int deptid, Model model) {
		model.addAttribute("dept",dao.selectById(deptid));
		System.out.println(dao.selectById(deptid));
		return "dept/deptdetail";
	}
	
	@RequestMapping(value = "/dept/deptdetail", method=RequestMethod.POST)
	public String deptdetailPost(DeptDTO dept) {
		dao.updateDept(dept);
		return "redirect:deptlist2";
	}
	
	@RequestMapping(value = "/dept/deptinsert", method=RequestMethod.GET)
	//page return하면 끝.
	public String deptinsertGet() {
		return "dept/deptinsert";
	}
	@RequestMapping("/dept/deptdownload")
	public void download(String folder, String file, HttpServletResponse response, HttpServletRequest request) throws IOException {
		 response.setHeader("Content-Disposition", "attachment;filename="+file); 
		 String fullPath = request.getSession().getServletContext().getRealPath( folder + "/" + file);   
		 FileInputStream fi = new FileInputStream(fullPath);    
		 ServletOutputStream sout = response.getOutputStream();    
		 byte[] buf = new byte[1024];
		 int size = 0;    
		 while((size = fi.read(buf, 0, 1024))!=-1){
			 sout.write(buf, 0, size);   
		}    
		fi.close();   
		sout.close();   
	}
	@RequestMapping(value = "/dept/deptinsert", method=RequestMethod.POST)
	public String deptinsertPost(DeptDTO dept, HttpServletRequest request) {
		MultipartFile uploadfile = dept.getUploadfile(); 
		if(uploadfile == null) return "redirect:deptinsert"; 
		String path = request.getSession().getServletContext().getRealPath("/resources"); 
		String fileName = uploadfile.getOriginalFilename(); 
		String fpath = path +"\\" + fileName ;
		dept.setFileName(fileName); 
		try {
			File file = new File(fpath);
			uploadfile.transferTo(file);
		} catch(IOException e) { e.printStackTrace(); }   

		dao.insertDept(dept);
		return "redirect:deptlist2";
	}
	@RequestMapping("/dept/deptdelete")
	//page return하면 끝.
	public String deptdeleteGet(int deptid) {
		dao.deleteDept(deptid);
		return "redirect:deptlist2";
	}
	
	
}
