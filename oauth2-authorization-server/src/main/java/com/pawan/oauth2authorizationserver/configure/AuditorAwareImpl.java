/*package com.pawan.oauth2authorizationserver.configure;




import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import com.pawan.oauth2authorizationserver.model.User;

public class AuditorAwareImpl implements AuditorAware<Long>{

	@Override
	public Long getCurrentAuditor() {
		// Can use Spring Security to return currently logged in user
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
		
	}

}
*/