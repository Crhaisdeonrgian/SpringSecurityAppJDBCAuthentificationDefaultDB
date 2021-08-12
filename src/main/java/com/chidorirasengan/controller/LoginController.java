package com.chidorirasengan.controller;

import com.chidorirasengan.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * See some comments later...
 * @author crhaisdeonrgian [https://github.com/Crhaisdeonrgian]
 */
@Controller
public class LoginController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @GetMapping("/loginpage")
    public String loginpage(){
        return "loginpage";
    }
    @GetMapping("/signup")
    public String signpage(@ModelAttribute("signupdto") UserModel userModel){
        return "signup";
    }
    @PostMapping("/process-signup")
    public String signprocess(UserModel userModel){
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        //signupDAO.saveUser(userModel);
        UserDetails userDetails = User.withUsername(userModel.getUsername()).password(userModel.getPassword()).roles("USER").build();

        jdbcUserDetailsManager.createUser(userDetails);
        return "redirect:/loginpage";
    }
}
