package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Hotel;
import models.HotelOrder;
import models.Role;
import models.TrainOrder;
import models.TrainSchedule;

import dao.CityDAO;
import dao.HotelDAO;
import dao.HotelOrderDAO;
import dao.RoleDAO;
import dao.TrainOrderDAO;
import dao.TrainScheduleDAO;
import play.mvc.Controller;

public class BasicController extends Controller {

	//=================================================================
	//查票
	public static void trainQuery(){
		//获取查询条件
		String pDate = params.get("date");
		String pStartCity = params.get("startCity");
		String pEndCity = params.get("endCity");
		
		List<TrainSchedule> trains = new ArrayList<TrainSchedule>();
		
		if(pDate != null && pStartCity != null && pEndCity != null){
			trains = TrainScheduleDAO.query(pDate, Long.valueOf(pStartCity), Long.valueOf(pEndCity));
		}
		
		renderArgs.put("trains", trains);
		renderArgs.put("cities", CityDAO.findAll());
		renderArgs.put("pDate", pDate);
		renderArgs.put("pStartCity", pStartCity == null ? pStartCity : Integer.parseInt(pStartCity));
		renderArgs.put("pEndCity", pEndCity == null ? pEndCity : Integer.parseInt(pEndCity));
		
		render("Basic/trains.html");
	}
	
	
	//买票
	public static void trainBuy(){
		String pDate = params.get("date");
		String pTrainId = params.get("trainId");
		
		boolean result = false;

		if(pDate != null && pTrainId != null){
			Long accountId = Long.valueOf(session.get("account_id"));
			List<Role> roles = RoleDAO.getRolesByAccountId(accountId);
			
			if(roles.size() > 0){
				result = TrainOrderDAO.makeOrder(Long.valueOf(pTrainId), pDate, roles.get(0).id, accountId);
			}
		}
		
		response.contentType = "text/html;charset=UTF-8";
    	if(result){
    		renderJSON("{success:true}");
    	}
    	else{
    		renderJSON("{success:false}");
    	}
	}
	
	
	//改签
	public static void trainChangeOrder(){
		String pOrderId = params.get("orderId");
		String pTrainId = params.get("newTrainId");
		String pDate = params.get("newDate");
		
		boolean result = TrainOrderDAO.changeOrderTrain(Long.valueOf(pOrderId), Long.valueOf(pTrainId), pDate);
		
		response.contentType = "text/html;charset=UTF-8";
    	if(result){
    		renderJSON("{success:true}");
    	}
    	else{
    		renderJSON("{success:false}");
    	}
	}
	
	
	//退票
	public static void trainAbortOrder(){
		String pOrderId = params.get("orderId");
		boolean result = TrainOrderDAO.abortOrder(Long.valueOf(pOrderId));
		
		response.contentType = "text/html;charset=UTF-8";
    	if(result){
    		renderJSON("{success:true}");
    	}
    	else{
    		renderJSON("{success:false}");
    	}
	}
	
	
	//改签时查可选车次
	public static void getChangeTrainList(){
		String pStartCity = params.get("startCityId");
		String pEndCity = params.get("endCityId");
		
		List<TrainSchedule> trains = TrainScheduleDAO.query(Long.valueOf(pStartCity), Long.valueOf(pEndCity));
		
		response.contentType = "text/html;charset=UTF-8";
		renderJSON(trains);
	}
	
	
	//=================================================================
	//查酒店
	public static void hotelQuery(){
		//获取查询条件
		String pDate = params.get("date");
		String pCity = params.get("city");
		
		List<Hotel> hotels = new ArrayList<Hotel>();
		
		if(pDate != null && pCity != null){
			hotels = HotelDAO.query(pDate, Long.valueOf(pCity));
		}
		
		renderArgs.put("hotels", hotels);
		renderArgs.put("cities", CityDAO.findAll());
		renderArgs.put("pDate", pDate);
		renderArgs.put("pCity", pCity == null ? pCity : Integer.parseInt(pCity));
		
		render("Basic/hotels.html");
	}
	
	
	//订酒店
	public static void hotelBuy(){
		String pDate = params.get("date");
		String pTypeId = params.get("typeId");
		
		boolean result = false;

		if(pDate != null && pTypeId != null){
			Long accountId = Long.valueOf(session.get("account_id"));
			result = HotelOrderDAO.makeOrder(Long.valueOf(pTypeId), pDate, accountId);
		}
		
		response.contentType = "text/html;charset=UTF-8";
    	if(result){
    		renderJSON("{success:true}");
    	}
    	else{
    		renderJSON("{success:false}");
    	}
	}
	
	
	//酒店退订
	public static void hotelAbortOrder(){
		String pOrderId = params.get("orderId");
		boolean result = HotelOrderDAO.abortOrder(Long.valueOf(pOrderId));
		
		response.contentType = "text/html;charset=UTF-8";
    	if(result){
    		renderJSON("{success:true}");
    	}
    	else{
    		renderJSON("{success:false}");
    	}
	}
	
	
	//=================================================================
	//我的订单，车票订单、酒店订单、景点订单
	public static void myOrders(){
		
		List<TrainOrder> trainOrders = TrainOrderDAO.findAll();
		List<HotelOrder> hotelOrders = HotelOrderDAO.findAll();
		
		renderArgs.put("trainOrders", trainOrders);
		renderArgs.put("hotelOrders", hotelOrders);
		
		render("Basic/orders.html");
	}
	
	

	
}
