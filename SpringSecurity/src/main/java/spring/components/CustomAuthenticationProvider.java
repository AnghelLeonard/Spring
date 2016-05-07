package spring.components;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anghel Leonard
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginDelegate loginDelegate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (authorizedUser(userName, password)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(() -> {
                return "AUTH_USER";
            });
            Authentication auth = new UsernamePasswordAuthenticationToken(userName, password, grantedAuths);
            System.out.println(auth.getAuthorities());
            return auth;
        } else {
            throw new AuthenticationCredentialsNotFoundException("Invalid Credentials!");
        }
    }

    private boolean authorizedUser(String userName, String password) {
        System.out.println("username is :" + userName + " and password is " + password + " loginDelegate:" + loginDelegate);
        try {
            return loginDelegate.isValidUser(userName, password);
        } catch (SQLException ex) {
            Logger.getLogger(CustomAuthenticationProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
