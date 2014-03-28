package dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import play.db.jpa.Model;

import models.TrainOrder;
import models.TrainSchedule;

public class TrainOrderDAO {

	public static List<TrainOrder> findAll(){
		return TrainOrder.findAll();
	}
	
	public static List<TrainOrder> findByAccount(Long accountId){
		return TrainOrder.find("byCreateAccountId", accountId.intValue()).fetch();
	}
	
	public static int getOrderCountByTrainAndDate(Long trainId, String leaveDate){
		String sql = "select count(*) from train_order where train_id=" + trainId + " and leave_date='" + leaveDate + "' and status<>-1";
		Query query = Model.em().createNativeQuery(sql);
		return ((BigInteger) query.getSingleResult()).intValue();
	}
	
	public static boolean makeOrder(Long trainId, String leaveDate, Long passengerId, Long accountId){
		//查询余票是否available
		TrainSchedule train = TrainSchedule.findById(trainId);
		int orders = getOrderCountByTrainAndDate(trainId, leaveDate);
		
		if(train.capacity - orders > 0){
			Date current = new Date();
			Timestamp time = new Timestamp(current.getTime());
			
			String sql = "insert into train_order (train_id, leave_date, passenger_id, total_price, create_time, create_account) " +
					"values(" + trainId + ", '" + leaveDate + "', " + passengerId + ", " + train.price + ", '" + time + "', " + accountId + ")";
			
			Query query = Model.em().createNativeQuery(sql);
			return query.executeUpdate() == 1;
		}
		return false;
	}
	
	public static boolean changeOrderTrain(Long orderId, Long trainId, String leaveDate){
		//查询余票是否available
		TrainSchedule train = TrainSchedule.findById(trainId);
		int orders = getOrderCountByTrainAndDate(trainId, leaveDate);
		
		if(train.capacity - orders > 0){
			String sql = "update train_order set train_id=" + trainId + ", leave_date='" + leaveDate + "', total_price=" + train.price + ", status=1 where id=" + orderId;
			Query query = Model.em().createNativeQuery(sql);
			return query.executeUpdate() == 1;
		}
		return false;
	}
	
	public static boolean abortOrder(Long orderId){
		String sql = "update train_order set status=-1 where id=" + orderId;
		Query query = Model.em().createNativeQuery(sql);
		return query.executeUpdate() == 1;
	}
	
}
