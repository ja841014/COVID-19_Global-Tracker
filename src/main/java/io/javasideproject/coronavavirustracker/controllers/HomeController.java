package io.javasideproject.coronavavirustracker.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.javasideproject.coronavavirustracker.models.LocationStats;
import io.javasideproject.coronavavirustracker.services.CoronaVirusDataService;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronaVirusDataService;

	// return a template
	@GetMapping("/")
	public String home(Model model, String country) {
		List<LocationStats> allStats = coronaVirusDataService.getAllStats();
		List<LocationStats> searchStats = new ArrayList<>();
		System.out.println("country: " + country);
		int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
//		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("totalNewCases", totalNewCases);
		model.addAttribute("totalRecoveredCases", coronaVirusDataService.getTotalRecoveredCases());
		
		if(country != null && !country.isEmpty()) {
			for(LocationStats loc : allStats) {
				if(loc.getCountry().toLowerCase().equals(country.toLowerCase())) {
					searchStats.add(loc);
				}
			}
			model.addAttribute("locationStats", searchStats);
		}
		else {
			model.addAttribute("locationStats", allStats);
		}
		
		return "home";
	}
	@GetMapping("/safetyTips")
	public String safetytips(Model model) {
		
		return "safetyTips";
	}
}
