package vttp.ssf.rng.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.rng.models.RNG;
import vttp.ssf.rng.services.RNGService;

@Controller
@RequestMapping(path="/") // Random Number Generator
public class RNGController {

	@Autowired
	private RNGService rngSvc;

	private static final Logger logger = 
		Logger.getLogger(RNGController.class.getName());

	@GetMapping
	public String getRNG(Model model) {
		RNG rng = new RNG();
		model.addAttribute("rng", rng); // key/value
		return "generate"; // generate.html
	}

	@PostMapping(path="/generate")
	public String postInputVal(@ModelAttribute RNG rng, Model model) {
		int inputVal = rng.getInputVal();
		rng = rngSvc.generateNumbers(inputVal);
		logger.log(Level.INFO, ">>> Generate %S numbers!".formatted(inputVal));
		model.addAttribute("rng", rng);
		return "results";
	}
}
