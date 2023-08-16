package me.dynomake.shortly.controller;

import me.dynomake.shortly.config.ShortlyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import me.dynomake.shortly.ShortlyRepository;

@RestController
public class MixController {

    @Autowired
    private ShortlyRepository repository;
    @Autowired
    private ShortlyConfiguration configuration;

    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    @RequestMapping("/{code}")
    public RedirectView find(@PathVariable(name = "code") String code) {
        String link = repository.getLink(code);

        if (link == null)
            return new RedirectView(configuration.getDomain() + "/404");

        return new RedirectView(link);
    }

}
