package com.controller;

import com.entity.FilmEntity;
import com.service.FilmEntityService;
import com.kinogo.Film;
import com.validator.FilmPriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    FilmEntityService filmService;

    @Autowired
    FilmPriceValidator filmPriceValidator;

    @GetMapping("/getFilmsFromSite")
    public String getReloadPage(@RequestParam String filmNum, HttpServletRequest httpServletRequest) throws Exception {
        Locale locale = httpServletRequest.getLocale();
        String language = locale.getLanguage();
        try {
            List<Film> parse = filmService.parse(Integer.parseInt(filmNum));
            filmService.saveFilms(parse, language);
            return "redirect:/admin";
        } catch (NumberFormatException e) {
            return "errors/numberFormatException";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteFilm(@PathVariable int id) {
        filmService.delete(id);
        return "redirect:/getAllFilmsFromStore";
    }

//    @GetMapping("/edit/{id}")
//    public String editFilmPrice(@PathVariable int id, Model m) {
//        FilmEntity filmById = filmService.getFilmById(id);
//        m.addAttribute("film", filmById);
////        List<FilmEntity> filmList = filmService.getAll();
////        m.addAttribute("filmList", filmList);
//        return "filmEditForm";
//    }
//
//    @PostMapping("/edit")
//    public String editSaveFilmPrice(@ModelAttribute("film") FilmEntity filmEntity) {
////        filmPriceValidator.validate(film, bindingResult);
////        if (bindingResult.hasErrors()) {
////            return "filmEditForm";
////        }
//        filmService.update(filmEntity);
//        return "redirect:/getAllFilmsFromStore";
//    }
}
