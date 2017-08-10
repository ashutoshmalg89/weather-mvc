package io.api.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.api.Entity.Weather;
import io.api.Exception.BadRequestException;
import io.api.Exception.NotFoundException;
import io.api.Repository.WeatherRepository;
import io.api.Service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService{
	
	private WeatherRepository weatherRepo;
	
	public WeatherServiceImpl(WeatherRepository weatherRepo) {
		this.weatherRepo = weatherRepo;
	}


	@Override
	@Transactional
	public Weather create(Weather weather) {
		
		return weatherRepo.create(weather);
	}


	@Override
	@Transactional(readOnly=true)
	public List<Weather> findAll() {
		// TODO Auto-generated method stub
		return weatherRepo.findAll();
	}


	@Override
	public List<String> getCities() {
		List<String> result = weatherRepo.getCities();
		if(result==null) {
			throw new NotFoundException("No Record Exists");
		}
		return result;
	}


	@Override
	public List<Weather> getWeatherByCity(String city) {
		// TODO Auto-generated method stub
		List<Weather> result = weatherRepo.getWeatherByCity(city);
		if(result==null) {
			throw new BadRequestException("No Record Exist for City :"+city);
		}
		return result; 
	}


	@Override
	public double getWeatherForCityByProperty(String city, String prop) {
		double result = weatherRepo.getWeatherForCityByProperty(city, prop);
		if(result==0) {
			throw new BadRequestException("No record for "+prop+" exist for city :"+city);
		}
		return result;
	}


	@Override
	public List<Weather> findAvgHourlyWeatherforCity(String city) {
		return weatherRepo.findAvgHourlyWeatherforCity(city);
	}


	@Override
	public List<Weather> findAvgDaillyWeatherforCity(String city) {
		// TODO Auto-generated method stub
		return weatherRepo.findAvgDaillyWeatherforCity(city);
	}

}
