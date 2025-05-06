package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("Home Page Handler");
        model.addAttribute("name", "Welcome to home page");
        model.addAttribute("youtubeChennel", "YouTube Channel ");
        model.addAttribute("githubRepo", "Git Hub Repository");
        return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage() {
        System.out.println("About Page loading");
        return "about";
    }

    // services
    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("Services Page loading");
        return "services";
    }

    // contact us

    @GetMapping("contact")
    public String contact() {
        return new String("contact");
    }

    // this is showing login page
    @GetMapping("login")
    public String login() {
        return new String("login");
    }

    // registration page
    @GetMapping("register")
    public String register(Model model) {

        UserForm userForm = new UserForm();
        // default data bhi daal sakte hai
        model.addAttribute("userForm", userForm);
        return"register";
    }

    // processing register    
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm , BindingResult rBindingResult, HttpSession session) {
        System.out.println("processing register");
        // fetch form data
        // user form
        System.out.println(userForm);
        // validate form data

        if (rBindingResult.hasErrors()) {
            return "register";
        }

        // save to database

        // user service
    /* 
        Convert UserForm to User
        User user = User.builder()
        .name(userForm.getName())
        .email(userForm.getEmail())
        .password(userForm.getPassword())
        .about(userForm.getAbout())
        .phoneNumber(userForm.getPhoneNumber())
        .profilePic("https://www.google.com/search?q=default+profile+picture&oq=default+pro&gs_lcrp=EgZjaHJvbWUqCggBEAAYsQMYgAQyBggAEEUYOTIKCAEQABixAxiABDIHCAIQABiABDIHCAMQABiABDIHCAQQABiABDIHCAUQABiABDIHCAYQABiABDIHCAcQABiABDIHCAgQABiABDIHCAkQABiABNIBCDc2MDNqMGo3qAIAsAIA&sourceid=chrome&ie=UTF-8#vhid=a79EuCifZPtiYM&vssid=l")
        .build();
    */

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setEnabled(false);
        user.setProfilePic(
            "https://www.google.com/search?q=default+profile+picture&oq=default+pro&gs_lcrp=EgZjaHJvbWUqCggBEAAYsQMYgAQyBggAEEUYOTIKCAEQABixAxiABDIHCAIQABiABDIHCAMQABiABDIHCAQQABiABDIHCAUQABiABDIHCAYQABiABDIHCAcQABiABDIHCAgQABiABDIHCAkQABiABNIBCDc2MDNqMGo3qAIAsAIA&sourceid=chrome&ie=UTF-8#vhid=a79EuCifZPtiYM&vssid=l");
        
        User saveduser = userService.saveUser(user); 

        System.out.println("User saved :" +saveduser);

        // message registration successfull
        // Add the message
       Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();

        session.setAttribute("message", message);

        // redirect to login page
        return "redirect:/register";
    }       
}
