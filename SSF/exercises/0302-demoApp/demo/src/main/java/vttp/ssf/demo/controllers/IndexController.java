package vttp.ssf.demo.controllers;

import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.demo.models.Person;

@Controller
@RequestMapping(path = "demo", produces = MediaType.TEXT_HTML_VALUE)
public class IndexController {
    
    @GetMapping("currTime")
    public String getCurrDateTime(Model model) {
        model.addAttribute("currTime", new Date().toString());
        return "currTime";
    }

    @GetMapping("person")
    public String getPerson(Model model) {
        Person p = new Person();
        p.setId(1);
        p.setFirstName("Zerita");
        p.setLastName("Wong");
        p.setGender('F');
        model.addAttribute("person", p);
        return "person";
    }
}
