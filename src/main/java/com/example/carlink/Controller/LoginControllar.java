package com.example.carlink.Controller;
import java.util.Objects;

import com.example.carlink.Entity.Login;
import com.example.carlink.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginControllar {

     @Autowired
    private LoginService loginService;

     @GetMapping("/login")
    public ModelAndView login(){
         ModelAndView mav = new ModelAndView("login");
         mav.addObject("user",new Login());
         return mav;
     }


     @PostMapping("/login")
    public String login(@ModelAttribute("user") Login user){
         Login oathUser =loginService.login(user.getUsername(), user.getPassword());



         if (Objects.nonNull(oathUser)) {
             return "redirect:/";
         } else {
             return "redirect:/login";
         }


     }

}
