package com.greedy.model.dto;

import java.sql.Date;

/* DTO�� ���
 * 1. ��� �ʵ� private
 * 2. �⺻ ������ �ʼ� / �Ű������ִ� ������ ����
 * 3. setter/getter �ʼ�
 * 4. toString ����(���� ��)
 * */

public class EmployeeDTO {
	
	/*���� ���ϴ� ���Ĵ�� ���� �Ѵ�... ��� �ʵ� private*/
	private String empId;
	private String empName;
	private String empNo;
	private String email;
	private String phone;
	private String deptCode;
	private String jobCode;
	private int salary;
	private double bonus;
	private Date hireDate;
	private Date entDate;
	private String entYn;
	
	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(String empId, String empName, String empNo, String email, String phone, String deptCode,
			String jobCode, int salary, double bonus, Date hireDate, Date entDate, String entYn) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		this.email = email;
		this.phone = phone;
		this.deptCode = deptCode;
		this.jobCode = jobCode;
		this.salary = salary;
		this.bonus = bonus;
		this.hireDate = hireDate;
		this.entDate = entDate;
		this.entYn = entYn;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Date getEntDate() {
		return entDate;
	}

	public void setEntDate(Date entDate) {
		this.entDate = entDate;
	}

	public String getEntYn() {
		return entYn;
	}

	public void setEntYn(String entYn) {
		this.entYn = entYn;
	}

	@Override
	public String toString() {
		return "EmployeeDTO [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", email=" + email
				+ ", phone=" + phone + ", deptCode=" + deptCode + ", jobCode=" + jobCode + ", salary=" + salary
				+ ", bonus=" + bonus + ", hireDate=" + hireDate + ", entDate=" + entDate + ", entYn=" + entYn + "]";
	}

	
	
}