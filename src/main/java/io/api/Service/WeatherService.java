package io.api.Service;

import java.util.List;

import io.api.Entity.Weather;

public interface WeatherService {
	
	public Weather create(Weather weather);
	public List<Weather> findAll();
	public List<String> getCities();
	public List<Weather> getWeatherByCity(String city);
	public double getWeatherForCityByProperty(String city, String prop);
	public List<Weather> findAvgHourlyWeatherforCity(String city);
	public List<Weather> findAvgDaillyWeatherforCity(String city);
}