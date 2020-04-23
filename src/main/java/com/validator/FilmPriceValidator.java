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
        if (!priceRegex(film.getPrice())) {//надо еще подумать
            errors.rejectValue("price", "FilmForm.correct.numbers");
        } else {
            if (film.getPrice().contains(",")) {
                String price = film.getPrice().replace(",", ".");
                if (!priceLimits(price)) {
                    errors.rejectValue("price", "FilmForm.size");
                }
            } else {
                if (!priceLimits(film.getPrice())) {
                    errors.rejectValue("price", "FilmForm.size");
                }
            }
        }
    }

    private boolean priceRegex(String price) {
        String regExp = "[0-9]+([,.][0-9]{1,2})?";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(price);
        return matcher.matches();
    }

    private boolean priceLimits(String price) {
        if (Double.valueOf(price) > 2.10 || Double.valueOf(price) < 0.0) {
            return false;
        }
        return true;
    }

    private boolean containsSpecialChars(String string) {
        Pattern pSymbols = Pattern.compile("\\W");
        Matcher mSymbols = pSymbols.matcher(string);
        return mSymbols.find();
    }
}
