package net.pd.aldaaya.integration.response;

import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.pd.aldaaya.common.model.Account;

public class LoginResponse extends BaseResponse {

	/**
	 *
	 */
	private static final long serialVersionUID = 1395918555510719966L;
	@JsonIgnore
	private String token;

	public LoginResponse() {
		super();
	}

	public LoginResponse(int status, String comment) {
		super(status, comment);
	}

	@Override
	public void setResult(Object result) {
		Account acc = (Account) result;
		this.result = new LoginAccountRes(acc);
	}

	@Override
	public Object getResult() {
		if (result != null && !StringUtils.isEmpty(token)) {
			((LoginAccountRes) result).setToken(token);
		}
		return result;
	}

	public void setToken(String token) {
		this.token = token;
	}
}

class LoginAccountRes extends Account {
	private String token;

	public LoginAccountRes(Account acc) {
		this.setId(acc.getId());
		this.setAccountStatus(acc.getAccountStatus());
		this.setAccountType(acc.getAccountType());
		this.setEmail(acc.getEmail());
		this.setMobile(acc.getMobile());
		this.setPassword(acc.getPassword());
		this.setUserName(acc.getUserName());
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
