package com.validator;

import com.entity.FilmEntity;
import com.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FilmPriceValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserEntity.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FilmEntity film = (FilmEntity) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");
        if (!priceRegex(film.getPrice())) {
            errors.rejectValue("price", "FilmForm.correct.numbers");
        }

        if (film.getPrice().length() < 2 || film.getPrice().length() > 5) {
            errors.rejectValue("price", "FilmForm.size");
        }
    }

    private boolean priceRegex(String price) {
        Pattern pattern = Pattern.compile("\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})?");
        Matcher matcher = pattern.matcher(price);
        return matcher.find();

    }
}
