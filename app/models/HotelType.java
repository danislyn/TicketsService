package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="hotel_type")
public class HotelType extends Model {

	@ManyToOne
	@JoinColumn(name="hotel_id", referencedColumnName="id")
	public Hotel hotel;
	
	@Column(name="name")
	public String name;
	
	@Column(name="num")
	public Integer num;  //总房间数
	
	@Column(name="price")
	public Integer price;
	
	public Integer available;  //剩余房间数
	
}
