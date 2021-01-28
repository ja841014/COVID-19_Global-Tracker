package io.javasideproject.coronavavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.javasideproject.coronavavirustracker.models.LocationStats;
import io.javasideproject.coronavavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronaVirusDataService;

	// return a template
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("totalRecoveredCases", coronaVirusDataService.getTotalRecoveredCases());

		return "home";
	}
}
