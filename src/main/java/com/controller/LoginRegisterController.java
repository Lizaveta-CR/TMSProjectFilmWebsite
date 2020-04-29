package com.controller;

import com.entity.*;
import com.model.PlainTextEmailSender;
import com.service.SecurityService;
import com.service.UserService;
import com.validator.UserValidator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginRegisterController {
    private static final Logger logger = LogManager.getLogger(LoginRegisterController.class);
    private final String host = "smtp.gmail.com";
    private final String port = "587";
    private final String mailFrom = "filmwebsitejava@gmail.com";
    private final String password = "FilmWebsiteJava2020";
    private String userPassCode = null;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserEntity());
        model.addAttribute("helloMessage", "hello.message");
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

        String subject = "Thank you for registration!";
        String message = "We are happy to see you in our film-shop. Have a nice day!:)";

        PlainTextEmailSender mailer = new PlainTextEmailSender();

        try {
            mailer.sendPlainTextEmail(host, port, mailFrom, password, userForm.getEmail(),
                    subject, message);
            logger.info("Email sent on address: " + userForm.getEmail());
        } catch (Exception ex) {
            logger.info("Failed to sent email.");
            ex.printStackTrace();
        }
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
    public String forgotPassPage() {
        return "forgotPassChoice";
    }

    @GetMapping("/forgotPassMobile")
    public String forgotPassPageMobile(Model model) {
        model.addAttribute("mobile", new UserEntity());
        return "forgotPassMobile";
    }

    @PostMapping("/forgotPassMobile")
    public String forgotPassMobile(@ModelAttribute("mobile") UserEntity userEntity) {
        String mobile = userEntity.getMobile();
        if (mobile.matches("^[0-9]*$")) {
            try {
                UserEntity user = userService.findByMobile(userEntity.getMobile());
                securityService.autoLogin(user.getUsername(), user.getPassword());
                return "redirect:/";
            } catch (NullPointerException e) {
                return "redirect:/registration";
            }
        } else {
            return "errors/mobileFormatError";
        }
    }

    @GetMapping("/forgotPassEmail")
    public String forgotPassPageEmail(Model model) {
        model.addAttribute("email", new UserEntity());
        return "forgotPassEmail";
    }

    @PostMapping("/forgotPassEmail")
    public String forgotPassEmail(@ModelAttribute("email") UserEntity userEntity, Model model) {
        String email = userEntity.getEmail();
        Pattern valid_email_regex = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = valid_email_regex.matcher(email);
        if (matcher.find()) {
            userPassCode = String.valueOf((int) (Math.random() * 100));
            try {
                PlainTextEmailSender mailer = new PlainTextEmailSender();
                String subject = "Let's update your password!";
                mailer.sendPlainTextEmail(host, port, mailFrom, password, email,
                        subject, userPassCode);
                logger.info("Email sent.");
            } catch (Exception ex) {
                logger.info("Failed to sent email.");
                return "errors/noSuchOperation";
            }
            model.addAttribute("email", email);
            model.addAttribute("userPassCode", userPassCode);
            return "emailConfirmationPass";
        } else {
            return "errors/emailError";
        }
    }

    @GetMapping("/forgotPassEmailConfirm")
    public String forgotPassEmailConfirm(@RequestParam String email, @RequestParam String code, Model model) {
        if (code.equals(userPassCode)) {
            try {
                UserEntity user = userService.findByEmail(email);
                if (user.isEnabled() == false) {
                    user.setEnabled(true);
                    userService.update(user);
                }
                securityService.autoLogin(user.getUsername(), user.getPassword());
                return "redirect:/";
            } catch (NullPointerException e) {
                return "redirect:/registration";
            }
        } else {
            model.addAttribute("errorMessage", "WRONG!");
            return "errors/emailError";
        }
    }

    @GetMapping("/retainAccount")
    public String retainAccountPage(Model model) {
        model.addAttribute("email", new UserEntity());
        return "retainAccount";
    }

    @PostMapping("/retainAccount")
    public String retainAccount(@ModelAttribute("email") UserEntity userEntity, Model model) {
        return forgotPassEmail(userEntity, model);
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