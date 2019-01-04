package ro.rosmof.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class VisitorAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    @Qualifier("visitorDetailsService")
    private UserDetailsService visitorDetails;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        VisitorDetails d = (VisitorDetails) visitorDetails.loadUserByUsername(authentication.getName());
        Authentication auth = new UsernamePasswordAuthenticationToken(d.getUsername(), d.getPassword(), d.getAuthorities());
        return auth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
