package com.greedy.model.dto;

import java.sql.Date;

public class EmployeetestDTO {
	
	private String empName;
	private String deptCode;
	private int salary;
	private String empNo;
	private String email;
	private String jobCode;
	private Date HireDate;
	private String salLevel;
	public EmployeetestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeetestDTO(String empName, String deptCode, int salary, String empNo, String email, String jobCode,
			Date hireDate, String salLevel) {
		super();
		this.empName = empName;
		this.deptCode = deptCode;
		this.salary = salary;
		this.empNo = empNo;
		this.email = email;
		this.jobCode = jobCode;
		HireDate = hireDate;
		this.salLevel = salLevel;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}
	public Date getHireDate() {
		return HireDate;
	}
	public void setHireDate(Date hireDate) {
		HireDate = hireDate;
	}
	public String getSalLevel() {
		return salLevel;
	}
	public void setSalLevel(String salLevel) {
		this.salLevel = salLevel;
	}
	
	@Override
	public String toString() {
		return "EmployeetestDTO [empName=" + empName + ", deptCode=" + deptCode + ", salary=" + salary + ", empNo="
				+ empNo + ", email=" + email + ", jobCode=" + jobCode + ", HireDate=" + HireDate + ", salLevel="
				+ salLevel + "]";
	}
	
	
	

}
