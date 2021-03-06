package net.pd.aldaaya.common;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;

import net.pd.aldaaya.common.model.Account;
import net.pd.aldaaya.common.model.Email;

public class CommonUtil {

	public static String generateToken(Account account) {

		String key = account.getEmail() + new Date().toString() + UUID.randomUUID().toString();
		String token = DigestUtils.sha256Hex(key);
		return token;

	}

	public static Email createEmail(String recipient, String subject, String body) {

		Email email = new Email();
		email.setSubject(subject);
		email.setTo(recipient);
		email.setBody(body);
		return email;
	}

	public static String generatePassword(int length) {
		return RandomStringUtils.randomNumeric(length);
	}

	public static boolean isEmpty(Long id) {
		return id == null ? true : id.equals(0l);
	}

	public static boolean isEmpty(List<?> items) {
		return items == null ? true : items.size() == 0;
	}

	public static boolean isEmpty(Object[] obj) {
		return obj == null ? true : obj.length == 0;
	}

	public static boolean isEmpty(Object obj) {
		return obj == null;
	}

}
