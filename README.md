# COVID-19 Global-Tracker

## Introduction
	
It is a full stack application by using Java Spring boot framework and Thymeleaf template engine. This project visualize data which provided by Johns Hopkins University Center for Systems Science and Engineering (JHU CSSE). The application can see total confirmed cases, new cases in each country and display on the cluster map. It also display the COVID-19 top news headlines and safety Tips.

## Architecture

<img alt="FlowChart" src="https://github.com/ja841014/COVID-19_Global-Tracker/blob/master/src/main/resources/static/Image/FLowChart.svg" width="400">

## Features

* Fetch the latest information of the COVID-19 situation in various countries from JHU CSSE repository and update the data automatically every day. 
* Display the accumulated confirmed cases in each country on the cluster map by parsing self-built GeoJson format into the MapBox.
* Search the specific country which is on our dashboard
* Display top news which is related to COVID-19


## Built with

### Frontend
• <a href="https://getbootstrap.com/docs/4.0/getting-started/introduction/">Bootstrap</a>

• <a href="https://www.mapbox.com/">Mapbox</a> 

• <a href="https://newsapi.org/">NewsAPI</a> 

• <a href="https://www.thymeleaf.org/">Thymeleaf template engine</a> 

### Backend
• <a href="https://spring.io/projects/spring-boot">Spring Boot</a> 




## Demo Picture

<p>This is the home page</p>
<img alt="ShowPage" src="https://github.com/ja841014/COVID-19_Global-Tracker/blob/master/src/main/resources/static/Image/ShowPage.png" width="500">

<p>Under the cluster map, it will show the list of the country and its information</p>
<img alt="ListOfCountry" src="https://github.com/ja841014/COVID-19_Global-Tracker/blob/master/src/main/resources/static/Image/ListOfCountry.png" width="500">

<p>This is the top news page. We can click any news that we are interest. we will be direct to the news page</p>
<img alt="newspage" src="https://github.com/ja841014/COVID-19_Global-Tracker/blob/master/src/main/resources/static/Image/newspage.png" width="500">



## Reference and things I learn in this project:

<a href="https://github.com/CSSEGISandData/COVID-19/tree/master/csse_covid_19_data/csse_covid_19_time_series">Coivd-19 data</a>

<a href="https://docs.mapbox.com/mapbox-gl-js/example/updating-choropleth/">MapBox side information</a>

<a href="https://blog.mapbox.com/clustering-properties-with-mapbox-and-html-markers-bb353c8662ba
">clustering-properties-with-mapbox-and-html-markers</a>

<a href = "https://docs.mapbox.com/mapbox-gl-js/example/cluster-html/"> MapBox cluster map example</a>

<a href = "https://commons.apache.org/proper/commons-csv/user-guide.html"> Java CSV file operation</a>

<a href = "https://www.youtube.com/watch?v=JAADtLFJJgs&t=962s&ab_channel=KindsonTheTechPro"> Java Spring form submit with Thymeleaf </a>

<a href = "https://ithelp.ithome.com.tw/articles/10218607"> innerHTML method</a>

<a href = "https://stackoverflow.com/questions/29460618/inserting-an-image-from-local-directory-in-thymeleaf-spring-framework-with-mave">Insert image in thymeleaf</a>

<p>Using Java fetch json data from URL and display information on website</p>
