package com.controller;

import com.entity.FilmEntity;
import com.entity.OrderEntity;
import com.entity.UserEntity;
import com.model.OrderCart;
import com.model.PaginationResult;
import com.service.FilmEntityService;
import com.service.OrderService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Controller
public class MainController {
    private Set<FilmEntity> filmEntitySet = new HashSet<>();

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
    public String getDescription(@PathVariable int id, Model model) throws IOException {
        FilmEntity filmById = filmEntityService.getFilmById(id);
        String filmDescription = filmEntityService.getFilmDescription(filmById);
        model.addAttribute("filmDescription", filmDescription);
        return "description";
    }


    @GetMapping("/buy/{id}")
    public String buy(@PathVariable int id, Model model, Authentication authentication) {
        UserEntity user = userService.findByUsername(authentication.getName());
        FilmEntity filmById = filmEntityService.getFilmById(id);
        for (FilmEntity filmEntity : filmEntitySet) {
            if (filmEntity.getName().equals(filmById.getName()))
                return "errors/errorAddedFilm";
        }
        filmEntitySet.add(filmEntityService.getFilmByFilmName(filmById.getName()));
        OrderCart orderCart = new OrderCart();
        double totalPrice = orderCart.buildTotalPrice(filmEntitySet);
        model.addAttribute("user", user);
        model.addAttribute("film", filmEntitySet);
        model.addAttribute("price", totalPrice);
        return "buyPage";

    }

    @GetMapping("/addFilmToOrder")
    public String addFilmToOrder() {
        return "redirect:/getAllFilmsFromStore";
    }

    @GetMapping("/removeFilmFromOrder/{filmName}")
    public String removeFilmFromOrder(@PathVariable String filmName, Authentication authentication, Model model) {
        FilmEntity film = filmEntityService.getFilmByFilmName(filmName);
        UserEntity user = userService.findByUsername(authentication.getName());
        try {
            Iterator<FilmEntity> iterator = filmEntitySet.iterator();
            while (iterator.hasNext()) {
                FilmEntity nextFilm = iterator.next();
                if (nextFilm.getName().equals(film.getName())) {
                    iterator.remove();
                }
            }
            OrderCart orderCart = new OrderCart();
            double totalPrice = orderCart.buildTotalPrice(filmEntitySet);
            model.addAttribute("user", user);
            model.addAttribute("price", totalPrice);
            model.addAttribute("film", filmEntitySet);
        } catch (NullPointerException e) {
            return "errors/noOrders";
        }
        return "buyPage";
    }

    @GetMapping("/removeAllOrders")
    public String removeAllOrders() {
        filmEntitySet.clear();
        return "redirect:/getAllFilmsFromStore";
    }

    @GetMapping("/confirmOrder/{username}/{price}")
    public String confirmOrder(@PathVariable String username, @PathVariable String price, Model model) {
        Map<String, String> nameLink = new HashMap<>();
        if (!filmEntitySet.isEmpty()) {
            UserEntity user = userService.findByUsername(username);
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setUser(user);
            orderEntity.setPrice(Double.parseDouble(price));
            orderEntity.setDate(new Date());
            filmEntitySet.stream().forEach(filmEntity -> {
                orderEntity.getFilms().add(filmEntity);
                nameLink.put(filmEntity.getName(), filmEntity.getLink());
            });
            filmEntitySet.stream().forEach(filmEntity -> filmEntity.getOrders().add(orderEntity));

            filmEntitySet.clear();
            orderService.saveOrder(orderEntity);
            model.addAttribute("nameLink", nameLink);
            return "linkPage";
        } else {
            return "errors/noOrders";
        }
    }

    @GetMapping("/showUserOrders/{username}")
    public String showUserOrders(@PathVariable String username, Model model) {
        try {
            UserEntity byUsername = userService.findByUsername(username);
            List<OrderEntity> ordersByUsername = orderService.getOrdersByUsername(byUsername);

            ordersByUsername.stream().forEach(orderEntity -> {
                orderEntity.setFilms(orderService.getFilmsByOrder(orderEntity.getOrder_id()));
            });
            model.addAttribute("orders", ordersByUsername);
            return "userOrders";
        } catch (NullPointerException e) {
            return "errors/noOrders";
        }
    }
}
