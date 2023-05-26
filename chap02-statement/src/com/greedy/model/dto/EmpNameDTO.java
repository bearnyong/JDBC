package com.greedy.model.dto;

public class EmpNameDTO {

	private String empId;
	private String empName;
	
	
	public EmpNameDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpNameDTO(String empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public String toString() {
		return "EmpNameDTO [empId=" + empId + ", empName=" + empName + "]";
	}
	
	
	
	
}
