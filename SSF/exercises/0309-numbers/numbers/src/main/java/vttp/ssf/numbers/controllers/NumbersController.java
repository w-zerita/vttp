package vttp.ssf.numbers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/number")
public class NumbersController {
    
    @GetMapping(path="/{num}")           // consider using int
    public String getNumber(@PathVariable String num, Model model) {

        Integer toDisplay = 0;

        // input is not a number
        try {
            toDisplay = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            model.addAttribute("errMsg", "Not a number: " + num);
            return "error";
        }

        if (toDisplay < 0 || toDisplay > 30) {
            model.addAttribute("errMsg", "Number is out of range: " + num);
            return "error";
        }

        StringBuilder sb = new StringBuilder(); // ""
        sb.append("/numbers/number"); // "/numbers/number"
        sb.append(num); // "numbers/number1"
        sb.append(".jpg"); // "numbers/number1.jpg"
        String numUrl = sb.toString();
        model.addAttribute("numUrl", numUrl);
        return "display";
    }
}
