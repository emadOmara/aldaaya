package net.pd.aldaaya.business;

import java.util.List;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Message;

public interface MessageService {

	void sendUserMessage(Message msg) throws AldaayaException;

	void sendAdminMessage(Message msg) throws AldaayaException;

	List<Message> getUserInbox(Long id) throws AldaayaException;

	List<Message> getOutBox(Long id) throws AldaayaException;

	List<Message> getAdminInbox() throws AldaayaException;

	Message readUserMessage(Long messageID) throws AldaayaException;

	Message readAdminMessage(Long messageID) throws AldaayaException;

}