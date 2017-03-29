package net.pd.aldaaya.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import net.pd.aldaaya.business.AccountService;
import net.pd.aldaaya.common.model.Account;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String credentials) throws UsernameNotFoundException {

		return null;

	}

	private String[] getAuthorities(Account account) {
		String[] result = new String[1];
		return result;
	}
}
