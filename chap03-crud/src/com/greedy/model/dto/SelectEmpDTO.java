package com.greedy.model.dto;

import java.sql.Date;

public class SelectEmpDTO {
	
	/*�ǹ����� �ּ��ޱ�*/
	private String empName;				//employee ������
	private String email;				//employee �̸���
	private String phone;				//employee ��ȭ��ȣ
	private int salary;					//employee �޿�
	private java.sql.Date hireDate;		//employee �Ի���
	private String entYn;				//employee ����
	
	public SelectEmpDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SelectEmpDTO(String empName, String email, String phone, int salary, Date hireDate, String entYn) {
		super();
		this.empName = empName;
		this.email = email;
		this.phone = phone;
		this.salary = salary;
		this.hireDate = hireDate;
		this.entYn = entYn;
	}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public java.sql.Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getEntYn() {
		return entYn;
	}
	public void setEntYn(String entYn) {
		this.entYn = entYn;
	}
	
	@Override
	public String toString() {
		return "SelectEmpDTO [empName=" + empName + ", email=" + email + ", phone=" + phone + ", salary=" + salary
				+ ", hireDate=" + hireDate + ", entYn=" + entYn + "]";
	}
	
}
