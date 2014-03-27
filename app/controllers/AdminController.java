package controllers;

import java.util.List;

import dao.CityDAO;
import dao.TrainScheduleDAO;

import models.City;
import models.TrainSchedule;

import play.mvc.Controller;

public class AdminController extends Controller {

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
	
}
