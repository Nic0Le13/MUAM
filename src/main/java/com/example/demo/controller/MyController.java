package com.example.demo.controller;

import com.example.demo.model.Painting;
import com.example.demo.repos.PainterRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MyController {
    @Autowired
    private PainterRepos painterRepos;

    @GetMapping("/menu")
    public String begin(){
        return "menu";
    }

    @GetMapping(path = "/menu", params = "addPainting")
    public String goToAdd(){
        return "redirect:/home";
    }

    @GetMapping(path = "/menu", params = "searchPainting")
    public String goToSearch(){
        return "redirect:/search";
    }

    @GetMapping(path = "/search", params = "back_to_menu")
    public String goBackToMenuFromFilter(){
        return "redirect:/menu";
    }

    @GetMapping(path = "/home", params = "back_to_menu")
    public String goToMenuFromMainPage(){
        return "redirect:/menu";
    }

    @GetMapping(path = "/home")
    public String formAddForPainting() {
        return "mainpage";
    }

    @GetMapping(path = "/search")
    public String formFilter() {
        return "filter";
    }

    @PostMapping(path = "/home")
    public String addPainting (@RequestParam String name, @RequestParam String painter, @RequestParam String buyer,
                               @RequestParam double askingPrice, @RequestParam double salePrice){
        Painting pain = new Painting(name, painter, buyer, askingPrice, salePrice);
        painterRepos.save(pain);
        return "mainpage";

    }

    @PostMapping (path = "/search")
    public String filter (@RequestParam String painter, Map<String, Object> model){
        Iterable<Painting> painters;
        if((painter == null) || (painter.isEmpty())) {
            painters = painterRepos.findAll();
        } else
            painters = painterRepos.findByPainter(painter);

        model.put("painters", painters);
        return "filter";
    }
}
