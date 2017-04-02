
package net.pd.aldaaya.business;

import org.springframework.scheduling.annotation.Async;

import net.pd.aldaaya.common.model.Email;

public interface MailService {

	@Async
	public String send(Email email);

}
