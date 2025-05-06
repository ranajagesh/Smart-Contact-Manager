package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {

   private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService usersService;

    @ModelAttribute
    public void addLoggedinUserInformation(Model model , Authentication authentication){
        if(authentication == null) {
            return;
        }
        System.out.println("User Logged in user information to the model");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("user logged in :{}",username);     
        // fetch data from database : user from db
        User user = usersService.getUserByEmail(username);
        System.out.println(user); 
        System.out.println(user.getName());
        System.out.println(user.getEmail());

        model.addAttribute("loggedInUser", user);


    }

}
