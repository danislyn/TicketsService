package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="hotel_order")
public class HotelOrder extends Model {

	@ManyToOne
	@JoinColumn(name="hotel_type_id", referencedColumnName="id")
	public HotelType hotelType;
	
	@Column(name="checkin_date")
	public String checkinDate;
	
	@Column(name="total_price")
	public Integer totalPrice;
	
	@Column(name="create_time")
	public Date createTime;
	
	@Column(name="create_account")
	public Integer createAccountId;
	
	@Column(name="status")
	public Integer status;  //0：正常；-1：已取消
	
}
