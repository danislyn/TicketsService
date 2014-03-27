package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import play.db.jpa.Model;

@Entity
@Table(name="train_schedule")
public class TrainSchedule extends Model {
	
	@Column(name="train_code")
	public String trainCode;

	@ManyToOne
	@JoinColumn(name="start_city", referencedColumnName="id")
	public City startCity;
	
	@ManyToOne
	@JoinColumn(name="end_city", referencedColumnName="id")
	public City endCity;
	
	@Column(name="start_hh")
	public Integer startHH;
	
	@Column(name="start_mm")
	public Integer startMM;
	
	@Column(name="end_hh")
	public Integer endHH;
	
	@Column(name="end_mm")
	public Integer endMM;
	
	@Column(name="end_day")
	public Integer endDay;  //0：当天；1：次日
	
	@Column(name="capacity")
	public Integer capacity;  //载客容量
	
	@Column(name="price")
	public Integer price;
	
	public Integer available;  //余票数
	
}
