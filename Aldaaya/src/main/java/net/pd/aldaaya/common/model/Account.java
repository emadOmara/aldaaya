package net.pd.aldaaya.common.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.pd.aldaaya.integration.jackson.Views;

@Entity
@Table(name = "ACCOUNT", uniqueConstraints = @UniqueConstraint(columnNames = { "mobile", "email" }) )
public class Account extends BaseEntity {

	/**
	 *
	 */

	private static final long serialVersionUID = 5105914722614237201L;

	@NotEmpty
	@JsonView(Views.Public.class)
	private String userName;
	@NotEmpty
	@Column(unique = true)
	@JsonView(Views.Details.class)
	private String mobile;
	@Email
	@Column(unique = true)
	@JsonView(Views.Details.class)
	private String email;
	@NotEmpty
	private String password;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	@JsonIgnore
	private List<Section> sections;

	@JsonView(Views.Details.class)
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	@JsonView(Views.Details.class)
	private Integer accountStatus;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Integer getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

}
