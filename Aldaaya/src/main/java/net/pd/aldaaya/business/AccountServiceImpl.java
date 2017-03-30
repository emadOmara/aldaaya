package net.pd.aldaaya.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.pd.aldaaya.common.AldaayaConstants;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.CommonUtil;
import net.pd.aldaaya.common.NullAwareBeanUtilsBean;
import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.dao.AccountDao;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;
	@Autowired
	private NullAwareBeanUtilsBean beanUtilService;

	@Override
	public Account saveAccount(Account account) throws AldaayaException {
		try {
			if (account.isNew()) {
				return accountDao.save(account);
			} else {// update
				Account fetchedAccount = accountDao.findOne(account.getId());
				if (fetchedAccount == null) {
					throw new AldaayaException("can't find account with specified id " + account.getId());
				}
				beanUtilService.copyProperties(fetchedAccount, account);
				return accountDao.save(fetchedAccount);
			}
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override

	public void activateAccount(Account account) throws AldaayaException {
		try {

			Account fetchedAccount = accountDao.findOne(account.getId());
			fetchedAccount.setAccountStatus(1);
			accountDao.save(fetchedAccount);

		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	@CacheEvict(cacheNames = "accounts")
	public void deleteAccount(Long id) throws AldaayaException {
		try {

			Account account = accountDao.findOne(id);
			if (CommonUtil.isEmpty(account))
				throw new AldaayaException("No account defined for the id " + id);

			accountDao.delete(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public List<Account> getAllAccounts() throws AldaayaException {
		try {
			return (List<Account>) accountDao.findAll();
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public Account find(Long id) {
		return accountDao.findOne(id);

	}

	@Override
	public void forgetPassword(Account account) throws AldaayaException {
		try {

			Account fetchedAccount = accountDao.findByMobile(account.getMobile());
			if (fetchedAccount == null) {
				throw new AldaayaException("No account defined for this mobile number");
			}

			String generatePassword = CommonUtil.generatePassword(AldaayaConstants.RANDOM_PASSWORD_LENGTH);

			fetchedAccount.setPassword(generatePassword);
			accountDao.save(fetchedAccount);

		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public Long countPendingUsers() throws AldaayaException {
		try {

			Long count = accountDao.countPending();
			return count;

		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public Account find(String mobile, String password, int status) throws AldaayaException {
		Account acc = null;
		try {
			acc = accountDao.findByMobileAndPasswordAndAccountStatus(mobile, password, status);
			return acc;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}
}
