package com.whirly.form;

import javax.validation.constraints.NotBlank;

public class LoginForm {
	@NotBlank(message = "帐号不能为空")
	private String account;

	@NotBlank(message = "密码不能为空")
	private String password;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginForm [account=" + account + ", password=" + password + "]";
	}

}
