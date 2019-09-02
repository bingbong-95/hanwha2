package com.hanhwa.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmpService {
	@Autowired
	EmpDAO_Mybatis dao;
	//EmpDAO dao;
	
	public List<String> selectAllJob() {
		return dao.selectAllJob();
	}
	
	
	public List<EmpVO> selectAll() {
		return dao.selectAll();
	}

	public EmpVO selectById(int empid) {
		return dao.selectById(empid);
	}

	public int insertEmp(EmpVO emp) {
		return dao.insertEmp(emp);
	}
	
	 public int updateEmp(EmpVO emp) { 
		return dao.updateEmp(emp);
	 }
	  
	 public int deleteEmp(int emp) {
		 return dao.deleteEmp(emp);
	}
	public List<Integer> selectAllManager() {
		return dao.selectAllManager();
	}

}
