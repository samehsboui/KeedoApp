package tn.esprit.pi.security;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.pi.entities.User;
import tn.esprit.pi.services.IUserservice;
import tn.esprit.pi.services.UserService;
@Component
public class CustomLoginSuccessHandler {
	
	@Autowired
	PasswordEncoder encoder;
    @Autowired
    private IUserservice userService;
    @Transactional
    public boolean onAuthenticationSuccess(String username, String password) throws Exception {
    	User user =  userService.findUserBylogin(username);
    	if (user==null)
    		return false;
    	else if (encoder.matches(password, user.getPassword())) {
            if (user.getFailedAttempt() > 0) {
                userService.resetFailedAttempts(user.getLogin());
            }
    		return true;
    	}
        if (user.isValid() && user.isAccountNonLocked()) {
            if (user.getFailedAttempt() < UserService.MAX_FAILED_ATTEMPTS - 1) {
                userService.increaseFailedAttempts(user);
                return false;
            } else {
                userService.lock(user);
                return false;
            }
        } else if (!user.isAccountNonLocked()) {
            if (userService.unlockWhenTimeExpired(user)) {
            	return false;
            }
        }
        return false;
    }
}