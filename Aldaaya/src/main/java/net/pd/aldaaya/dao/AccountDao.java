package net.pd.aldaaya.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.common.model.AccountType;

@Repository
public interface AccountDao extends CrudRepository<Account, Long> {

	Account findByUserName(String userName);

	List<Account> findByAccountTypeAndAccountStatus(AccountType type, Integer status);

	List<Account> findByAccountTypeAndUserNameContainingIgnoreCaseAndAccountStatus(AccountType type, String userName,
			Integer status);

	Account findByMobile(String mobile);

	List<Account> findByAccountType(AccountType type);

	Account findByMobileAndPassword(String mobile, String password);

	List<Account> findByAccountStatus(int i);

	List<Account> findByUserNameContainingIgnoreCaseAndAccountStatus(String userName, int i);

	List<Account> findByUserNameContainingIgnoreCaseAndAccountStatusAndAccountType(String userName, int i,
			AccountType type);


}