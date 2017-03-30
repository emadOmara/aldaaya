package net.pd.aldaaya.integration;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.GetMapping;
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
import net.pd.aldaaya.integration.response.BaseResponse;
import net.pd.aldaaya.integration.response.LoginResponse;

@RestController()
@RequestMapping(path = "api/account")
public class AccountController extends BaseController {

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenManagementService tokenManagementService;

	@RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
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

		Account fetchedAccount = accountService.find(account.getMobile(), userDetails.getPassword(),
				AldaayaConstants.ACTIVE);
		String token = CommonUtil.generateToken(account);
		tokenManagementService.addUser(token, userDetails);

		LoginResponse response = new LoginResponse(AldaayaConstants.OK, AldaayaConstants.GENERAL_SUCCESS);
		response.setToken(token);
		response.setResult(fetchedAccount);

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
	public BaseResponse searchUsers() throws AldaayaException {

		BaseResponse response = new BaseResponse();

		List<Account> accounts = accountService.getAllAccounts();
		handleSuccessResponse(response, accounts);

		return response;

	}

	/**
	 * count Pending users
	 *
	 * @return
	 * @throws AldaayaException
	 */
	@GetMapping(path = "/countPending")
	public BaseResponse countPendingUsers() throws AldaayaException {

		BaseResponse response = new BaseResponse();
		Long count = accountService.countPendingUsers();
		handleSuccessResponse(response, count);

		return response;
	}

}
