package io.api.Entity;

import java.util.Map;
import java.util.UUID;

import javax.management.Query;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@Entity
@NamedQueries({
	@NamedQuery(name="Weather.findById", query="SELECT w from Weather w where w.id=:wId"),
	@NamedQuery(name="Weather.findAll", query="SELECT w from Weather w"),
	@NamedQuery(name="Weather.getCities", query="SELECT DISTINCT w.city from Weather w"),
	@NamedQuery(name="Weather.weatherForCity", query="SELECT w from Weather w where w.city=:wCity ORDER BY w.timestamp DESC"),
	@NamedQuery(name="Weather.avgHourlyWeather", query="SELECT new io.api.Entity.Weather(w.city, round(avg(w.temperature),2), round(avg(w.windSpeed),2),"
														+ "round(avg(w.windDegree),2),round(avg(w.pressure),2)," + 
			                                            "round(avg(w.humidity),2))" + 
			                                            " FROM Weather w where w.city=:wCity group by HOUR(SUBSTRING(w.timestamp,12,12))"),
	@NamedQuery(name="Weather.avgDailyWeather", query="SELECT new io.api.Entity.Weather(w.city, round(avg(w.temperature),2), round(avg(w.windSpeed),2),"
														+ "round(avg(w.windDegree),2),round(avg(w.pressure),2)," + 
															"round(avg(w.humidity),2))" + 
															" FROM Weather w where w.city=:wCity group by DAY(SUBSTRING(w.timestamp,12,12))")
})
public class Weather {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String city;
	private String description;
	private double humidity;
	private double temperature;
	private double pressure;
	private String timestamp;
	
	@JsonProperty("speed")
	private double windSpeed;
	
	@JsonProperty("degree")
	private double windDegree;
	
	public Weather() {
		super();
	}
	
	public Weather(String city,double temperature, double windSpeed,double windDegree, double pressure, double humidity) {
		this.city = city;
		this.temperature = temperature;
		this.windSpeed = windSpeed;
		this.windDegree = windDegree;
		this.pressure = pressure;
		this.humidity = humidity;
	}
	public Weather(@JsonProperty("wind") Wind wind) {
		
		this.windSpeed = (double)wind.getSpeed();
		this.windDegree = (double)wind.getDegree();
	}


	public double getHumidity() {
		return humidity;
	}

	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}

class Wind{
	
	@JsonProperty("speed")
	private double speed;
	@JsonProperty("degree")
	private double degree;
	
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getDegree() {
		return degree;
	}
	public void setDegree(double degree) {
		this.degree = degree;
	}
	
	
}
