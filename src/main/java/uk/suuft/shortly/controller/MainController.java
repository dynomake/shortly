package uk.suuft.shortly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uk.suuft.shortly.ShortlyRepository;

@Controller
public class MainController {

    @Autowired
    private ShortlyRepository repository;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/style")
    public String style() {
        return "index.css";
    }


    @RequestMapping("/rules")
    public String rules() {
        return "rules";
    }

    @GetMapping("/404")
    public String notFound(Model model) {
        return "notFound";
    }

    @GetMapping("/test")
    public String test(Model model) {
        model.addAttribute("pageTitle", "Welcome to my page!");
        model.addAttribute("pageContent", "This is some dynamic content.");
        return "thymeleafTemplate";
    }
    @RequestMapping("/cut")
    public String test(@RequestParam String url, Model model) {
        model.addAttribute("result", repository.createLink(url));
        return "result";
    }
}
