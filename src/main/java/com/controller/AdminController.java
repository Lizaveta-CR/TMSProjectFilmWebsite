package com.controller;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.entity.UserRole;
import com.model.PaginationResult;
import com.service.FilmEntityService;
import com.kinogo.Film;
import com.service.OrderService;
import com.service.UserService;
import com.validator.FilmPriceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
}
