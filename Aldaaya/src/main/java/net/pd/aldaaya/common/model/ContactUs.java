package net.pd.aldaaya.common.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.validator.constraints.Email;

import net.pd.aldaaya.integration.jackson.Views;

/***
 * Contact Entity
 *
 * @author Emad
 *
 */
@Entity
public class ContactUs extends BaseEntity {

	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 382221162074130975L;
	
	@JsonView(Views.Public.class)
	private String subject;
	@Lob
	@JsonView(Views.Details.class)
	protected String msg;
  
	private String name;
	 
	@JsonView(Views.Details.class)
	private String mobile;
	
	@Email
	@JsonView(Views.Details.class)
	private String email;
	

	@Temporal(TemporalType.TIMESTAMP)
	@JsonView(Views.Public.class)
	private Date creationDate = new Date();
 

	@JsonView(Views.Public.class)
	private boolean newMessage = true;
 
  
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	 
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public boolean isNewMessage() {
		return newMessage;
	}

	public void setNewMessage(boolean newMessage) {
		this.newMessage = newMessage;
	}

	
}