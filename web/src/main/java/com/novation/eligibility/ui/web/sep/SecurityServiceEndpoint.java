package com.novation.eligibility.ui.web.sep;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.io.Files;
import com.novation.eligibility.ui.web.model.User;


@Controller
@RequestMapping("/security")
public class SecurityServiceEndpoint {
	
	private static final Logger log = LoggerFactory.getLogger(SecurityServiceEndpoint.class);
	

    @RequestMapping("/authenticate")
    public @ResponseBody void login() {
    	log.info("Reached authenticate");
    }    
    
    @RequestMapping("/fetchuser")
    public @ResponseBody User fetchUser() {
    	log.info("Reached fetch user");
    	User user = createTestUser();
        return user;
    }    
    
    // TODO purely temporary method, remove local file reading by reading from OIM.
	private User createTestUser() {
		User user = new User();
		user.setUserName("TestUser");
		try {
			user.setRole( Files.toString(new File("/tmp/role.txt"), Charset.defaultCharset()));
		} catch (IOException e) {
			user.setRole(User.MEMBER_ROLE);
		}  
		return user;
	}

	@RequestMapping("/fetchCxrfCookie")
    public @ResponseBody String returnCxrfCookie() {
        return "H4TR56TYUI";
    }
	
	@RequestMapping("/fetchSsoAuthCookie")
    public @ResponseBody String returnSsoAuthCookie(ModelMap modelMap) {
        return "H4TR56TYUI";
    }
    

}
