package com.controller;

import com.service.FilmService;
import com.kinogo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    FilmService filmService;

    @GetMapping("/parse")
    public String getReloadPage(@RequestParam String filmNum) throws Exception {
        List<Film> parse = filmService.parse(Integer.parseInt(filmNum));
        filmService.saveFilms(parse);
        return "redirect:/";
    }
}
