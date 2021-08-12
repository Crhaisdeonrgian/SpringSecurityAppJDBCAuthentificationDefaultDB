package com.chidorirasengan.controller;

import com.chidorirasengan.model.ChangePasswordModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.Collection;

/**
 * See some comments later...
 * @author crhaisdeonrgian [https://github.com/Crhaisdeonrgian]
 */
@Controller
public class HelloWorldController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JdbcUserDetailsManager userDetailsManager;
    @GetMapping("/hello")
    public String hello(Principal principal, Authentication auth, Model model){

        String username = principal.getName();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        model.addAttribute("username", username);
        model.addAttribute("roles", authorities);
        return "helloworld";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("username")String username){
        userDetailsManager.deleteUser(username);
        return "redirect:/loginpage";
    }
    @GetMapping("/changePassword")
    public String changePassword(Model model){
        model.addAttribute("passwordchng", new ChangePasswordModel());
        return "changepasswordpage";
    }
    @PostMapping("/save-password")
    public String savepassword(Principal principal, ChangePasswordModel changePasswordModel){
        UserDetails userDetails = userDetailsManager.loadUserByUsername(principal.getName());
        boolean matches = passwordEncoder.matches(changePasswordModel.getOldPassword(),userDetails.getPassword());
        if(!changePasswordModel.getNewPassword().equals(changePasswordModel.getConfirmPassword())){
            return "redirect:/changePassword?notMatched";
        }
        if(matches) {
        userDetailsManager.changePassword(changePasswordModel.getOldPassword(), passwordEncoder.encode(changePasswordModel.getConfirmPassword()));
        return "redirect:/";}
        return "redirect:/changePassword?invalidPassword";
    }
    @GetMapping("/")
    public String main(){
        return "mainpage";
    }
    @GetMapping("/admin")
    public String adminzone(){
        return "adminpage";
    }
    @GetMapping("/accessDenied")
    public String accessdenied(){
        return "accessdenied";
    }
}
