package com.wyf.action;


import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	
	
	public String login(){
		if("admin".equals(userName)){
			return SUCCESS;
		}else{
			return ERROR;
		}
	}
	public String logout(){
		return "logout";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
