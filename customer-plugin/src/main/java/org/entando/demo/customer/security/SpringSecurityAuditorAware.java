package org.entando.demo.customer.security;

import org.entando.demo.customer.config.Constants;

import java.util.Optional;
import java.util.Map;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link AuditorAware} based on Spring Security.
 */
@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(this.getCurrentUserLogin().orElse(Constants.SYSTEM_ACCOUNT));
    }

    public Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
            .map(authentication -> {
                if (authentication.getPrincipal() instanceof UserDetails) {
                    UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                    return springSecurityUser.getUsername();
                } else if (authentication instanceof JwtAuthenticationToken) {
                    return (String) ((JwtAuthenticationToken)authentication).getToken().getClaims().get("preferred_username");
                } else if (authentication.getPrincipal() instanceof DefaultOidcUser) {
                    Map<String, Object> attributes = ((DefaultOidcUser) authentication.getPrincipal()).getAttributes();
                    if (attributes.containsKey("preferred_username")) {
                        return (String) attributes.get("preferred_username");
                    }
                } else if (authentication.getPrincipal() instanceof String) {
                    return (String) authentication.getPrincipal();
                }
                return null;
            });
    }
}
