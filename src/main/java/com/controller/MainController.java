package com.controller;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.model.PaginationResult;
import com.model.UserStorage;
import com.service.FilmEntityService;
import com.service.OrderService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;


@Controller
public class MainController {
    private final Map<String, UserStorage> userFilmMap = new HashMap<>();

    @Autowired
    FilmEntityService filmEntityService;

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

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

    @GetMapping("/buy/{id}")
    public String buyPage(@PathVariable int id, Model model, Authentication authentication) {
        Set<FilmEntity> films = new HashSet<>();
        UserEntity user = userService.findByUsername(authentication.getName());
        FilmEntity filmById = filmEntityService.getFilmById(id);

        UserStorage userStorage = new UserStorage();
        userStorage.setUsername(user.getUsername());
        userStorage.addFilm(filmById.getName());

        Set<String> filmNames = userStorage.getFilmNames();
        for (String filmName : filmNames) {
            films.add(filmEntityService.getFilmByFilmName(filmName));
        }

        OrderEntity order = new OrderEntity();
        order.setFilms(films);

        order.setUser(user);
        filmById.addOrder(order);

        userStorage.setOrder(order);

        userFilmMap.put(user.getUsername(), userStorage);

        double totalPrice = 0;
        for (FilmEntity film : order.getFilms()) {
            String price = film.getPrice();
            if (price.equals("free")) {
                totalPrice = totalPrice + 0;
            } else {
                double doubleFilmPrice = Double.parseDouble(price.replace(',', '.'));
                totalPrice = totalPrice + doubleFilmPrice;
                BigDecimal totalPriceBigDecimal = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
                totalPrice = totalPriceBigDecimal.doubleValue();
            }
        }

        model.addAttribute("user", userStorage);
        model.addAttribute("film", films);
        model.addAttribute("price", totalPrice);
        return "buyPage";
    }

    @GetMapping("addFilmToOrder")
    public String addFilmToOrder() {
        return "redirect:/getAllFilmsFromStore";
    }

    @GetMapping("/confirmOrder/{username}")
    public String confirmOrder(@PathVariable String username, Model model) {
        double totalPrice = 0;
        UserStorage userStorage = userFilmMap.get(username);
        OrderEntity order = userStorage.getOrder();

        orderService.saveOrder(order);
        return "redirect:/";
    }

    @GetMapping("/showUserOrders/{username}")
    public String showUserOrders(@PathVariable String username, Model model) {
        UserEntity byUsername = userService.findByUsername(username);
        List<OrderEntity> ordersByUsername = orderService.getOrdersByUsername(byUsername);

        model.addAttribute("orders", ordersByUsername);
        return "userOrders";
    }
}
