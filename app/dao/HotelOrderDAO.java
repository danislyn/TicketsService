package dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import play.db.jpa.Model;

import models.HotelOrder;
import models.HotelType;

public class HotelOrderDAO {

	public static List<HotelOrder> findAll(){
		return HotelOrder.findAll();
	}
	
	public static int getOrderCountByHotelAndDate(Long hotelTypeId, String checkinDate){
		String sql = "select count(*) from hotel_order where hotel_type_id=" + hotelTypeId + " and checkin_date='" + checkinDate + "' and status<>-1";
		Query query = Model.em().createNativeQuery(sql);
		return ((BigInteger) query.getSingleResult()).intValue();
	}
	
	public static boolean makeOrder(Long hotelTypeId, String checkinDate, Long accountId){
		//查询房间数是否available
		HotelType hotelType = HotelType.findById(hotelTypeId);
		int orders = getOrderCountByHotelAndDate(hotelTypeId, checkinDate);
		
		if(hotelType.num - orders > 0){
			Date current = new Date();
			Timestamp time = new Timestamp(current.getTime());
			
			String sql = "insert into hotel_order (hotel_type_id, checkin_date, total_price, create_time, create_account) " +
					"values(" + hotelTypeId + ", '" + checkinDate + "', " + hotelType.price + ", '" + time + "', " + accountId + ")";
			
			Query query = Model.em().createNativeQuery(sql);
			return query.executeUpdate() == 1;
		}
		return false;
	}
	
	public static boolean abortOrder(Long orderId){
		String sql = "update hotel_order set status=-1 where id=" + orderId;
		Query query = Model.em().createNativeQuery(sql);
		return query.executeUpdate() == 1;
	}
	
}
