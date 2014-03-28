package dao;

import java.util.List;

import models.City;
import models.Hotel;
import models.HotelType;

public class HotelDAO {

	public static List<Hotel> findAll(){
		return Hotel.findAll();
	}
	
	//不带剩余房间数
	public static List<Hotel> query(Long cityId){
		City city = City.findById(cityId);
		return Hotel.find("byCity", city).fetch();
	}
	
	//加入date查询剩余房间数
	public static List<Hotel> query(String date, Long cityId){
		City city = City.findById(cityId);
		List<Hotel> raw = Hotel.find("byCity", city).fetch();
		return countAvailable(raw, date);
	}
	
	private static List<Hotel> countAvailable(List<Hotel> hotels, String date){
		for(Hotel hotel : hotels){
			for(HotelType type : hotel.hotelTypes){
				int orders = HotelOrderDAO.getOrderCountByHotelAndDate(type.id, date);
				type.available = type.num - orders;
			}
		}
		return hotels;
	}
	
}
