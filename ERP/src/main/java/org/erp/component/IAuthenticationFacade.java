package org.erp.component;

import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
	public Authentication getAuthentication();
}
