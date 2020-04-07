package com.controller;

import com.service.FilmEntityService;
import com.kinogo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    FilmEntityService filmService;

    @GetMapping("/getFilmsFromSite")
    public String getReloadPage(@RequestParam String filmNum) throws Exception {
        try {
            List<Film> parse = filmService.parse(Integer.parseInt(filmNum));
            filmService.saveFilms(parse);
            return "redirect:/admin";
        } catch (NumberFormatException e) {
            return "errors/numberFormatException";
        }

    }
}
