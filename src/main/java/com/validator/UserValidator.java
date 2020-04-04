package com.validator;

import com.entity.UserEntity;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEntity user = (UserEntity) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (containsSpecialChars(user.getUsername()) || containsDigits(user.getUsername())) {
            errors.rejectValue("username", "Username.English.Only");
        }

        if (user.getUsername().length() < 3 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
        if (userService.findByMobile(user.getMobile()) != null) {
            errors.rejectValue("mobile", "Duplicate.userForm.mobile");
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobile", "NotEmpty");

        if (!containsDigits(user.getMobile())) {
            errors.rejectValue("mobile", "Mobile.only.digits");
        }
        if (user.getMobile().length() < 5 || user.getMobile().length() > 10) {
            errors.rejectValue("mobile", "Size.userForm.mobile");
        }
    }

    private boolean containsSpecialChars(String string) {
        Pattern pSymbols = Pattern.compile("\\W");
        Matcher mSymbols = pSymbols.matcher(string);
        return mSymbols.find();
    }

    private boolean containsDigits(String string) {
        Pattern pDigits = Pattern.compile("^[0-9]");
        Matcher mDigits = pDigits.matcher(string);
        return mDigits.find();
    }
}
