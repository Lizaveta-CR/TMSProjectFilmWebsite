package com.controller;

import com.entity.*;
import com.service.SecurityService;
import com.service.UserService;
import com.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginRegisterController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserEntity userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/";
    }

    @GetMapping("/")
    public String defaultPage() {
        return "index";
    }

    @GetMapping("/admin**")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessge = null;
        if (error != null) {
            errorMessge = "Username or Password is incorrect !";
        }
        if (logout != null) {
            errorMessge = "You have been successfully logged out !";
        }
        model.addAttribute("errorMessge", errorMessge);
        return "login";
    }

    @GetMapping("/forgotPass")
    public String forgotPassPage(Model model) {
        model.addAttribute("mobile", new UserEntity());
        return "forgotPass";
    }

    @PostMapping("/forgotPass")
    public String forgotPass(@ModelAttribute("mobile") UserEntity userEntity) {
        try {
            UserEntity user = userService.findByMobile(userEntity.getMobile());
            securityService.autoLogin(user.getUsername(), user.getPassword());
            return "redirect:/";
        } catch (NullPointerException e) {
            return "redirect:/registration";
        }
    }

    @GetMapping("/403")
    public String accessDenied(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addAttribute("username", userDetail.getUsername());
        }
        return "403";
    }

}