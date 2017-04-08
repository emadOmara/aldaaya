package net.pd.aldaaya.business;

import java.util.List;

import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.common.model.AccountType;

public interface AccountService {

	List<Account> getAccounts(AccountType normal) throws AldaayaException;

	Account saveAccount(Account account) throws AldaayaException;

	void deleteAccount(Long id) throws AldaayaException;

	Account find(Long id) throws AldaayaException;

	void activateAccount(Account account) throws AldaayaException;

	void forgetPassword(Account account) throws AldaayaException;

	Account login(String mobile, String password) throws AldaayaException;

	List<Account> findByUserName(String name) throws AldaayaException;

	List<Account> findByUserName(String userName, AccountType type, Integer accountStatus);

}
