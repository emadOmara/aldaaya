package net.pd.aldaaya.integration;

import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.pd.aldaaya.business.MessageService;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.common.model.Message;
import net.pd.aldaaya.integration.jackson.Views;
import net.pd.aldaaya.integration.request.MessageRequest;
import net.pd.aldaaya.integration.response.BaseResponse;

@RestController()
@RequestMapping(path = "api/messages")
public class MessageController extends BaseController {

	@Autowired
	private MessageService messageService;

	/**
	 * Normal user sends to admin
	 *
	 * @param msg
	 * @return
	 * @throws AldaayaException
	 */
	@RequestMapping(path = "/user/send", method = RequestMethod.POST)
	public BaseResponse sendUserMessage(@Valid @RequestBody MessageRequest request) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		Message msg = new Message();

		msg.setMsg(request.getMsg());

		Account acc = new Account();
		acc.setId(request.getSender());
		msg.setSender(acc);

		messageService.sendUserMessage(msg);
		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/admin/send", method = RequestMethod.POST)
	public BaseResponse sendAdminMessage(@Valid @RequestBody MessageRequest msg) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		messageService.sendAdminMessage(msg);
		handleSuccessResponse(response, null);

		return response;

	}

	@GetMapping(path = "/user/inbox/{id}")
	@JsonView(Views.Public.class)
	public BaseResponse getUserInbox(@PathVariable("id") Long id) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		handleNullID(id);

		List<Message> userMessages = messageService.getUserInbox(id);
		handleSuccessResponse(response, userMessages);

		return response;

	}

	@GetMapping(path = "/outbox/{id}")
	@JsonView(Views.Public.class)
	public BaseResponse getUserOutbox(@PathVariable("id") Long id) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		handleNullID(id);

		List<Message> userMessages = messageService.getOutBox(id);
		handleSuccessResponse(response, userMessages);

		return response;

	}

	@GetMapping(path = "/admin/inbox")
	@JsonView(Views.Public.class)
	public BaseResponse getAdminInbox() throws AldaayaException {

		BaseResponse response = new BaseResponse();

		List<Message> messages = messageService.getAdminInbox();

		handleSuccessResponse(response, messages);

		return response;

	}

	@GetMapping(path = "/user/get/{messageID}")
	@JsonView(Views.Public.class)
	public BaseResponse readUserMessage(@PathVariable("messageID") Long messageID) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		handleNullID(messageID);

		Message msg = messageService.readUserMessage(messageID);
		handleSuccessResponse(response, msg);

		return response;

	}

	@GetMapping(path = "/admin/get/{messageID}")
	@JsonView(Views.Public.class)
	public BaseResponse readAdminMessage(@PathVariable("messageID") Long messageID) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		handleNullID(messageID);

		Message msg = messageService.readAdminMessage(messageID);
		handleSuccessResponse(response, msg);

		return response;

	}

}
