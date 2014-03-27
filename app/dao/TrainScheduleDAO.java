package dao;

import java.util.List;

import models.City;
import models.TrainSchedule;

public class TrainScheduleDAO {

	public static List<TrainSchedule> findAll(){
		return TrainSchedule.findAll();
	}
	
	//不带余票数
	public static List<TrainSchedule> query(Long startCity, Long endCity){
		City city1 = City.findById(startCity);
		City city2 = City.findById(endCity);
		return TrainSchedule.find("byStartCityAndEndCity", city1, city2).fetch();
	}
	
	//加入date查询余票数
	public static List<TrainSchedule> query(String date, Long startCity, Long endCity){
		City city1 = City.findById(startCity);
		City city2 = City.findById(endCity);
		
		List<TrainSchedule> raw = TrainSchedule.find("byStartCityAndEndCity", city1, city2).fetch();
		return countAvailable(raw, date);
	}
	
	private static List<TrainSchedule> countAvailable(List<TrainSchedule> trains, String date){
		for(TrainSchedule train : trains){
			int orders = TrainOrderDAO.getOrderCountByTrainAndDate(train.id, date);
			train.available = train.capacity - orders;
		}
		return trains;
	}
	
}
