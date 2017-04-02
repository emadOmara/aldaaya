package net.pd.aldaaya.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Message;
import net.pd.aldaaya.dao.MessageDao;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	@Autowired
	private MessageDao messageDao;

	/**
	 * send user message to admin
	 */
	@Override
	public void sendUserMessage(Message msg) throws AldaayaException {
		try {
			msg.setNewAdminMessage(true);
			messageDao.save(msg);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public void sendAdminMessage(Message msg) throws AldaayaException {
		try {
			msg.setNewUserMessage(true);
			messageDao.save(msg);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public List<Message> getUserInbox(Long userID) throws AldaayaException {

		try {
			List<Message> messages = messageDao.getUserInbox(userID);
			return messages;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public List<Message> getOutBox(Long userID) throws AldaayaException {

		try {
			List<Message> messages = messageDao.getOutbox(userID);
			return messages;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public List<Message> getAdminInbox() throws AldaayaException {
		try {

			List<Message> messages = messageDao.findByToAdminTrueOrderByCreationDateDesc();

			return messages;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public Message readUserMessage(Long messageID) throws AldaayaException {

		try {

			Message message = messageDao.findOne(messageID);
			message.setNewUserMessage(false);
			messageDao.save(message);
			return message;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public Message readAdminMessage(Long messageID) throws AldaayaException {
		try {

			Message message = messageDao.findOne(messageID);
			message.setNewAdminMessage(false);

			message = messageDao.save(message);
			return message;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

}
