package com.scm.helper;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        // AuthenticationPrincipal principal = (AuthenticationPrincipal)authentication.getPrincipal();

        // how to fetch email if login with email and password
        if (authentication instanceof OAuth2AuthenticationToken) {

            var aOAuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuthenticationToken.getAuthorizedClientRegistrationId();
    
            var oauth2User = (OAuth2User)authentication.getPrincipal();
            String username ="";

            if(clientId.equalsIgnoreCase("google")) {
                // how to fetch email if login with google
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();
            }
            else if (clientId.equalsIgnoreCase("github")){
                // how to fetch email if login with github 
                System.out.println("Getting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }

            return username;
 
        }
        else {
            System.out.println("Getting data from local data base");
            return authentication.getName();
        }

    }


    public static String getLinkForEmailVerification(String emailToken) {
        String link = "http://localhost:8081/auth/verify-email?token=" +emailToken;
        return link;
    }
}
