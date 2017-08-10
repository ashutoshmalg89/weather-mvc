package io.api.Controller;

import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.TypedQuery;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.api.Entity.Weather;
import io.api.Service.WeatherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@ResponseBody
@RequestMapping(value="weather")
@CrossOrigin(origins="http://mocker.egen.io")

@Api(tags="Weather")
public class WeatherController {
	
	private WeatherService weatherService;
	
	public WeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}
	
	@ApiOperation(value="Populate Database", notes="REST endpoint that accepts the weather reading from the mock weather sensor [http://mocker.egen.io] and stores into your app datastore")
	@RequestMapping(method=RequestMethod.POST)
	public Weather create(@RequestBody Weather weather) {
		return weatherService.create(weather);
	}
	
	@ApiOperation(value="Get all records", notes="Displays all records from database")
	@RequestMapping(method=RequestMethod.GET)
	public List<Weather> findAll() {
		
		return weatherService.findAll();
	}
	
	
	@ApiOperation(value="GET all cities", notes="Get the list of cities that have ever reported their weather in the past")
	@RequestMapping(method=RequestMethod.GET, value="cities")
	public List<String> getCities(){
		
		return weatherService.getCities();
	}
	
	@ApiOperation(value="GET Weather for given City", notes="Get the latest weather for a given city")
	@RequestMapping(method=RequestMethod.GET, value="{wCity}")
	public List<Weather> findWeatherForCity(@PathVariable("wCity") String city){
		
		return weatherService.getWeatherByCity(city);
	}
	
	@ApiOperation(value="GET Weather property for City", notes="Get the latest weather property for a given city")
	@RequestMapping(method=RequestMethod.GET, value="{wCity}/{wProperty}")
	public double findWeatherForCityByProperty(@PathVariable("wCity") String city, @PathVariable("wProperty") String prop) {

		return weatherService.getWeatherForCityByProperty(city, prop);
	}
	
	@ApiOperation(value="GET Hourly Avgerage Weather", notes="Get hourly averaged weather for a given city")
	@RequestMapping(method=RequestMethod.GET, value="hourly/{wCity}")
	public List<Weather> findAvgHourlyWeatherforCity(@PathVariable("wCity") String city) {
		
		return weatherService.findAvgHourlyWeatherforCity(city);
	}
	
	@ApiOperation(value="GET daily Average Weather", notes="Get hourly averaged weather for a given city")
	@RequestMapping(method=RequestMethod.GET, value="daily/{wCity}")
	public List<Weather> findAvgDaillyWeatherforCity(@PathVariable("wCity") String city) {
		
		return weatherService.findAvgDaillyWeatherforCity(city);
	}
}
