package net.pd.aldaaya.integration;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.annotate.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.pd.aldaaya.business.AccountService;
import net.pd.aldaaya.business.TokenManagementService;
import net.pd.aldaaya.common.AldaayaConstants;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.CommonUtil;
import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.common.model.AccountType;
import net.pd.aldaaya.integration.jackson.Views;
import net.pd.aldaaya.integration.response.BaseResponse;
import net.pd.aldaaya.integration.response.LoginResponse;

@RestController()
@RequestMapping(path = "api/account")
public class AccountController extends BaseController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenManagementService tokenManagementService;

	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
	@JsonView(Views.Details.class)
	public BaseResponse get(@PathVariable("id") Long id) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		handleNullID(id);

		Account account = accountService.find(id);
		handleSuccessResponse(response, account);

		return response;

	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public BaseResponse login(@RequestBody Account account) throws AldaayaException {

		UserDetails userDetails = null;

		if (account == null || StringUtils.isEmpty(account.getMobile()) || StringUtils.isEmpty(account.getPassword())) {
			throw new UsernameNotFoundException("Invalid User Name");
		}

		String credentials = account.getMobile() + "-" + account.getPassword();
		userDetails = userDetailsService.loadUserByUsername(credentials);
		Account fetchedAccount = null;
		try {
			fetchedAccount = accountService.login(account.getMobile(), userDetails.getPassword());
		} catch (AldaayaException ex) {
			BaseResponse res = new BaseResponse();
			res.setStatus(2);
			res.setComment(ex.getMessage());
			return res;
		}

		String token = CommonUtil.generateToken(account);
		tokenManagementService.addUser(token, userDetails);

		LoginResponse response = new LoginResponse(AldaayaConstants.OK, AldaayaConstants.GENERAL_SUCCESS);
		response.setToken(token);
		response.setResult(fetchedAccount);

		return response;
	}

	@RequestMapping(path = "/forgetPassword", method = RequestMethod.POST)
	public BaseResponse forgetPassword(@RequestBody Account account) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		if (account.getMobile() == null) {
			throw new AldaayaException(AldaayaConstants.ERROR_MSG_MOBILE_CAN_T_BE_NULL);
		}

		accountService.forgetPassword(account);
		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@JsonView(Views.Details.class)
	public BaseResponse add(@RequestBody Account account) throws AldaayaException {
		BaseResponse response = new BaseResponse();
		try {
			account = accountService.saveAccount(account);

			handleSuccessResponse(response, null);
		} catch (Exception e) {
			if (e instanceof TransactionSystemException) {
				response.setStatus(2);
				response.setComment(((TransactionSystemException) e).getApplicationException().getMessage());
			} else {
				response.setStatus(AldaayaConstants.ERROR);
				response.setComment(e.getMessage());
			}

		}
		return response;

	}

	@RequestMapping(path = "/edit", method = RequestMethod.POST)
	public BaseResponse edit(@RequestBody Account account) throws AldaayaException {
		BaseResponse response = new BaseResponse();
		try {
			if (account.isNew()) {
				throw new AldaayaException(AldaayaConstants.ERROR_MSG_ID_CAN_T_BE_NULL);
			}
			account = accountService.saveAccount(account);

			handleSuccessResponse(response, null);
		} catch (Exception e) {
			if (e instanceof TransactionSystemException) {
				response.setStatus(2);
				response.setComment(((TransactionSystemException) e).getApplicationException().getMessage());
			} else {
				response.setStatus(AldaayaConstants.ERROR);
				response.setComment(e.getMessage());
			}

		}
		return response;

	}

	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public BaseResponse register(@RequestBody Account account) throws AldaayaException {
		BaseResponse response = new BaseResponse();
		try {
			account.setAccountType(AccountType.NORMAL);
			account.setAccountStatus(AldaayaConstants.INACTIVE);
			accountService.saveAccount(account);
			handleSuccessResponse(response, null);
		} catch (Exception e) {
			if (e instanceof TransactionSystemException) {
				response.setStatus(2);
				response.setComment(((TransactionSystemException) e).getApplicationException().getMessage());
			} else {
				response.setStatus(AldaayaConstants.ERROR);
				response.setComment(e.getMessage());
			}

		}
		return response;

	}

	@RequestMapping(path = "/activate", method = RequestMethod.POST)
	public BaseResponse activateAccount(@RequestBody Account account) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		if (account.isNew()) {
			throw new AldaayaException(AldaayaConstants.ERROR_MSG_ID_CAN_T_BE_NULL);
		}

		accountService.activateAccount(account);
		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public BaseResponse deleteAccount(@PathVariable("id") Long id) throws AldaayaException {

		BaseResponse response = new BaseResponse();
		accountService.deleteAccount(id);
		handleSuccessResponse(response, null);

		return response;

	}

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	@JsonView(Views.Public.class)
	public BaseResponse list() throws AldaayaException {

		BaseResponse response = new BaseResponse();

		List<Account> accounts = accountService.getAllAccounts();
		handleSuccessResponse(response, accounts);

		return response;

	}

	@RequestMapping(path = "/search/{name}", method = RequestMethod.GET)
	@JsonView(Views.Public.class)
	public BaseResponse searchUsers(@PathVariable("name") String name) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		List<Account> accounts = accountService.findByUserName(name);
		handleSuccessResponse(response, accounts);

		return response;

	}

	@RequestMapping(path = "/search", method = RequestMethod.GET)
	@JsonView(Views.Public.class)
	public BaseResponse searchUsers() throws AldaayaException {

		BaseResponse response = new BaseResponse();

		List<Account> accounts = accountService.findByUserName(null);
		handleSuccessResponse(response, accounts);

		return response;

	}

}
