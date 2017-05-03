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
 * Mission Entity
 *
 * @author Emad
 *
 */
@Entity
public class Mission extends BaseEntity {

	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 382221162074130975L;
	
	private String subject;

	protected String msg;
  
	private String name;
	
	private String certificate;
	 
	private String mobile;
	
	private String office;
	
	private String duration;
	
	private String tools;
	
	private String results;
	
	@Email
	private String email;
	

	@Temporal(TemporalType.TIMESTAMP)
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

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getTools() {
		return tools;
	}

	public void setTools(String tools) {
		this.tools = tools;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}
 
	
}