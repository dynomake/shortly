package me.dynomake.shortly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import me.dynomake.shortly.ShortlyRepository;

@Controller
public class MainController {

    @Autowired
    private ShortlyRepository repository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    @RequestMapping("/terms")
    public String rules() {
        return "/terms.html";
    }

    @GetMapping("/404")
    public String notFound(Model model) {
        return "notFound";
    }

    @RequestMapping("/cut")
    public String test(@RequestParam String url, Model model) {
        model.addAttribute("result", repository.createLink(url));
        return "result";
    }
}
