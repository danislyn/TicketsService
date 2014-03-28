package dao;

import java.util.List;

import models.City;
import models.Spot;

public class SpotDAO {

	public static List<Spot> findAll(){
		return Spot.findAll();
	}
	
	public static List<Spot> query(Long cityId){
		City city = City.findById(cityId);
		return Spot.find("byCity", city).fetch();
	}
	
}
