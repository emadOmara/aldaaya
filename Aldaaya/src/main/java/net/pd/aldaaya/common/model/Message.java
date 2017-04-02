package net.pd.aldaaya.common.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/*****
 * Message Entity******
 *
 * @author Emad
 *
 */
@Entity
public class Message extends BaseEntity {

	/**
	*
	*/

	private static final long serialVersionUID = 5105914722614237201L;

	@Lob
	protected String msg;

	@ManyToOne
	protected Account sender;
	@ManyToOne
	protected Account receiver;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();

	private boolean toAdmin = false;

	private boolean newAdminMessage = false;

	private boolean newUserMessage = false;

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

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

	public boolean isToAdmin() {
		return toAdmin;
	}

	public void setToAdmin(boolean toAdmin) {
		this.toAdmin = toAdmin;
	}

	@Transient
	public String getSenderDisplayName() {
		if (sender != null)
			return sender.getUserName();
		else
			return "";
	}

	public boolean isNewAdminMessage() {
		return newAdminMessage;
	}

	public void setNewAdminMessage(boolean newAdminMessage) {
		this.newAdminMessage = newAdminMessage;
	}

	public Account getReceiver() {
		return receiver;
	}

	public void setReceiver(Account receiver) {
		this.receiver = receiver;
	}

	public boolean isNewUserMessage() {
		return newUserMessage;
	}

	public void setNewUserMessage(boolean newUserMessage) {
		this.newUserMessage = newUserMessage;
	}

}