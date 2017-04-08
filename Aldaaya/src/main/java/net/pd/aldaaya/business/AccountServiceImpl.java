package net.pd.aldaaya.business;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.pd.aldaaya.common.AldaayaConstants;
import net.pd.aldaaya.common.AldaayaException;
import net.pd.aldaaya.common.CommonUtil;
import net.pd.aldaaya.common.NullAwareBeanUtilsBean;
import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.common.model.AccountType;
import net.pd.aldaaya.common.model.Email;
import net.pd.aldaaya.dao.AccountDao;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private MailService mailService;

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
	public List<Account> findByUserName(String userName,AccountType type,Integer accountStatus) {

		if (StringUtils.isEmpty(userName)) {
			return accountDao.findByAccountTypeAndAccountStatus(type,accountStatus);
		}

		return accountDao.findByUserNameContainingIgnoreCaseAndAccountStatusAndAccountType(userName, 1,type);
	}
	
	@Override
	public List<Account> findByUserName(String userName) {

		if (StringUtils.isEmpty(userName)) {
			return accountDao.findByAccountStatus(1);
		}

		return accountDao.findByUserNameContainingIgnoreCaseAndAccountStatus(userName, 1);
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
	public void forgetPassword(Account account) throws AldaayaException {
		try {

			Account fetchedAccount = accountDao.findByMobile(account.getMobile());
			if (fetchedAccount == null) {
				throw new AldaayaException("No account defined for this mobile number");
			}

			String generatePassword = CommonUtil.generatePassword(AldaayaConstants.RANDOM_PASSWORD_LENGTH);

			fetchedAccount.setPassword(generatePassword);
			accountDao.save(fetchedAccount);

			Email forgetPasswordEmail = CommonUtil.createEmail(fetchedAccount.getEmail(),
					AldaayaConstants.EMAIL_SUBJECT_FORGET_PASSWORD,
					AldaayaConstants.EMAIL_BODY_FORGET_PASSWORD + generatePassword);
			mailService.send(forgetPasswordEmail);

		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
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
	public List<Account> getAccounts(AccountType type) throws AldaayaException {
		try {
			return (List<Account>) accountDao.findByAccountType(type);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}
	}

	@Override
	public Account find(Long id) throws AldaayaException {

		try {

			return accountDao.findOne(id);
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}

	@Override
	public Account login(String mobile, String password) throws AldaayaException {
		Account acc = null;
		try {
			acc = accountDao.findByMobileAndPassword(mobile, password);
			if (acc.getAccountStatus().equals(AldaayaConstants.INACTIVE)) {
				throw new AldaayaException("User status is inactive");
			}

			return acc;
		} catch (Exception e) {
			throw new AldaayaException(e);
		}

	}
}
