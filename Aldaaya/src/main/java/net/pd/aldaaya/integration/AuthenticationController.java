package net.pd.aldaaya.integration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.pd.aldaaya.business.AccountService;
import net.pd.aldaaya.business.TokenManagementService;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.integration.response.BaseResponse;

@RestController()
@RequestMapping(path = "api/authentication")
// TODO add logout method later
public class AuthenticationController extends BaseController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private TokenManagementService tokenManagementService;

	// @JsonView(Views.LoginSuccess.class)
	// @RequestMapping(path = "/login", method = RequestMethod.POST)
	// public BaseResponse login(@RequestBody Account account) throws
	// AldaayaException {
	//
	// UserDetails userDetails = null;
	// BaseResponse response = new LoginResponse();
	//
	// if (account == null || StringUtils.isEmpty(account.getMobile()) ||
	// StringUtils.isEmpty(account.getPassword())) {
	// throw new UsernameNotFoundException("Invalid User Name");
	// }
	//
	// String credentials = account.getMobile() + "-" + account.getPassword();
	// userDetails = userDetailsService.loadUserByUsername(credentials);
	//
	// Account fetchedAccount =
	// accountService.findUserWithPermissions(account.getMobile(),
	// userDetails.getPassword(),
	// AldaayaConstants.ACTIVE);
	// String token = CommonUtil.generateToken(account);
	// tokenManagementService.addUser(token, userDetails);
	//
	// response = new LoginResponse(AldaayaConstants.OK,
	// AldaayaConstants.GENERAL_SUCCESS);
	// response.setToken(token);
	// response.setResult(fetchedAccount);
	//
	// return response;
	// }

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public BaseResponse logout(@RequestBody Account account) throws AldaayaException {

		BaseResponse response = new BaseResponse();

		if (account == null || StringUtils.isEmpty(account.getMobile())) {
			throw new UsernameNotFoundException("Invalid User Name");
		}

		tokenManagementService.deleteUser(account.getMobile());

		return response;
	}

}
