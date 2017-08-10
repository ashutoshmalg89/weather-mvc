package io.api.RepositoryImpl;

import java.util.ArrayList;
import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import io.api.Entity.Weather;
import io.api.Repository.WeatherRepository;

@Repository
public class WeatherRepositoryImpl implements WeatherRepository{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Weather create(Weather weather) {
		// TODO Auto-generated method stub
		em.persist(weather);
		return weather;
	}

	@Override
	public Weather findById(Weather weather) {
		
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findById", Weather.class);
		query.setParameter("wId", weather.getId());
		List<Weather> result = query.getResultList();
		
		if(!result.isEmpty()) {
			return result.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Weather> findAll() {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.findAll", Weather.class);
		return query.getResultList();
	}

	@Override
	public List<String> getCities() {
		
		TypedQuery<String> query = em.createNamedQuery("Weather.getCities", String.class);
		List<String> result = query.getResultList();
		return result;
	}

	@Override
	public List<Weather> getWeatherByCity(String city) {
		
		TypedQuery<Weather> query = em.createNamedQuery("Weather.weatherForCity", Weather.class);
		query.setParameter("wCity", city);
		query.setMaxResults(1);
		return query.getResultList();
	}

	@Override
	public double getWeatherForCityByProperty(String city, String prop) {
		//TypedQuery<String> query = em.createNamedQuery("Weather.weatherForCityByProp", String.class);
		TypedQuery<Double> query = em.createQuery("SELECT w."+prop+" from Weather w where w.city=:wCity ORDER BY w.timestamp DESC", Double.class);
		query.setParameter("wCity", city);
		query.setMaxResults(1);
		List<Double> result = query.getResultList();
		
		return result.get(0);
	}

	@Override
	public List<Weather> findAvgHourlyWeatherforCity(String city) {
		
		TypedQuery<Weather> query = em.createNamedQuery("Weather.avgHourlyWeather", Weather.class);
		query.setParameter("wCity", city);
		
		return query.getResultList();
	}

	@Override
	public List<Weather> findAvgDaillyWeatherforCity(String city) {
		TypedQuery<Weather> query = em.createNamedQuery("Weather.avgDailyWeather", Weather.class);
		query.setParameter("wCity", city);
		
		return query.getResultList();
	}
	
	
}
