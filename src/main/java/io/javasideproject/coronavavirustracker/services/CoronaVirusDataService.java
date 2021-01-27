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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import io.javasideproject.coronavavirustracker.models.LocationStats;

@Service
public class CoronaVirusDataService {
	private static String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
	private List<LocationStats> allStats = new ArrayList<>();
	
	public List<LocationStats> getAllStats() {
		return allStats;
	}

	//	when the server start execute this method
	@PostConstruct
	// the first hour's of day execute this method
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationStats> newStats = new ArrayList<>();
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_DATA_URL))
				.build();
		// getting the response					// sending the request
		HttpResponse<String> httpResponse =  client.send(request, HttpResponse.BodyHandlers.ofString());
		// System.out.println(httpResponse.body());
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
		    LocationStats newLocStat = new LocationStats();
		    newLocStat.setState(record.get("Province/State"));
		    newLocStat.setCountry(record.get("Country/Region"));
		    int latestCases = Integer.parseInt( record.get(record.size() - 1) );
		    int prevDayCases = Integer.parseInt( record.get(record.size() - 2) );
		    newLocStat.setLatestTotalCases(latestCases);
		    newLocStat.setDiffFromPrevDay(latestCases - prevDayCases);
		    
//		    newLocStat.setGeometry(record.get("Long"), record.get("Lat"));
		    newLocStat.setLatitude(record.get("Lat"));
		    newLocStat.setLongitude(record.get("Long"));
		    
		    newStats.add(newLocStat);
		}
		this.allStats = newStats;
	}
}
