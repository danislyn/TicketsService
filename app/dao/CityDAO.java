package dao;

import java.util.List;

import models.City;

public class CityDAO {

	public static List<City> findAll(){
		return City.findAll();
	}
	
}
