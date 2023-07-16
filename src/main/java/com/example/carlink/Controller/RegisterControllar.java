package com.example.carlink.Controller;

import com.example.carlink.Entity.Register;
import com.example.carlink.Repository.RegisterRepository;
import com.example.carlink.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class RegisterControllar {

    @Autowired

    private RegisterService RegService;
    private RegisterRepository regrepo;

    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new Register());
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveReg(@ModelAttribute("Register") Register register){
        RegService.save(register);
        return "redirect:/login";
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("user") Register user) {
        Register regUser = RegService.save(user);
        if (regUser != null) {
            regrepo.save(regUser);
            // Redirect to success page or perform other actions
            return "success";
        } else {
            // Redirect to registration page with error message
            return "redirect:/register";
        }
    }




}
