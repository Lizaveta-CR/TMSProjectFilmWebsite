package com.controller;

import com.entity.FilmEntity;
import com.model.PaginationResult;
import com.service.FilmEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    FilmEntityService filmEntityService;

    @GetMapping("/getAllFilmsFromStore")
    public String getAllFilmsFromStore(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;

        PaginationResult<FilmEntity> films = filmEntityService.getAll(page, maxResult, maxNavigationPage);
        model.addAttribute("films", films);
        return "film";
    }

    @GetMapping("/description/{id}")
    public String getDescription(@PathVariable int id, Model model) {
        FilmEntity filmById = filmEntityService.getFilmById(id);
        String filmDescription = filmEntityService.getFilmDescription(filmById);
        model.addAttribute("filmDescription", filmDescription);
        return "description";
    }
}
