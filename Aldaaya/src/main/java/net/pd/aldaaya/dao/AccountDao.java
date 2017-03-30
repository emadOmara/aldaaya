package net.pd.aldaaya.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
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

	@Query("select count (acc) from Account acc where acc.accountStatus != 1")
	Long countPending();

	List<Account> findByAccountType(AccountType type);

	Account findByMobileAndPasswordAndAccountStatus(String mobile, String password, int status);

}