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
	private static String VIRUS_RECOVERED_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
	private static String VIRUS_CONFIRMED_US_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
	
	private List<LocationStats> allStats = new ArrayList<>();
	private int totalRecoveredCases;
	
	public int getTotalRecoveredCases() {
		return totalRecoveredCases;
	}
	public List<LocationStats> getAllStats() {
		return allStats;
	}

	//	when the server start, execute this method
	@PostConstruct
	// the first hour's of day execute this method
	@Scheduled(cron = "* * 1 * * *")
	public void fetchVirusData() throws IOException, InterruptedException {
		
		List<LocationStats> newStats = new ArrayList<>();
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_DATA_URL))
				.build();
		
		HttpRequest recoverdRequest = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_RECOVERED_DATA_URL))
				.build();
		
		HttpRequest comfirmedUSRequest = HttpRequest.newBuilder()
				.uri(URI.create(VIRUS_CONFIRMED_US_DATA_URL))
				.build();
		
		// getting the response					// sending the request
		HttpResponse<String> httpResponse =  client.send(request, HttpResponse.BodyHandlers.ofString());
		HttpResponse<String> httpRecoverdResponse =  client.send(recoverdRequest, HttpResponse.BodyHandlers.ofString());
		HttpResponse<String> httpComfirmedUSResponse =  client.send(comfirmedUSRequest, HttpResponse.BodyHandlers.ofString());

		// System.out.println(httpResponse.body());
		
		StringReader csvBodyReader = new StringReader(httpResponse.body());
		StringReader csvRecoveredBodyReader = new StringReader(httpRecoverdResponse.body());
		StringReader csvComfirmedUSBodyReader = new StringReader(httpComfirmedUSResponse.body());
		
		Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
		for (CSVRecord record : records) {
			
			String state = record.get("Province/State");
			String country = record.get("Country/Region");
			if(country.equals("US")) {
				continue;
			}
		    LocationStats newLocStat = new LocationStats();
		    newLocStat.setState(state);
		    newLocStat.setCountry(country);
		    int latestCases = Integer.parseInt( record.get(record.size() - 1) );
		    int prevDayCases = Integer.parseInt( record.get(record.size() - 2) );
		    newLocStat.setLatestTotalCases(latestCases);
		    newLocStat.setDiffFromPrevDay(latestCases - prevDayCases);
		    
		    newLocStat.setLatitude(record.get("Lat"));
		    newLocStat.setLongitude(record.get("Long"));
		    
		    newStats.add(newLocStat);
		}
		
		Iterable<CSVRecord> comfirmedUSRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvComfirmedUSBodyReader);
		for(CSVRecord record : comfirmedUSRecords) {
			
			String country = record.get("Country_Region");
			String state = record.get("Admin2") + ", " + record.get("Province_State");
			int latestCases = Integer.parseInt( record.get(record.size() - 1) );
		    int prevDayCases = Integer.parseInt( record.get(record.size() - 2) );
		    
		    LocationStats usLocStat = new LocationStats();
		    
		    usLocStat.setState(state);
		    usLocStat.setCountry(country);
		    usLocStat.setLatestTotalCases(latestCases);
		    usLocStat.setDiffFromPrevDay(latestCases - prevDayCases);
		    usLocStat.setLatitude(record.get("Lat"));
		    usLocStat.setLongitude(record.get("Long_"));
		    newStats.add(usLocStat);

		}
		
		
		
		Iterable<CSVRecord> recoverRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvRecoveredBodyReader);
		for(CSVRecord record: recoverRecords) {
			totalRecoveredCases = totalRecoveredCases + Integer.parseInt(record.get(record.size() - 1));
		}
		
		
		
		this.allStats = newStats;
	}
}
