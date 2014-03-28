package controllers;

import java.util.List;

import dao.CityDAO;
import dao.HotelDAO;
import dao.SpotDAO;
import dao.TrainScheduleDAO;

import models.City;
import models.Hotel;
import models.HotelType;
import models.Spot;
import models.TrainSchedule;

import play.mvc.Controller;

public class AdminController extends Controller {

	//============================================================
	public static void trainQuery(){
		List<TrainSchedule> trains = TrainScheduleDAO.findAll();
		renderArgs.put("trains", trains);
		renderArgs.put("cities", CityDAO.findAll());
		render("Admin/trains.html");
	}
	
	
	//添加新车次
	public static void trainAdd(){
		String pTrainCode = params.get("trainCode");
		String pStartCity = params.get("startCity");
		String pEndCity = params.get("endCity");
		String pStartHH = params.get("startHH");
		String pStartMM = params.get("startMM");
		String pEndHH = params.get("endHH");
		String pEndMM = params.get("endMM");
		String pEndDay = params.get("endDay");
		String pCapacity = params.get("capacity");
		String pPrice = params.get("price");
		
		boolean result = false;
		
		try {
			TrainSchedule train = new TrainSchedule();
			train.trainCode = pTrainCode;
			train.startCity = City.findById(Long.valueOf(pStartCity));
			train.endCity = City.findById(Long.valueOf(pEndCity));
			train.startHH = Integer.parseInt(pStartHH);
			train.startMM = Integer.parseInt(pStartMM);
			train.endHH = Integer.parseInt(pEndHH);
			train.endMM = Integer.parseInt(pEndMM);
			train.endDay = Integer.parseInt(pEndDay);
			train.capacity = Integer.parseInt(pCapacity);
			train.price = Integer.parseInt(pPrice);
			
			train = train.save();
			if(train.id != null){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.contentType = "text/html;charset=UTF-8";
    	if(result){
    		renderJSON("{success:true}");
    	}
    	else{
    		renderJSON("{success:false}");
    	}
	}
	
	
	//============================================================
	public static void hotelQuery(){
		List<Hotel> hotels = HotelDAO.findAll();
		renderArgs.put("hotels", hotels);
		renderArgs.put("cities", CityDAO.findAll());
		render("Admin/hotels.html");
	}
	
	
	//添加酒店，不含房型
	public static void hotelAdd(){
		String pName = params.get("name");
		String pCity = params.get("city");
		String pInfo = params.get("info");
		
		boolean result = false;
		
		try {
			Hotel hotel = new Hotel();
			hotel.name = pName;
			hotel.city = City.findById(Long.valueOf(pCity));
			hotel.info = pInfo;
			
			hotel = hotel.save();
			if(hotel.id != null){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.contentType = "text/html;charset=UTF-8";
    	if(result){
    		renderJSON("{success:true}");
    	}
    	else{
    		renderJSON("{success:false}");
    	}
	}
	
	
	//添加房型
	public static void hotelTypeAdd(){
		String pHotelId = params.get("hotelId");
		String pName = params.get("name");
		String pNum = params.get("num");
		String pPrice = params.get("price");
		
		boolean result = false;
		
		try {
			HotelType hotelType = new HotelType();
			hotelType.hotel = Hotel.findById(Long.valueOf(pHotelId));
			hotelType.name = pName;
			hotelType.num = Integer.parseInt(pNum);
			hotelType.price = Integer.parseInt(pPrice);
			
			hotelType = hotelType.save();
			if(hotelType.id != null){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.contentType = "text/html;charset=UTF-8";
    	if(result){
    		renderJSON("{success:true}");
    	}
    	else{
    		renderJSON("{success:false}");
    	}
	}
	
	
	//============================================================
	public static void spotQuery(){
		List<Spot> spots = SpotDAO.findAll();
		renderArgs.put("spots", spots);
		renderArgs.put("cities", CityDAO.findAll());
		render("Admin/spots.html");
	}
	
	
	//添加景点
	public static void spotAdd(){
		String pName = params.get("name");
		String pCity = params.get("city");
		String pInfo = params.get("info");
		String pPrice = params.get("price");
		
		boolean result = false;
		
		try {
			Spot spot = new Spot();
			spot.name = pName;
			spot.city = City.findById(Long.valueOf(pCity));
			spot.info = pInfo;
			spot.price = Integer.parseInt(pPrice);
			
			spot = spot.save();
			if(spot.id != null){
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.contentType = "text/html;charset=UTF-8";
    	if(result){
    		renderJSON("{success:true}");
    	}
    	else{
    		renderJSON("{success:false}");
    	}
	}
	
}
