package io.javasideproject.coronavavirustracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaySafeController {
	
	
	@GetMapping("/safetyTips")
	public String newsPage(Model model) {
		return "safetyTips";
	}
}
