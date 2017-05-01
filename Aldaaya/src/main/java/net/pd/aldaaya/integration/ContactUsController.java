package net.pd.aldaaya.integration;

import java.util.List;

import javax.validation.Valid;

import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.pd.aldaaya.business.MessageService;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.common.model.ContactUs;
import net.pd.aldaaya.common.model.Message;
import net.pd.aldaaya.integration.jackson.Views;
import net.pd.aldaaya.integration.request.MessageRequest;
import net.pd.aldaaya.integration.response.BaseResponse;

@RestController()
@RequestMapping(path = "api/contactus")
public class ContactUsController extends BaseController {

	@Autowired
	private MessageService messageService;

	 
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	public BaseResponse add( @RequestBody ContactUs request) throws AldaayaException {

		BaseResponse response = new BaseResponse();
 

		request=messageService.addContactUsMsg(request); 
		handleSuccessResponse(response, request);

		return response;

	}
 

	@GetMapping(path = "/list")
	@JsonView(Views.Public.class)
	public BaseResponse list() throws AldaayaException {

		BaseResponse response = new BaseResponse();

		List<ContactUs> messages = messageService.getContactUsMessages();
		handleSuccessResponse(response, messages);

		return response;

	}
	
	@GetMapping(path = "/get/{id}")
	@JsonView(Views.Public.class)
	public BaseResponse list(@PathVariable("id")Long id) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		handleNullID(id);
	   ContactUs msg = messageService.readContactUsMessage(id);
		handleSuccessResponse(response, msg);

		return response;

	}
	@DeleteMapping(path = "/delete/{id}")
	public BaseResponse delete(@PathVariable("id")Long id) throws AldaayaException {
		
		BaseResponse response = new BaseResponse();
		handleNullID(id);
		  messageService.deleteContactUsMessage(id);
		handleSuccessResponse(response, null);
		
		return response;
		
	}
	@DeleteMapping(path = "/deleteAndGetNext/{id}")
	public BaseResponse deleteAndGetNext(@PathVariable("id")Long id) throws AldaayaException {
		
		BaseResponse response = new BaseResponse();
		handleNullID(id);
		messageService.deleteContactUsMessageAndGetNext(id);
		handleSuccessResponse(response, null);
		
		return response;
		
	}


  

}
