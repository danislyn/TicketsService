package dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import play.db.jpa.Model;

import models.Spot;
import models.SpotOrder;

public class SpotOrderDAO {

	public static List<SpotOrder> findAll(){
		return SpotOrder.findAll();
	}
	
	public static List<SpotOrder> findByAccount(Long accountId){
		return SpotOrder.find("byCreateAccountId", accountId.intValue()).fetch();
	}
	
	public static boolean makeOrder(Long spotId, Integer num, Long accountId){
		Spot spot = Spot.findById(spotId);
		Date current = new Date();
		Timestamp time = new Timestamp(current.getTime());
		
		String sql = "insert into spot_order (spot_id, num, total_price, create_time, create_account) " +
				"values(" + spotId + ", " + num + ", " + (spot.price*num) + ", '" + time + "', " + accountId + ")";
		
		Query query = Model.em().createNativeQuery(sql);
		return query.executeUpdate() == 1;
	}
	
	public static boolean abortOrder(Long orderId){
		String sql = "update spot_order set status=-1 where id=" + orderId;
		Query query = Model.em().createNativeQuery(sql);
		return query.executeUpdate() == 1;
	}
	
}
