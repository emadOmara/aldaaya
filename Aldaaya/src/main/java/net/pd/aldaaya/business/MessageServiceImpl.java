package net.pd.aldaaya.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.pd.aldaaya.common.AldaayaConstants;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.CommonUtil;
import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.common.model.ContactUs;
import net.pd.aldaaya.common.model.Message;
import net.pd.aldaaya.dao.ContactUsDao;
import net.pd.aldaaya.dao.MessageDao;
import net.pd.aldaaya.integration.request.MessageRequest;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

	Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private ContactUsDao contactUsDao;
	private final Pageable limit = new PageRequest(0, 1);;

	/**
	 * send user message to admin
	 */
	@Override
	public void sendUserMessage(Message msg) throws AldaayaException {
		try {
			msg.setToAdmin(true);
			msg.setNewAdminMessage(true);
			messageDao.save(msg);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public void sendAdminMessage(MessageRequest request) throws AldaayaException {
		try {

			List<Long> userIds = request.getUsers();

			if (CommonUtil.isEmpty(userIds)) {
				throw new AldaayaException("Please specify target");
			}

			addUserMessages(request);

		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	private void addUserMessages(MessageRequest request) {

		List<Long> userIds = request.getUsers();

		if (CommonUtil.isEmpty(userIds)) {
			return;
		}

		for (Long userId : userIds) {
			Message message = new Message();
			message.setSubject(request.getSubject());
			message.setMsg(request.getMsg());

			Account sender = new Account();
			sender.setId(request.getSender());
			message.setSender(sender);

			Account receiver = new Account();
			receiver.setId(userId);
			message.setReceiver(receiver);

			message.setNewUserMessage(true);
			messageDao.save(message);

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
	public List<Message> getAdminOutBox() throws AldaayaException {
		try {
			List<Message> messages = messageDao.findByToAdmin(false);
			return messages;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public void deleteMessage(Long id,int mode) throws AldaayaException {
		try {
			Message msg=messageDao.findOne(id);
			if(CommonUtil.isEmpty(msg)){
				throw new AldaayaException("No message found for specified id");
			}
			if(mode!=AldaayaConstants.INBOX_TYPE &&mode!=AldaayaConstants.OUTBOX_TYPE){
				throw new AldaayaException("Invalide mode");
			}
			msg.setDeleteStatus(mode);
			messageDao.save(msg);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}
	@Override
	public Message deleteUserMessageAndGetNext(Long msgId,Long userId,int mode) throws AldaayaException {
		
		Message msg=null;
		try {
			deleteMessage(msgId, mode);
			
			Page<Message> page=null;
			switch (mode) {
			case  AldaayaConstants.INBOX_TYPE: 
				 page = messageDao.getNextInboxMessage(msgId,userId,limit);
				break;
			case  AldaayaConstants.OUTBOX_TYPE: 
				page = messageDao.getNextOutBoxMessage(msgId,userId,limit);
				break;

			default:
				break;
			}
			if (page != null) {
				List<Message> list = page.getContent();
				if(!CommonUtil.isEmpty(list)){
					msg= list.get(0);
					msg.setNewUserMessage(false);
					messageDao.save(msg);
				}
			}  
			return msg;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
		
		
		 
	}
	@Override
	public Message deleteAdminMessageAndGetNext(Long msgId,int mode) throws AldaayaException {
		
		Message msg=null;
		try {
			deleteMessage(msgId, mode);
			
			Page<Message> page=null;
			switch (mode) {
			case  AldaayaConstants.INBOX_TYPE: 
				page = messageDao.getAdminNextInboxMessage(msgId,limit);
				break;
			case  AldaayaConstants.OUTBOX_TYPE: 
				page = messageDao.getAdminNextOutBoxMessage(msgId,limit);
				break;
				
			default:
				break;
			}
			if (page != null) {
				List<Message> list = page.getContent();
				if(!CommonUtil.isEmpty(list)){
					msg= list.get(0);
					msg.setNewAdminMessage(false);
					messageDao.save(msg);
				}
			}  
			return msg;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
		
		
		
	}

	@Override
	public void deleteContactUsMessage(Long id) throws AldaayaException {

		try {
			contactUsDao.delete(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public ContactUs deleteContactUsMessageAndGetNext(Long id) throws AldaayaException {
		ContactUs msg=null;
		try {
			contactUsDao.delete(id);
			
			Page<ContactUs> page = contactUsDao.getNext(id,limit);
			if (page != null) {
				List<ContactUs> list = page.getContent();
				if(!CommonUtil.isEmpty(list)){
					msg= list.get(0);
					msg.setNewMessage(false);
					contactUsDao.save(msg);
				}
			}  
			return msg;
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

	@Override
	public ContactUs addContactUsMsg(ContactUs request) throws AldaayaException {
		try {

			request = contactUsDao.save(request);
			return request;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public List<ContactUs> getContactUsMessages() throws AldaayaException {
		try {

			return (List<ContactUs>) contactUsDao.findAll();
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public ContactUs readContactUsMessage(Long id) throws AldaayaException {
		try {

			ContactUs msg = contactUsDao.findOne(id);
			msg.setNewMessage(false);
			contactUsDao.save(msg);
			return msg;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}
}
