package io.javasideproject.coronavavirustracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.javasideproject.coronavavirustracker.models.NewsInfo;
import io.javasideproject.coronavavirustracker.services.NewsApiDataService;

@Controller
public class NewsController {
	
	@Autowired
	NewsApiDataService newsApiDataService;
	
//	https://newsapi.org/v2/top-headlines?q=Covid-19&apiKey=02c6d78f468549408055033343a7c50d
	@GetMapping("/news")
	public String newsPage(Model model) {
//		 List<NewsInfo> allNews = newsApiDataService.getAllNews();
		String allNews = newsApiDataService.getAllNews();
//		System.out.print("newcontrolelr: "+newsData);
		model.addAttribute("newsData", allNews);
		return "news";
	}
}
