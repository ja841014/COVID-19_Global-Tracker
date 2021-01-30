package io.javasideproject.coronavavirustracker.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;


import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javasideproject.coronavavirustracker.models.NewsInfo;

@Service
public class NewsApiDataService {
	
	private static String NEWS_DATA_URL = "https://newsapi.org/v2/top-headlines?q=Covid-19&apiKey=02c6d78f468549408055033343a7c50d";
//	private List<NewsInfo> allNews = new ArrayList<>();
//	public List<NewsInfo> getAllNews() {
//		return allNews;
//	}
	public String allNews;
	public String getAllNews() {
		return allNews;
	}

	
	@PostConstruct
	public void fetchNewsData() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(NEWS_DATA_URL))
				.build();
		HttpResponse<String> httpResponse =  client.send(request, HttpResponse.BodyHandlers.ofString());
		String tmpNews = new String(httpResponse.body());
		allNews = tmpNews;
//		ObjectMapper mapper = new ObjectMapper();
//		List<NewsInfo> tmpNews = mapper.readValue(httpResponse.body(), new TypeReference<List<NewsInfo>>() {});
//		allNews = tmpNews;

	}

}
