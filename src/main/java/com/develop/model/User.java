package com.develop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String pwd;
	private String age;
	private String addr;
	private String email;

	private String rolename = "∆’Õ®”√ªß";

	private List<Role> role;

	private BigDecimal amount;

	private String pageNumber;
	private String pageSize;

	public User() {
		super();
	}

	public User(String id, String pwd, String username, String age, String addr) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.username = username;
		this.age = age;
		this.addr = addr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", username=" + username + ", age=" + age + ", addr=" + addr + "]";
	}

}
