package vttp.ssf.poandquote.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.poandquote.services.QuoteService;

@Controller // HTML content negotiation - manipulate resources through representation
@RequestMapping(path = "quote", produces = MediaType.TEXT_HTML_VALUE)
public class QuoteController {
    @Autowired
    private QuoteService quoteSvc;

    @GetMapping
    public String getQuote(Model model) {
        model.addAttribute("quote", quoteSvc.getQuote());
        return "quote";
    }
}
