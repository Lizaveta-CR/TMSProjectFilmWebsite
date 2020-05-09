package com.controller;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.entity.UserRole;
import com.model.PaginationResult;
import com.service.FilmEntityService;
import com.FilmStorageKinogo.Film;
import com.service.OrderService;
import com.service.UserService;
import com.validator.FilmPriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    FilmEntityService filmService;

    @Autowired
    FilmPriceValidator filmPriceValidator;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

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

    @GetMapping("/getAllOrdersFromStore")
    public String getAllFilmsFromStore(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        final int maxResult = 5;
        final int maxNavigationPage = 10;

        PaginationResult<OrderEntity> orders = orderService.getAll(page, maxResult, maxNavigationPage);
        model.addAttribute("orders", orders);
        return "adminOrders";
    }

    @GetMapping("/delete/{id}")
    public String deleteFilm(@PathVariable int id) {
        filmService.delete(id);
        return "redirect:/getAllFilmsFromStore";
    }

    @GetMapping("/statistics")
    public String ordersStatistics(Model model) {
        Map<UserEntity, Double> statistics = orderService.getStatistics();

        model.addAttribute("statistics", statistics);
        return "statistics";
    }

    @GetMapping("/allUsers")
    public String allUsers(Model model) {
        List<UserEntity> users = userService.getAll();
        users.stream().forEach(userEntity -> userEntity.setUserRole(userService.getRolesByUser(userEntity.getUsername())));
        model.addAttribute("users", users);
        return "allUsers";
    }

    @GetMapping("/addAdmin/{username}")
    public String addAdmin(@PathVariable("username") String username, Model model) {
        UserEntity byUsername = userService.findByUsername(username);
        for (UserRole userRole : byUsername.getUserRole()) {
            if (userRole.getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("user", byUsername);
                model.addAttribute("errorMessage", "Already.admin");
                return "errors/errorAdminAlready";
            }
        }
        userService.makeAdmin(byUsername);
        return "redirect:/admin/allUsers";
    }

    @GetMapping("/deleteAdmin/{username}")
    public String deleteAdmin(@PathVariable("username") String username, Model model) {
        UserEntity byUsername = userService.findByUsername(username);
        Set<UserRole> userRole = byUsername.getUserRole();
        if (userRole.size() == 1) {
            if (userRole.iterator().next().getRole().equals("ROLE_ADMIN")) {
                model.addAttribute("errorMessage", "Can't delete this admin!");
            } else {
                model.addAttribute("errorMessage", "No admin authorities");
            }
            return "errors/errorAdminAlready";
        }
        userService.deleteAuthority(byUsername);
        return "redirect:/admin/allUsers";
    }

    @GetMapping("/changePrice/{filmId}")
    public String changePricePage(@PathVariable String filmId, Model model) {
        FilmEntity filmById = filmService.getFilmById(Integer.parseInt(filmId));
        model.addAttribute("film", filmById);
        return "changePrice";
    }

    @PostMapping("/changePrice")
    public String changePrice(@ModelAttribute("film") FilmEntity filmForm, BindingResult bindingResult) {
        filmPriceValidator.validate(filmForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "changePrice";
        }
        String price = filmForm.getPrice();
        FilmEntity filmById = filmService.getFilmById(filmForm.getFilm_id());
        if (price.equals("0.0") || price.equals("0,0") || price.equals("0")) {
            filmById.setPrice("free");
        } else {
            try {
                Double doublePrice = Double.valueOf(filmForm.getPrice());
                DecimalFormat df = new DecimalFormat("0.00");
                filmById.setPrice(df.format(doublePrice));
            } catch (Exception e) {
                if (price.length() <= 3) {
                    price = price + "0";
                }
                filmById.setPrice(price);
            }
        }
        filmService.save(filmById);
        return "redirect:/getAllFilmsFromStore";
    }
}
