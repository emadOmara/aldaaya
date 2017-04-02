// package net.pd.aldaaya.integration;
//
// import java.util.List;
//
// import javax.validation.Valid;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RestController;
//
// import net.pd.aldaaya.business.MessageService;
// import net.pd.aldaaya.common.AldaayaException;
// import net.pd.aldaaya.common.model.Account;
// import net.pd.aldaaya.common.model.Message;
// import net.pd.aldaaya.integration.request.MessageRequest;
// import net.pd.aldaaya.integration.response.BaseResponse;
//
// @RestController()
// @RequestMapping(path = "api/messages")
// public class MessageController extends BaseController {
//
// @Autowired
// private MessageService messageService;
//
// /**
// * Normal user sends to admin
// *
// * @param msg
// * @return
// * @throws AldaayaException
// */
// @RequestMapping(path = "/user/send", method = RequestMethod.POST)
// public BaseResponse sendUserMessage(@Valid @RequestBody MessageRequest
// request) throws AldaayaException {
//
// BaseResponse response = new BaseResponse();
// Message msg = new Message();
//
// msg.setToAdmin(true);
// msg.setNewAdminMessage(true);
// msg.setMsg(request.getMsg());
//
// Account acc = new Account();
// acc.setId(request.getSender());
// msg.setSender(acc);
//
// messageService.sendMessage(msg);
// handleSuccessResponse(response, null);
//
// return response;
//
// }
//
// @RequestMapping(path = "/admin/send", method = RequestMethod.POST)
// public BaseResponse sendFromAdmin(@Valid @RequestBody MessageRequest msg)
// throws AldaayaException {
//
// BaseResponse response = new BaseResponse();
//
// messageService.sendMessage(msg);
// handleSuccessResponse(response, null);
//
// return response;
//
// }
//
// /**
// * retrieve the list of {@link Message}s of the user that was sent from him
// * , from the administraion to him , or from administration to a group which
// * he is a member of .
// *
// * @param msg
// * @return
// * @throws AldaayaException
// */
// @GetMapping(path = "/user/list/{id}")
// public BaseResponse listUserMessages(@PathVariable("id") Long id) throws
// AldaayaException {
//
// BaseResponse response = new BaseResponse();
// handleNullID(id);
//
// List<Message> userMessages = messageService.getUserInbox(id);
// handleSuccessResponse(response, userMessages);
//
// return response;
//
// }
//
// @GetMapping(path = "/user/readNew/{id}")
// public BaseResponse listNewUserMessages(@PathVariable("id") Long id) throws
// AldaayaException {
//
// BaseResponse response = new BaseResponse();
// handleNullID(id);
//
// List<Message> userMessages = messageService.readNewUserMessages(id);
// handleSuccessResponse(response, userMessages);
//
// return response;
//
// }
//
// @GetMapping(path = "/admin/list")
// public BaseResponse listAdminMessages() throws AldaayaException {
//
// BaseResponse response = new BaseResponse();
//
// List<Message> messages = messageService.getAdminMessages();
//
// // TODO this is just atest
// // messages = messageService.getAllAdminMessagesForUser(3l, 2l);
// handleSuccessResponse(response, messages);
//
// return response;
//
// }
//
// /**
// * get new messages to admins from some user
// *
// * @return
// * @throws AldaayaException
// */
// @GetMapping(path = "/admin/listNew/{userId}")
// public BaseResponse listAdminMessages(@PathVariable("userId") Long userId)
// throws AldaayaException {
//
// handleNullID(userId);
// BaseResponse response = new BaseResponse();
//
// List<Message> messages = messageService.getNewAdminMessagesForUser(userId);
// handleSuccessResponse(response, messages);
//
// return response;
// }
//
// @GetMapping(path = "/user/get/{userID}/{messageID}")
// public BaseResponse readUserMessage(@PathVariable("userID") Long userID,
// @PathVariable("messageID") Long messageID)
// throws AldaayaException {
//
// BaseResponse response = new BaseResponse();
// handleNullID(userID);
// handleNullID(messageID);
//
// Message msg = messageService.readUserMessage(userID, messageID);
// handleSuccessResponse(response, msg);
//
// return response;
//
// }
//
// @GetMapping(path = "/admin/get/{messageID}")
// public BaseResponse readAdminMessage(@PathVariable("messageID") Long
// messageID) throws AldaayaException {
//
// BaseResponse response = new BaseResponse();
// handleNullID(messageID);
//
// Message msg = messageService.readAdminMessage(messageID);
// handleSuccessResponse(response, msg);
//
// return response;
//
// }
//
// }
