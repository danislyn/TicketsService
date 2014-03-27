package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="train_order")
public class TrainOrder extends Model {

	@ManyToOne
	@JoinColumn(name="train_id", referencedColumnName="id")
	public TrainSchedule train;
	
	@Column(name="leave_date")
	public String leaveDate;
	
	@ManyToOne
	@JoinColumn(name="passenger_id", referencedColumnName="id")
	public Role passenger;
	
	@Column(name="total_price")
	public Integer totalPrice;
	
	@Column(name="create_time")
	public Date createTime;
	
	@Column(name="create_account")
	public Integer createAccountId;
	
	@Column(name="status")
	public Integer status;  //0：正常；1：已改签；-1：已退票
	
}
